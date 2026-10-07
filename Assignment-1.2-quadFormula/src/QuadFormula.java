/* QuadFormula.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.2-quadFormula (due 9/22/08)
 * The program accepts values for coefficients a, b, and c 
 * and inputs them into the quadratic formula to yield one, two, or no x values.
 */

import java.util.Scanner;

public class QuadFormula {
	public static void main(String[] args) {

		// Initialize scanner and print introductory message to user
		Scanner keyboard = new Scanner(System.in);
		System.out.println("This program will return the solutions to a quadratic equation in general form provided the coeffiecients a, b, and c.");

		// Get the value of coefficient a from the user, verify it to make sure
		// it is a number, and store it as a double
		System.out.println("\nEnter the value of coefficient a: ");
		String coeffastr = keyboard.nextLine();
		double coeffa = checkIfDouble(coeffastr, "a");

		// Get the value of coefficient b from the user, verify it to make sure
		// it is a number, and store it as a double
		System.out.println("\nEnter the value of coefficient b: ");
		String coeffbstr = keyboard.nextLine();
		double coeffb = checkIfDouble(coeffbstr, "b");

		// Get the value of coefficient c from the user, verify it to make sure
		// it is a number, and store it as a double
		System.out.println("\nEnter the value of coefficient c: ");
		String coeffcstr = keyboard.nextLine();
		double coeffc = checkIfDouble(coeffcstr, "c");

		// Calculate the x values using the quadratic formula
		double discriminant = Math.pow(coeffb, 2.0) - (4 * coeffa * coeffc);
		double xresult1 = (-coeffb + Math.pow(discriminant, 0.5))
		/ (2 * coeffa);
		double xresult2 = (-coeffb - Math.pow(discriminant, 0.5))
		/ (2 * coeffa);

		// Display solutions and handle special cases
		if (Double.isNaN(xresult1) && Double.isNaN(xresult2)) {
			System.out.println("\nThere are no solutions to the equation.");
		} else if (xresult1 == xresult2) {
			System.out.println("\nx = " + xresult1);
		} else {
			System.out.println("\nx = " + xresult1 + " or x = " + xresult2);
		}
	}

	public static double checkIfDouble(String inputstr, String coeffname) {

		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		double inputdouble;

		// Loop to check if input is a number using try-catch; every time an
		// error is thrown we reset the loop
		for (int i = 0; i < 2; i++) {
			try {
				inputdouble = Double.parseDouble(inputstr);
			} catch (NumberFormatException exception) {
				System.out.println("Please enter a number for coefficient "
						+ coeffname + ".");
				i = 0;
				inputstr = keyboard.nextLine();
			}
		}

		// Once loop is over we know that the input is a number, so we can
		// assign its value to our double
		inputdouble = Double.parseDouble(inputstr);

		return inputdouble;
	}
}