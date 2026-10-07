/* BinConvert.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.3-binConvert (due 10/1/08)
 * This program will convert decimal numbers to binary numbers and back.
 */

import java.util.Scanner;

public class BinConvert {

	public static void main(String[] args) {
		
		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("This program will convert decimal numbers to binary numbers and back.");
		
		// Loop to convert numbers (loop because user might want to convert more than one pair of decimal / binary numbers)
		for(int i = 0; i < 2; i++){
		
			// Prompt user for first input
			System.out.println("\nEnter a decimal number: ");
			
			// Get first input and check if it is a long between 0 and 65536
			String inputstr = keyboard.nextLine();
			long input = checkIfLong(inputstr, "Please enter a decimal number between 0 and 65536, inclusive.", "decimal");
			
			// Convert to binary
			long output = decToBin(input);
			
			// Display first output to user
			System.out.println("\nThe binary equivalent of the decimal number " + input + " is: " + output);
			
			// Prompt user for second input
			System.out.println("\nEnter a binary number: ");
			
			// Get second input and check if it is a long between 0 and 10^16 AND contains only 0s and 1s
			inputstr = keyboard.nextLine();
			input = checkIfLong(inputstr, "Please enter a binary number between 0 and 10000000000000000, inclusive.", "binary");
			
			// Convert to decimal
			output = binToDec(input);
			
			// Display second output to user
			System.out.println("\nThe decimal equivalent of the binary number " + input + " is: " + output);
			
			// Ask if user would like to convert another number pair
			System.out.println("\nWould you like to enter another decimal / binary number pair?");
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
	public static long decToBin(long dec){
		long bin = 0;
		
		// Take the logarithm in base 2 of the decimal number 
		long powerOfTwo = (long) (Math.log(dec)/Math.log(2));
		
		// Loop to create the binary number by adding together powers of 10 (so that the only digits can be 0 or 1)
		for(long i = powerOfTwo; i >= 0; i--){
			
			// If the decimal number is a power of 2, raise 10 to that power and exit the loop
			if(dec == Math.pow(2, i)){
				bin += Math.pow(10, i);
				break;
			}
			
			// If the decimal number is greater than the greatest power of 2 contained in it, 
			// subtract that 2 raised to that power from the decimal number and add 10 raised 
			// to that power to the bin variable
			if(dec > Math.pow(2, i)){
				dec -= Math.pow(2, i);
				bin += Math.pow(10, i);
			}
		}
		return bin;
	}
	public static long binToDec(long bin){
		long dec = 0;
		long powerOfTen = 0;
		
		// Loop to create the decimal number by "peeling away" the powers of 10 of the binary number and adding 
		// 2 raised to each power of 10 to the dec variable
		while(bin >= 0){
		
			// This case exists to prevent the next step from trying to take a logarithm of 0 (when bin becomes 0)
			if(bin == 0){
				break;
			}
			
			// Take the logarithm in base 10 of the binary number
			powerOfTen = (long) (Math.log(bin)/Math.log(10));
			
			// Subtract 10 raised to powerOfTen from the binary number and add 2 raised to powerOfTen to the decimal variable
			bin -= Math.pow(10, powerOfTen);
			dec += Math.pow(2, powerOfTen);
		}
		return dec;
	}
	public static long checkIfLong(String inputStr, String displayText, String decimalOrBinary) {

		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		long inputLong;

		// Loop to check if input is an integer using try-catch; every time an
		// error is thrown we reset the loop
		for (int i = 0; i < 2; i++) {
			try {
				// If we know the input should be a decimal number we want it to be above 0 and no greater than 65536
				if(decimalOrBinary.equals("decimal")){
					if(Long.parseLong(inputStr) > 65536 || Long.parseLong(inputStr) < 0){
						inputStr = "JUNKJUNK";
					}
				}
				// If the input is not a decimal number we know it has to be a binary number (because of the string we passed to this method)
				else{
					// If the input contains numbers other than 0s and 1s we reject it
					if(inputStr.contains("2") || inputStr.contains("3") || inputStr.contains("4") || inputStr.contains("5") || inputStr.contains("6") || inputStr.contains("7") || inputStr.contains("8") || inputStr.contains("9")){
						inputStr = "JUNKJUNK";
					}
					// If the input is not between 0 and 10000000000000000 we reject it
					if(Long.parseLong(inputStr) > (long) Math.pow(10, 16) || Long.parseLong(inputStr) < 0){
						inputStr = "JUNKJUNK";
					}
				}
				inputLong = Long.parseLong(inputStr);
			}
			catch (NumberFormatException exception) {
				System.out.println(displayText);
				i = 0;
				inputStr = keyboard.nextLine();
			}
		}

		// Once loop is over we know that the input is a double that meets the above 
		// specifications, so we can assign its value to our double
		inputLong = Long.parseLong(inputStr);

		return inputLong;
	}
	public static boolean checkIfYesorNo(String inputStr, String displayText) {
		
		boolean yesNoBoolean = false;
		
		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);
		
		// Loop until user enters yes, no, y, or n; if user enters yes or y, set yesNoBoolean to true, 
		// if user enters no or n, set yesNoBoolean to false
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
