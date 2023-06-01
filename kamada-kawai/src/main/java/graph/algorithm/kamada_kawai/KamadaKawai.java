package graph.algorithm.kamada_kawai;

import java.util.HashMap;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.Dijkstra;


public class KamadaKawai {

	@SuppressWarnings("unused")
	private KKGraph G;
	@SuppressWarnings("unused")
	private Double K;
	@SuppressWarnings("unused")
	private Double m;
	
	
	public KamadaKawai(KKGraph G) {
		
		this.G=G;
		
	}
	
	
	public KamadaKawai(KKGraph G,Double K,Double m) {
		
		this.G=G;
		this.K=K;
		this.m=m;

	}
	
	public void set_G(KKGraph G) {
		this.G=G;
	}
	
	public void set_K(Double K) {
		this.K=K;
	}
	
	public void set_m(Double m) {
		this.m=m;
	}
	
	/**
	 * compute and return d HashMap of the graph 
	 * d = The length of the shortest distance between source and target in the graph
	 * @return d HashMap of the  graph
	 */
	public HashMap<String,HashMap<String,Double>> get_d(){
		
		HashMap<String, HashMap<String, Double>> d_dic=new HashMap<String,HashMap<String,Double>>();
		
		
		for(int source=0;source<this.G.getGraph().getNodeCount();source++) {
			HashMap<String, Double> shortestDistance=new HashMap<>();
			for(int target=0;target<this.G.getGraph().getNodeCount();target++) {
				
				if(source!=target){
					Dijkstra dijkstra=new Dijkstra();
					dijkstra.init(this.G.getGraph());
					dijkstra.setSource(this.G.getGraph().getNode(source));
			 		dijkstra.compute();
					
					shortestDistance.put(this.G.getGraph().getNode(target).toString(), dijkstra.getPathLength(this.G.getGraph().getNode(target)));
				}

			}
			d_dic.put(this.G.getGraph().getNode(source).toString(), shortestDistance);
		}
		
		return d_dic;
		
	}
	
	/**
	 * compute and return L value of the graph
	 * 
	 * 
	 * @param display length
	 * @return L value of the graph
	 */
	public Double get_L(Double displayLength){
		
		return Toolkit.diameter(this.G.getGraph());
		
	}
	
	
	/**
	 * compute and return l HashMap of the graph
	 * 
	 * 
	 * @return l HashMap ot the graph
	 */
	public HashMap<String,HashMap<String,Double>> get_l(Double displayLength){
		
		HashMap<String, HashMap<String, Double>> l_dic=new HashMap<String,HashMap<String,Double>>();
		
		double L=get_L(displayLength);
		
		for(int source=0;source<this.G.getGraph().getNodeCount();source++) {
			HashMap<String, Double> target_dic=new HashMap<>();
			for(int target=0;target<this.G.getGraph().getNodeCount();target++) {
				
				if(source!=target){
					Dijkstra dijkstra=new Dijkstra();
					dijkstra.init(this.G.getGraph());
					dijkstra.setSource(this.G.getGraph().getNode(source));
			 		dijkstra.compute();
					
			 		
			 		
			 		target_dic.put(this.G.getGraph().getNode(target).toString(),L*dijkstra.getPathLength(this.G.getGraph().getNode(target)) );
				}

			}
			l_dic.put(this.G.getGraph().getNode(source).toString(), target_dic);
		}
		
		
		return l_dic;
		
	}
	
	public HashMap<String,HashMap<String,Double>> get_k(){
		
		HashMap<String, HashMap<String, Double>> k_dic=new HashMap<String,HashMap<String,Double>>();
		
		
		return k_dic;
		
	}
	
}
