package ie.gmit.sw.measure;

import ie.gmit.sw.reflection.ClassLab;
import ie.gmit.sw.reflection.JarContent;

public class AbstractionMeasure implements Measurable {

	
	// fields
	private double abstraction;
	private int na;
	private int nc;
	
	// classlab instance => full composition
	private ClassLab c = new ClassLab();
	
	// parameterized constructor
	public AbstractionMeasure(JarContent cls){
		this.na = getAbstractsNum(cls);
		this.nc = getConcretesNum(cls);
	}
	
	// method that returns result
	public double getResult() {
		return abstraction;
	}

	@Override
	public void measure(JarContent cls) {
		
		this.abstraction = (double)this.na / (double)this.nc;
	}
	
	// number of all abstract classes including interfaces 
	private int getAbstractsNum(JarContent cls){
		JarContent abs = this.c.getAbstracts(cls);
		return abs.numberOfClasses();
	}
	
	// number of all concrete classes
	private int getConcretesNum(JarContent cls){
		JarContent con = this.c.getConcretes(cls);
		return con.numberOfClasses();
	}

}
