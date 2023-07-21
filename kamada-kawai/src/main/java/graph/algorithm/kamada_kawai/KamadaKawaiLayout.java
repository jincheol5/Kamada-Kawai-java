package graph.algorithm.kamada_kawai;

import java.util.Collections;
import java.util.Iterator;

import org.graphstream.algorithm.Algorithm;


import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


public class KamadaKawaiLayout implements Algorithm{

	
	private Graph graph;
	
	private KamadaKawai kamadaKawai;
	

	
	private Double threshold;
	
	
	public KamadaKawaiLayout(){
		
	}
	
	@Override
	public void init(Graph graph) {
		
		this.graph=graph;
		this.kamadaKawai=new KamadaKawai(this.graph);
		
	}
	
	
	
	
	@Override
	public void compute() {

	}
	
	/**
	 * compute kamada kawai algorithm layout 
	 * @param K
	 * @param threshold
	 */
	public KamadaKawaiLayout compute(Double K,Double threshold) {
		
		////Kamada Kawai Algorithm
		//필요조건
		//display width = 디스플레이 평면의 한 변의 길이
		//K = spring 강도 상수
		//threshold = 종료 조건 값 
		
		
		//0. set display length,K,threshold	
		this.kamadaKawai.set_displayLength(500.0);
		this.threshold=threshold;
		this.kamadaKawai.set_K(K);
		
		
		
		
		System.out.println("compute l,k start");
		//1. compute l,k
		this.kamadaKawai.compute_l();
		this.kamadaKawai.compute_k();
		
		
		
		
		
		//2. 반복 update
		
		System.out.println("compute delta start");
		//초기 delta 설정 
		this.kamadaKawai.compute_delta();
		
		
		
		Double maxDeltaValue=Collections.max(this.kamadaKawai.getDelta().values()); //max delta value;
		
		
		
		System.out.println("iterate start max delta value : "+maxDeltaValue);
		
		
		//max delta value가 threshold보다 작아질 때 까지 반복
		
		while(maxDeltaValue>this.threshold) {
			
			System.out.println("iterator doing...");
			
			Node updateNode=null;
			
			@SuppressWarnings("rawtypes")
			Iterator keySetIterator=this.kamadaKawai.getDelta().keySet().iterator();
			
			//max delta value를 가지고 있는 노드 찾기 -> update 될 노드 
			while(keySetIterator.hasNext()) {
				String key=keySetIterator.next().toString();
				if(maxDeltaValue==this.kamadaKawai.getDelta().get(key)) {
					updateNode=this.graph.getNode(key);
					break;
				}
			}
			
			
			
			double max=maxDeltaValue;
			
			
			
			//조건 만족할 때 까지 x,y update
			while(max>this.threshold) {
				
				
				//update position
				this.kamadaKawai.updatePosition(updateNode);
				
				//update max
				max=this.kamadaKawai.computeDelta(this.kamadaKawai.compute_EXm(updateNode), this.kamadaKawai.compute_EYm(updateNode));
				
				
				
			}
			
			//update된 graph에 대한 delta value map과 max delta value setting 
			this.kamadaKawai.compute_delta();
			maxDeltaValue=Collections.max(this.kamadaKawai.getDelta().values());
			
			
			
			
		}
		
		
		return this;
	}
	
	public void visualize() {
		
		//graph stream
		System.setProperty("org.graphstream.ui", "swing");
		
		
		
		//graph visualization
		this.graph.display(false);
	}

}
