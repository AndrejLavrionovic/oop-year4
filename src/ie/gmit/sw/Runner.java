package ie.gmit.sw;

import java.util.List;

import ie.gmit.sw.graph.GraphHandler;
import ie.gmit.sw.graph.JarGraph;
import ie.gmit.sw.graph.Node;
import ie.gmit.sw.measure.Instability;
import ie.gmit.sw.measure.InstabilityMeasure;

public class Runner {
	

	// main method for running reflection
	// example:
	// java -cp ./bin:../people.jar ie.gmit.sw.Runner ../people.jar
	public static void main(String[] args) {
		
		
		// Graph representation
		GraphHandler gh = new GraphHandler(args[0]);
		gh.setGraph();
		JarGraph graph = gh.getGraph();
		
		// Instability measure
		InstabilityMeasure im = new InstabilityMeasure(graph);
		im.measure();
		List<Instability> iList = im.getiList();
		
		System.out.println("\n\n\n----------INSTABILITY MEASURE----------");
		System.out.println("---------------------------------------");
		System.out.printf("|     CLASS     |  CE  |  CA  |   I   |\n");
		System.out.println("|---------------|------|------|-------|");
		for(int i = 0; i < iList.size(); i++){
			Instability in = iList.get(i);
			System.out.printf("| %14s|  %2d  |  %2d  |  %4.2f |\n", in.getCl().getSimpleName(), in.getCe(), in.getCa(), in.getI());
		}
		System.out.println("---------------------------------------");
		
		// graph schema
		System.out.println("\n\n\n***********************************************");
		System.out.println("-----------GRAPH REPRESENTATION----------------");
		System.out.println("***********************************************\n");
		for(int i = 0; i < graph.nodesNumber(); i++){
			Node n = graph.getNode(i);
			System.out.println("---NODE(" + n.getC().getName() + ")---");
			System.out.println("\n---IN---");
			
			for(int j = 0; j < n.getDeps().size(); j++){
				System.out.println("      (" + n.getDeps().get(j).getC().getSimpleName() + ") --> (" + n.getC().getSimpleName() + ")");
			}
			
			System.out.println("\n---OUT---");
			
			for(int j = 0; j < n.getEdges().size(); j++){
				System.out.println("      (" + n.getEdges().get(j).getStart().getC().getSimpleName() + ") --> (" + n.getEdges().get(j).getEnd().getC().getSimpleName() + ")");
			}
			
			System.out.println("---------------------------------------------------\n\n\n");
		}
	}
}
