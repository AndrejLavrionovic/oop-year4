package ie.gmit.sw.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import ie.gmit.sw.graph.Edge;
import ie.gmit.sw.graph.Node;

/**
 * JarClassLab is general handler for defining all available types that are depend on current class,
 * or types that current class is depend on.
 * 
 * It contains List<Node> and Map<Class, Node> that are used for simplify the searching of dependencies
 * for inspected class. By creating Map we spending O(n) time in big O notation but we spent no time for
 * matching Nodes in further in lots of situations.
 * 
 * @author g00196984 - Andrej Lavrinovic
 *
 */
public class JarClassesLab {
	
	private static JarClassesLab instance;
	
	private List<Node> listOfNodes;
	private Map<Class, Node> mapOfNodes;
	
	/**
	 * Parameterized constructor that creates List<Node> and Map<Class, Node>.
	 * @param jarName String that represents path to the JAR archive that meed to be inspected.
	 */
	private JarClassesLab(String jarName){
		setNodes(jarName);
	}
	
	/**
	 * Initialization of current instance to use it late in GraphHandler as a singlton pattern.
	 * 
	 * JarClassesLab is used just ones for creation of graph by GraphHandler.
	 * 
	 * @param jarName String that represents path to the JAR archive that meed to be inspected.
	 * @return Returns instance of JarClassesLab (current object)
	 */
	public static JarClassesLab getInstance(String jarName){
		if(instance == null)
			instance = new JarClassesLab(jarName);
		return instance;
	}
	
