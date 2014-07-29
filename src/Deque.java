import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int N;
	private Node first;
	private Node last;
	
	private class Node {
		private Item item;
		private Node next;
		private Node previous;
	}
	
	public Deque() {
		N = 0;
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void addFirst (Item item) {
		if (item == null) throw new NullPointerException();
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		first.previous = null;
		if (N == 0) {
			last = first;
		}
		else {
			oldfirst.previous = first;
		}
		N++;
	}
	
	public void addLast (Item item) {
		if (item == null) throw new NullPointerException();
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (N == 0) {
			first = last;
		}
		else {
			oldlast.next = last;
			last.previous = oldlast;
		}
		N++;
		
	}
	
	public Item removeFirst () {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		Node second = first.next;
		first = second;
		if (N == 1) last = null;
		else
			second.previous = null;		
		N--;
		return item;
	}
	
	public Item removeLast () {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = last.item;
		Node secondLast = last.previous;
		last = secondLast;
		if (N == 1) first = null;
		else
			secondLast.next = null;
		N--;
		return item;
	}
		
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item> {
		
		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext()) 
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}		
	}
	public static void main(String[] args) {
		Deque<String> dq = new Deque<String>();
		dq.addFirst("first");
		dq.addLast("Last");
		dq.removeFirst();
		dq.removeLast();
		for (String s : dq) {
			StdOut.println(s);
		}
	}
	
}
