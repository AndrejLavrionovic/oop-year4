package ie.gmit.sw.measure;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.graph.JarGraph;
import ie.gmit.sw.graph.Node;

public class InstabilityMeasure implements Measurable{
	
	private JarGraph jg;
	private List<Instability> iList = new ArrayList<Instability>();

	// Constructor generated by Eclipse
	public InstabilityMeasure(JarGraph jg) {
		this.jg = jg;
	}

	@Override
	public void measure() {
		if(jg != null){
			for(int i = 0; i < jg.nodesNumber(); i++){
				Node n = jg.getNode(i);
				Class c = n.getC();
				int ce = n.getDeps().size();
				int ca = n.getEdges().size();
				double ins = 0;
				if(ce != 0 || ca != 0){
					ins = (double)ce / ((double)ce + (double)ca);
				}
				Instability inst = new Instability(c, ce, ca, ins);
				
				this.iList.add(inst);
			}
		}
	}

	// Return list of classes with instability measurement
	public List<Instability> getiList() {
		return iList;
	}
}
