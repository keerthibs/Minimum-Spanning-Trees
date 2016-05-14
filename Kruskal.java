

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
* This class implements the code for Kruskals MST algorithm.
*@author : Keerthi Bala Sundram,Aastha Dixit.
*/
public class Kruskal {

	// The forest List stores the list of edges that forms the minimum spanning
	// tree
	static List<Edge> forest = new LinkedList<>();

	/*
	 * This method accepts an Undirected graph as input and returns the weight
	 * of the minimum spanning tree by using Kruskal's MST algorithm
	 * 
	 * @param g: The graph for which MST weight has to be found
	 * 
	 * Note that the below class can be modified to return an MST too. Just return the 
	 * list of edges i.e forest
	 */
	public static void findMST(Graph g) {
		int wmst = 0;
		for (Vertex u : g) {
			MakeSet(u);
		}

		Collections.sort(g.allEdges); //The Edge class must implement Comparable

		for (Edge e : g.allEdges) {
			Vertex u = e.From;
			Vertex v = e.To;
			Vertex ru = Find(u); // representative of u's component
			Vertex rv = Find(v); // representative of v's component

			if (ru != rv) {
				//if u and v belong to different components
				wmst = wmst + e.Weight;
				forest.add(e);
				Union(ru, rv);
			}

		}
		
		System.out.println(wmst);
		for (Edge e : forest) {
			System.out.println(e + " ");
		}

	}

	/*
	Creates a new set whose only member is x
	@ param x : Vertex x for which a new set has to be 
	created
	 */	
	static void MakeSet(Vertex x) {
		x.parent = x;
		x.rank = 0;

	}
/*
* This method finds the representative of the set 
* that contains the given Vertex x. If x and y are 
* in the same set, the both Find for x and y are equal 
* Otherwise they are not equal.
* 
* @param x : Vertex x for which the representative has to be found	
*/
	static Vertex Find(Vertex x) {
		if (x != x.parent) {
			x.parent = Find(x.parent);
		}
		return x.parent;
	}
/*
 * This method replaces the sets of x and y by 
 * Joining them(UNION).
 * @ param x: first input vertex to perform Union
 * @ param y: second input vertex to perform Union 
	
*/	
	static void Union(Vertex x, Vertex y) {
		if (x.rank > y.rank) {
			y.parent = x;
		} else if (y.rank > x.rank) {
			x.parent = y;
		} else {
			x.parent = y;
			y.rank++;
		}
	}
}
