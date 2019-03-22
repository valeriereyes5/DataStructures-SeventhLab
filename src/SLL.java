import java.util.ArrayList;
import java.util.Queue;

public class SLL<E> {
	private static class Node<E> {
		private E element; 
		private Node<E> next;
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
		public Node(E element) { this(element, null); } 
		public Node() { this(null, null); }
		public E getElement() { return element; }
		public void setElement(E element) { this.element = element; }
		public Node<E> getNext() { return next; }
		public void setNext(Node<E> next) { this.next = next; }	
	}

	private Node<E> first = null; 
	private int size = 0; 	
	public void addFirst(E e) { 
		first = new Node<>(e, first); 
		size++; 
	}

	// returns string formed by elements in this list, separated by spaces
	public String toString() { 
		String s = ""; 
		Node<E> current = first; 
		while (current != null) { 
			s += " " + current.getElement(); 
			current = current.getNext(); 
		}
		return s; 
	}	

	private Pair<Node<E>> recReverst(Node<E> first) {

		if(first.getNext()==null) {
			return new  Pair<Node<E>>(first, first);
		}
		else {
			Pair<Node<E>> pair = recReverst(first.getNext());
			pair.second().setNext(first);
			first.setNext(null);
			return  new  Pair<Node<E>>(pair.first(),first);
		}
	}

	public void reverse() { 
		if (size > 1) 
			first = recReverst(first).first(); 
	} 

	public ArrayList<Pair<E>> consecutiveIncreasingPairs(){
		ArrayList<Pair<E>> result = new ArrayList<>();     // and empty ArrayList object
		if (size > 0) 
			recCIP(first, result); 
		return result; 

	}

	private void recCIP(Node<E> first2, ArrayList<Pair<E>> result) {
		// TODO Auto-generated method stub

		if(first2!=null || first2.getNext()!=null) {

			if (((Comparable<E>) first.getElement()).compareTo(first.getNext().getElement()) < 0) {
				Pair<E> pair = new Pair(first.getElement(), first.getNext().getElement());
				result.add(pair);
				recCIP(first2.getNext(),result);
			}
		}

	}




	public void	sort(ArrayList<E> a) { //srts the elements in q in non-decreasing order
		if (a.size() > 1) {     // if size is <= 1, then nothing needs to be done
			ArrayList<E> a1, a2;
			// Initialize q1 and q2 to empty instances of  ArrayQueue as implemented in previous lab.
			a1 = new ArrayList<E>();
			a2 = new ArrayList<E>(); 

			// split the elements of q in two halves (or close to), first half into q1 and second half into q2
			int n = a.size(); 
			for (int i=0; i<n/2; i++) 
				a1.add(a.remove(0));
			; 
			while (!a.isEmpty())
				a2.add(a.remove(0));

			sort(a1);    // recursively sort q1
			sort(a2);    // recursively sort q2

			// What is left to do now is the merging operation. Given that q1 and q2 are each sorted, 
			// efficiently combine is elements back into q so that they are placed in order from first to last. 
			// This process efficiently exploits the property that both, q1 and q2, are sorted.
			while (!a1.isEmpty() && !a2.isEmpty()) {
				if ( ((Comparable<E>) a1.get(0)).compareTo(a2.get(0))<=0){
					a.add(a1.remove(0));
				}
				else {
					a.add(a2.remove(0));
				}

			}
			// At this moment, one of the two queues, either q1 or q2, is empty.
			ArrayList<E>r = (!a1.isEmpty() ? a1 : a2);  // find which, q1 or q2, is not empty yet


			while (!r.isEmpty()) {
				a.add(r.remove(0));
			}
		} 
	}

}
