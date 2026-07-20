import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.io.*;

public class Prog2task {
    
	// The Task1 function creates a graph and returns a minimum spanning tree of this 
	// graph. An object of the MyGraph class with a size of n is created, and a for loop 
	// is used to add all the edges from the pipes ArrayList to the graph. The MST()
	// function is called to create the minimum spanning tree and linkHelper from the
	// MyHelper class is used to create a copy of the mst. 
    public static ArrayList<Link> Task1(int n, ArrayList<Link> pipes, MyHelper helper) {
    	MyGraph graph = new MyGraph(n);
    	for(int i = 0; i < pipes.size(); i++) {
    		int a = pipes.get(i).v1 - 1;
    		int b = pipes.get(i).v2 - 1;
    		float w = pipes.get(i).w;
    		graph.addEdge(a, b, w);
    	}
    	ArrayList<Link> task1MST = graph.MST();

    	helper.linkHelper.addAll(task1MST);
    	
    	return task1MST;
     }

    // The function Task2 takes a new edge and determines whether or not it should replace
    // an existing edge in the graph, and if so, which edge. The function returns either an
    // ArrayList containing the edge to be replaced by the new edge, or an empty list
    // if the new edge will not replace an existing edge. A new map labeled adj2 is declared
    // and defined using variable n to represent the number of vertices in the graph. A for 
    // loop is used to add all the edges from the copied mst in linkHelper to the adj2 map.
    // The function uses depth-first search to determine if the new edge creates a cycle 
    // within the mst, and uses the set visited to keep track of the vertices visited during
    // dfs. Three variables labeled heaviest, maxW, and found are declared for dfs, with
    // heaviest storing the heaviest edge, maxW storing the weight of the heaviest edge, and
    // found representing whether or not a cycle has been found. A map labeled edgeTP is
    // declared to keep track of edges and their respective parents during dfs. A stack is
    // used to perform depth-first search traversal and a while loop is used to iterate
    // through the stack until a cycle is found. Once a cycle is found, the cycle is traced
    // back in order to determine the heaviest edge. If the heaviest edge's weight is
    // greater than the new pipe's weight, then the heaviest edge is added to an ArrayList
    // and returned by the function. 
    public static ArrayList<Link> Task2(int n, ArrayList<Link> pipes, Link newPipe, MyHelper helper) {
    	Map<Integer, ArrayList<Link>> adj2 = new HashMap<>();
    	for(int i = 1; i <= n; i++) {
    		adj2.put(i, new ArrayList<>());
    	}
    	
    	for(Link edge : helper.linkHelper) {
    		adj2.get(edge.v1()).add(edge);
    		adj2.get(edge.v2()).add(edge);
    	}
    	
    	Set<Integer> visited = new HashSet<>();
    	Link heaviest = null;
    	float maxW = 0.0f;
    	boolean found = false; 
    	
    	LinkedList<Integer> stack = new LinkedList<>();
    	Map<Integer, Link> edgeTP = new HashMap<>();
    	
    	stack.push(newPipe.v1());
    	visited.add(newPipe.v1());
    	
    	while(!stack.isEmpty() && !found) { // Traverses the stack until a cycle is found
    										// or the stack is empty. 
    		int current = stack.pop();
    		if(current == newPipe.v2()) { // If the current vertex is the new pipe's second
    									  // vertex, then a cycle has been found.
    			found = true;
    			break;
    		}
    		for(Link edge : adj2.get(current)) { 
    			int next;
        		if(edge.v1() == current) {
        			next = edge.v2();
        		}
        		else {
        			next = edge.v1();
        		}
        		
        		if(!visited.contains(next)) { // If the next vertex has not been visited,
        									  // then the vertex is added to the visited set,
        									  // pushed onto the stack, and its path is added
        									  // to the edgeTP map.
        			visited.add(next);
        			stack.push(next);
        			edgeTP.put(next, edge);
        		}
        	}
    	}
    	
    	if(found) { // A while loop and the edgeTP map are used to go back through
    				// the cycle from the new pipe's second vertex to the new pipe's 
    				// first vertex and find the heaviest edge. 
    		int current = newPipe.v2();
    		while(current != newPipe.v1()) {
    			Link edge = edgeTP.get(current);
    			if(edge.w() > maxW) {
    				maxW = edge.w();
    				heaviest = edge;
    			}
    			if(edge.v1() == current) {
    				current = edge.v2();
    			}
    			else {
    				current = edge.v1();
    			}
    		}
    	}
    	
    	ArrayList<Link> res = new ArrayList<>();
    	if(found && newPipe.w() < maxW) { 
    		for(Link edge : helper.linkHelper) { // a for loop is used to iterate through
    											 // every edge in the copied mst and if an
    											 // edge matches the cycle's heaviest edge,
    											 // then the edge is added to the res 
    											 // ArrayList.
    			if(edge == heaviest) {
    				res.add(edge);
    				break;
    			}
    		}
    	}

	return res;

     }

}

