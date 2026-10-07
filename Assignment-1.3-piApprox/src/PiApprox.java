/* PiApprox.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.3-nFactorial (due 10/1/08)
 * This program will calculate an approximation of pi.
 */

public class PiApprox {
	public static void main(String[] args) {
		double piApprox = 0;
		int n = 1;
		
		for(;n < 10000; n++){
			// Calculate the numerator and denominator of a term in the infinite series
			double fractionNumerator = Math.pow(-1, n - 1);
			double fractionDenominator = 2*n - 1;
			
			// Calculate a term and add it to the value of piApprox
			piApprox += fractionNumerator / fractionDenominator;
		}
		// Multiply piApprox by 4 to reach the final value
		piApprox *= 4;
		
		// Display both the approximation and the actual value of pi for comparison purposes
		System.out.println("Our approximation: " + piApprox);
		System.out.println("Actual value of pi: " + Math.PI);
		System.out.println("n = " + n);
	}

}
