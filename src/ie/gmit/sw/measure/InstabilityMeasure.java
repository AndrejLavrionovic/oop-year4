package ie.gmit.sw.measure;

import java.util.LinkedList;
import java.util.List;
import ie.gmit.sw.reflection.JarContent;

public class InstabilityMeasure {
	
	// instances
	private List<EfferentCoupling> ceList = new LinkedList<EfferentCoupling>();
	private List<AfferentCoupling> caList = new LinkedList<AfferentCoupling>();
	private List<Instability> instabilities = new LinkedList<Instability>();
	private JarContent jarContent;
	
	public InstabilityMeasure(){}

	public InstabilityMeasure(JarContent jarContent) {
		this.jarContent = jarContent;
	}
	
	public void createCouplings(){
		
		for(int i = 0; i < this.jarContent.numberOfClasses(); i++){
			EfferentCoupling ce = new EfferentCoupling(this.jarContent.getClass(i), this.jarContent);
			this.ceList.add(ce);
		}
		
		for(int i = 0; i < this.jarContent.numberOfClasses(); i++){
			AfferentCoupling ca = new AfferentCoupling(this.jarContent.getClass(i), this.ceList);
			this.caList.add(ca);
		}
	}
	
	public void calculateInstabilities(){
		double ce, ca, in;
		Instability inst;
		
		for(int i = 0; i < jarContent.numberOfClasses(); i++){
			
			Class cl = jarContent.getClass(i);
			
			ce = ca = in = 0; // inicialization
			
			for(int j = 0; j < this.ceList.size(); j++){
				EfferentCoupling ec = this.ceList.get(j);
				if(cl.equals(ec.getCeClass())){
					ce = ec.getResult();
					break;
				}
			}
			for(int j = 0; j < this.caList.size(); j++){
				AfferentCoupling ac = this.caList.get(j);
				if(cl.equals(ac.getCaClass())){
					ca = ac.getResult();
					break;
				}
			}
			
			if(ce == 0 && ca == 0)
				in = 0;
			else
				in = ce / (ce + ca);
			
			inst = new Instability(cl, ce, ca, in);
			instabilities.add(inst);
		}
	}
	
	public List<EfferentCoupling> getCeList(){
		return this.ceList;
	}
	
	public List<AfferentCoupling> getCaList(){
		return this.caList;
	}
	
	public List<Instability> getInstabilities(){
		return this.instabilities;
	}
}
