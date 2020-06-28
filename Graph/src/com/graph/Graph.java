package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Graph<V> {
	
	private List<Vertex<V>> vertices;
	private Map<Vertex<V> , List<Vertex<V>>> adjList = new HashMap<>();
	public void addEdge(Vertex<V> source , Vertex<V> dest){
		List<Vertex<V>> adj = adjList.get(source);
		if(adj == null){
			adj = new ArrayList<>();
		}
		adj.add(dest);
		adjList.put(source, adj);
		
		List<Vertex<V>> adj1 = adjList.get(dest);
		if(adj1 == null){
			adj1 = new ArrayList<>();
		}
		adj1.add(source);
		adjList.put(dest, adj1);
	}
	
	public void addVertices(Vertex<V> v){
		if(this.vertices == null){
			this.vertices = new ArrayList<>();
		}
		this.vertices.add(v);
	}
	public List<Vertex<V>> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vertex<V>> vertices) {
		this.vertices = vertices;
	}
	public Map<Vertex<V>, List<Vertex<V>>> getAdjList() {
		return adjList;
	}
	public void setAdjList(Map<Vertex<V>, List<Vertex<V>>> adjList) {
		this.adjList = adjList;
	}
	public void depthFirstSearchRecurive(){
		if(this.vertices != null){
			
			for(Vertex<V> v : vertices){
				if(!v.isVisited){
					dfsUtil(v);
				}
			}
			
		}
	}
	public void dfsUtil(Vertex<V> v){
		v.isVisited = true;
		System.out.println(v);
		List<Vertex<V>> adj = adjList.get(v);
		if(adj != null){
			
			for(Vertex<V> vertex: adj){
				if(!vertex.isVisited){
					dfsUtil(vertex);
				}
			}
			
		}
		
	}
	public void depthFirstSearchIterative(){
		if(this.vertices != null){
			Stack<Vertex<V>> stack = new Stack<>();
			for(int i = 0;i<this.vertices.size();i++){
				if(!this.vertices.get(i).isVisited){
					stack.push(this.vertices.get(i));
					while(!stack.isEmpty()){
						Vertex<V> v = stack.pop();
						if(!v.isVisited){
							v.isVisited = true;
							System.out.println(v);
						}
						List<Vertex<V>> adj = adjList.get(v);
						for(Vertex<V> v1 : adj){
							if(!v1.isVisited)
								stack.push(v1);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Vertex<V> findMotherVertex(){
		Vertex<V> mother = null;
		
		for(int i=0;i<this.vertices.size();i++){
			if(!this.vertices.get(i).isVisited){
				dfsUtil(this.vertices.get(i));
				mother = this.vertices.get(i);
			}
		}
		this.vertices.stream().forEach(v->{
			v.isVisited = false;
		});
		dfsUtil(mother);
		
		for(int i = 0;i<this.vertices.size();i++){
			if(!this.vertices.get(i).isVisited){
				return null;
			}
		}
		
		return mother;
	}
	
	
	public static void main(String[] args) {
		Vertex<Character> vA = new Vertex<>('A');
		Vertex<Character> vB = new Vertex<>('B');
		Vertex<Character> vC = new Vertex<>('C');
		Vertex<Character> vD = new Vertex<>('D');
		Vertex<Character> vE = new Vertex<>('E');
		Vertex<Character> vF = new Vertex<>('F');
		Graph<Character> graph = new Graph();
		graph.addVertices(vA);
		graph.addVertices(vB);
		graph.addVertices(vC);
		graph.addVertices(vD);
		graph.addVertices(vE);
		graph.addVertices(vF);
		graph.addEdge(vA, vB);
		graph.addEdge(vA, vD);
		graph.addEdge(vA, vC);
		graph.addEdge(vB, vD);
		graph.addEdge(vD, vE);
		graph.addEdge(vB, vF);
		graph.depthFirstSearchRecurive();
		//graph.depthFirstSearchIterative();
	}
	
}
