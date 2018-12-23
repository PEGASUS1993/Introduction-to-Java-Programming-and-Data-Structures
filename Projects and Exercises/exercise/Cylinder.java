/**
 * Title:        Chapter 8, "Class Inheritance and Interfaces"
 * Description:  Examples for Chapter 8
 * Copyright:    Copyright (c) 2000
 * Company:      Armstrong Atlantic State University
 * @author Y. Daniel Liang
 * @version 1.0
 */

// Cylinder.java: The new cylinder class that extends the circle
// class
public class Cylinder extends Circle {
  private double length;

  /**Default constructor*/
  public Cylinder() {
    this(1.0, 1.0);
  }

  /**Construct a cylinder with specified radius, and length*/
  public Cylinder(double radius, double length) {
    this(radius, "white", false, length);
  }

  /**Construct a cylinder with specified radius, filled, color, and
     length
   */
  public Cylinder(double radius,
    String color, boolean filled, double length) {
    super(radius, color, filled);
    this.length = length;
  }

  /**Return length*/
  public double getLength() {
    return length;
  }

  /**Set a new length*/
  public void setLength(double length) {
    this.length = length;
  }

  /**Return the surface area of this cylinder*/
  public double getArea() {
    return 2*super.getArea()+(2*getRadius()*Math.PI)*length;
  }

  /**Return the volume of this cylinder*/
  public double findVolume() {
    return super.getArea()*length;
  }

  /**Override the equals method defined in the Object class*/
  public boolean equals(Cylinder cylinder) {
    return (this.getRadius() == cylinder.getRadius()) &&
      (this.length == cylinder.getLength());
  }

  @Override 
  public String toString() {
    return "[Cylinder] radius = " + getRadius() + " and length "
      + length;
  }
}
