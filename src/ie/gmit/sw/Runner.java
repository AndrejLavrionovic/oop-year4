package ie.gmit.sw;

import ie.gmit.sw.measure.AbstractionMeasure;
import ie.gmit.sw.measure.Measurable;
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
		printInterfaces(cls);
		printAbstracts(cls);
		
		// measure abstraction
		Measurable abstraction = new AbstractionMeasure(cls);
		abstraction.measure(cls);
		System.out.println("\n\n-------ABSTRACTION DEGREE--------");
		System.out.println(String.format("==> Abstracion: (A = Na / NC) = %.1f", abstraction.getResult()));
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
		JarContent concretes = c.getConcretes(cls);
		
		System.out.println("\n\n-----CONCRETE CLASSES(" + concretes.numberOfClasses() + ")---------");
		printClasses(concretes);
	}
	
	// print all interfaces
	public static void printInterfaces(JarContent cls){
		
		ClassLab c = new ClassLab();
		JarContent ifs = c.getInterfaces(cls);
		
		System.out.println("\n\n-----INTERFACES(" + ifs.numberOfClasses() + ")---------");
		printClasses(ifs);
	}
	
	// print Abstracts
	public static void printAbstracts(JarContent cls){
		
		ClassLab c = new ClassLab();
		JarContent abs = c.getAbstractClasses(cls);
		
		System.out.println("\n\n-----ABSTRACT CLASSES(" + abs.numberOfClasses() + ")---------");
		printClasses(abs);
	}
	
	// print
	public static void printClasses(JarContent csl){
		
		for(int i = 0; i < csl.numberOfClasses(); i++){
			Class cl = csl.getClass(i);
			
			System.out.println("==> " + cl.getName());
		}
	}
}
