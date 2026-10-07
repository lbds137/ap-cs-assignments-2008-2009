/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-4.1-PriorityQueue (due 03/18/09)
 * This is the tester class.
 */

public class Tester {

	public static void main(String[] args) {
		HeapPriorityQueue hPQ = new HeapPriorityQueue();
		for(int i = 20; i >= 1; i--){
			hPQ.add(i);
		}
		hPQ.print();
		System.out.println("\n" + hPQ.remove() + "\n");
		hPQ.print();
		System.out.println("\n" + hPQ.remove() + "\n");
		hPQ.print();
	}

}
