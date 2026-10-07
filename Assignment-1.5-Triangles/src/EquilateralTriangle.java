/* EquilateralTriangle.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.5-Triangles (due 10/23/08)
 * This is the equilateral triangle derived class.
 */

public class EquilateralTriangle extends Triangle {
	public EquilateralTriangle(double aSide){
		super(aSide);
	}
	// Area is the square of one of the sides times (square root(3) divided by 4)
	public double getArea(){
		return Math.sqrt(3) / 4 * getSide() * getSide();
	}
	// Perimeter is 3 times one of the sides
	public double getPerimeter(){
		return 3 * getSide();
	}
}
