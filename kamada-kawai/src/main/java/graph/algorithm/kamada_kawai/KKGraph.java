package graph.algorithm.kamada_kawai;

import java.util.HashMap;


import org.graphstream.graph.Graph;

public class KKGraph {

	
	@SuppressWarnings("unused")
	private Graph graph;
	
	@SuppressWarnings("unused")
	private HashMap<String,Integer> d;
	@SuppressWarnings("unused")
	private HashMap<String,Double> l;
	@SuppressWarnings("unused")
	private HashMap<String,Double> k;
	
	@SuppressWarnings("unused")	
	private HashMap<String,Double> M;
	
	public KKGraph(Graph graph) {
		
		this.graph=graph;
		
		d=new HashMap<>();
		l=new HashMap<>();
		k=new HashMap<>();
		M=new HashMap<>();
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
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
	
	public HashMap<String,Integer> get_d() {
		return this.d;		
	}
	
	public HashMap<String,Double> get_l() {
		return this.l;
	}
	
	public HashMap<String,Double> get_k() {
		return this.k;
	}
	
	public HashMap<String,Double> get_M() {
		return this.M;
	}
}
