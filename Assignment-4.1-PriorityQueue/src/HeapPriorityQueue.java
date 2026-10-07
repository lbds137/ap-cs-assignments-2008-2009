/* HeapPriorityQueue.java
 * Java Methods A & AB - modified by Vladlena Costescu
 * AP Computer Science AB
 * Assignment-4.1-PriorityQueue (due 03/18/09)
 * Implements a priority queue based on a min-heap.
 */

import java.util.*;

public class HeapPriorityQueue {
	private static final int DFLT_CAPACITY = 101;
	private Object[] items;
	private int numItems;
	private final Comparator<Object> comparator;

	public HeapPriorityQueue() {
		this(DFLT_CAPACITY, null);
	}

	public HeapPriorityQueue(Comparator<Object> c) {
		this(DFLT_CAPACITY, c);
	}

	public HeapPriorityQueue(int initialCapacity) {
		this(initialCapacity, null);
	}

	public HeapPriorityQueue(int initialCapacity, Comparator<Object> c) {
		items = new Object[initialCapacity + 1];
		comparator = c;
	}

	/**
	 * Returns true if this priority queue is empty; otherwise returns false.
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Returns the highest priority element without removing it from this
	 * priority queue.
	 */
	public Object peek() {
		if (numItems == 0) {
			throw new NoSuchElementException();
		}
		return items[1];
	}

	/**
	 * Adds obj to this priority queue; returns true.
	 */
	public boolean add(Object obj) {
		numItems++;
		if (numItems >= items.length) // items[0] is not used
			growCapacity();
		items[numItems] = obj;
		reheapUp();
		return true;
	}

	/**
	 * Removes and returns the highest priority item.
	 */
	public Object remove() {
		if (numItems == 0) {
			throw new NoSuchElementException();
		}

		Object minObject = items[1];
		items[1] = items[numItems];
		numItems--;
		reheapDown();
		return minObject;
	}

	//**************************************************************************

	private boolean lessThan(Object obj1, Object obj2) {
		if (comparator != null)
			return comparator.compare(obj1, obj2) < 0;
		else
			return ((Comparable) obj1).compareTo(obj2) < 0;
	}

	private void reheapDown() {
		reheapDown(1);
	}
	private void reheapDown(int itemPos){
		// Declare variables and initialize item
		Object item = items[itemPos];
		int[] childrenPos = new int[2];
		int smallestChildPos;
		Object temp;
		
		// Initialize the children positions (i.e. indices)
		childrenPos[0] = itemPos * 2;
		childrenPos[1] = childrenPos[0] + 1;
		
		// If there is at least one child (i.e. the left one), execute this code; otherwise, do nothing
		if(items[childrenPos[0]] != null){
			// If there is no right child, the only child is also the smallest
			if(items[childrenPos[1]] == null){
				smallestChildPos = 0;
			}
			// If the right child is less than the left child, make it the smallest child
			else if(lessThan(items[childrenPos[1]], items[childrenPos[0]])){
				smallestChildPos = 1;	
			}
			// If the left child is less than the right child, make it the smallest child
			else{
				smallestChildPos = 0;
			}
			 
			temp = items[childrenPos[smallestChildPos]];
			
			// Perform the actual switching
			items[itemPos] = temp;
			items[childrenPos[smallestChildPos]] = item;
			
			// Save the new position of the item and recurse down
			itemPos = childrenPos[smallestChildPos];
			reheapDown(itemPos);
		}
	}

	private void reheapUp() {
		reheapUp(numItems);
	}
	
	private void reheapUp(int itemPos){
		Object item = items[itemPos];
		int parentPos = itemPos / 2;
		Object temp;
		
		// If item has a parent (i.e. item is not the root), and the item is less than 
		// its parent, execute this code; otherwise, do nothing
		if(parentPos != 0 && lessThan(items[itemPos], items[itemPos / 2])){
			temp = items[parentPos];
			
			// Perform the actual switching
			items[itemPos] = temp;
			items[parentPos] = item;
			
			// Save the new position of the item and recurse up
			itemPos = parentPos;
			reheapUp(itemPos);
		}
	}

	private void growCapacity() {
		Object[] tempItems = new Object[2 * items.length - 1];
		System.arraycopy(items, 0, tempItems, 0, items.length);
		items = tempItems;
	}
	public void print(){
		for(int i = 1; i <= numItems; i++){
			System.out.println(items[i] + " i = " + i);
		}
	}
}
