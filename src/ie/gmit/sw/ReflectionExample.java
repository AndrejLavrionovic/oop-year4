package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ReflectionExample {

	// Instance of a Class type
	private Class c;
	List<Class> classes = new ArrayList<Class>();

	// main method for running reflection
	// expample:
	// java -cp ./bin ie.gmit.sw.ReflecionExample ie.gmit.sw.Person <Enter>
	public static void main(String args[]) {
		if (args.length == 0) { // if class is not specified as argument
			System.out.println("Please specify a class name.");
			System.exit(1);
		}
		
		// read jar;
		// jar passed as argument: java -cp ./bin ie.gmit.sw.ReflectionExample ../people.jar
		getJar(args[0]);
	}

	// Constructor
	public ReflectionExample(Class c) {
		super();
		this.c = c;

		getClassPackage();
		printIsInterface();
		printInterfaces();
		printConstructors();
		printFields();
		printMethods();
		createArray();
	}
	
	// get class package
	public void getClassPackage(){
		Package pack = c.getPackage(); //Get the package
		String packName = pack.getName();
		
		System.out.println("Package ==> " + packName);
	}
	
	// Is interface
	public void printIsInterface(){
		
		String isInterface = c.isInterface() ? "YES" : "NO";
		
		System.out.println("Is Interface: " + isInterface);
	}
	
	// Print implemented interfaces
	public void printInterfaces(){
		Class interfaces[] = c.getInterfaces(); // get all imlemented interfaces
		System.out.println("--------------" + interfaces.length + " Implemented Interfaces --------------");
		if(interfaces.length > 0){
			for(Class iface : interfaces){
				System.out.println("\timplements " + iface.getName());
			}
		}
		else{
			System.out.println("\n\t" + c.getName() + " has no any implemented interfaces.");
		}
	}

	// Print all constructors
	public void printConstructors() {
		Constructor ctorlist[] = c.getDeclaredConstructors();
		System.out.println("--------------" + ctorlist.length + " Constructors --------------");
		for (int i = 0; i < ctorlist.length; i++) {
			Constructor ct = ctorlist[i];
			System.out.println("\tname  = " + ct.getName());
			System.out.println("\tdecl class = " + ct.getDeclaringClass());

			Class pvec[] = ct.getParameterTypes();
			for (int j = 0; j < pvec.length; j++) {
				System.out.println("\tparam #" + j + " " + pvec[j]);
			}

			Class evec[] = ct.getExceptionTypes();
			for (int j = 0; j < evec.length; j++) {
				System.out.println("\texc #" + j + " " + evec[j]);
			}
			System.out.println("\t-----");
		}
	}

	// print all fields
	public void printFields() {
		Field fieldlist[] = c.getDeclaredFields();
		System.out.println("--------------" + fieldlist.length + " Fields --------------");
		for (int i = 0; i < fieldlist.length; i++) {
			Field fld = fieldlist[i];
			System.out.println("\tname = " + fld.getName());
			System.out.println("\tdecl class = " + fld.getDeclaringClass());
			System.out.println("\ttype = " + fld.getType());
			int mod = fld.getModifiers();
			System.out.println("\tmodifiers = " + Modifier.toString(mod));
			System.out.println("-----");
		}
	}

	// print all methods
	public void printMethods() {
		Method methlist[] = c.getDeclaredMethods();
		System.out.println("--------------" + methlist.length + " Methods --------------");
		for (int i = 0; i < methlist.length; i++) {
			Method m = methlist[i];
			System.out.println("\tname = " + m.getName());
			System.out.println("\tdecl class = " + m.getDeclaringClass());
			Class pvec[] = m.getParameterTypes();
			for (int j = 0; j < pvec.length; j++) {
				System.out.println("\tparam #" + j + " " + pvec[j]);
			}
			Class evec[] = m.getExceptionTypes();
			for (int j = 0; j < evec.length; j++) {
				System.out.println("\texc #" + j + " " + evec[j]);
			}
			System.out.println("\treturn type = " + m.getReturnType());
			System.out.println("\t-----");
		}
	}

	public void createArray() {
		try {
			Class cls = Class.forName("java.lang.String");
			Object arr = Array.newInstance(cls, 10);
			Array.set(arr, 5, "Msc OO");
			String s = (String) Array.get(arr, 5);
			System.out.println(s);
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
	
	// inspect jar library
	public static List<Class> getJar(String jar){
		
		List<Class> cls = new ArrayList<Class>();
		
		try {
			
			
			JarInputStream in = new JarInputStream(new FileInputStream(new File(jar))); // get stream
			JarEntry next = in.getNextJarEntry(); // get jar instance
			
			
			while(next != null){ // run through
				if(next.getName().endsWith(".class")){ // search for classes
					
					// get class name
					String name = next.getName().replaceAll("/", "\\.");
					name = name.replaceAll(".class", "");
					if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
					System.out.println("\n\n" + name); // print class name
					
					// inspect class
					Class queryClass;
					try {
						queryClass = Class.forName(name);
						cls.add(queryClass);
						new ReflectionExample(queryClass);
					} catch (ClassNotFoundException e) {
						System.out.println("Couldn't find class '" + name + "'"); // if class is not found
						System.exit(1);
					} // reflection
					
				}
				next = in.getNextJarEntry();
			}
			return cls;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * Calculation of abstraction.
	 * This metric calculates how the package is abstract.
	 * If A = 0, then package is completely abstract and  not changeable.
	 * A = Na / Nc
	 * Na - number of interfaces and abstract classes
	 * Nc - number of concrete classes
	 * Should be compared to instability to be sure that package are not complete concrete
	 * and not a highly unstable.
	 */
	
	public double calculateAbstraction(){
		
		// We need to know Na and Nc
		int Na = 0;
		int Nc = 0;
		return 0;
	}
	
	// Na calculation
	public int numberOfAbstractClasses(){
		
		Class interfaces[] = c.getInterfaces();
		//Class abstractClasses[]
		return 0;
	}
	
	// get list of classes
	public List<Class> getAllClasses(){
		return null;
	}
}
