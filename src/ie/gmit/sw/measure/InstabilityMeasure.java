package ie.gmit.sw.measure;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import ie.gmit.sw.reflection.ClassLab;
import ie.gmit.sw.reflection.JarContent;

public class InstabilityMeasure implements Measurable {
	
	// fields
	private int ce; // efferent coupling
	private int ca; // afferent coupling
	private double instability;
	

	// classlab instance => full composition
	private ClassLab cLab = new ClassLab();

	@Override
	public void measure(JarContent cls) {
		
	}

	@Override
	public double getResult() {
		
		return 0;
	}
	
	/*
	 * Efferent coupling calculation
	 * Ce - is a number of all income dependencies, that is
	 * the number of classes that have implemented upon current class.
	 * 
	 * 1) check all inheritances
	 */
	public int efferentCoupling(Class c, JarContent cls){
		
		int ifaceNum = 0;
		int superClass = 0;
		int composition = 0;
		
		// 1) implemented from interfaces
		JarContent ifaces = cLab.getAnnotatedInterfaces(c);
		ifaceNum = ifaces.numberOfClasses();
		
		// 2) extended from superclasses
		if(cLab.getSuperclass(c, cls) != null) superClass = 1;
		
		// 3) Full composition
		// checking for full composition
		// declared instancies
		JarContent instances = cLab.getInstances(cls, c);
		composition = instances.numberOfClasses();
		
		// 4) 
		
		System.out.println("--------CLASS => " + c.getName() + "------------");
		System.out.println("--------INTERFACES (" + ifaceNum + ")------------");
		System.out.println("--------SUPERCLASSES (" + superClass + ")------------");
		System.out.println("--------INSTANCES (" + composition + ")------------\n\n");
			
		
		return 0;
	}
}
