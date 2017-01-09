package ie.gmit.sw.graph;

import java.util.LinkedList;
/**
 * @author g00196984 - Andrej Lavrinovic
 * 
 * Class Node represents single class in graph representation
 * with instance of class and collection of dependencies
 */
import java.util.List;

public class Node {

	// instances
	private Class c;
	private List<Edge> connections = new LinkedList<Edge>();
	private List<Node> deps = new LinkedList<Node>();
	
	// constructors
	public Node(){} // default
	
	/*
	 * Parameterized constructor
	 * Generated using eclipse.	
	 */
	public Node(Class c) {
		this.c = c;
	}
	
	public Node(Class c, List<Edge> con) {
		this.c = c;
		this.connections = con;
	}

	// getters/setters - generted using eclipse
	public Class getC() {
		return c;
	}
	public void setC(Class c) {
		this.c = c;
	}

	public List<Node> getDeps() {
		return deps;
	}
	public List<Edge> getEdges(){
		return connections;
	}

	public void setDeps(List<Node> deps) {
		this.deps = deps;
	}

	public boolean containsEdge(Edge o) {
		return connections.contains(o);
	}

	public boolean addEdge(Edge e) {
		return connections.add(e);
	}
	
	
}
