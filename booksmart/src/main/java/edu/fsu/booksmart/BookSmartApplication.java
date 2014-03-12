package edu.fsu.booksmart;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class BookSmartApplication extends Application {
	
	public BookSmartApplication() {
		//EMF.get();
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());
		Reflections reflections = new Reflections(new ConfigurationBuilder()
	    	.setScanners(new SubTypesScanner(false), new ResourcesScanner())
	    	.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
	    	.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("edu.fsu.booksmart.entity.resource"))));
		return reflections.getSubTypesOf(Object.class);
	}

}
