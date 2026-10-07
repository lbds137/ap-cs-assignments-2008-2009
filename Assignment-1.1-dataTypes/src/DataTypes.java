/* DataTypes.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.1-dataTypes (due 9/12/08)
 * The program stores and displays some variable values, demonstrating my understanding of how data types work.
 */

public class DataTypes {

	public static void main(String[] args) {

		// Declare variables
		int integername = 137;
		double doublename = 97879.168123;
		char charactername = 70;
		boolean booleanname = false;
		String stringname = "BOO";
		final int CONSTANT_NAME = 6172;

		// Create variables containing maximum values
		int intmaxvalue = Integer.MAX_VALUE;
		double doublemaxvalue = Double.MAX_VALUE;
		char charmaxvalue = Character.MAX_VALUE;

		// Create variables containing minimum values
		int intminvalue = Integer.MIN_VALUE;
		double doubleminvalue = Double.MIN_VALUE;
		char charminvalue = Character.MIN_VALUE;

		// Display variable values and maximum / minimum values
		System.out.println("Integer value: " + integername + "; "
				+ "Integer maximum value: " + intmaxvalue + "; "
				+ "Integer minimum value: " + intminvalue);
		System.out.println("Double value: " + doublename + "; "
				+ "Double maximum value: " + doublemaxvalue + "; "
				+ "Double minimum value: " + doubleminvalue);
		System.out.println("Character value: " + charactername + "; "
				+ "Character maximum value: " + charmaxvalue + "; "
				+ "Character minimum value: " + charminvalue);
		System.out.println("Boolean value: " + booleanname);
		System.out.println("String value: " + stringname);
		System.out.println("Constant value: " + CONSTANT_NAME);

	}

}
