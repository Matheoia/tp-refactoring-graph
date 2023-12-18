package org.acme.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.graph.routing.DijkstraPathFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathTree {

	private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);

	public Map<Vertex, PathNode> nodes = new HashMap<>();
	
	public PathTree(Graph graph, Vertex origin){
		
		this.nodes = new HashMap<>();
		
		log.trace("initGraph({})", origin);
		for (Vertex vertex : graph.getVertices()) {
			PathNode pathNode = new PathNode();	
			pathNode.setCost(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY);
			pathNode.setReachingEdge(null);
			pathNode.setVisited(false);
			nodes.put(vertex, pathNode);
		}
	}
	
	public PathNode getNode(Vertex vertex) {
		return nodes.get(vertex);
	}
	
	
	public Path getPath(Vertex destination) {
		
		List<Edge> result = new ArrayList<>();

		Edge current = getNode(destination).getReachingEdge();
		do {
			result.add(current);
			current = getNode(current.getSource()).getReachingEdge();
		} while (current != null);

		Collections.reverse(result);
		return new Path(result);
	}
	


	
	
	
	
}
