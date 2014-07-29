import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item>{
	
	private Item[] a;
	private int N;
	
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		a = (Item[]) new Object[2];
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	private void resize (int capacity) {
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void enqueue (Item item) {
		if (item == null) throw new NullPointerException();
		if (N == a.length) resize (2 * a.length);
		a[N++] = item;
	}
	
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		int luckyNumber = StdRandom.uniform(0, N);
		Item temp = a[luckyNumber];
		a[luckyNumber] = a[N-1];
		a[N-1] = temp;
		Item item = a[N-1];
		a[N-1] = null;
		N--;
		if(N > 0 && N == a.length/4) resize (a.length/2);
		return item;
	}
	
	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException();
		return a[StdRandom.uniform(0, N)];
	}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {

		private int i;
		@SuppressWarnings("unchecked")
		private Item[] aa = (Item[]) new Object[N];
		
		public RandomizedQueueIterator() {
			i = N;
			for (int j = 0; j < N; j++)
				aa[j] = a[j];				
			StdRandom.shuffle(aa, 0, N-1);
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i > 0;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext()) 
				throw new NoSuchElementException();
			return aa[--i];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
	}
	

}
