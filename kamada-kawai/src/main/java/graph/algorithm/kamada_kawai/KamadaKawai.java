package graph.algorithm.kamada_kawai;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Node;
import org.graphstream.algorithm.Dijkstra;


public class KamadaKawai {

	@SuppressWarnings("unused")
	private KKGraph G;
	
	@SuppressWarnings("unused")
	private Double K;
	
	@SuppressWarnings("unused")
	private Double hyperE;
	
	@SuppressWarnings("unused")
	private Double displayLength;
	
	
	public KamadaKawai(KKGraph G) {
		
		this.G=G;
		
	}
	
	
	
	public void set_G(KKGraph G) {
		this.G=G;
	}
	
	public void set_K(Double K) {
		this.K=K;
	}
	
	public void set_hyperE(Double hyperE) {
		this.hyperE=hyperE;
	}
	
	public void set_displayLength(Double displayLength) {
		this.displayLength=displayLength;
	}
	
	
	/**
	 * compute and return shortest length between source and target in the graph
	 * 
	 * @param source : source node
	 * @param target : target node
	 * @return shortest length between source and target in the graph
	 */
	public Double getShortestPathLength(Node source,Node target) {
		
		Dijkstra dijkstra=new Dijkstra();
		dijkstra.init(this.G.getGraph());
		dijkstra.setSource(source);
 		dijkstra.compute();
		
		return dijkstra.getPathLength(target);
	}
	

	
	/**
	 * compute and return L value of the graph
	 * 
	 * 
	 * @param display length
	 * @return L value of the graph
	 */
	public Double get_L(){
		
		return this.displayLength/Toolkit.diameter(this.G.getGraph());
		
	}
	
	
	/**
	 * compute and return l HashMap of the graph
	 * 
	 * 
	 * @return l HashMap ot the graph
	 */
	public HashMap<String,HashMap<String,Double>> get_l(){
		
		HashMap<String, HashMap<String, Double>> l_dic=new HashMap<String,HashMap<String,Double>>();
		
		double L=get_L();
		
		for(int source=0;source<this.G.getGraph().getNodeCount();source++) {
			HashMap<String, Double> target_dic=new HashMap<>();
			
			for(int target=0;target<this.G.getGraph().getNodeCount();target++) 
				if(source!=target)
			 		target_dic.put(this.G.getGraph().getNode(target).getId(),L*getShortestPathLength(this.G.getGraph().getNode(source),this.G.getGraph().getNode(target)) );
			
			l_dic.put(this.G.getGraph().getNode(source).getId(), target_dic);
		}
		
		
		return l_dic;
		
	}
	
	/**
	 * compute and return k HashMap of the graph
	 * 
	 * @return k HashMap of the graph
	 */
	public HashMap<String,HashMap<String,Double>> get_k(){
		
		HashMap<String, HashMap<String, Double>> k_dic=new HashMap<String,HashMap<String,Double>>();
		
		for(int source=0;source<this.G.getGraph().getNodeCount();source++) {
			HashMap<String, Double> target_dic=new HashMap<>();
			
			for(int target=0;target<this.G.getGraph().getNodeCount();target++) 
				if(source!=target)
			 		target_dic.put(this.G.getGraph().getNode(target).getId(),
			 				this.K/Math.pow(getShortestPathLength(this.G.getGraph().getNode(source),this.G.getGraph().getNode(target)),2) );
			
			
			k_dic.put(this.G.getGraph().getNode(source).getId(), target_dic);
		}
		
		return k_dic;
		
	}
	
	
	
