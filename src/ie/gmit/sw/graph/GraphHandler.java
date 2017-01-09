package ie.gmit.sw.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ie.gmit.sw.reflection.ClassLab;
import ie.gmit.sw.reflection.JarClassesLab;
import ie.gmit.sw.reflection.JarContent;

public class GraphHandler {

	private JarGraph graph;
	private String jarName;
	
	/*
	 * Singleton pattern used to exploit JarClassesLab
	 * for creating the graph pattern
	 */
	private JarClassesLab cLab;
	
	
	//Constructores
	public GraphHandler(){} // default
	
	public GraphHandler(String jarName){ // parameterized
		this.jarName = jarName;
		cLab = JarClassesLab.getInstance(this.jarName);
	}
	
	
	public void setGraph(){
		graph = new JarGraph(cLab.getListOfNodes());
		
		// create dependencies for each node in graph
		for(int i = 0; i < graph.nodesNumber(); i++){
			Node node = graph.getNode(i);
			
			// 1) set interfaces
			cLab.findInterfaces(node);
			// 2) find superclass
			cLab.findSuperclass(node);
			// 3) find Fields
			cLab.findFields(node);
			// 4) find Constructors params
			cLab.findConstrParams(node);
			// 5) find Method params
			cLab.findMethodsParams(node);
			// 6) find Methods return type
			cLab.findMethodsReturnType(node);
		}
	}

	// Getters/Setters generated by Eclipse
	public JarGraph getGraph() {
		return graph;
	}
}
