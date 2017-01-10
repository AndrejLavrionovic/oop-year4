package ie.gmit.sw.graph;

import java.util.List;
import java.util.LinkedList;

/**
 * Class Node represents single class in graph format.
 * It also contains list of edges (dependencies), and
 * list of types that pointing on current class.
 * 
 * @author g00196984 - Andrej Lavrinovic
 */
public class Node {

	// instances
	private Class c;
	private List<Edge> connections = new LinkedList<Edge>();
	private List<Node> deps = new LinkedList<Node>();
	
	// constructors
	public Node(){} // default
	
	/**
	 * 
	 * @param c - is Class that should be represented by current Node.
	 */
	public Node(Class c) {
		this.c = c;
	}
	
	/**
	 * 
	 * @param c Class that should be represented by current Node.
	 * @param con Outgoing connections (edges).
	 */
	public Node(Class c, List<Edge> con) {
		this.c = c;
		this.connections = con;
	}

	// getters/setters - generted using eclipse
	/**
	 * 
	 * @return Returns Class represented by current node.
	 */
	public Class getC() {
		return c;
	}
	
	/**
	 * 
	 * @param c Class that should be represented by current Node.
	 */
	public void setC(Class c) {
		this.c = c;
	}

	/**
	 * 
	 * @return Returns list of nodes that pointing on current class.
	 */
	public List<Node> getDeps() {
		return deps;
	}
	
	/**
	 * 
	 * @return Returns list of outgoing dependencies (edges).
	 */
	public List<Edge> getEdges(){
		return connections;
	}

	/**
	 * 
	 * @param List<Node> Takes list as parameter.
	 */
	public void setDeps(List<Node> deps) {
		this.deps = deps;
	}

	/**
	 * Deligated method from List<E>.add
	 * @param edge is Edge object that represents connection from current class to related Node
	 * @return Returns true if edge is added saccessfuly, or false in other case.
	 */
	public boolean addEdge(Edge edge) {
		return connections.add(edge);
	}
	
	
}
