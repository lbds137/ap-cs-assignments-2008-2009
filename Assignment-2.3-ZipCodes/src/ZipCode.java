/* ZipCode.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-2.3-ZipCodes (due 11/25/08)
 * This is the ZipCode class.
 */

public class ZipCode {
	// Declare variables we'll need
	private String zipCode = "";
	private String city = "";
	private String state = "";
	
	// ZipCode constructor takes values for zip code, city, state, and stores them in the appropriate variables
	public ZipCode(String aZipCode, String aCity,  String aState) {
		zipCode = aZipCode;
		city = aCity;
		state = aState;
	}
	// Zip code getter method
	public String getZipCode() {
		return zipCode;
	}
	// City getter method
	public String getCity() {
		return city;
	}
	//State getter method
	public String getState() {
		return state;
	}
}
