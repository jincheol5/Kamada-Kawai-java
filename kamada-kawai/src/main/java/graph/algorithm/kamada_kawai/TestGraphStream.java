package graph.algorithm.kamada_kawai;


import java.util.HashMap;


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
		graph.addEdge("AB", "A", "B").setAttribute("distance", 1);
		graph.addEdge("AE", "A", "E").setAttribute("distance", 1);
		graph.addEdge("BD", "B", "D").setAttribute("distance", 1);
		graph.addEdge("BE", "B", "E").setAttribute("distance", 1);
		graph.addEdge("CD", "C", "D").setAttribute("distance", 1);
		graph.addEdge("CF", "C", "F").setAttribute("distance", 1);
		graph.addEdge("DF", "D", "F").setAttribute("distance", 1);
		
		
		
		
		
		
		
		//coordinate setting 
		graph.getNode("A").setAttribute("xy", 0, 0);
		graph.getNode("B").setAttribute("xy", 1, 0);
		graph.getNode("C").setAttribute("xy", 2, 0);

		graph.getNode("D").setAttribute("xy", 0, 1);
		graph.getNode("E").setAttribute("xy", 1, 1);
		graph.getNode("F").setAttribute("xy", 2, 1);
        
		
		
		Object[] xy=(Object[]) graph.getNode("B").getAttribute("xy");
		
		for(Object o:xy) {
			System.out.println(Double.valueOf(o.toString()));
		}
		
		
		
		
        
		//KKGraph setting
		KKGraph kkGraph=new KKGraph(graph);
        
		//KamadaKawai setting
		KamadaKawai kk=new KamadaKawai(kkGraph);
		
	
       
//	    //graph visualization
//	    graph.display().disableAutoLayout();

		
	}

}