	/**
	 * mNode의 x에 대한 E의 편미분값을 반환 
	 * @param mNode
	 * @return 편미분값 EXm
	 */
	public Double getEXm(Node mNode) {
		
		
		double[] xyArray=this.G.get_pos().get(mNode.getId());
		
		double xm=xyArray[0];
		double ym=xyArray[1];
		
		
		Double EXm=0.0;
		
		for(int index=0;index<this.G.getGraph().getNodeCount();index++) {
			
			if(!this.G.getGraph().getNode(index).equals(mNode)) {
				
				EXm+=this.G.get_k().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(
						(xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0])
						-
						(this.G.get_l().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0]))
						/
						Math.sqrt(Math.pow((xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0]), 2)+Math.pow((ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]), 2))
						);
			}
			
			
		}
		
		
		
		return EXm;
	}
	
	
	/**
	 * mNode의 y에 대한 E의 편미분값을 반환 
	 * @param mNode
	 * @return 편미분값 EYm
	 */
	public Double getEYm(Node mNode) {
		
		double[] xyArray=this.G.get_pos().get(mNode.getId());
		
		double xm=xyArray[0];
		double ym=xyArray[1];
		
		
		Double EYm=0.0;
		
		for(int index=0;index<this.G.getGraph().getNodeCount();index++) {
			
			if(!this.G.getGraph().getNode(index).equals(mNode)) {
				
				EYm+=this.G.get_k().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(
						(ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1])
						-
						(this.G.get_l().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]))
						/
						Math.sqrt(Math.pow((xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0]), 2)+Math.pow((ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]), 2))
						);
			}
			
			
		}
		
		
		
		return EYm;
	}
	
	/**
	 * compute and return delta value 
	 * 
	 * @param EXm
	 * @param EYm
	 * @return
	 */
	public Double computeDeltaValue(double EXm,double EYm) {
		
		return Math.sqrt(EXm*EXm+EYm*EYm);
	}
	
	/**
	 * compute and return delta value HashMap about graph
	 * @return delta value HashMap
	 */
	public HashMap<String,Double> compute_M(){
		
		
		HashMap<String,Double> M=new HashMap<String,Double>();
		
		double deltaValue;

		for(int index=0;index<this.G.getGraph().getNodeCount();index++) {
			deltaValue=computeDeltaValue(getEXm(this.G.getGraph().getNode(index)),getEYm(this.G.getGraph().getNode(index)));
			M.put(this.G.getGraph().getNode(index).getId(),deltaValue);
		}
		
		return M;
		
	}
	
	
	/**
	 * compute and return value of the E2Xm2
	 * 
	 * 
	 * @param mNode
	 * @return value of the E2Xm2
	 */
	public Double getE2Xm2(Node mNode) {
		
		double[] xyArray=this.G.get_pos().get(mNode.getId());
		
		double xm=xyArray[0];
		double ym=xyArray[1];
		
		double E2Xm2=0.0;
		
		for(int index=0;index<this.G.getGraph().getNodeCount();index++) {
			
			if(!this.G.getGraph().getNode(index).equals(mNode)) {
				
				
				E2Xm2+=this.G.get_k().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(
						
						1
						-
						(this.G.get_l().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*Math.pow(ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1],2))
						/
						Math.pow(Math.pow((xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0]), 2)+Math.pow((ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]), 2), 1.5)
						
						);
						
				
			}
			
		}
		return E2Xm2;
	}
	
	/**
	 * compute and return value of the E2Ym2
	 * 
	 * @param mNode
	 * @return value of the E2Ym2
	 */
	public Double getE2Ym2(Node mNode) {
		
		double[] xyArray=this.G.get_pos().get(mNode.getId());
		
		double xm=xyArray[0];
		double ym=xyArray[1];
		
		double E2Ym2=0.0;
		
		for(int index=0;index<this.G.getGraph().getNodeCount();index++) {
			
			if(!this.G.getGraph().getNode(index).equals(mNode)) {
				
				
				E2Ym2+=this.G.get_k().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(
						
						1
						-
						(this.G.get_l().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*Math.pow(xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0],2))
						/
						Math.pow(Math.pow((xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0]), 2)+Math.pow((ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]), 2), 1.5)
						
						);
						
				
			}
			
		}
		return E2Ym2;
	}
	
	/**
	 * compute and return value of the E2XmYm
	 * @param mNode
	 * @return value of the E2XmYm
	 */
	public Double getE2XmYm(Node mNode) {
		
		double[] xyArray=this.G.get_pos().get(mNode.getId());
		
		double xm=xyArray[0];
		double ym=xyArray[1];
		
		double E2XmYm=0.0;
		
		for(int index=0;index<this.G.getGraph().getNodeCount();index++) {
			
			if(!this.G.getGraph().getNode(index).equals(mNode)) {
				
				
				E2XmYm+=this.G.get_k().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(
						
						(this.G.get_l().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0])*(ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]))
						/
						Math.pow(Math.pow((xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0]), 2)+Math.pow((ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]), 2), 1.5)
						
						);
						
				
			}
			
		}
		return E2XmYm;
	}
	
	/**
	 * compute and return value of the E2YmXm
	 * @param mNode
	 * @return value of the E2YmXm
	 */
	public Double getE2YmXm(Node mNode) {
		
		double[] xyArray=this.G.get_pos().get(mNode.getId());
		
		double xm=xyArray[0];
		double ym=xyArray[1];
		
		double E2YmXm=0.0;
		
		for(int index=0;index<this.G.getGraph().getNodeCount();index++) {
			
			if(!this.G.getGraph().getNode(index).equals(mNode)) {
				
				
				E2YmXm+=this.G.get_k().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(
						
						(this.G.get_l().get(mNode.getId()).get(this.G.getGraph().getNode(index).getId())*(xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0])*(ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]))
						/
						Math.pow(Math.pow((xm-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[0]), 2)+Math.pow((ym-this.G.get_pos().get(this.G.getGraph().getNode(index).getId())[1]), 2), 1.5)
						
						);
						
				
			}
			
		}
		return E2YmXm;
	}
	
	/**
	 * compute and update position
	 * 
	 * @param updateNode
	 * @param EXm
	 * @param EYm
	 * @param E2Xm2
	 * @param E2Ym2
	 * @param E2XmYm
	 * @param E2YmXm
	 */
	public void updatePosition(Node updateNode,double EXm,double EYm,double E2Xm2,double E2Ym2,double E2XmYm,double E2YmXm) {
		
		
		
		
		//compute update value of the x,y position
		double updateValueX=(EYm*E2XmYm/E2Ym2-EXm)/(E2Xm2-E2YmXm*E2XmYm/E2Ym2);
		double updateValueY=(EYm*E2Xm2/E2YmXm)/(E2XmYm-E2Ym2*E2Xm2/E2YmXm);
		
		//update x,y position
		
		double[] xyArray=this.G.get_pos().get(updateNode.getId());
		
		double xm=xyArray[0];
		double ym=xyArray[1];
		
		this.G.getGraph().getNode(updateNode.getId()).setAttribute("xy", xm+updateValueX,ym+updateValueY);
		
		//update pos 
		xyArray[0]+=updateValueX;
		xyArray[1]+=updateValueY;
		
		System.out.println("node : "+updateNode.getId()+" updated x = "+xyArray[0]+" y = "+xyArray[1]);
		System.out.println();
		System.out.println();
		
		
	}
	
	
	/**
	 * kamada kawai algorithm
	 */
	public void updateAlgorithm() {
		
		////Kamada Kawai Algorithm
		//필요조건
		//display width = 디스플레이 평면의 한 변의 길이
		//K = spring 강도 상수
		//hyperE = 종료 조건 값 
		
		
		//1. compute l,k
		this.G.set_l(get_l());
		this.G.set_k(get_k());
		
		//2. initialize position
		this.G.update_pos();
		
		//3. 반복 update
		
		//초기 설정 
		this.G.set_M(this.compute_M());
		
		double maxM=Collections.max(this.G.get_M().values());
		
		//max_M이 hyperE보다 작아질 때 까지 반복 
		while(maxM>this.hyperE) {
			
			System.out.println("###update maxM value = "+maxM+" ###");
			System.out.println();
			System.out.println();
			
			Node updateNode=null;
			
			@SuppressWarnings("rawtypes")
			Iterator keySetIterator=this.G.get_M().keySet().iterator();
			
			//maxM값을 가지고 있는 노드 찾기 -> update 될 노드 
			while(keySetIterator.hasNext()) {
				String key=keySetIterator.next().toString();
				if(maxM==this.G.get_M().get(key)) {
					updateNode=this.G.getGraph().getNode(key);
					break;
				}
			}
			
			double M=maxM;
			
			//setting EXm,EYm,E2Xm2,E2Ym2,E2XmYm,E2YmXm
			double EXm=this.getEXm(updateNode);
			double EYm=this.getEYm(updateNode);
			double E2Xm2=this.getE2Xm2(updateNode);
			double E2Ym2=this.getE2Ym2(updateNode);
			double E2XmYm=this.getE2XmYm(updateNode);
			double E2YmXm=this.getE2YmXm(updateNode);
			
			//조건 만족할 때 까지 x,y update
			while(M>hyperE) {
				
				
				

				
				
				System.out.println("update node = "+updateNode.getId()+" M value : "+M);
				
				
				
				
				
				//update position
				updatePosition(updateNode,EXm,EYm,E2Xm2,E2Ym2,E2XmYm,E2YmXm);
				
				
				//update EXm,EYm,E2Xm2,E2Ym2,E2XmYm,E2YmXm
				EXm=this.getEXm(updateNode);
				EYm=this.getEYm(updateNode);
				E2Xm2=this.getE2Xm2(updateNode);
				E2Ym2=this.getE2Ym2(updateNode);
				E2XmYm=this.getE2XmYm(updateNode);
				E2YmXm=this.getE2YmXm(updateNode);
				
				//update M
				M=computeDeltaValue(EXm,EYm);
			}
			
			
			//update된 G에 대한 M값과 max M값 setting
			this.G.set_M(this.compute_M());
			maxM=Collections.max(this.G.get_M().values());
			
		}
		
		
		
	}
	
	
	
	
}
