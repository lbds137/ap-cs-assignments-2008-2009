/* SieveEratos.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.4-sieveEratos (due 10/13/08)
 * This program will display all primes less than or equal to a number entered by the user.
 */

import java.util.Scanner;

public class SieveEratos {
	public static void main(String[] args) {
		
		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("This program will display all primes less than or equal to a number entered by the user.");
		
		// Loop to calculate factorial (loop because user might want to calculate more than one factorial)
		for(int i = 0; i < 2; i++){
		
			// Prompt user for input
			System.out.println("\nEnter a positive integer: ");
			
			// Get input and check if it is an integer
			String inputstr = keyboard.nextLine();
			int n = checkIfInt(inputstr, "Please enter a positive integer: ");
			
			// Initialize numArray (our boolean array) to have all values be true
			boolean[] numArray = new boolean[n];
			numArray = initNumArray(numArray);
			
			// Set numArray[number] to false if number is not prime
			numArray = getPrimesBooleanArray(n, numArray);
			
			// Get the number of primes
			int numberOfPrimes = 0;
			numberOfPrimes = numberOfPrimes(numArray);
			
			// Fill primesArray (of length numberOfPrimes) with the primes
			int[] primesArray = new int[numberOfPrimes];
			primesArray = getPrimes(numberOfPrimes, numArray);
			
			// Loop to print out the primes
			for(int j = 0; j < primesArray.length; j++){
				System.out.print(primesArray[j] + " ");
			}
			System.out.println();
			
			// Ask if user would like to calculate another factorial
			System.out.println("\nWould you like to enter another integer below the value of which you wish to look for primes?");
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
	public static boolean[] getPrimesBooleanArray(int topN, boolean[] numArray){
		// Create an array for all the numbers less than or equal to topN
		int[] allNumbers = new int[topN];
		for(int i = 0; i < allNumbers.length; i++){
			allNumbers[i] = i + 1;
		}
		
		// Loop through to check for primes
		for(int i = 2; i < allNumbers.length; i++){	
			
			// Use i as a number to divide by only if it is prime
			if(numArray[i - 1] == true){
				
				// Loop through all numbers below the maximum number in the array and perform the prime test
				for(int j = 0; j < allNumbers.length; j++){
					
					// A number is not prime if i evenly divides into it (and we already eliminated 1 when we initialized numArray)
					if(allNumbers[j] % i == 0 && allNumbers[j] != i){
						numArray[j] = false;
					}
					else{
					}
				}
			}
			else{
			}
		}
		return numArray;
	}
	public static boolean[] initNumArray(boolean[] numArray){
		for(int i = 0; i < numArray.length; i++){
			numArray[i] = true;
		}
		// 1 is not a prime
		numArray[0] = false;
		return numArray;
	}
	public static int numberOfPrimes(boolean[] numArray){
		int numberOfPrimes = 0;
		for(int i = 0; i < numArray.length; i++){
			if(numArray[i] == true){
				numberOfPrimes++;
			}
		}
		return numberOfPrimes;
	}
	public static int[] getPrimes(int numberOfPrimes, boolean[] numArray){
		int[] primesArray = new int[numberOfPrimes];
		int i = 0;
		
		// The while loop keeps going until all the primes have been written to our primesArray
		while(i < numberOfPrimes){	
			
			// Go through all values of numArray
			for(int j = 0; j < numArray.length; j++){
					
				// If a number is prime, write it to our primesArray	
				if(numArray[j] == true){
					primesArray[i] = j + 1;
					
					// Increase our i so we know we have written a prime to our primesArray
					i++;
				}
			}
		}
		return primesArray;
	}
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
