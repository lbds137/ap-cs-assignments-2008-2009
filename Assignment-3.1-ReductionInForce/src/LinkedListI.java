/* LinkedListI.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.1-ReductionInForce (due 01/21/09)
 * This is the LinkedListI interface provided by Mr. Wulsin.
 */

public interface LinkedListI{

	public DblListNode getNode(int index);
	
	public void setNode(int index, DblListNode aNode);
	
	public void insertAfter(DblListNode aNode, DblListNode newNode);
	
	public void insertAfter(int index, DblListNode newNode);
		
	public DblListNode remove(DblListNode aNode);
	
	public DblListNode remove(int index);
}
