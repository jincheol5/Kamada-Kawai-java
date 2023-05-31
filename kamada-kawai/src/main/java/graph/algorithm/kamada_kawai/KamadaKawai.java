package graph.algorithm.kamada_kawai;

import java.util.HashMap;

import org.graphstream.algorithm.Toolkit;


public class KamadaKawai {

	@SuppressWarnings("unused")
	private KKGraph G;
	@SuppressWarnings("unused")
	private Double K;
	@SuppressWarnings("unused")
	private Double m;
	
	
	public KamadaKawai(KKGraph G,Double K,Double m) {
		
		this.G=G;
		this.K=K;
		this.m=m;

	}
	
	public HashMap<String,HashMap<String,Integer>> get_d(){
		
		HashMap<String, HashMap<String, Integer>> d_dic=new HashMap<String,HashMap<String,Integer>>();
		
		
		return d_dic;
		
	}
	
	public Double get_L(){
		
		return Toolkit.diameter(this.G.getGraph());
		
	}
	
	public HashMap<String,HashMap<String,Double>> get_l(){
		
		HashMap<String, HashMap<String, Double>> l_dic=new HashMap<String,HashMap<String,Double>>();
		
		
		return l_dic;
		
	}
	
	public HashMap<String,HashMap<String,Double>> get_k(){
		
		HashMap<String, HashMap<String, Double>> k_dic=new HashMap<String,HashMap<String,Double>>();
		
		
		return k_dic;
		
	}
	
}
