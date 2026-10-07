/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-2.1-GraphingCalc (due 11/7/08)
 * This is the Tester class.
 */

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int graphWindow;
		String inputStr = "";
		boolean yesOrNoBoolean = false;
		
		System.out.println("Welcome to Bane's graphing calculator program!\n");
		
		// Prompt the user for the graph window
		System.out.println("How wide do you want the graphing window to be? \nFor example, entering 10 sets xMax and yMax to 10 and xMin and yMin to -10.");
		inputStr = scanner.nextLine();
		graphWindow = Validation.checkIfInt(inputStr, "Please enter a positive integer.");
		
		// Initialize the graphing calculator based on the graph window (only square windows allowed)
		GraphingCalc graphC = new GraphingCalc(graphWindow, graphWindow);
		
		// Variables we need for a linear equation
		double slope = 0;
		double yIntercept = 0;
		char lineSymbol = '.';
		boolean drawLine = true;
		
		// Variables we need for a quadratic equation
		double aCoeff = 0;
		double bCoeff = 0;
		double cCoeff = 0;
		char quadSymbol = '.';
		boolean drawQuad = true;
		
		// Ask the user if he/she wants to graph a line
		System.out.println("Would you like to graph a line? (yes/no)");
		inputStr = scanner.nextLine();
		yesOrNoBoolean = Validation.checkIfYesorNo(inputStr, "Please say either \"yes\" or \"no\".");
		
		// If the user wants a line, prompt him/her for the slope, the y-intercept, 
		// and the symbol to be used for the graph
		if(yesOrNoBoolean){
			System.out.println("Please enter a number for the slope.");
			inputStr = scanner.nextLine();
			slope = Validation.checkIfDouble(inputStr, "Invalid entry. Please enter a valid number.");
			
			System.out.println("Please enter a number for the y-intercept.");
			inputStr = scanner.nextLine();
			yIntercept = Validation.checkIfDouble(inputStr, "Invalid entry. Please enter a valid number.");
			
			System.out.println("Please choose a symbol that will be used to graphically represent the line.");
			inputStr = scanner.nextLine();
			lineSymbol = inputStr.charAt(0);
		}
		else{
			drawLine = false; 
		}
		
		// Ask the user if he wants to graph a quadratic equation
		System.out.println("Would you like to graph a quadratic equation? (yes/no)");
		inputStr = scanner.nextLine();
		yesOrNoBoolean = Validation.checkIfYesorNo(inputStr, "Please say either \"yes\" or \"no\".");
		
		// If the user wants a quadratic equation, prompt him/her for the a coefficient, 
		// b coefficient, the c coefficient, and the symbol to be used for the graph
		if(yesOrNoBoolean){
			System.out.println("Please enter a number for the a coefficient.");
			inputStr = scanner.nextLine();
			aCoeff = Validation.checkIfDouble(inputStr, "Invalid entry. Please enter a valid number.");
			
			System.out.println("Please enter a number for the b coefficient.");
			inputStr = scanner.nextLine();
			bCoeff = Validation.checkIfDouble(inputStr, "Invalid entry. Please enter a valid number.");
			
			System.out.println("Please enter a number for the c coefficient.");
			inputStr = scanner.nextLine();
			cCoeff = Validation.checkIfDouble(inputStr, "Invalid entry. Please enter a valid number.");
			
			System.out.println("Please choose a symbol that will be used to graphically represent the quadratic equation.");
			inputStr = scanner.nextLine();
			quadSymbol = inputStr.charAt(0);
		}
		else{
			drawQuad = false;
		}
		
		// If the user wants to graph a line, pass the necessary values to the addLinearEqn method
		if(drawLine == true){
			graphC.addLinearEqn(slope, yIntercept, lineSymbol);
		}
		// If the user wants to graph a quadratic equation, pass the necessary values to the addQuadEqn method
		if(drawQuad == true){
			graphC.addQuadEqn(aCoeff, bCoeff, cCoeff, quadSymbol);
		}
		
		// Draw the graph	
		graphC.printGraph();
	}

}
