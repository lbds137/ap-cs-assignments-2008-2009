/* DblLinkedListI.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.1-ReductionInForce (due 01/21/09)
 * This is the DblLinkedListI interface provided by Mr. Wulsin.
 */

public interface DblLinkedListI extends LinkedListI{

	public void insertBefore(DblListNode aNode, DblListNode newNode);
	
	public void insertBefore(int index, DblListNode newNode);
	
	public void insertBeginning(DblListNode newNode);
	
	public void insertEnd(DblListNode newNode);
}
