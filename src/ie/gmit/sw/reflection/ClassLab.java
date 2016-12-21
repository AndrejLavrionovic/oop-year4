package ie.gmit.sw.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * 
 * @author g00196984
 *
 * ClassLab is used to work with classes
 * that are retrieved from Jar pachage
 */

public class ClassLab {
	
	// fields
	private JarContent jarClasses;

	// get all classes from jar
	public JarContent getAllClasses(String jarName){
		
		jarClasses = new JarContent();
		
		try{
			JarInputStream in = new JarInputStream(new FileInputStream(new File(jarName))); // get stream
			JarEntry next = in.getNextJarEntry(); // get jar instance
			
			while(next != null){ // run through
				
				if(next.getName().endsWith(".class")){ // search for classes
					
					// get class name
					String name = next.getName().replaceAll("/", "\\.");
					name = name.replaceAll(".class", "");
					if (!name.contains("$")) name.substring(0, name.length() - ".class".length());

					// inspect class
					Class queryClass;
					try {
						queryClass = Class.forName(name);
						jarClasses.addClass(queryClass);
					} catch (ClassNotFoundException e) {
						System.out.println("Couldn't find class '" + name + "'"); // if class is not found
						System.exit(1);
					} // reflection
				}
				next = in.getNextJarEntry();
			}
					
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		return jarClasses;
	}
	
	// search only concrete classes
	public JarContent getConcretes(JarContent jarClasses){
		
		JarContent concretes = new JarContent();
		for(int i = 0; i < jarClasses.numberOfClasses(); i++){
			Class c = jarClasses.getClass(i);
			
			// filter abstract and interfaces
			if(!(c.isInterface() || Modifier.isAbstract(c.getModifiers()))){
				concretes.addClass(c);
			}
		}
		
		return concretes;
	}
	
	// get only interfaces
	public JarContent getInterfaces(JarContent jarClasses){
		
		JarContent ifaces = new JarContent();
		for(int i = 0; i < jarClasses.numberOfClasses(); i++){
			Class c = jarClasses.getClass(i);
			
			// filter abstract and interfaces
			if(c.isInterface()){
				ifaces.addClass(c);
			}
		}
		
		return ifaces;
	}
	
	// get only abstract classes excluding interfaces
	public JarContent getAbstractClasses(JarContent jarClasses){
		
		JarContent abs = new JarContent();
		for(int i = 0; i < jarClasses.numberOfClasses(); i++){
			Class c = jarClasses.getClass(i);
			
			// filter abstract and interfaces
			if(Modifier.isAbstract(c.getModifiers()) && !c.isInterface()){
				abs.addClass(c);
			}
		}
		
		return abs;
	}
	
	// get all abstracts includeing interfaces
	public JarContent getAbstracts(JarContent jarClasses){
		
		JarContent abs = new JarContent();
		for(int i = 0; i < jarClasses.numberOfClasses(); i++){
			Class c = jarClasses.getClass(i);
			
			// filter abstract and interfaces
			if(Modifier.isAbstract(c.getModifiers())){
				abs.addClass(c);
			}
		}
		
		return abs;
	}
}
