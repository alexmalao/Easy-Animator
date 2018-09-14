package cs3500.animator.model;

import java.awt.Color;

import cs3500.animator.control.TransitionVisitor;

/**
 * This is a transition for changing a color in the animation.
 */
public class ColorTransition extends Transition {

  private Color oldColor;
  private Color newColor;

  /**
   * Constructs a color transition.
   * @param startTime the time the transition starts
   * @param endTime the time the transition ends
   * @param oldColor the old color
   * @param newColor the new color
   */
  public ColorTransition(int startTime, int endTime, Color oldColor, Color newColor) {
    super(startTime, endTime);
    this.oldColor = oldColor;
    this.newColor = newColor;
  }

  /**
   * Converts this transition into a string.
   * @param ticks the amount of ticks per second
   * @return the string representation of this transition
   */
  public String toString(int ticks) {

    double start = startTime / (double) ticks;
    double end = endTime / (double) ticks;
    return String.format("Shape %s changes color from (" + this.oldColor.getRed() + ".0,"
                    + this.oldColor.getGreen() + ".0," + this.oldColor.getBlue()
                    + ".0) to (" + this.newColor.getRed() + ".0," + this.newColor.getGreen() + ".0,"
                    + this.newColor.getBlue() + ".0) from t=" + start + "s to t=" + end + "s",
            this.shape.getName());
  }

  /**
   * Checks whether this transition overlaps with the given transition.
   * @param t the given transition
   * @return whether the transitions overlap
   */
  public boolean overlap(ITransition t) {
    return t.overlap(this);
  }

  /**
   * Checks whether this transition overlaps with the given transition.
   * @param t the given transition
   * @return whether the transitions overlap
   */
  public boolean overlap(ColorTransition t) {
    return this.getStartTime() < t.getEndTime()
            && this.getEndTime() > t.getStartTime();
  }

  /**
   * Accepts the visitor and does the function on this object.
   * @param visitor the visitor to be used on this shape
   * @param <R> the return type of the visitor
   * @return the return object of the visitor
   */
  public <R> R accept(TransitionVisitor<R> visitor) {
    return visitor.visit(this);
  }

  /*
  Below getters are added for visitor the visitor pattern.
   */

  /**
   * Gets the oldColor.
   * @return the previous color
   */
  public Color getOldColor() {
    return oldColor;
  }

  /**
   * Gets the newColor.
   * @return the new Color
   */
  public Color getNewColor() {
    return newColor;
  }
}
