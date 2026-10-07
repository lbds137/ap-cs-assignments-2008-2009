/* LineEquation.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Classwork
 * The program accepts two ordered pairs and yields the equation of the line containing the points in slope / intercept form.
 */

import java.util.Scanner;

public class LineEquation {
	public static void main(String[] args) {
		
		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		// Get ordered pairs from user
		System.out.println("This program will return a linear equation in y = mx + b form for any two points entered.");
		System.out.println("Enter a point in the format x,y: ");
		String orderedPairInput1 = keyboard.nextLine();
		System.out.println("Enter another point in the format x,y: ");
		String orderedPairInput2 = keyboard.nextLine();
		
		// Split up each ordered pair string into a string array and store each number as a separate double
		String[] orderedPairStr1 = orderedPairInput1.split(",");
		String[] orderedPairStr2 = orderedPairInput2.split(",");
		double xcoord1 = Double.parseDouble(orderedPairStr1[0]);
		double ycoord1 = Double.parseDouble(orderedPairStr1[1]);
		double xcoord2 = Double.parseDouble(orderedPairStr2[0]);
		double ycoord2 = Double.parseDouble(orderedPairStr2[1]);
		
		// Get the slope and Y-intercept
		double slope = (ycoord2 - ycoord1) / (xcoord2 - xcoord1);
		double yintercept = ycoord1 - (slope * xcoord1);
		
		// Negative sign if Y-intercept is negative
		String operationsign = " ";
		if (yintercept >= 0)
			operationsign = " + ";
		else
			operationsign = " - ";
			yintercept = Math.abs(yintercept);
		
		// Display equation
		System.out.println("Equation: y = " + slope + "x" + operationsign + yintercept);
	}
}
