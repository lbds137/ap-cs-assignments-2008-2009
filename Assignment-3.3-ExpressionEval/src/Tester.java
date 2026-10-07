/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.3-ExpressionEval (due 02/12/09)
 * This is the tester class.
 */

public class Tester {

	public static void main(String[] args) {
		ExpressionTree tree = new ExpressionTree("*");
		
		// Creates the sample expression tree shown on page 599
		tree.addLeft(tree.getRoot(), "+");
		
		tree.addLeft((ExpressionTreeNode) tree.getRoot().getLeft(), "5");
		tree.addRight((ExpressionTreeNode) tree.getRoot().getLeft(), "6");
		
		tree.addRight(tree.getRoot(), "*");
		
		tree.addRight((ExpressionTreeNode) tree.getRoot().getRight(), "+");
		
		tree.addLeft((ExpressionTreeNode) tree.getRoot().getRight().getRight(), "2");
		tree.addRight((ExpressionTreeNode) tree.getRoot().getRight().getRight(), "3");
		
		tree.addLeft((ExpressionTreeNode) tree.getRoot().getRight(), "4");
		
		// Tests the eval and toInfixNotation methods of the expression tree
		System.out.println(tree.eval());
		System.out.println(tree.toInfixNotation());
	}

}
