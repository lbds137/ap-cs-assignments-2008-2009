/* Decoder.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-2.2-Cipher (due 11/19/08)
 * This class decodes an encoded file.
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Decoder {
	
	// Declare variables we'll need
	private ArrayList<String> encLines = new ArrayList<String>();
	private ArrayList<String> decLines = new ArrayList<String>();
	
	private ArrayList<CharData> myCharFreqs = new ArrayList<CharData>();
	private ArrayList<CharData> acptCharFreqs = new ArrayList<CharData>();
	
	public char[] charMatches = new char[256];
	
	public Decoder(String encFilePath){
		
		// Read the encoded file
		Scanner sc = readFile(encFilePath);
		
		// Initialize encLines
		while(sc.hasNextLine()){
			encLines.add(sc.nextLine());
		}
		
		// Initialize charMatches
		for(int i = 0; i < 256; i++){
			charMatches[i] = (char) i;
		}
	}
	
	private Scanner readFile(String fileLocation) {
		
		Scanner myScanner = null;
		
		// Initialize myFile
		File myFile = new File(fileLocation);
		
		try{
			myScanner = new Scanner(myFile);
		}
		// If reading the file fails, exit the program
		catch(FileNotFoundException exception){
			System.out.println("Cannot find file.");
			System.exit(1);
		}
		
		return myScanner;
	}
	
	private void loadAcceptedFreqs(String acptFreqsFilepath){
		// Read the accepted frequencies file
		Scanner sc = readFile(acptFreqsFilepath);
		
		// Declare variables we'll need
		ArrayList<String> charFreqLines = new ArrayList<String>();
		char currentChar = ' ';
		double charFreq = 0;
		CharData currentCharData = new CharData(' ', 0.0);
		
		// Initialize charFreqLines
		while(sc.hasNextLine()){
			charFreqLines.add(sc.nextLine());
		}
		
		// Loop through each string in charFreqLines
		for(String s : charFreqLines){
			currentChar = s.charAt(0);
			charFreq = Double.parseDouble(s.substring(4));
			currentCharData = new CharData(currentChar, charFreq);
			acptCharFreqs.add(currentCharData);
		}
	}
	
	private void matchCharacters(){
		
		// Loop through each CharData of myCharFreqs and acptCharFreqs
		for(int i = 0; i < myCharFreqs.size(); i++){
			
			// This deals with the lowercase letters
			charMatches[myCharFreqs.get(i).getChar()] = acptCharFreqs.get(i).getChar();	
			// This deals with the uppercase letters
			charMatches[myCharFreqs.get(i).getChar() - 32] = (char) (acptCharFreqs.get(i).getChar() - 32);
		}
	}
	
	private void writeDecodedText(String decFilepath){
		matchCharacters();
		
		String tempString = "";
		
		// Loop through each string in encLines
		for(String s : encLines){
			
			// Loop through each character in s
			for(int i = 0; i < s.length(); i++){
				// Decode the current character and add it to tempString
				tempString += charMatches[s.charAt(i)];
			}
			// Add each tempString to decLines
			decLines.add(tempString);
			tempString = "";
		}
		
		File f = new File(decFilepath);
		
		PrintWriter out = null;
		
		try{
			out = new PrintWriter(f);
		}
		// Exit the program if the file cannot be created
		catch(FileNotFoundException ex){
			System.out.println("Cannot create file " + decFilepath);
			System.exit(1);
		}
		
		// Print each line of decLines in the text file
		for(String s : decLines){
			out.println(s);
		}
		
		out.close();
	}
	
	private void performFreqAnalysis(){
		
		// Declare variables we'll need
		double[] charFreq = new double[256];
		double totalNumChars = 0;
		CharData currentCharData;
		ArrayList<String> encLinesLowerCase = new ArrayList<String>();
		
		// Initialize encLinesLowerCase to encLines converted to lowercase 
		for(String s : encLines){
			encLinesLowerCase.add(s.toLowerCase());
		}
		
		// Loop through each string in encLinesLowerCase
		for(String s : encLinesLowerCase){
			
			// Loop through each char in s
			for(int i = 0; i < s.length(); i++){
				
				// We are only interested in the frequencies of the lowercase letters
				if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
					charFreq[s.charAt(i)]++;
				}
			}
			// Keep track of how many characters we have in total
			totalNumChars += removeSpacesPunctuationAndCaps(s).length();
		}
		// Loop through each character in charMatches
		for(Character c: charMatches){
			currentCharData = new CharData(c, (charFreq[c] / totalNumChars) * 100);
			// Again, we are only interested in the frequencies of the lowercase letters (any other chars have a frequency of 0)
			if(charFreq[c] > 0){
				myCharFreqs.add(currentCharData);
			}
		}
		
		// Sort the CharData
		myCharFreqs = mergesortCharData(myCharFreqs);
		
	}
	
	/*
	 * here's a little method for looking at your frequencies, if you
	 * wish to use it in your debugging
	 */
	public void printFreqAnalysis(){
		for(CharData d : myCharFreqs){
			System.out.println(d.getChar() + " - " + d.getFreqStr());
		}
	}
	
	/*
	 * another little method for printing the frequencies to a file,
	 * which may also be useful in your debugging
	 */
	public void printFreqAnalysis(String filepath){
		
		File f = new File(filepath);
		
		PrintWriter out = null;
		
		try{
			out = new PrintWriter(f);
		}
		catch(FileNotFoundException ex){
			System.out.println("Cannot create file " + filepath);
			System.exit(1);
		}
		
		for(CharData d : myCharFreqs){
			out.println(d.getChar() + " - " + d.getFreqStr());
		}
		
		out.close();
		
	}
	
	/*
	 * mergesortCharData takes an ArrayList of CharData objects and mergesorts them 
	 * according to their frequency - don't worry, you get this one for free
	 */
	private ArrayList<CharData> mergesortCharData(ArrayList<CharData> theData){
		
		// sorts in descending order according to character frequency
		
		// if there is only one element, return 
		if(theData.size() == 1){
			return theData;
		}
		
		// if the first element is less than the second, switch them and return
		else if(theData.size() == 2){
			if(theData.get(0).getFreq() < theData.get(1).getFreq()){
				CharData temp = theData.remove(0);
				theData.add(temp);
			}
			
			return theData;
		}
		
		// if there are more than two elements, split list in half and make recursive call
		else{
			
			ArrayList<CharData> firstHalf = new ArrayList<CharData>();
			ArrayList<CharData> secondHalf = new ArrayList<CharData>();
			
			for(int c = 0; c < theData.size(); c++){
				// if element is in first half of list, add to firstHalf, otherwise add to secondHalf
				if(c < theData.size()/2){
					firstHalf.add(theData.get(c));
				}
				else{
					secondHalf.add(theData.get(c));
				}
			}
			
			// recursively sort the two halves
			firstHalf = mergesortCharData(firstHalf);
			secondHalf = mergesortCharData(secondHalf);
			
			// merge the two sorted halves back into one array
			theData = new ArrayList<CharData>(); // reset theData ArrayList
			
			while(firstHalf.size() > 0 && secondHalf.size() > 0){
				// if the element in the first half is larger, remove it and add to theData list
				if(firstHalf.get(0).getFreq() > secondHalf.get(0).getFreq()){
					theData.add(firstHalf.remove(0));
				}
				// if the element in the second half is larger, remove it and add to the data list
				else{
					theData.add(secondHalf.remove(0));
				}
			}
			
			// if the first half still has elements, then the second half must be empty
			if(firstHalf.size() > 0){
				for(CharData d: firstHalf){
					theData.add(d);
				}
			}
			// vice versa here
			else{
				for(CharData d: secondHalf){
					theData.add(d);
				}
			}
			
			return theData;
		}
	}
	private String removeSpacesPunctuationAndCaps(String inputString){	
		inputString = inputString.toLowerCase();
		// Replace any characters not between A and Z or a and z with a space
		for(int i = 0; i < inputString.length(); i++){
			if((inputString.charAt(i) > 'Z' && inputString.charAt(i) < 'a') || inputString.charAt(i) < 'A' || inputString.charAt(i) > 'z'){
				inputString = inputString.replace(inputString.charAt(i), ' ');
			}
		}
		// Remove all spaces
		inputString = inputString.replace(" ", "");
		return inputString;
	}
	public void decodeFile(String acptCharFreqFilepath, String decodedFilePath){
		loadAcceptedFreqs(acptCharFreqFilepath);
		performFreqAnalysis();
		writeDecodedText(decodedFilePath);
	}
}
