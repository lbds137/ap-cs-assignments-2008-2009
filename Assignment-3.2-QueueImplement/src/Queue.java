/* Queue.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.2-QueueImplement (due 01/28/09)
 * This class implements a queue using a doubly linked list.
 */

public class Queue {
	private DblLinkedList queueList;
	
	public Queue(){
		queueList = new DblLinkedList();
	}
	public boolean isEmpty(){
		return queueList.getNodeCount() == 0;
	}
	public Object peek(){
		return queueList.getNode(queueList.getNodeCount() - 1).getValue();
	}
	public void add(Object obj){
		queueList.insertBeginning(new DblListNode(obj));
	}
	public Object remove(){
		return queueList.remove(queueList.getNodeCount() - 1).getValue();
	}
}
