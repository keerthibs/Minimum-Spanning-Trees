

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
Driver program for Kruskals MST. Pass an undirected graph as an input to the FindMST method
of the Kruskal class.
*/public class Driver_Krushkal {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		Graph g = Graph.readGraph(in, false);
		Kruskal.findMST(g);

	}

}
