/* Stack.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.2-StackImplement (due 01/28/09)
 * This class implements a stack using a doubly linked list.
 */

public class Stack {
	private DblLinkedList stackList;
	
	public Stack(){
		stackList = new DblLinkedList();
	}
	public boolean isEmpty(){
		return stackList.getNodeCount() == 0;
	}
	public Object peek(){
		return stackList.getNode(stackList.getNodeCount() - 1).getValue();
	}
	public void push(Object obj){
		stackList.insertEnd(new DblListNode(obj));
	}
	public Object pop(){
		return stackList.remove(stackList.getNodeCount() - 1).getValue();
	}
}
