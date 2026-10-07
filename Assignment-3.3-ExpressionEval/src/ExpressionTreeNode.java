/* ExpressionTreeNode.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.3-ExpressionEval (due 02/12/09)
 * This class implements an expression tree node.
 */

public class ExpressionTreeNode extends TreeNode {
	
	// Constructor
	public ExpressionTreeNode(String initValue){
		super(initValue);
	}
	
	// Tests the node to see whether it's a number or an operator
	public boolean isOperator(){
		boolean isOperator = false;
		
		try{
			Integer.parseInt(super.getValue().toString());
		}
		catch(Exception e){
			isOperator = true;
		}
		
		return isOperator;
	}
	
	// Returns the int value of the node; if the node is not a number, returns 0
	public int getIntValue(){
		if(!isOperator()){
			return Integer.parseInt(super.getValue().toString());
		}
		else{
			return 0;
		}
	}
}
