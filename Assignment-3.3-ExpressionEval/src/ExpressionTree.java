/* ExpressionTree.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.3-ExpressionEval (due 02/12/09)
 * This class partially implements an expression tree.
 */

public class ExpressionTree{
	private ExpressionTreeNode root = null;
	
	// Constructors
	public ExpressionTree(){
		
	}
	public ExpressionTree(String value){
		root = new ExpressionTreeNode(value);
	}
	
	// Gets the root
	public ExpressionTreeNode getRoot(){
		return root;
	}
	// Adds a node as the left child of node
	public void addLeft(ExpressionTreeNode node, String value){
		node.setLeft(new ExpressionTreeNode(value));
	}
	// Adds a node as the right child of node
	public void addRight(ExpressionTreeNode node, String value){
		node.setRight(new ExpressionTreeNode(value));
	}
	// Evaluates the expression tree
	public int eval(){
		return eval(root);
	}
	// Recursive helper method that evaluates the expression tree
	private int eval(ExpressionTreeNode root){
		if(root.getValue().equals("*")){
			return eval((ExpressionTreeNode) root.getLeft()) * eval((ExpressionTreeNode) root.getRight());
		}
		else if(root.getValue().equals("+")){
			return eval((ExpressionTreeNode) root.getLeft()) + eval((ExpressionTreeNode) root.getRight());
		}
		else if(!root.isOperator()){
			return Integer.parseInt(root.getValue().toString());
		}
		else{
			return 0;
		}
	}
	// Returns a string representation (infix notation) of the expression tree
	public String toInfixNotation(){
		return toInfixNotation(root);
	}
	// Recursive helper method that returns a string representation (infix notation) of the expression tree
	private String toInfixNotation(ExpressionTreeNode root){
		String s = "";
		if(root.getValue().equals("*")){
			return "(" + toInfixNotation((ExpressionTreeNode) root.getLeft()) + " * " + toInfixNotation((ExpressionTreeNode) root.getRight()) + ")";
		}
		else if(root.getValue().equals("+")){
			return "(" + toInfixNotation((ExpressionTreeNode) root.getLeft()) + " + " + toInfixNotation((ExpressionTreeNode) root.getRight()) + ")";
		}
		else if(!root.isOperator()){
			return root.getValue().toString();
		}
		else{
			return "";
		}
	}
}
