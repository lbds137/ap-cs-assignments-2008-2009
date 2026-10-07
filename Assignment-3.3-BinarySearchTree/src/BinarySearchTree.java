/* BinarySearchTree.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.3-BinarySearchTree (due 02/12/09)
 * This class implements a binary search tree.
 */

public class BinarySearchTree {
	public BinarySearchTreeNode root = null;
	
	public BinarySearchTree(){
		
	}
	
	// Returns true if this BST contains value; otherwise returns false
	public boolean contains(Object value){
		return contains(root, value);
	}
	// Returns true if the BST rooted at node contains value;
	// otherwise returns false (recursive version)
	private boolean contains (BinarySearchTreeNode node, Object value){
		if(node == null){
			return false;
		}
		else{
			int difference = ((Comparable<Object>)value).compareTo(node.getValue());
			// If equal, the value has been found
			if(difference == 0){
				return true;
			}
			// If less, check the left subtree
			else if(difference < 0){
				return contains(node.getLeft(), value);
			}
			// If greater, check the right subtree
			else{ // if (difference > 0)
				return contains(node.getRight(), value);
			}
		}
	}
	// Adds value to this BST, unless this tree already holds value
	// Returns true if value has been added; otherwise returns false
	public boolean add(Object value){
		if(contains(value)){
			return false;
		}
		root = add(root, value);
		return true;
	}
	// Adds value to the BST rooted at node; returns the root of the new tree
	// Precondition: the tree rooted at node does not contain value
	private BinarySearchTreeNode add(BinarySearchTreeNode node, Object value){
		if(node == null){
			node = new BinarySearchTreeNode(value);
		}
		else{
			int difference = ((Comparable<Object>)value).compareTo(node.getValue());
			if(difference < 0){
				node.setLeft(add(node.getLeft(), value));
			}
			else{ // if (difference > 0)
				node.setRight(add(node.getRight(), value));
			}
		}
		return node;
	}
	// Removes value from this BST; returns true if value has been
	// found and removed; otherwise returns false
	public boolean remove(Object value){
		if(!contains(value)){
			return false;
		}
		root = remove(root, value);
		return true;
	}
	// Removes value from the BST rooted at node; returns the root of the new tree
	// Precondition: the tree rooted at node contains value
	public BinarySearchTreeNode remove(BinarySearchTreeNode node, Object value){
		int difference = ((Comparable<Object>)value).compareTo(node.getValue());
		if(difference == 0){
			node = removeRoot(node);
		}
		else if(difference < 0){
			node.setLeft(remove(node.getLeft(), value));
		}
		else{
			node.setRight(remove(node.getRight(), value));
		}
		return node;
	}
	// Returns a string representation of this BST
	public String toString(){
		String str = toString(root);
		if(str.endsWith(", ")){
			str = str.substring(0, str.length() - 2);
		}
		return "[" + str + "]";
	}
	// Returns a string representation of the tree rooted at node
	private String toString(BinarySearchTreeNode node){
		if(node == null){
			return "";
		}
		else{
			return toString(node.getLeft()) + node.getValue() + ", " + toString(node.getRight());
		}
	}
	// Removes the root of the BST rooted at root, replacing it with the 
	// smallest node from the right subtree; returns the root of the new tree
	private BinarySearchTreeNode removeRoot(BinarySearchTreeNode oldRoot){
		BinarySearchTreeNode newRootParent;
		BinarySearchTreeNode newRoot;
		
		if(oldRoot.getRight() != null && oldRoot.getRight().getLeft() != null){
			newRootParent = findParentOfSmallestChild(oldRoot.getRight());
			newRoot = newRootParent.getLeft();
			
			// Unlinks newRoot from the tree
			newRootParent.setLeft(newRoot.getRight());
			
			// Links newRoot to oldRoot's children
			newRoot.setLeft(oldRoot.getLeft());
			newRoot.setRight(oldRoot.getRight());
		}
		else if(oldRoot.getRight() != null){
			newRootParent = oldRoot;
			newRoot = oldRoot.getRight();
			
			// Unlinks newRoot from the tree
			newRootParent.setRight(newRoot.getRight());
			
			// Links newRoot to oldRoot's children
			newRoot.setLeft(oldRoot.getLeft());
			newRoot.setRight(oldRoot.getRight());
		}
		else{
			newRoot = oldRoot.getLeft();
		}
		
		return newRoot;
	}
	// Recursively finds the parent of the smallest child in the subtree rooted at root
	// Precondition: the root must have a left child
	private BinarySearchTreeNode findParentOfSmallestChild(BinarySearchTreeNode root){
		// If there is nothing less than the node, return it
		if(root.getLeft().getLeft() == null){
			return root;
		}
		// If there is something less, keep looking in the node's left subtree
		else{
			return findParentOfSmallestChild(root.getLeft());
		}
	}
}
