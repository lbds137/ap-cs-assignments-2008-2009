/* LLTester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.1-DoublyLinkedList (due 01/21/09)
 * This is the LLTester class provided by Mr. Wulsin.
 */

public class LLTester {

	public static void main(String[] args){
		
		DblLinkedList myLL = new DblLinkedList();
		
		int[] fib = {1, 1, 2, 3, 5, 8};
		DblListNode fibNode = new DblListNode(fib);
		myLL.insertBeginning(fibNode);
		
		myLL.insertEnd(new DblListNode('e'));
				
		myLL.insertAfter(fibNode, new DblListNode("Hi there."));
		
		myLL.insertBefore(fibNode, new DblListNode(29));
		
		myLL.insertBefore(2, new DblListNode(2009));
		
		myLL.insertAfter(1, new DblListNode("Third Quarter"));
		
		for(DblListNode curNode = myLL.getNode(0); curNode != null; curNode = curNode.getNext()){
			System.out.println(curNode.getValue());
			
		}
		
		System.out.println("----");
		
		System.out.println("The element value at index 2 is: " + myLL.getNode(2).getValue());
		myLL.setNode(2, new DblListNode("Second Semester"));
		System.out.println("The new value at index 2 is: " + myLL.getNode(2).getValue());
	}
}
