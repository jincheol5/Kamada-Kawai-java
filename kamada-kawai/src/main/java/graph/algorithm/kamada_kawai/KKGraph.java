package graph.algorithm.kamada_kawai;

import java.util.HashMap;
import java.util.Map;

public class KKGraph {

	private Graph graph;
	
	private Map<String,Integer> d;
	private Map<String,Double> l;
	private Map<String,Double> k;
	
	public KKGraph() {
		d=new HashMap<>();
		l=new HashMap<>();
		k=new HashMap<>();
	}
}
