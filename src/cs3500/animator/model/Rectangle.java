package cs3500.animator.model;

import java.awt.Color;

import cs3500.animator.control.ShapeVisitor;

/**
 * This is the rectangle that contains a width, height and left corner.
 */
public class Rectangle extends Shape {

  private double leftCornerX;
  private double leftCornerY;
  private double width;
  private double height;

  /**
   * Creates a rectangle with all the fields except for transitions.
   * @param name the name of rectangle
   * @param appears the time when the rectangle appears
   * @param disappears the time when the rectangle disappears
   * @param color the color of the shape
   * @param leftCornerX the left corner of the rectangle X coordinate
   * @param leftCornerY the left corner of the rectangle Y coordinate
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   */
  public Rectangle(String name, int appears, int disappears, Color color,
                   double leftCornerX, double leftCornerY, double width,
                   double height) {
    super(name, appears, disappears, color);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Invalid rectangle size.");
    }
    this.leftCornerX = leftCornerX;
    this.leftCornerY = leftCornerY;
    this.width = width;
    this.height = height;
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
            "Name: %s\nType: rectangle\nLower-left corner: (" + this.leftCornerX + ","
                    + this.leftCornerY + "), Width: " + this.width + ", Height: " + this.height
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
   * Gets the leftCornerX.
   * @return the left corner X value.
   */
  public double getLeftCornerX() {
    return leftCornerX;
  }

  /**
   * Gets the leftCornerY.
   * @return the left corner Y value.
   */
  public double getLeftCornerY() {
    return leftCornerY;
  }

  /**
   * Gets the width.
   * @return the width value.
   */
  public double getWidth() {
    return width;
  }


  /**
   * Gets the height.
   * @return the height value.
   */
  public double getHeight() {
    return height;
  }
}
