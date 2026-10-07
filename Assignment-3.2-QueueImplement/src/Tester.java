/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.2-QueueImplement (due 01/28/09)
 * This is the tester class.
 */

import java.util.ArrayList;

public class Tester {
	public static void main(String[] args){
		Queue myQueue = new Queue();
		myQueue.add(1);
		myQueue.add("two");
		myQueue.add(3.0001);
		myQueue.add("FOUR");
		myQueue.add("5");
		
		myQueue.remove();
		printQueue(myQueue);
		
		myQueue.remove();
		printQueue(myQueue);
	}
	public static void printQueue(Queue aQueue){
		ArrayList<Object> queueElements = new ArrayList<Object>();
		// Add the queue elements to an ArrayList
		while(!aQueue.isEmpty()){
			queueElements.add(aQueue.remove());
		}
		// Print the elements
		for(int i = 0; i < queueElements.size(); i++){
			System.out.println(queueElements.get(i));
		}
		// Add the removed elements back to the queue
		for(int i = 0; i < queueElements.size(); i++){
			aQueue.add(queueElements.get(i));
		}
	}
}
