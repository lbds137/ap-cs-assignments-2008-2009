/* BinarySearchTreeNode.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.3-BinarySearchTreeNode (due 02/12/09)
 * This class implements a binary search tree node.
 */

public class BinarySearchTreeNode {
	private Object value;
	private BinarySearchTreeNode left = null;
	private BinarySearchTreeNode right = null;
	
	// Constructors
	public BinarySearchTreeNode(Object initValue){
		value = initValue;
	}
	public BinarySearchTreeNode(Object initValue, BinarySearchTreeNode initLeft, BinarySearchTreeNode initRight){
		this(initValue);
		left = initLeft;
		right = initRight;
	}
	
	// Getter and setter methods
	public Object getValue(){
		return value;
	}
	public BinarySearchTreeNode getLeft(){
		return left;
	}
	public BinarySearchTreeNode getRight(){
		return right;
	}
	public void setValue(Object newValue){
		value = newValue;
	}
	public void setLeft(BinarySearchTreeNode newLeft){
		left = newLeft;
	}
	public void setRight(BinarySearchTreeNode newRight){
		right = newRight;
	}
}
