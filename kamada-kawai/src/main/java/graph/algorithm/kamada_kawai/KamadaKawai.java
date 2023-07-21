package graph.algorithm.kamada_kawai;


import java.util.HashMap;
import java.util.Iterator;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class KamadaKawai {
	
	
	private Graph graph;
	
	private Dijkstra dijkstra;
	
	private Double K;
	
	
	
	private Double displayLength;
	
	private HashMap<String,HashMap<String,Double>> l;
	
	private HashMap<String,HashMap<String,Double>> k;
	
	private HashMap<String,Double> delta;
	
	public KamadaKawai(Graph g) {
		
		this.graph=g;
		this.dijkstra=new Dijkstra();
		this.dijkstra.init(this.graph);
	}
	
	/**
	 * set Dijkstra
	 * @param source
	 */
	public void computeDijkstra(Node source) {
		
		this.dijkstra.setSource(source);
 		this.dijkstra.compute();
	}
	
	
	
	/**
	 * set K
	 * K = constant
	 * @param K
	 */
	public void set_K(Double K) {
		this.K=K;
	}
	
	
	/**
	 * set display length
	 * @param displayLength
	 */
	public void set_displayLength(Double displayLength) {
		this.displayLength=displayLength;
	}
	
	/**
	 * return shortest length between source and target in the graph to use Dijkstra
	 * @param target
	 * @return shortest length
	 */
	public Double getShortestPathLength(Node target) {
		return this.dijkstra.getPathLength(target);
	}
	
	

	/**
	 * get delta map
	 * @return delta map
	 */
	public HashMap<String, Double> getDelta() {
		return delta;
	}

	/**
	 * compute and return L value of the graph
	 * L = desirable length of  a single edge
	 * @return L value of the graph
	 */
	public Double compute_L(){
		
		return this.displayLength/Toolkit.diameter(this.graph);
		
	}
	
	/**
	 * compute and return l HashMap of the graph
	 * l = desirable distances between particles
	 * @return l HashMap of the graph
	 */
	public HashMap<String,HashMap<String,Double>> compute_l(){
		
		HashMap<String, HashMap<String, Double>> l=new HashMap<String,HashMap<String,Double>>();
		
		double L=this.compute_L();
		
		Iterator<Node> sourceNodes=this.graph.nodes().iterator();
		Iterator<Node> targetNodes=this.graph.nodes().iterator();

		while(sourceNodes.hasNext()) {

			Node source=sourceNodes.next();
			
			this.computeDijkstra(source);
			
			HashMap<String, Double> targetMap=new HashMap<>();
			
			targetNodes=this.graph.nodes().iterator();
			
			while(targetNodes.hasNext()) {

				Node target=targetNodes.next();

				if(source.getId()!=target.getId()) 
					targetMap.put(target.getId(), L*this.getShortestPathLength(target));
					
				
					
					
			}
			
			l.put(source.getId(), targetMap);
			
			
		}
		
		
		
		
		this.l=l;
		
		return l;
		
	}
	
	/**
	 * compute and return k HashMap of the graph
	 * k = strength of the spring
	 * @return k HashMap of the graph
	 */
	public HashMap<String,HashMap<String,Double>> compute_k(){
		
		HashMap<String, HashMap<String, Double>> k=new HashMap<String,HashMap<String,Double>>();
		
		Iterator<Node> sourceNodes=this.graph.nodes().iterator();
		Iterator<Node> targetNodes=this.graph.nodes().iterator();

		while(sourceNodes.hasNext()) {

			Node source=sourceNodes.next();
			
			this.computeDijkstra(source);
			
			HashMap<String, Double> targetMap=new HashMap<>();
			
			
			targetNodes=this.graph.nodes().iterator();
			
			while(targetNodes.hasNext()) {

				Node target=targetNodes.next();

				if(source.getId()!=target.getId()) 
					targetMap.put(target.getId(),this.K/Math.pow(this.getShortestPathLength(target), 2));
					
				
					
					
			}
			
			k.put(source.getId(), targetMap);
			
			
		}
		

		
		this.k=k;
		
		return k;
		
	}
	
	/**
	 * return EXm
	 * EXm = partial derivative xm of the mNode
	 * @param mNode
	 * @return EXm 
	 */
	public double compute_EXm(Node mNode) {
		
		
		Double EXm=0.0;
		
		double[] coordinate=Toolkit.nodePosition(mNode);
		
		double xm=coordinate[0];
		double ym=coordinate[1];
		
		Iterator<Node> nodes=this.graph.nodes().iterator();
		
	
		
		while(nodes.hasNext()) {
			Node node=nodes.next();
		
			
			double x=Toolkit.nodePosition(node)[0];
			double y=Toolkit.nodePosition(node)[1];
			
			if(mNode.getId()!=node.getId()) {
				
				EXm+=this.k.get(mNode.getId()).get(node.getId())*(
						(xm-x)
						-
						(this.l.get(mNode.getId()).get(node.getId())*(xm-x))
						/
						Math.sqrt(Math.pow(xm-x, 2)+Math.pow(ym-y, 2))	
						);
				
			}
			
			
		}
		
		return EXm;
	}
	
	/**
	 * return EYm
	 * EXm = partial derivative ym of the mNode
	 * @param mNode
	 * @return EYm 
	 */
	public double compute_EYm(Node mNode) {
		Double EYm=0.0;
		
		double[] coordinate=Toolkit.nodePosition(mNode);
		
		double xm=coordinate[0];
		double ym=coordinate[1];
		
		Iterator<Node> nodes=this.graph.nodes().iterator();
		
		while(nodes.hasNext()) {
			Node node=nodes.next();
			
			double x=Toolkit.nodePosition(node)[0];
			double y=Toolkit.nodePosition(node)[1];
			
			if(mNode.getId()!=node.getId()) {
				
				EYm+=this.k.get(mNode.getId()).get(node.getId())*(
						(ym-y)
						-
						(this.l.get(mNode.getId()).get(node.getId())*(ym-y))
						/
						Math.sqrt(Math.pow(xm-x, 2)+Math.pow(ym-y, 2))	
						);
				
			}
			
			
		}
		
		return EYm;	
	}
	
	/**
	 * compute and return value of the E2Xm2
	 * @param mNode
	 * @return value of the E2Xm2
	 */
	public Double compute_E2Xm2(Node mNode) {
		
		Double E2Xm2=0.0;
		
		double[] coordinate=Toolkit.nodePosition(mNode);
		
		double xm=coordinate[0];
		double ym=coordinate[1];
		
		Iterator<Node> nodes=this.graph.nodes().iterator();
		
		while(nodes.hasNext()) {
			Node node=nodes.next();
			
			double x=Toolkit.nodePosition(node)[0];
			double y=Toolkit.nodePosition(node)[1];
			
			if(mNode.getId()!=node.getId()) {
				
				E2Xm2+=this.k.get(mNode.getId()).get(node.getId())*(
						1
						-
						(this.l.get(mNode.getId()).get(node.getId())*Math.pow(ym-y, 2))
						/
						Math.pow(Math.pow(xm-x, 2)+Math.pow(ym-y, 2), 1.5)
						);
			}
			
			
		}
		
		return E2Xm2;
		
	}
	
	/**
	 * compute and return value of the E2Ym2
	 * @param mNode
	 * @return value of the E2Ym2
	 */
	public Double compute_E2Ym2(Node mNode) {
		
		Double E2Ym2=0.0;
		
		double[] coordinate=Toolkit.nodePosition(mNode);
		
		double xm=coordinate[0];
		double ym=coordinate[1];
		
		Iterator<Node> nodes=this.graph.nodes().iterator();
		
		while(nodes.hasNext()) {
			Node node=nodes.next();
			
			double x=Toolkit.nodePosition(node)[0];
			double y=Toolkit.nodePosition(node)[1];
			
			if(mNode.getId()!=node.getId()) {
				
				E2Ym2+=this.k.get(mNode.getId()).get(node.getId())*(
						1
						-
						(this.l.get(mNode.getId()).get(node.getId())*Math.pow(xm-x, 2))
						/
						Math.pow(Math.pow(xm-x, 2)+Math.pow(ym-y, 2), 1.5)
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
	public Double compute_E2XmYm(Node mNode) {
		
		Double E2XmYm=0.0;
		
		double[] coordinate=Toolkit.nodePosition(mNode);
		
		double xm=coordinate[0];
		double ym=coordinate[1];
		
		Iterator<Node> nodes=this.graph.nodes().iterator();
		
		while(nodes.hasNext()) {
			Node node=nodes.next();
			
			double x=Toolkit.nodePosition(node)[0];
			double y=Toolkit.nodePosition(node)[1];
			
			if(mNode.getId()!=node.getId()) {
				
				E2XmYm+=this.k.get(mNode.getId()).get(node.getId())*(
						(this.l.get(mNode.getId()).get(node.getId())*(xm-x)*(ym-y))
						/
						Math.pow(Math.pow(xm-x, 2)+Math.pow(ym-y, 2), 1.5)
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
	public Double compute_E2YmXm(Node mNode) {
		
		Double E2YmXm=0.0;
		
		double[] coordinate=Toolkit.nodePosition(mNode);
		
		double xm=coordinate[0];
		double ym=coordinate[1];
		
		Iterator<Node> nodes=this.graph.nodes().iterator();
		
		while(nodes.hasNext()) {
			Node node=nodes.next();
			
			double x=Toolkit.nodePosition(node)[0];
			double y=Toolkit.nodePosition(node)[1];
			
			if(mNode.getId()!=node.getId()) {
				
				E2YmXm+=this.k.get(mNode.getId()).get(node.getId())*(
						(this.l.get(mNode.getId()).get(node.getId())*(xm-x)*(ym-y))
						/
						Math.pow(Math.pow(xm-x, 2)+Math.pow(ym-y, 2), 1.5)
						);
			}
			
			
		}
		
		return E2YmXm;
		
	}
	
	
	/**
	 * compute and return delta value 
	 * 
	 * delta = amount of change
	 * 
	 * @param EXm
	 * @param EYm
	 * @return delta
	 */
	public Double computeDelta(double EXm,double EYm) {
		
		
		
		return Math.sqrt(EXm*EXm+EYm*EYm);
	}
	
	/**
	 * compute and return delta value HashMap about graph
	 * @return delta map
	 */
	public HashMap<String,Double> compute_delta(){
		
		HashMap<String,Double> delta=new HashMap<String,Double>();
		
		double deltaValue;
		
		Iterator<Node> nodes=this.graph.nodes().iterator();
		
		while(nodes.hasNext()) {
			Node node=nodes.next();
			
			deltaValue=this.computeDelta(this.compute_EXm(node), this.compute_EYm(node));
			
			
			delta.put(node.getId(), deltaValue);
			
			
		}
		
		this.delta=delta;
		
		return delta;
	}
	
	/**
	 * compute and update position
	 * @param mNode
	 */
	public void updatePosition(Node mNode) {
		
		Double EXm=this.compute_EXm(mNode);
		Double EYm=this.compute_EYm(mNode);
		Double E2Xm2=this.compute_E2Xm2(mNode);
		Double E2Ym2=this.compute_E2Ym2(mNode);
		Double E2XmYm=this.compute_E2XmYm(mNode);
		Double E2YmXm=this.compute_E2YmXm(mNode);
		
		Double updateValueX=(EYm*E2XmYm/E2Ym2-EXm)/(E2Xm2-E2YmXm*E2XmYm/E2Ym2);
		Double updateValueY=(EYm*E2Xm2/E2YmXm)/(E2XmYm-E2Ym2*E2Xm2/E2YmXm);
		
		double[] coordinate=Toolkit.nodePosition(mNode);
		
		double xm=coordinate[0];
		double ym=coordinate[1];
		
		this.graph.getNode(mNode.getId()).setAttribute("xy",  xm+updateValueX,ym+updateValueY);
		
		
	}
	
	
	
	

}
