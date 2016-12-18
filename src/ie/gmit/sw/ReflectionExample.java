package ie.gmit.sw;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionExample {
	
	// Instance of a Class type
	private Class c;
	
		// main method for running reflection
		// expample:
	 	// java -cp ./bin ie.gmit.sw.ReflecionExample ie.gmit.sw.Person <Enter>
	   public static void main(String args[]){
	        if (args.length == 0) { // if class is not specified as argument
	            System.out.println("Please specify a class name.");
	            System.exit(1);
	        }
	        try {
	            Class queryClass = Class.forName(args[0]); // reflection
	            new ReflectionExample(queryClass);
	        } catch (ClassNotFoundException ee) {
	            System.out.println("Couldn't find class '"+ args[0] + "'"); // if class is not found
	            System.exit(1);
	        }
	   }

	   // Constructor
	   public ReflectionExample(Class c){
	      super();
	      this.c = c;

	      printConstructors();
	      printFields();
	      printMethods();
	      createArray();
	   }

	   // Print all constructors
	   public void printConstructors(){
	      Constructor ctorlist[] = c.getDeclaredConstructors();
	      System.out.println("--------------" + ctorlist.length + " Constructors --------------");
	      for (int i = 0; i < ctorlist.length; i++) {
	         Constructor ct = ctorlist[i];
	         System.out.println("\tname  = " + ct.getName());
	         System.out.println("\tdecl class = " + ct.getDeclaringClass());

	         Class pvec[] = ct.getParameterTypes();
	         for (int j = 0; j < pvec.length; j++){
	            System.out.println("\tparam #" + j + " " + pvec[j]);
	         }

	         Class evec[] = ct.getExceptionTypes();
	         for (int j = 0; j < evec.length; j++){
	            System.out.println("\texc #" + j + " " + evec[j]);
	         }
	         System.out.println("\t-----");
	      }
	   }

	   // print all fields
	   public void printFields(){
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
	   public void printMethods(){
	      Method methlist[] = c.getDeclaredMethods();
	      System.out.println("--------------" + methlist.length + " Methods --------------");
	      for (int i = 0; i < methlist.length;i++) {
	      	Method m = methlist[i];
	      	System.out.println("\tname = " + m.getName());
	      	System.out.println("\tdecl class = " + m.getDeclaringClass());
	      	Class pvec[] = m.getParameterTypes();
	      	for (int j = 0; j < pvec.length; j++){
	         		System.out.println("\tparam #" + j + " " + pvec[j]);
	    	}
	      	Class evec[] = m.getExceptionTypes();
	      	for (int j = 0; j < evec.length; j++){
	         		System.out.println("\texc #" + j + " " + evec[j]);
	      	}
	      	System.out.println("\treturn type = " + m.getReturnType());
	      	System.out.println("\t-----");
	      }
	   }

	   public void createArray(){
	      try {
	         Class cls = Class.forName("java.lang.String");
	         Object arr = Array.newInstance(cls, 10);
	         Array.set(arr, 5, "Msc OO");
	         String s = (String)Array.get(arr, 5);
	         System.out.println(s);
	      }catch (Throwable e) {
	         System.err.println(e);
	      }
	   }
}
