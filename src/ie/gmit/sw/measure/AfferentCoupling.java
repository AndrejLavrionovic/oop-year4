package ie.gmit.sw.measure;

import java.util.List;
import ie.gmit.sw.reflection.JarContent;

/**
 * @author g00196984 - Andrej Lavrinovic
 * 
 * Afferent Coupling is a number representing all classes
 * that depend on current class
 * 
 * To get this number we need to calculate Efferent Coupling
 * first for each class in the given jar. When we have complete
 * list of Efferent couplings -
 * (that is a collection of all classes that have other collection
 * of classes which are depend upon for particular class) - 
 * we running through each class and creating the collection
 * of classes that are depended on current class.
 * 
 */

public class AfferentCoupling implements Measurable {
	
	// Fields
	private JarContent deps;
	private Class cl;
	private List<EfferentCoupling> ceList;
	
	// parameterized constructor
	public AfferentCoupling(Class cl, List<EfferentCoupling> ceList) {
		this.cl = cl;
		this.ceList = ceList;
		measure();
	}

	/* 
	 * 
	 * (non-Javadoc)
	 * @see ie.gmit.sw.measure.Measurable#measure()
	 * 
	 * this method populates the collection with classes
	 * that are depend on current class.
	 */
	@Override
	public void measure() {
		
		this.deps = new JarContent();
		
		// 1) get class and check how many other classes use it.
		for (int i = 0; i < ceList.size(); i++){
			
			JarContent d = ceList.get(i).getDeps();
			
			if(d.numberOfClasses() > 0){
				for(int j = 0; j < d.numberOfClasses(); j++){
					if(cl.equals(d.getClass(j))){
						this.deps.addClass(ceList.get(i).getCeClass());
					}
				}
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ie.gmit.sw.measure.Measurable#getResult()
	 * 
	 * Method returns the number that represents Afferent Coupling
	 * for current class
	 * This number basicaly is a size of collection of 
	 * classes that are depend on current class.
	 */
	@Override
	public double getResult() {
		return (double)this.deps.numberOfClasses();
	}
	
	public JarContent getDept(){
		return this.deps;
	}
	
	// returns current class
	public Class getCaClass(){
		return this.cl;
	}
}
