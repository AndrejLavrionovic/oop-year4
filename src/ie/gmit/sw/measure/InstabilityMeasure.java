package ie.gmit.sw.measure;

import java.util.LinkedList;
import java.util.List;
import ie.gmit.sw.reflection.JarContent;

public class InstabilityMeasure {
	
	// instances
	private List<EfferentCoupling> ceList = new LinkedList<EfferentCoupling>();
	private JarContent JarContent;
	
	public InstabilityMeasure(){}

	public InstabilityMeasure(JarContent jarContent) {
		this.JarContent = JarContent;
	}
	
	public void createCeList(){
		for(int i = 0; i < this.JarContent.numberOfClasses(); i++){
			EfferentCoupling ce = new EfferentCoupling(this.JarContent.getClass(i), this.JarContent);
			this.ceList.add(ce);
		}
	}
	
	
}
