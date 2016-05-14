import java.util.Iterator;

public class IndexedPriorityQueue {

	/*
	 *  This class has implementation for indexed Priority Queue
	 *  Here PQ is of Vertices and distance is used to compare the vertices.
	 *  Vertex.distance saves the distance of that vertex from its parent vertex.
	 *  We update the distance such that it is always the minimum 
	 *  Vertex.index saves the inde of that vertex in that priority queue
	 */
	Vertex[] pq;
	int counter;
	int size;

	IndexedPriorityQueue(Graph g){

		size = g.verts.size();
		pq = new Vertex[size];
		counter = 0;

		Iterator<Vertex> it = g.iterator();
		while(it.hasNext()){
			Vertex v = it.next();
			v.isInfinite = true;
			this.insert(v);
			//System.out.println(v.name);
		}

	}

	public void insert(Vertex v){

		pq[counter] = v;
		pq[counter].putIndex(counter);

		int child = counter;
		int parent = (child-1)/2;

		// Heapify or Percolating Up
		while(parent>=0){

			//if(pq[child].distance<pq[parent].distance){
			if(v.compare(pq[child], pq[parent])<0){
				swap(child,parent);
				child = parent;
				parent = (child-1)/2;
			}
			else{
				break;
			}
		}

		counter++;
	}

	public Vertex removeMin(){
		// if there is no element in the heap
		if(counter == 0)
		{
			return null;
		}
		
		Vertex min = pq[0];
		swap(0,counter-1);
		counter--;
		int parent = 0;
		
		// Percolating Down
		while(2*parent+1 < counter) {
            
			int child = 2*parent+1;

            //find the minimum from the left and right child
			
            if(child < counter-1 && min.compare(pq[child], pq[child + 1]) > 0)
            	child++;

            //if the child is less than parent, then swap them and update index
            if(min.compare(pq[child], pq[parent]) < 0) 
                    swap(parent,child);
            else break;

            parent = child;
		}

		return min;
	}
	
	public void decreaseKey(Vertex v, int newDistance){
		
		// get index of the element which shows us where this element is in the PQ
		// Then we decrease its Key and Percolate It up to its correct position.
		int currentIndex = v.getIndex();
		pq[currentIndex].distance = newDistance;
		
		int child = currentIndex;
		int parent = (child-1)/2;

		// Heapify or Percolating Up
		while(parent>=0){

			//if(pq[child].distance<pq[parent].distance){
			if(v.compare(pq[child], pq[parent])<0){
				swap(child,parent);
				child = parent;
				parent = (child-1)/2;
			}
			else{
				break;
			}
		}
		
	}
	private void swap(int i,int j){
		Vertex temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
		
		// Update Index every time we swap an element. 
		// i.e every time we change the position of an element.
		//its corresponding index has to be changed
		pq[i].putIndex(i);
		pq[j].putIndex(j);
	}
	
	

	public boolean isEmpty(){
		if(counter == 0){
			return true;
		}
		return false;
	}

}
