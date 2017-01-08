package ie.gmit.sw.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nodes {

	private List<Node> nodesList = new ArrayList<Node>();
	private Map<Class, Node> nodesMap = new HashMap<Class, Node>();
	
	public Nodes(List<Class> jarClasses){
		nodesList = getListOfNodes(jarClasses);
		nodesMap = getMapOfNodes(jarClasses);
	}

	private Map<Class, Node> getMapOfNodes(List<Class> jarClasses) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Node> getListOfNodes(List<Class> jarClasses) {
		// TODO Auto-generated method stub
		return null;
	}
}
