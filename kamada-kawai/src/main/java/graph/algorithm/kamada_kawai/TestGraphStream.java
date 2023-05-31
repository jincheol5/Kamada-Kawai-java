package graph.algorithm.kamada_kawai;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;


public class TestGraphStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		 // 그래프 생성
        Graph graph = new SingleGraph("Static Graph");
        
      

        // 노드 추가
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Node nodeC = graph.addNode("C");

        // 엣지 추가
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");

	}

}
