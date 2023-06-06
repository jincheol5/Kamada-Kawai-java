package graph.algorithm.kamada_kawai;

import java.util.HashMap;


import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class KKGraph {

	
	@SuppressWarnings("unused")
	private Graph graph;
	
	
	@SuppressWarnings("unused")
	private HashMap<String,HashMap<String,Double>> l;
	@SuppressWarnings("unused")
	private HashMap<String,HashMap<String,Double>> k;
	
	@SuppressWarnings("unused")	
	private HashMap<String,Double> M;
	
	@SuppressWarnings("unused")	
	private HashMap<String,double[]> pos;
	
	public KKGraph(Graph graph) {
		
		this.graph=graph;
		
		
		l=new HashMap<>();
		k=new HashMap<>();
		M=new HashMap<>();
		pos=new HashMap<>();
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	
	
	public void set_l(HashMap<String,HashMap<String,Double>> l) {
		this.l=l;
	}
	
	public void set_k(HashMap<String,HashMap<String,Double>> k) {
		this.k=k;
	}
	
	public void set_M(HashMap<String,Double> M) {
		this.M=M;
	}
	
	/**
	 * update pos HashMap
	 * 중복처리 필요
	 */
	public void update_pos() {
		
		for(int index=0;index<this.graph.getNodeCount();index++) {
			
			Node node=this.graph.getNode(index);
			
			Object[] xy= (Object[]) node.getAttribute("xy");
			
			double xm=Double.valueOf(xy[0].toString());
			double ym=Double.valueOf(xy[1].toString());
			
			double[] posArray= new double[2];
			posArray[0]=xm;
			posArray[1]=ym;
				
			this.pos.put(node.getId(),posArray);
			
		}
	}
	
	
	public HashMap<String,HashMap<String,Double>> get_l() {
		return this.l;
	}
	
	public HashMap<String,HashMap<String,Double>> get_k() {
		return this.k;
	}
	
	public HashMap<String,Double> get_M() {
		return this.M;
	}
	
	public HashMap<String,double[]> get_pos(){
		return this.pos;
	}
}
