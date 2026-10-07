/* GraphingCalc.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-2.1-GraphingCalc (due 11/7/08)
 * This is the GraphingCalc class, which is a rudimentary, linear graphing calculator.
 */

public class GraphingCalc {

	// Declare useful variables
	private char[][] graph;
	private int rowIndex;
	private int colIndex;
	
	public GraphingCalc(int height, int width){
		
		rowIndex = (int) (height * 2 + 1);
		colIndex = (int) (width * 2 + 1);
		
		graph = new char[rowIndex][colIndex];
		
		// Draw dots everywhere
		for(int i = 0; i < rowIndex; i++){
			for(int j = 0; j < colIndex; j++){
				graph[i][j] = '.';
			}
		}
		// Draw the Y axis
		for(int i = 0; i < rowIndex; i++){
			graph[i][(rowIndex - 1) / 2] = '|';
		}
		// Draw the X axis
		for(int i = 0; i < colIndex; i++){
			graph[(colIndex - 1) / 2][i] = '–';
		}
		// Draw the origin
		graph[(rowIndex - 1) / 2][(colIndex - 1) / 2] = '+';
	}
	
	public void printGraph(){
		
		for(int i = 0; i < rowIndex; i++){
			for(int j = 0; j < colIndex; j++){
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void addLinearEqn(double m, double b, char symbol){
		double xValue;
		double yValue;
		
		// Negative signs in front of xValue and yValue because we print from the top down, not from the bottom up
		for(xValue = -(colIndex - 1) / 2; xValue < (colIndex - 1) / 2; xValue++){
			yValue = (int) -(m * xValue + b);
			
			// This "if" makes sure we don't try to graph outside of the window (i.e. get an ArrayOutOfBoundsException)
			if(yValue >= -(rowIndex - 1) / 2 && yValue < (rowIndex - 1) / 2){
				int yIndex = (int) (yValue + (rowIndex - 1) / 2);
				int xIndex = (int) (xValue + (colIndex - 1) / 2);
				
				// If the line intersects anything other than the axes, origin, or whitespace, draw an "O"
				if(graph[yIndex][xIndex] == '.' || graph[yIndex][xIndex] == '–' || graph[yIndex][xIndex] == '|' || graph[yIndex][xIndex] == '+'){
					graph[yIndex][xIndex] = symbol;
				}
				else{
					graph[yIndex][xIndex] = 'O';
				}
			}
		}
		// Print the equation for the line and the legend (i.e. what symbol was used for it)
		System.out.println(symbol + ": y = " + m + "x + " + b);
	}
	public void addQuadEqn(double a, double b, double c, char symbol){
		int xValue;
		int yValue;
		
		// Negative signs in front of xValue and yValue because we print from the top down, not from the bottom up
		for(xValue = (int) -(colIndex - 1) / 2; xValue < (colIndex - 1) / 2; xValue++){
			yValue = (int) -(a * xValue * xValue + b * xValue + c);
			
			// This "if" makes sure we don't try to graph outside of the window (i.e. get an ArrayOutOfBoundsException)
			if(yValue >= -(rowIndex - 1) / 2 && yValue < (rowIndex - 1) / 2){
				int yIndex = (int) (yValue + (rowIndex - 1) / 2);
				int xIndex = (int) (xValue + (colIndex - 1) / 2);
				
				// If the line intersects anything other than the axes, origin, or whitespace, draw an "O"
				if(graph[yIndex][xIndex] == '.' || graph[yIndex][xIndex] == '–' || graph[yIndex][xIndex] == '|' || graph[yIndex][xIndex] == '+'){
					graph[yIndex][xIndex] = symbol;
				}
				else{
					graph[yIndex][xIndex] = 'O';
				}
			}
		}
		// Print the equation for the line and the legend (i.e. what symbol was used for it)
		System.out.println(symbol + ": y = " + a + "x^2 + " + b + "x + " + c);
	}
}
