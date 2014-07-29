
public class Subset {

	public static void main(String args[]) {
		int integerNumber = Integer.parseInt(args[0]);
		RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			randomQueue.enqueue(item);
		}
		int times = 0;
		for(String s : randomQueue) {
			if (times >= integerNumber)
				break;
			StdOut.println(s);
			times++;
			
		}
		
	}
}
