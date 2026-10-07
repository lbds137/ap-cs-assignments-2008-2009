/* ZipTester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-2.3-ZipCodes (due 11/25/08)
 * This is the ZipTester class.
 */

public class ZipTester {

	public static void main(String[] args) {
		
		ZipCodesData data = new ZipCodesData("UnsortedZipData.csv");

		// use data.insertionSort() for A track or
		// data.mergeSort() for the B track
		//data.insertionSort();
		data.mergeSort();
		
		// the below method might be useful in testing your
		// sorting algorithm
		//data.printSortedZipCodes();
		
		// uncomment one of the two lines below to test
		// a sequential search and a binary search
		//ZipCode zc = data.sequentialSearch("00210");
		ZipCode zc = data.binarySearch("00210");
		
		if(zc != null){
			System.out.println("Zip Code: " + zc.getZipCode() + " --> " + 
								zc.getCity() + ", " + zc.getState());
		}
		else{
			System.out.println("Zip code not found.");
		}
	}

}
