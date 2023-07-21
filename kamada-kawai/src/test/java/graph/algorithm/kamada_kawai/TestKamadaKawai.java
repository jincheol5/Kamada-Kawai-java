package graph.algorithm.kamada_kawai;


import org.graphstream.graph.Graph;


import org.graphstream.graph.implementations.SingleGraph;

public class TestKamadaKawai {

	public static void main(String[] args) {
		
		
		
		
		Graph graph=new SingleGraph("test graph");
		
		// node setting
		graph.addNode("A").setAttribute("xy", 0, 0);
		graph.addNode("B").setAttribute("xy", 1, 0);
		graph.addNode("C").setAttribute("xy", 2, 0);
		graph.addNode("D").setAttribute("xy", 0, 1);
		graph.addNode("E").setAttribute("xy", 1, 1);
		graph.addNode("F").setAttribute("xy", 2, 1);

		// edge setting
		graph.addEdge("AB", "A", "B");
		graph.addEdge("AE", "A", "E");
		graph.addEdge("BD", "B", "D");
		graph.addEdge("BE", "B", "E");
		graph.addEdge("CD", "C", "D");
		graph.addEdge("CF", "C", "F");
		graph.addEdge("DF", "D", "F");
		

		
		KamadaKawaiLayout layout=new KamadaKawaiLayout();
		
		layout.init(graph);
		
		
		
		layout.compute(1.0, 2.0).visualize();
		
//		//graph stream
//		System.setProperty("org.graphstream.ui", "swing");
//				
//							
//		//graph visualization
//		graph.display(false);
		
		
	}

}
