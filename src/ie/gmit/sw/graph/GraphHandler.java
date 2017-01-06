package ie.gmit.sw.graph;

import java.util.LinkedList;
import java.util.List;

import ie.gmit.sw.reflection.ClassLab;
import ie.gmit.sw.reflection.JarContent;

public class GraphHandler {

	private JarGraph graph;
	private ClassLab cLab;
	
	public GraphHandler(String jarName){
		cLab = new ClassLab(jarName);
	}
	
	
	public void setGraph(String jarName){
		graph = new JarGraph();
		// get list of classes
		JarContent jarClasses = this.cLab.getJarClasses();
		
		// create not connected graph with nodes
		for(int i = 0; i < jarClasses.numberOfClasses(); i++){
			Node node = new Node(jarClasses.getClass(i));
			graph.addNode(node);
		}
		
		// create dependencies for each node in graph
		for(int i = 0; i < graph.graphSize(); i++){
			
		}
	}
	
	private List<Node> getNodeDeps(Node n, JarContent j){
		
		List<Node> deps = new LinkedList<Node>();
		
		// 1) implemented interfaces
		JarContent ifaces = cLab.getInterfaces(n.getC());
		if(ifaces.numberOfClasses() > 0){
			for(int i = 0; i < ifaces.numberOfClasses(); i ++){
				
				//deps.addClass(ifaces.getClass(i));
			}
		}
		
		return deps;
	}
}
