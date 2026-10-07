/* PalinTester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-2.1-PalinTester (due 11/7/08)
 * This is the PalinTester class, which reads a text file at a user-provided location and tests for palindromes.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PalinTester {
	// Declare some useful variables
	private String errorMsg = "File not found. Please enter a valid file location.";
	private Scanner myScanner = null;
	private int numberOfLines;
	private String[] fileContent;
	private String[] fileContentNoSpaces;
	
	// We don't need anything in our constructor because our methods take care of everything
	public PalinTester(){
	}
	
	// This method accepts a string for the file location and tries to read the file
	private boolean readFile(String fileLocation) {
		
		// Initialize myFile and set exceptionThrown to false for now
		File myFile = new File(fileLocation);
		boolean exceptionThrown = false;
		
		try{
			myScanner = new Scanner(myFile);
		}
		// If reading the file fails, set exceptionThrown to true
		catch(FileNotFoundException exception){
			exceptionThrown = true;
		}
		
		return exceptionThrown;
	}
	
	// This method prompts the user for the file location and calls readFile to read the file
	private void getFileLocation() {
		String fileLocation;
		Scanner getInputSc = new Scanner(System.in);
		
		// Prompt user for the file location
		System.out.println("Please enter a file location.");
		fileLocation = getInputSc.nextLine();
		
		for (int i = 0; i < 2; i++) {
			
			// If the file cannot be read (i.e. an exception is thrown), 
			// ask the user to enter the correct file location and reset the loop
			if(readFile(fileLocation) == true) {
				System.out.println(errorMsg);
				i = 0;
				fileLocation = getInputSc.nextLine();	
			}
			else{
				readFile(fileLocation);
			}
		}
	}
	
	// This method removes spaces and punctuation from the input string and sets it to lowercase
	private String removeSpacesPunctuationAndCaps(String inputString){	
		inputString = inputString.toLowerCase();
		inputString = inputString.replace(" ", "");
		inputString = inputString.replace(".", "");
		inputString = inputString.replace(",", "");
		inputString = inputString.replace(";", "");
		inputString = inputString.replace(":", "");
		inputString = inputString.replace("'", "");
		inputString = inputString.replace("\"", "");
		inputString = inputString.replace("!", "");
		inputString = inputString.replace("?", "");
		inputString = inputString.replace("-", "");
		
		return inputString;
	}
	
	// This method reads the file and initializes fileContent and fileContentNoSpaces
	private void createStringArray() {
		
		// The first thing we need to do is get the file location, so we call our getFileLocation method
		getFileLocation();
		
		// We assume the first line of the file to contain the number of lines in the text file;
		// we subtract 1 because we're not interested in the first line after we get the number of lines
		numberOfLines = Integer.parseInt(myScanner.nextLine()) - 1;
		
		// Initialize fileContent and fileContentNoSpaces, allocating the number of indexes based on numberOfLines
		fileContent = new String[numberOfLines];
		fileContentNoSpaces = new String[numberOfLines];
		
		// We loop through to simultaneously assign values to both fileContent and fileContentNoSpaces
		for(int i = 0; i < numberOfLines; i++){
			fileContent[i] = myScanner.nextLine();
			fileContentNoSpaces[i] = removeSpacesPunctuationAndCaps(fileContent[i]);
		}
	}
	
	// This is our most important method, which actually performs the palindrome testing
	private boolean[] testIfPalindrome(){
		
		// Initialize a boolean array of the same length as our fileContent / fileContentNoSpaces arrays
		boolean[] isPalindrome = new boolean[numberOfLines];
		
		// Initialize an integer for the number of characters that are equal to each other
		int numberOfCharsEqual = 0;
		
		// Our outer loop loops through the number of lines (i.e. performs the palindrome test for each line)
		for(int i = 0; i < numberOfLines; i++){
			
			// On each iteration we reset our numberOfCharsEqual integer
			numberOfCharsEqual = 0;
			
			// The inner loop actually performs the palindrome test; it only loops through
			// the length of the string divided by two (because otherwise it would re-test the same characters)
			for(int j = 0; j < fileContentNoSpaces[i].length() / 2; j++){
				
				// If the character at j characters away from the beginning of the string 
				// is equal to the character at j characters away from the end of the string,
				// then we increase numberOfCharsEqual
				if(fileContentNoSpaces[i].charAt(j) == fileContentNoSpaces[i].charAt(fileContentNoSpaces[i].length() - 1 - j)){
					numberOfCharsEqual++;
				}
			}
			
			// Now we determine based on numberOfCharsEqual if each line is a palindrome or not and
			// set our boolean value for that line to true or false
			if(numberOfCharsEqual == fileContentNoSpaces[i].length() / 2){
				isPalindrome[i] = true;
			}
			else{
				isPalindrome[i] = false;
			}
		}
		return isPalindrome;
	}
	
	// This method (our only public method) pulls all of our other methods together and prints out the results
	public void printResults(){
		createStringArray();
		
		System.out.println();
		for(int i = 0; i < numberOfLines; i++){
			System.out.println(fileContent[i] + " " + testIfPalindrome()[i]);
		}
	}
}
