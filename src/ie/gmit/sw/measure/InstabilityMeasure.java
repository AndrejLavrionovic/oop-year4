package ie.gmit.sw.measure;

import java.util.LinkedList;
import java.util.List;
import ie.gmit.sw.reflection.JarContent;

public class InstabilityMeasure {
	
	// instances
	private List<EfferentCoupling> ceList = new LinkedList<EfferentCoupling>();
	private List<AfferentCoupling> caList = new LinkedList<AfferentCoupling>();
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
	
	public List<EfferentCoupling> getCeList(){
		return this.ceList;
	}
	
	public List<AfferentCoupling> getCaList(){
		return this.caList;
	}
}
