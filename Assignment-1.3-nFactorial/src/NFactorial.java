/* NFactorial.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.3-nFactorial (due 10/1/08)
 * This program will calculate the factorial of the number the user inputs (must be between 0 and 20, inclusive).
 */

import java.util.Scanner;

public class NFactorial {
	public static void main(String[] args) {
		
		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("This program will calculate the factorial of the number you input (must be between 0 and 20, inclusive).");
		
		// Loop to calculate factorial (loop because user might want to calculate more than one factorial)
		for(int i = 0; i < 2; i++){
		
			// Prompt user for input
			System.out.println("\nEnter an integer: ");
			
			// Get input and check if it is an integer
			String inputstr = keyboard.nextLine();
			int input = checkIfInt(inputstr, "Please enter an integer between 0 and 20, inclusive: ");
			
			// Calculate factorial
			long output = calculateFactorialRecursion(input, 1);
			
			// Display output to user
			System.out.println("\nThe factorial of " + input + " is: " + output);
			
			// Ask if user would like to calculate another factorial
			System.out.println("\nWould you like to calculate the factorial of another number?");
			String againStr = keyboard.nextLine();
			boolean again = checkIfYesorNo(againStr, "Please enter either \"yes\" or \"no\".");
			
			// If user says no, end loop; if user says yes, reset loop
			if(again == false){
				i = 2;
			}
			else{
				i = 0;
			}
		}
		System.out.println("Goodbye!");
	}
	public static int checkIfInt(String inputStr, String displayText) {

		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		int inputInt;

		// Loop to check if input is an integer using try-catch; every time an
		// error is thrown we reset the loop
		for (int i = 0; i < 2; i++) {
			try {
				if(Integer.parseInt(inputStr) > 20 || Integer.parseInt(inputStr) < 0){
					inputStr = "JUNKJUNK";
				}
				inputInt = Integer.parseInt(inputStr);
			}
			catch (NumberFormatException exception) {
				System.out.println(displayText);
				i = 0;
				inputStr = keyboard.nextLine();
			}
		}

		// Once loop is over we know that the input is an integer, so we can
		// assign its value to our int
		inputInt = Integer.parseInt(inputStr);

		return inputInt;
	}
	public static long calculateFactorialLoop(int input) {
		long output = 1;
		
		while(input > 1){
			output *= input;
			input--; 
		}
		return output;
	}
	public static long calculateFactorialRecursion(int input, int output) {
		output *= input;
		
		if(input == 1){
			return output;
		}
		else{
			return calculateFactorialRecursion(input-1, output);
		}
	}
	public static boolean checkIfYesorNo(String inputStr, String displayText) {
		
		boolean yesNoBoolean = false;
		
		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);
		
		// Loop until user enters yes, no, y, or n; if user enters yes or y, set yesNoBoolean to true, if user 
		// enters no or n, set yesNoBoolean to false
		for (int i = 0; i < 2; i++) {
			if(inputStr.toLowerCase().equals("yes") || inputStr.toLowerCase().equals("y")) {
				yesNoBoolean = true;
			} 
			else if(inputStr.toLowerCase().equals("no") || inputStr.toLowerCase().equals("n")){
				yesNoBoolean = false;
			}
			else{
				i = 0;
				System.out.println(displayText);
				inputStr = keyboard.nextLine();
			}
		}
		
		return yesNoBoolean;
	}
}
