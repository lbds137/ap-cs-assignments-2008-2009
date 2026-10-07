/* Cipher.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-2.2-Cipher (due 11/19/08)
 * This is the main entry point for the cipher assignment.
 */
public class Cipher {

	public static void main(String[] args) {
			
		Decoder dec = new Decoder("encoded.txt");
		dec.decodeFile("charFrequencies.txt", "decoded.txt");
		System.out.println("File has been decoded and saved to decoded.txt!");
	}

}
