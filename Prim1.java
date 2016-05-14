
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prim1 {
	
	
	// Driver function to find MST of a graph
	// Graph is read as input from the console
	public static void main(String arg[]) throws FileNotFoundException{
		
		
		Scanner in;
		if (arg.length > 0) {
			File inputFile = new File(arg[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		Graph graph = Graph.readGraph(in, false);

		
		int minWeight = Prim1.MST(graph);
		System.out.println("Minimum Weight : "+minWeight);
		
	}
	
	
	/*
	 * * *  This method accepts an Undirected graph as input and returns the weight
	 * of the minimum spanning tree by using Prim's MST algorithm
	 * 
	 *  This function finds the MST using Prim's algorithm and priority queue (Prim1)
	 *  
	 *  Each vertex is observed and its adjacent edges are added to the PQ
	 *  Everytime minimum edge is popped out of the PQ and checked whether its vertices are
	 *  already seen or not
	 *  if both are seen then that edge is ignored
	 *  if one of them is a Tree Vertex and one of them is a non tree vertex then that edge is
	 *  added to the MST and non tree vertex is marked as seen.
	 *  
	 */
	static int MST(Graph g) {
		int wmst = 0;
		Vertex src = g.verts.get(1);
		src.seen = true;
		Edge e1 = new Edge(src, src, 0);
		PriorityQueue<Edge> pQueue = new PriorityQueue<>(g.edges, e1);
		for (Edge e : src.Adj) {
			pQueue.add(e);

		}

		while (!pQueue.isEmpty()) {

			Edge e = pQueue.remove();
			//Vertex temp=e.From;
			Vertex u=e.From,v=e.To;
			if (e.From.seen && e.To.seen) {
				continue;
			} else {
				if (e.From.seen) {
					u=e.From;
					v=e.To;
				} else if(e.To.seen){
					u=e.To;
					v=e.From;
				}
					
				v.parent=u;
				System.out.println("("+u.name+","+v.name+")");
				wmst = wmst + e.Weight;
				v.seen=true;
				
				for (Edge f : v.Adj) {
					Vertex w = f.otherEnd(v);
					if (!w.seen) {
						pQueue.add(f);
					}
				}
			}
			
		}

		return wmst;
	}

}
