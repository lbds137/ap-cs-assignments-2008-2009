/* DblLinkedList.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.2-QueueImplement (due 01/28/09)
 * This class implements a doubly linked list.
 */

public class DblLinkedList {
	private DblListNode head = null;
	private DblListNode tail = null;
	private int nodeCount = 0;
	
	public DblLinkedList(){
		
	}
	
	public DblListNode getNode(int index){
		// Implement the IndexOutOfBoundsException if an invalid index is passed into the getNode method
		if(index < 0 || index > nodeCount){
			throw new IndexOutOfBoundsException("The index passed into the getNode method is invalid.");
		}
		
		DblListNode tempNode;
		
		// Start from the tail if the index is more than halfway through the list
		if(index > 1 && index > nodeCount / 2){
			tempNode = tail;
			for(int i = nodeCount - 1; i > index; i--){
				tempNode = tempNode.getPrevious();
			}
		}
		// Start from the head if the index is less than halfway through the list
		else{
			tempNode = head;
			for(int i = 0; i < index; i++){
				tempNode = tempNode.getNext();
			}
		}
		
		return tempNode;
	}
	public void setNode(int index, DblListNode aNode){
		DblListNode tempNode = getNode(index);
		
		// Set up the connections from the new node
		aNode.setPrevious(tempNode.getPrevious());
		aNode.setNext(tempNode.getNext());
		
		// Set up the connections to the new node
		if(aNode.getPrevious() != null){
			aNode.getPrevious().setNext(aNode);
		}
		else{
			head = aNode;
		}
		if(aNode.getNext() != null){
			aNode.getNext().setPrevious(aNode);
		}
		else{
			tail = aNode;
		}
	}
	public void insertAfter(DblListNode aNode, DblListNode newNode){
		// Set up the connections from the new node
		newNode.setPrevious(aNode);
		newNode.setNext(aNode.getNext());
		
		// Set up the connections to the new node
		aNode.setNext(newNode);
		if(newNode.getNext() != null){
			newNode.getNext().setPrevious(newNode);
		}
		else{
			tail = newNode;
		}
		
		nodeCount++;
	}
	public void insertAfter(int index, DblListNode newNode){
		DblListNode tempNode = getNode(index);
		insertAfter(tempNode, newNode);
	}
	public DblListNode remove(DblListNode aNode){
		if(nodeCount >= 3){
			if(head == aNode){
				head = aNode.getNext();
			}
			else if(tail == aNode){
				tail = aNode.getPrevious();
			}
			
			// Link aNode's next node to its previous node
			if(aNode.getNext() != null){
				aNode.getNext().setPrevious(aNode.getPrevious());
			}
			
			// Link aNode's previous node to its next node
			if(aNode.getPrevious() != null){
				aNode.getPrevious().setNext(aNode.getNext());
			}
		}
		else if(nodeCount == 2){
			if(head == aNode){
				head = aNode.getNext();
				head.setPrevious(null);
				tail = head;
			}
			else if(tail == aNode){
				tail = aNode.getPrevious();
				tail.setNext(null);
				head = tail;
			}
		}
		else{
			head = null;
			tail = null;
		}
		
		nodeCount--;
		return aNode;
	}
	public DblListNode remove(int index){
		DblListNode tempNode = getNode(index);
		return remove(tempNode);
	}
	public void insertBefore(DblListNode aNode, DblListNode newNode){
		// Set up the connections from the new node
		newNode.setPrevious(aNode.getPrevious());
		newNode.setNext(aNode);
		
		// Set up the connections to the new node
		if(newNode.getPrevious() != null){
			aNode.getPrevious().setNext(newNode);
		}
		else{
			head = newNode;
		}
		aNode.setPrevious(newNode);
		
		nodeCount++;
	}
	public void insertBefore(int index, DblListNode newNode){
		DblListNode tempNode = getNode(index);
		insertBefore(tempNode, newNode);
	}
	public void insertBeginning(DblListNode newNode){
		// If the list is empty, set the newNode as both the head and tail of the list
		if(nodeCount == 0){
			tail = newNode;
		}
		else{ 
			// Set the connection from the new head to the old head
			newNode.setNext(head);
			
			// Set the connection from the old head to the new head
			head.setPrevious(newNode);
		}
		// Set the new head as the head of the list
		head = newNode;
		
		nodeCount++;
	}
	public void insertEnd(DblListNode newNode){
		// Set the connection from the old tail to the new tail
		if(nodeCount == 0){
			head = newNode;
		}
		else{
			// Set the connection from the new tail to the old tail
			newNode.setPrevious(tail);
			
			tail.setNext(newNode);
		}
		// Set the new tail as the tail of the list
		tail = newNode;
		
		nodeCount++;
	}
	public int getNodeCount(){
		return nodeCount;
	}
}
