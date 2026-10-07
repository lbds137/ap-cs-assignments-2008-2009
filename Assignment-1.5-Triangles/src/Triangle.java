/* Triangle.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.5-Triangles (due 10/23/08)
 * This is the triangle abstract class.
 */

public abstract class Triangle
{
  private double side;

  public Triangle(double aSide)
  {
    side = aSide;
  }

  // Abstract methods we will deal with in our derived classes
  public abstract double getPerimeter();
  public abstract double getArea();
  
  // Method to get the ratio of the triangle's area to its perimeter
  public double getRatio()
  {
    return getArea()/getPerimeter();
  }
  
  // Getter method for the triangle's side
  public double getSide() {
	return side;
  }
}  
