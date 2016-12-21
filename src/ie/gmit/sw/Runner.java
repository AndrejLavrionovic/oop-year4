package ie.gmit.sw;

import ie.gmit.sw.reflection.ClassLab;
import ie.gmit.sw.reflection.JarContent;

public class Runner {
	

	// main method for running reflection
	// example:
	// java -cp ./bin:../people.jar ie.gmit.sw.Runner ../people.jar
	public static void main(String[] args) {
		
		// retrieve classes from jar
		JarContent cls = getClasses(args[0]);
		
		// prints
		printAllClasses(cls);
		printConcreteClasses(cls);
	}

	// print all classes that present in the jar
	public static void printAllClasses(JarContent cls){
		
		System.out.println("\n\n-----ALL CLASSES(" + cls.numberOfClasses() + ")---------");
		printClasses(cls);
	}
	
	// get all classes that jar contains
	private static JarContent getClasses(String jarName){
		ClassLab c = new ClassLab();
		JarContent cls = c.getAllClasses(jarName);
		
		return cls;
	}
	
	// print only concrete classes that jar contains
	public static void printConcreteClasses(JarContent cls){
		
		ClassLab c = new ClassLab();
		JarContent concretes = c.getAllConcreteClasses(cls);
		
		System.out.println("\n\n-----CONCRETE CLASSES(" + concretes.numberOfClasses() + ")---------");
		printClasses(concretes);
	}
	
	// print
	public static void printClasses(JarContent csl){
		
		for(int i = 0; i < csl.numberOfClasses(); i++){
			Class cl = csl.getClass(i);
			
			System.out.println("==> " + cl.getName());
		}
	}
}
