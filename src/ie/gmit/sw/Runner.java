package ie.gmit.sw;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import ie.gmit.sw.measure.AbstractionMeasure;
import ie.gmit.sw.measure.AfferentCoupling;
import ie.gmit.sw.measure.EfferentCoupling;
import ie.gmit.sw.measure.InstabilityMeasure;
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
		
		/*
		// prints
		printAllClasses(cls);
		printConcreteClasses(cls);
		printInterfaces(cls);
		printAbstracts(cls);
		*/
		
		// measure abstraction
		Measurable abstraction = new AbstractionMeasure(cls);
		abstraction.measure();
		System.out.println("\n\n-------ABSTRACTION DEGREE--------");
		System.out.println(String.format("==> Abstracion: (A = Na / NC) = %.1f", abstraction.getResult()));
		
		// Ca/Ce
		InstabilityMeasure im = new InstabilityMeasure(cls);
		im.createCouplings();
		
		List<EfferentCoupling> ceList = im.getCeList();
		List<AfferentCoupling> caList = im.getCaList();
		
		for(int i = 0; i < cls.numberOfClasses(); i++){
			double ce = 0;
			double ca = 0;
			Class c = cls.getClass(i);
			
			for(int j = 0; j < cls.numberOfClasses(); j++){
				if(c.equals(ceList.get(j).getCeClass()))
					ce = ceList.get(j).getResult();
				if(c.equals(caList.get(j).getCaClass()))
					ca = caList.get(j).getResult();
			}
			System.out.println("==> CLASS: " + cls.getClass(i).getSimpleName() + "Ce=" + ce + ", Ca=" + ca);
		}
		
		/*
		System.out.println("\n\n");
		printDetails(cls);
		*/
		
		
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
	
	public static void printDetails(JarContent cls){
		
		ClassLab c = new ClassLab();
		
		System.out.println("---------CLASSES (" + cls.numberOfClasses() + ")------------\n");
		
		
		
		// testing for class manipulating
		for(int i = 0; i < cls.numberOfClasses(); i++){
			Class cl = cls.getClass(i);
			
			System.out.println("--------CLASS => " + cl.getName() + "------------");
			
			Constructor constructors[] = cl.getConstructors();
			
			System.out.println("-----CONSTRUCTORS (" + constructors.length + ") ------");
			for(int j = 0; j < constructors.length; j++){
				
				Constructor item = constructors[j];
				Class constructorclasses[] = item.getParameterTypes();
				
				System.out.println("==> Constructor[" + j + "] -> parameters(" + constructorclasses.length + ")");
				// System.out.println("--> " + t.getTypeName());
				if(constructorclasses.length > 0){
					for(int k = 0; k < constructorclasses.length; k++){
						System.out.println("    Parameter type: " + constructorclasses[k].getSimpleName());
					}
				}
			}
			
			Method methods[] = cl.getDeclaredMethods();
			
			System.out.println("-----METHODS (" + methods.length + ") ------");
			
			for(int j = 0; j < methods.length; j++){
				Method m = methods[j];
				System.out.println("==> Method: " + m.getName());
				System.out.println("    Retrun: " + m.getReturnType().getName());
				System.out.println("    Parameters(" + m.getParameterCount() + ")");
				Parameter parameters[] = m.getParameters();
				for (int k = 0; k < m.getParameterCount(); k++){
					System.out.println("    [" + k + "] -> " + parameters[k].getType().getSimpleName());
				}
			}
			
			System.out.println();
			printFields(cl);
			
			/*
			String fullname = cl.getName();
			String name = cl.getSimpleName();
			String canname = cl.getCanonicalName();
			
			String supercls = null;
			if(!cl.isInterface()){
				supercls = cl.getAnnotatedSuperclass().getType().getTypeName();
			}
			
			
			
			System.out.printf("\n==>  %-15s    |", name);
			
			if(ifaces.length > 0){
				for(int j = 0; j < ifaces.length; j++){
					Type t = ifaces[j].getType();
					String tname = t.getTypeName();
					System.out.printf("%-15s", tname);
				}
				
				ifaces = null;
			}
			else{
				System.out.printf("%-15s", "");
			}
			if(supercls != null){
				System.out.printf("%50s %s", "superclass -> ", supercls);
			}
			*/

			System.out.println("\n\n");
		}
	}
	
	// print
	public static void printClasses(JarContent csl){
		
		for(int i = 0; i < csl.numberOfClasses(); i++){
			Class cl = csl.getClass(i);
			
			System.out.println("==> " + cl.getName());
		}
	}
	
	public static void printFields(Class c){
		Field f[] = c.getFields();
		System.out.println("==> FIELDS(" + f.length + ")-----------------");
		for(int k = 0; k < f.length; k++){
			System.out.println("    [" + k + "] -> " + f[k].getType().getSimpleName());
		}
		
	}
	
	public static void printSuperclass(Class c){
		if(!c.isInterface()){
			System.out.println("-------CLASS -> " + c.getSimpleName() + " extends " + c.getSuperclass().getSimpleName());
		}
		else{
			System.out.println("-------CLASS -> " + c.getSimpleName() + " is interface.");
		}
	}
}
