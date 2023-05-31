package graph.algorithm.kamada_kawai;

import java.util.HashMap;
import java.util.Map;

import org.graphstream.graph.Graph;

public class KKGraph {

	
	@SuppressWarnings("unused")
	private Graph graph;
	
	@SuppressWarnings("unused")
	private Map<String,Integer> d;
	@SuppressWarnings("unused")
	private Map<String,Double> l;
	@SuppressWarnings("unused")
	private Map<String,Double> k;
	
	@SuppressWarnings("unused")	
	private Map<String,Double> M;
	
	public KKGraph(Graph graph) {
		
		this.graph=graph;
		
		d=new HashMap<>();
		l=new HashMap<>();
		k=new HashMap<>();
		M=new HashMap<>();
	}
	
	public void set_d(HashMap<String,Integer> d) {
		this.d=d;		
	}
	
	public void set_l(HashMap<String,Double> l) {
		this.l=l;
	}
	
	public void set_k(HashMap<String,Double> k) {
		this.k=k;
	}
	
	public void set_M(HashMap<String,Double> M) {
		this.M=M;
	}
}
