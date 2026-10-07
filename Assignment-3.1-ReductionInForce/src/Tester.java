/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.1-ReductionInForce (due 01/21/09)
 * This is the Tester class.
 */

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of people.");
		
		// Initialize the list
		CircLinkedList cLL = new CircLinkedList();
		int listSize = sc.nextInt();
		for(int i = 0; i < listSize; i++){
			cLL.insertEnd(new DblListNode(i));
		}
		
		// Initialize the shooter
		DblListNode shooter = cLL.getNode(0);
		DblListNode victim;
		
		// Kill (lay) off the person after the shooter, then go to the next shooter
		while(cLL.getNodeCount() > 1){
			victim = shooter.getNext();
			cLL.remove(victim).getValue();
			shooter = shooter.getNext();
		}
		
		System.out.println(cLL.getNode(0).getValue() + " is the safe position.");
		
	}

}
