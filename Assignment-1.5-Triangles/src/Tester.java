/* Tester.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.5-Triangles (due 10/23/08)
 * This is the tester class.
 */

public class Tester {
	public static void main(String[] args)
	  {
	    // Make an equilateral triangle and a right triangle, both of side length 12 (for the right triangle 12 is a leg)
		Triangle equilateralTr = new EquilateralTriangle(12);
	    Triangle rightTr = new RightTriangle(12);
	    
	    // Print the areas of the two triangles
	    System.out.println("The area of the equilateral triangle is " + equilateralTr.getArea() + ".");
	    System.out.println("The area of the right triangle is " + rightTr.getArea() + ".\n");
	    
	    // Print the perimeters of the two triangles
	    System.out.println("The perimeter of the equilateral triangle is " + equilateralTr.getPerimeter() + ".");
	    System.out.println("The perimeter of the right triangle is " + rightTr.getPerimeter() + ".\n");
	    
	    // Print the area-perimeter ratios of the two triangles
	    System.out.println("The area-perimeter ratio of the equilateral triangle is " + equilateralTr.getRatio() + ".");
	    System.out.println("The area-perimeter ratio of the right triangle is " + rightTr.getRatio() + ".");
	  }
}
