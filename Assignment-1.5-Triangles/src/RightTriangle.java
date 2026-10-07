/* RightTriangle.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.5-Triangles (due 10/23/08)
 * This is the right triangle derived class.
 */

public class RightTriangle extends Triangle {
	
	// We're assuming this is an isosceles right triangle and that aSide is one of its legs
	public RightTriangle(double aSide){
		super(aSide);
	}
	// Area is half of the square of one of the legs
	public double getArea(){
		return getSide() * getSide() / 2;
	}
	// Perimeter is (2 + square root(2)) times one of the legs 
	public double getPerimeter(){
		return (2 + Math.sqrt(2.0)) * getSide();
	}
}

