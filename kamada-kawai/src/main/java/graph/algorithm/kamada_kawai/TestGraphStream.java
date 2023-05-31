package graph.algorithm.kamada_kawai;


import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;

import org.graphstream.graph.implementations.SingleGraph;


public class TestGraphStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("org.graphstream.ui", "swing");

		// create graph
		Graph graph = new SingleGraph("Static Graph");

		
		
		// node setting
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addNode("D");
		graph.addNode("E");
		graph.addNode("F");

		// edge setting
		graph.addEdge("AB", "A", "B");
		graph.addEdge("AE", "A", "E");
		graph.addEdge("BD", "B", "D");
		graph.addEdge("BE", "B", "E");
		graph.addEdge("CD", "C", "D");
		graph.addEdge("CF", "C", "F");
		graph.addEdge("DF", "D", "F");
		
		
		
		
		
		
		
		//coordinate setting 
		graph.getNode("A").setAttribute("xy", 0, 0);
		graph.getNode("B").setAttribute("xy", 1, 0);
		graph.getNode("C").setAttribute("xy", 2, 0);

		graph.getNode("D").setAttribute("xy", 0, 1);
		graph.getNode("E").setAttribute("xy", 1, 1);
		graph.getNode("F").setAttribute("xy", 2, 1);
        
		
		
		
		
        
		//KKGraph setting
		KKGraph kkGraph=new KKGraph(graph);
        
       
	    //graph visualization
	    graph.display().disableAutoLayout();

	}

}
