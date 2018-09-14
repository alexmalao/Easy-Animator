package cs3500.animator.model;

import java.awt.Color;

import cs3500.animator.control.ShapeVisitor;

/**
 * This is the rectangle that contains a x radius, y radius and center.
 */
public class Oval extends Shape {

  private double centerX;
  private double centerY;
  private double radiusX;
  private double radiusY;

  /**
   * Constructs an oval with all the fields except for transitions.
   * @param name the name of oval
   * @param appears the time the oval appears
   * @param disappears the time the oval disappears
   * @param color the color of the oval
   * @param centerX the center of the oval X coordinate
   * @param centerY the center of the oval Y coordinate
   * @param radiusX the horizontal radius of the oval
   * @param radiusY the vertical radius of the oval
   */
  public Oval(String name, int appears, int disappears, Color color,
                   double centerX, double centerY, double radiusX,
                   double radiusY) {
    super(name, appears, disappears, color);
    if (radiusX < 0 || radiusY < 0) {
      throw new IllegalArgumentException("Invalid oval size.");
    }
    this.centerX = centerX;
    this.centerY = centerY;
    this.radiusX = radiusX;
    this.radiusY = radiusY;
  }

  /**
   * Returns a string representation of the rectangle based on its fields.
   * @param ticks the amount of ticks per second
   * @return string representation of the rectangle shape
   */
  public String toString(int ticks) {

    double start = this.appears / (double) ticks;
    double end = this.disappears / (double) ticks;
    return String.format(
            "Name: %s\nType: oval\nCenter: (" + this.centerX + ","
                    + this.centerY + "), X radius: " + this.radiusX + ", Y radius: " + this.radiusY
                    + ", Color: (%d.0,%d.0,%d.0)\n"
                    + "Appears at t=" + start + "s\nDisappears at t=" + end + "s",
            this.name, this.color.getRed(), this.color.getGreen(),
            this.color.getBlue());
  }

  /**
   * Accepts the visitor and does the function on this object.
   * @param visitor the visitor to be used on this shape
   * @param <R> the return type of the visitor
   * @return the return object of the visitor
   */
  public <R> R accept(ShapeVisitor<R> visitor) {
    return visitor.visit(this);
  }

  /*
  Below getters are added for visitor the visitor pattern.
   */

  /**
   * Gets the centerX.
   * @return the center X value
   */
  public double getCenterX() {
    return centerX;
  }

  /**
   * Gets the centerY.
   * @return the center Y value
   */
  public double getCenterY() {
    return centerY;
  }

  /**
   * Gets the radiusX.
   * @return the radius X value
   */
  public double getRadiusX() {
    return radiusX;
  }

  /**
   * Gets the radiusY.
   * @return the radius Y value
   */
  public double getRadiusY() {
    return radiusY;
  }
}
