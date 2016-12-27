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
	private JarContent cls;
	
	public AbstractionMeasure(){}
	
	// parameterized constructor
	public AbstractionMeasure(JarContent cls){
		this.cls = cls;
		this.na = getAbstractsNum();
		this.nc = getConcretesNum();
	}
	
	// sets content of jar file
	public void setCls(JarContent cls) {
		this.cls = cls;
	}

	// method that returns result
	public double getResult() {
		return abstraction;
	}

	@Override
	public void measure() {
		
		this.abstraction = (double)this.na / (double)this.nc;
	}
	
	// number of all abstract classes including interfaces 
	private int getAbstractsNum(){
		JarContent abs = this.c.getAbstracts(this.cls);
		return abs.numberOfClasses();
	}
	
	// number of all concrete classes
	private int getConcretesNum(){
		JarContent con = this.c.getConcretes(this.cls);
		return con.numberOfClasses();
	}
}
