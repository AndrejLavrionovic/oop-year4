package ie.gmit.sw.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ClassLab {
	
	private JarContent jarClasses;

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
}