	/**
	 * Method setNodes used to populate list of nodes and map of nodes
	 * Bought List and Map of nodes used for organization of graph in
	 * GraphHandler class.
	 * 
	 * @param jarName String that represents path to the JAR archive that meed to be inspected.
	 */
	public void setNodes(String jarName){
		
		listOfNodes = new LinkedList<Node>();
		mapOfNodes = new HashMap<Class, Node>();

		try{
			JarInputStream in = new JarInputStream(new FileInputStream(new File(jarName))); // get stream
			JarEntry next = in.getNextJarEntry(); // get jar instance
			
			while(next != null){ // run through
				
				if(next.getName().endsWith(".class")){ // search for classes
					
					// get class name
					String name = next.getName().replaceAll("/", "\\.");
					name = name.replaceAll(".class", "");
					if (!name.contains("$")) name.substring(0, name.length() - ".class".length());

					// inspect class
					Class queryClass;
					try {
						queryClass = Class.forName(name);
						Node n = new Node(queryClass);
						listOfNodes.add(n);
						mapOfNodes.put(queryClass, n);
					} catch (ClassNotFoundException e) {
						System.out.println("Couldn't find class '" + name + "'"); // if class is not found
						System.exit(1);
					} // reflection
				}
				next = in.getNextJarEntry();
			}
					
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	/*
	 * Methods below used to find and add all dependencies that
	 * pointing on current node
	 */
	// get all implemented interfaces
	/**
	 * Method takes Node, retrieves class and check for presence of implemented interfaces,
	 * and if it is creates appropriate edges and nodes that related to current node.
	 * 
	 * @param n Node that is inspected for the presence of implemented Interfaces
	 */
	public void findInterfaces(Node n){
		Class c = n.getC(); // get class
		
		Class ifaces[] = c.getInterfaces();
		if(ifaces.length > 0){
			for(Class item : ifaces){
				setEdges(item, n);
			}
		}
	}
	
	/**
	 * Method takes Node, retrieves class and checks for presence of superclasses. It ignores interfaces.
	 * If types are not from standard jdk libraries,
	 * then edges are created for current node and nodes that related to current node.
	 * @param n Inspected Node
	 */
	public void findSuperclass(Node n){
		
		// get nodes's class
		Class c = n.getC();
		
		if(!c.isInterface()){ // if class is not an interface
			
			Class sclass = c.getSuperclass(); // get superclass
			
			setEdges(sclass, n);
		}
	}
	
	// get node's fiedls
	/**
	 * Method lookes for types among Node's fields. If Types from standard jdk libriry it is ignored.
	 * If types are not from standard jdk libraries,
	 * then edges are created for current node and nodes that related to current node
	 * @param n Inspected Node
	 */
	public void findFields(Node n) {
		
		Class c = n.getC();
		
		Field flds[] = c.getDeclaredFields();
		
		if(flds.length > 0){
			
			for(Field item : flds){
				Class fieldtype = null;
				for(Node nd : listOfNodes){
					if(item.getType().getName().equals(nd.getC().getName())){
						fieldtype = nd.getC();
						break;
					}
				}
				
				if(fieldtype != null){
					setEdges(fieldtype, n);
				}
			}
		}
	}
	
	// get Constructor parameters
	/**
	 * Method check constructors and its parameters for types. If types are not from standard jdk libraries,
	 * then edges are created for current node and nodes that related to current node.
	 * @param n Inspected node
	 */
	public void findConstrParams(Node n){
		
		Class c = n.getC();
		
		Constructor constrs[] = c.getDeclaredConstructors();
		
		if(constrs.length > 0){
			for(Constructor constr : constrs){
				Class p[] = constr.getParameterTypes();
				if(p.length > 0){
					for(Class item : p){
						setEdges(item, n);
					}
				}
			}
		}
	}
	
	// get Method parameters
	/**
	 * Method check all methods and its parameters for types. If types are not from standard jdk libraries,
	 * then edges are created for current node and nodes that related to current node.
	 * @param n Inspected node
	 */
	public void findMethodsParams(Node n){
		
		Class c = n.getC();
		
		Method methods[] = c.getDeclaredMethods();
		
		if(methods.length > 0){
			for(Method m : methods){
				Class p[] = m.getParameterTypes();
				if(p.length > 0){
					for(Class item : p){
						setEdges(item, n);
					}
				}
			}
		}
	}
	
	// Method return type
	/**
	 * Method checks node for methods and its return types. If types are not from standard jdk libraries,
	 * then edges are created for current node and nodes that related to current node.
	 * @param n Inspected node
	 */
	public void findMethodsReturnType(Node n){
		
		Class c = n.getC();
		
		Method methods[] = c.getDeclaredMethods();
		
		if(methods.length > 0){
			for(Method m : methods){
				Class p = m.getReturnType();
				setEdges(p, n);
			}
		}
	}
	
	// filter checks if current node exist in the list of nodes
	/**
	 * Methos checks if the given node exists in the list.
	 * @param n Node that need to be checked is it in the list, or not.
	 * @param nodes List of nodes that should or should not to contain inspected node.
	 * @return Returns true if nodes is already exists within the list of nodes and false in other case.
	 */
	private boolean nodeFilter(Node n, List<Node> nodes){
		if(nodes.size() > 0){
			return nodes.contains(n) ? true : false;
		}
		return false;
	}
	
	/*
	 * method sets dependencies for current node
	 * and sets edges for nodes pointing on current node
	 */
	/**
	 * Mathods sets edges if types are found in inspected class.
	 * @param c Type (Class) that found in the inspected Node.
	 * @param n Inspected node.
	 */
	private void setEdges(Class c, Node n){
		if(mapOfNodes.containsKey(c) && !nodeFilter(mapOfNodes.get(c), n.getDeps())){
			n.getDeps().add(this.mapOfNodes.get(c));
			Edge e = new Edge(this.mapOfNodes.get(c), n);
			mapOfNodes.get(c).addEdge(e);
		}
	}

	/*
	 * Getters generated by Eclipse
	 */
	/**
	 * 
	 * @return Returns lsit of Nodes List<Node>
	 */
	public List<Node> getListOfNodes() {
		return listOfNodes;
	}

	/**
	 * 
	 * @return Returns Map of Nodes - Map<Class, Node>
	 */
	public Map<Class, Node> getMapOfNodes() {
		return mapOfNodes;
	}
}
