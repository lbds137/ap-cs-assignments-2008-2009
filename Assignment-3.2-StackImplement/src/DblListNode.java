/* DblListNode.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.2-StackImplement (due 01/28/09)
 * This class implements a node in a doubly linked list.
 */

public class DblListNode {
	private Object value = null;
	private DblListNode next = null;
	private DblListNode previous = null;
	
	public DblListNode(Object myValue){
		value = myValue;
	}
	public Object getValue(){
		return value;
	}
	public DblListNode getNext(){
		return next;
	}
	public void setNext(DblListNode myNext){
		next = myNext;
	}
	public DblListNode getPrevious(){
		return previous;
	}
	public void setPrevious(DblListNode myPrevious){
		previous = myPrevious;
	}
}
