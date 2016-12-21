package ie.gmit.sw;

import ie.gmit.sw.reflection.ClassLab;
import ie.gmit.sw.reflection.JarContent;

public class Runner {

	public static void main(String[] args) {
		
		printAllClasses(args[0]);
	}

	public static void printAllClasses(String jarName){
		
		ClassLab c = new ClassLab();
		
		JarContent cls = c.getAllClasses(jarName);
		System.out.println("number of classes: " + cls.numberOfClasses());
		for(int i = 0; i < cls.numberOfClasses(); i++){
			Class cl = cls.getClass(i);
			
			System.out.println("==> " + cl.getName());
		}
	}
}
