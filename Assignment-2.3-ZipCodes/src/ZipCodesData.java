/* ZipCodesData.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-2.3-ZipCodes (due 11/25/08)
 * This is the ZipCodesData class.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class ZipCodesData {

	// Declare variables we will need
	private ArrayList<ZipCode> sortedZipCodes = new ArrayList<ZipCode>();
	private ArrayList<ZipCode> unsortedZipCodes = new ArrayList<ZipCode>();
	private int mergeSortCompCount = 0;
	
	public ZipCodesData(String dataPath){
		
		// Declare variables we will need
		Scanner myScanner = null;
		ArrayList<String> fileData = new ArrayList<String>();
		String tempString[] = new String[3];
		ZipCode tempZipCode = new ZipCode("", "", "");
		
		// Initialize myFile
		File myFile = new File(dataPath);
		
		try{
			myScanner = new Scanner(myFile);
		}
		// If reading the file fails, exit the program
		catch(FileNotFoundException exception){
			System.out.println("Cannot find file.");
			System.exit(1);
		}
		
		// Initialize fileData
		while(myScanner.hasNextLine()){
			fileData.add(myScanner.nextLine());
		}
		
		// Loop through each string in fileData
		for(String s : fileData){
			tempString = s.split(",");
			tempZipCode = new ZipCode(tempString[0], tempString[1], tempString[2]);
			unsortedZipCodes.add(tempZipCode);
		}
	}
	
	/*
	 * Here is a simple method for printing your sorted zip codes that 
	 * may be useful when testing your sorting method
	 */
	public void printUnsortedZipCodes(){
		
		for(ZipCode zc : unsortedZipCodes){
			System.out.println(zc.getZipCode());
		}
	}
	public void printSortedZipCodes(){
		
		for(ZipCode zc : sortedZipCodes){
			System.out.println(zc.getZipCode());
		}
	}
	
	public ZipCode sequentialSearch(String zipCodeStr){
		
		int numComps = 0;
		int compResult;
		
		// Loop through each ZipCode and perform a comparison - if compareTo returns 0 that means the strings are equal
		for(ZipCode zc : unsortedZipCodes){
			compResult = zc.getZipCode().compareTo(zipCodeStr);
			numComps++;
			if(compResult == 0) {
				System.out.println("Sequential search performed with " + numComps + " comparisons.");
				return zc;
			}
		}
		return null;
	}
	
	public ZipCode binarySearch(String zipCodeStr){
		
		int numComps = 0;
		int compResult;
		int size = sortedZipCodes.size();
		int span = size / 2;
		int i = size / 2;
		
		while(i < size && i >= 0){
			
			// Perform the comparison and store the integer returned in compResult
			compResult = sortedZipCodes.get(i).getZipCode().compareTo(zipCodeStr);
			
			numComps++;
			
			// We don't want span to become 0 because then the loop gets stuck
			if(span != 1){
				// Each time we halve the span and add its modulus, so we can zero in on the right value
				// by bouncing back and forth closer and closer to it
				span = span / 2;
			}
			
			// If the zip code is found, we're done searching
			if(compResult == 0) {
				System.out.println("Binary search performed with " + numComps + " comparisons.");
				return sortedZipCodes.get(i);
			}
			// If not high enough, look for the zip code at a higher index
			else if(compResult < 0){
				i += span;
			}
			// If too high, look for the zip code at a lower index
			else{
				i -= span;
			}
			
			
		}
		return null;
	}
	
	public void insertionSort(){
		
		// We initially set the sortedZipCodes ArrayList to the unsortedZipCodes
		// ArrayList and then sort the sortedZipCodes list
		sortedZipCodes = unsortedZipCodes;
		
		int numComps = 0;
		
		for(int i = 1; i < sortedZipCodes.size(); i++){
			ZipCode tempZD = sortedZipCodes.get(i);
			
			int j = i;
			while(j > 0 && tempZD.getZipCode().compareTo(sortedZipCodes.get(j - 1).getZipCode()) < 0){
				numComps++;
				sortedZipCodes.set(j, sortedZipCodes.get(j - 1));
				j--;
			}
				sortedZipCodes.set(j, tempZD);	
		}
		
		System.out.println("Insertion sort performed with " + numComps + " comparisons");
	}
	
	public void mergeSort(){
		
		sortedZipCodes = mergeSort(unsortedZipCodes);
		
		System.out.println("Mergesort performed with " + mergeSortCompCount + " comparisons");
	}
	
	private ArrayList<ZipCode> mergeSort(ArrayList<ZipCode> ar){
		
		// sorts in descending order according to character frequency
		
		// if there is only one element, return 
		if(ar.size() == 1){
			mergeSortCompCount++;
			return ar;
		}
		
		// if the first element is less than the second, switch them and return
		else if(ar.size() == 2){
			if(ar.get(0).getZipCode().compareTo(ar.get(1).getZipCode()) > 0){
				mergeSortCompCount++;
				ZipCode temp = ar.remove(0);
				ar.add(temp);
			}
			
			return ar;
		}
		
		// if there are more than two elements, split list in half and make recursive call
		else{
			
			ArrayList<ZipCode> firstHalf = new ArrayList<ZipCode>();
			ArrayList<ZipCode> secondHalf = new ArrayList<ZipCode>();
			
			for(int i = 0; i < ar.size(); i++){
				// if element is in first half of list, add to firstHalf, otherwise add to secondHalf
				if(i < ar.size()/2){
					firstHalf.add(ar.get(i));
				}
				else{
					secondHalf.add(ar.get(i));
				}
			}
			
			// recursively sort the two halves
			firstHalf = mergeSort(firstHalf);
			secondHalf = mergeSort(secondHalf);
			
			// merge the two sorted halves back into one array
			ar = new ArrayList<ZipCode>(); // reset ar ArrayList
			
			while(firstHalf.size() > 0 && secondHalf.size() > 0){
				// if the element in the first half is larger, remove it and add to ar list
				if(firstHalf.get(0).getZipCode().compareTo(secondHalf.get(0).getZipCode()) < 0){
					ar.add(firstHalf.remove(0));
				}
				// if the element in the second half is larger, remove it and add to the data list
				else{
					ar.add(secondHalf.remove(0));
				}
				//mergeSortCompCount++;
			}
			
			// if the first half still has elements, then the second half must be empty
			if(firstHalf.size() > 0){
				for(ZipCode d: firstHalf){
					ar.add(d);
				}
			}
			// vice versa here
			else{
				for(ZipCode d: secondHalf){
					ar.add(d);
				}
			}
			
			return ar;
		}
	}
}
