package graph.algorithm.kamada_kawai;

import java.util.HashMap;
import java.util.Map;

public class KamadaKawai {

	private Map<String,Integer> d;
	private Map<String,Double> l;
	private Map<String,Double> k;
	
	public KamadaKawai() {
		
		d=new HashMap<>();
		l=new HashMap<>();
		k=new HashMap<>();
	}
	
}
