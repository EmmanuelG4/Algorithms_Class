import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class MyGraph {
    
    private ArrayList<ArrayList<Link>> edges; // a list of lists is declared for the edges
    private Map<Integer, ArrayList<Link>> adj; // and a map is declared for an adjacency
    										   // list representation of the graph.
    
    // Create an empty graph with n vertices and no edges
    //
    // The MyGraph constructor initializes the edges list and adj map as empty. Using
    // a for loop, the vertices of the graph are added to adj and edges based on the 
    // parameter n. 
    public MyGraph(int n) {
        edges = new ArrayList<>();
        adj = new HashMap<>();
        for(int i = 0; i < n; i++) {
        	edges.add(new ArrayList<>());
        	adj.put(i, new ArrayList<>());
        }

	}

    // Copy g into the new graph
    //
    // The copy constructor creates a new graph based on the attributes of the MyGraph
    // object g. The edges list and adj map are initialized as empty and a for loop is
    // used to cary over the edges from g. A second for loop is used to carry over the
    // the keys and values from the adj map of g. 
    public MyGraph(MyGraph g) {
    	edges = new ArrayList<>();
        adj = new HashMap<>();
        for(int i = 0; i < g.edges.size(); i++) {
        	ArrayList<Link> edgesList = new ArrayList<>(g.edges.get(i - 1)); 
        	edges.add(edgesList);
        }
        
        for(Map.Entry<Integer, ArrayList<Link>> entry : g.adj.entrySet()) {
        	adj.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    }

    // add an edge a->b with weight w to the graph
    //
    // The addEdge function adds an edge to the graph based on the vertices and weight
    // given. The function creates a new Link object containing the parameters, and the
    // Link object is added to both the adj map and the edges list. The Link object is 
    // added for both of the passed vertices to ensure edge consistency.
    public boolean addEdge(int a, int b, float w) {
    	if(a < 0 || a >= edges.size() || b < 0 || b >= edges.size()) {
    		return false; // If the vertices passed to the function are not valid, 
    					  // the function returns false and does nothing. 
    	}
    	
    	for(Link edge : adj.get(a)){
    		if((edge.v1 == a && edge.v2 == b) || (edge.v1 == b && edge.v2 == a)){
    			return false; // If the edge is already in the graph, the function 
    						  // returns false and does nothing. 
    	    }
    	}

    	Link addedEdge = new Link();
    	addedEdge.v1 = a;
    	addedEdge.v2 = b;
    	addedEdge.w = w;
    	
    	adj.get(a).add(addedEdge);
    	adj.get(b).add(addedEdge);
    	edges.get(a).add(addedEdge);
    	edges.get(b).add(addedEdge);
    	
        return true;
	}


    // return MST of a graph
    //
    // The MST function returns a minimum spanning tree of the graph as an ArrayList.
    // The mst is calculated using Prim's Algorithm. Three arrays are declared: minNeighbor,
    // minWeight, and selected. minNeighbor keeps track of vertices closest to unselected 
    // vertices, minWeight keeps track of the weights of edges between selected and  
    // unselected vertices, and selected keeps track of which edges have already been 
    // selected for the mst. The three arrays are initialized to correspond with the first 
    // vertex in the graph and to indicate the beginning of the mst. Two for loops are
    // used to choose a vertex that has not been selected and has the lowest-weighted edge.
    // This edge is added to the mst and the vertex is marked as selected. A for loop is
    // used to check the weight of every edge connected to the selected vertex, and if there
    // is an unselected edge with a smaller weight, minWeight is updated with this edge and
    // minNeighbor is updated with the current selected vertex.
    public ArrayList<Link>  MST() {
    	int n = edges.size();
    	int count;
    	
    	int[] minNeighbor = new int[n + 1];
    	float[] minWeight = new float[n + 1];
    	boolean[] selected = new boolean[n + 1];
    	
    	ArrayList<Link> res = new ArrayList<>();
    	Arrays.fill(minWeight, Float.MAX_VALUE);
    	Arrays.fill(minNeighbor, -1);
    	minWeight[0] = 0;
    	
    	
    	for (count = 0; count < n; count++) {
	    	float min = Float.MAX_VALUE; 
	    	int minindex = -1;
	    	for (int i = 0; i < n; i++) {
		    	if (!selected[i] && (minWeight[i] < min)) {
			    	min = minWeight[i];
			    	minindex = i;
		    	}
	    	}
	    	
	    	if(minindex == -1) { // validates the vertex with the smallest weight.
	    		break;
	    	}
	    	
	    	if(minNeighbor[minindex] >= 0 && minindex >= 0) { // validates the two vertices
	    													  // to be added to the mst.
	    		res.add(new Link(minNeighbor[minindex] + 1, minindex + 1, min));
	    	}
	    	
	    	selected[minindex] = true;
	    	for (Link edge : adj.get(minindex)) {
	    		int neighbor;
	    		if(edge.v1 == minindex) {
	    			neighbor = edge.v2;
	    		}
	    		else {
	    			neighbor = edge.v1;
	    		}
	    		
		    	if (!selected[neighbor] && edge.w < minWeight[neighbor]) {
			    	minWeight[neighbor] = edge.w;
			    	minNeighbor[neighbor] = minindex;
		    	}
	    	}
    	}
    	
    	return res; 
    }

    // The output function outputs the graph, as well as its size, using 2 for loops.  
    // 2 vertices are taken from a list of edges and a set is used to keep track of
    // the edges that have already been printed. If an edge has not been printed, the  
    // smaller vertex of the edge is output, followed by the larger vertex and the 
    // edge's weight. 
    public void output() {
    	System.out.println(edges.size());
    	
    	Set<String> printed = new HashSet<>();
    	
    	for(int i = 0; i < edges.size(); i++) {
    		for(Link edge : edges.get(i)) {
    			int v1 = Math.min(edge.v1, edge.v2) + 1;
    			int v2 = Math.max(edge.v1, edge.v2) + 1;
    			
    			String key = v1 + " " + v2;
    			if(!printed.contains(key)) {
    				if(v1 < v2) {
    					System.out.println(v1 + " " + v2 + " " + edge.w);
    				}
    				else {
    					System.out.println(v2 + " " + v1 + " " + edge.w);
    				}
    				printed.add(key);
    			}
    		}
    	}
    }
}

