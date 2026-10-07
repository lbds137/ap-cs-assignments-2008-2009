/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.3-BinarySearchTree (due 02/12/09)
 * This is the tester class.
 */

public class Tester {
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		
		// Add some values to the tree
		tree.add(50);
		tree.add(41);
		tree.add(33);
		tree.add(65);
		tree.add(22);
		tree.add(91);
		tree.add(57);
		tree.add(100);
		tree.add(51);
		tree.add(59);
		
		// Test the contains, remove, and toString methods
		System.out.println(tree.contains(50));
		System.out.println(tree.toString());
		System.out.println(tree.remove(65));
		System.out.println(tree.remove(57));
		System.out.println(tree.toString());
	}

}
