import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prim2 {
	
	public static void main(String arg[]) throws FileNotFoundException{
		
		
		Scanner in;
		if (arg.length > 0) {
			File inputFile = new File(arg[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		Graph graph = Graph.readGraph(in, false);

		
		Prim2.findMST(graph);
		
	}
	
	/*
	 * *  This method accepts an Undirected graph as input and returns the weight
	 * of the minimum spanning tree by using Prim's MST algorithm
	 * 
	 *  This function finds the MST using Prim's algorithm and indexed priority queue (Prim2)
	 *  Indexed Minimum Binary Heap is formed of Vertex and Vertex implements comparable to compare the elements of PQ
	 *  For each iteration, distance variable of a vertex is updated such that distance always shows the  
	 *  minimum edge weight from some Tree vertex to that vertex.
	 *  
	 *  Here, by using indexed priority queue we can decrease key of an element of PQ in logn time.
	 *  
	 *  
	 *  Time Complexity : O(E logV)
	 
	 * 
	 * @param g: The graph for which MST weight has to be found
	 * 
	 * 
	 */
	public static void findMST(Graph graph){
		
		long wmst = 0;
		IndexedPriorityQueue hp = new IndexedPriorityQueue(graph);
        
        Vertex source = graph.verts.get(1);
        source.parent = null;
        
        // reduce the source's distance to 0
        hp.decreaseKey(source, 0);
        
        while(!hp.isEmpty()){
        	
        	source = hp.removeMin();
        	source.seen = true;
        	wmst = wmst + source.distance;
        	
        	if(source.parent!=null)
        		System.out.println("("+source.parent.name+","+source.name+")");
        	
        	for(Edge e : source.Adj){
        		Vertex current = e.otherEnd(source);
        		
        		// Update distance if edge is smaller than previous distance
        		if(!current.seen){
	        		if(current.distance>e.Weight){
		        		current.distance = e.Weight;
		        		current.parent = source;
		        		hp.decreaseKey(current, e.Weight);
		        	}
        		}
        	}
        	
        }
        
        System.out.println("Min Weight "+wmst+"\n");
        
		
	}

}
