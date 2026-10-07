/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-3.2-StackImplement (due 01/28/09)
 * This is the tester class.
 */

import java.util.ArrayList;

public class Tester {
	public static void main(String[] args){
		Stack myStack = new Stack();
		myStack.push(1);
		myStack.push("two");
		myStack.push(3.0001);
		myStack.push("FOUR");
		myStack.push("5");
		
		myStack.pop();
		printStack(myStack);
		
		myStack.pop();
		printStack(myStack);
	}
	public static void printStack(Stack aStack){
		ArrayList<Object> stackElements = new ArrayList<Object>();
		// Add the stack elements to an ArrayList
		while(!aStack.isEmpty()){
			stackElements.add(0, aStack.pop());
		}
		// Print the elements
		for(int i = 0; i < stackElements.size(); i++){
			System.out.println(stackElements.get(i));
		}
		// Add the removed elements back to the stack
		for(int i = 0; i < stackElements.size(); i++){
			aStack.push(stackElements.get(i));
		}
	}
}
