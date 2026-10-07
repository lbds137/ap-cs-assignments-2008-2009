/* Validation.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.5-Blackjack (due 10/22/08)
 * This class contains useful methods for user input validation.
 */

import java.util.Scanner;

public class Validation {
	public static int checkIfInt(String inputStr, String displayText) {

		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		int inputInt;

		// Loop to check if input is an integer using try-catch; every time an
		// error is thrown we reset the loop
		for (int i = 0; i < 2; i++) {
			try {
				if(Integer.parseInt(inputStr) < 0){
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
	public static double checkIfDouble(String inputStr, String displayText) {

		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		double inputDoub;

		// Loop to check if input is an integer using try-catch; every time an
		// error is thrown we reset the loop
		for (int i = 0; i < 2; i++) {
			try {
				inputDoub = Double.parseDouble(inputStr);
			}
			catch (NumberFormatException exception) {
				System.out.println(displayText);
				i = 0;
				inputStr = keyboard.nextLine();
			}
		}

		// Once loop is over we know that the input is an integer, so we can
		// assign its value to our int
		inputDoub = Double.parseDouble(inputStr);

		return inputDoub;
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
