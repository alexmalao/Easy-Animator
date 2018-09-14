package cs3500.animator.model;

import cs3500.animator.control.TransitionVisitor;

/**
 * This is a transition for scaling a shape in the animation.
 */
public class ScaleTransition extends Transition {

  private double oldHeight;
  private double oldWidth;
  private double newHeight;
  private double newWidth;

  /**
   * Constructs a move transition with all of its fields.
   * @param startTime the time the transition starts
   * @param endTime the time the transition ends
   * @param oldHeight the old height
   * @param oldWidth the old weight
   * @param newHeight the new height
   * @param newWidth the new weight
   */
  public ScaleTransition(int startTime, int endTime, double oldHeight, double oldWidth,
                        double newHeight, double newWidth) {
    super(startTime, endTime);
    this.oldHeight = oldHeight;
    this.oldWidth = oldWidth;
    this.newHeight = newHeight;
    this.newWidth = newWidth;
  }

  /**
   * Converts this transition into a string.
   * @param ticks the amount of ticks per second
   * @return the string representation of this transition
   */
  public String toString(int ticks) {

    double start = startTime / (double) ticks;
    double end = endTime / (double) ticks;
    if (this.shape instanceof Rectangle) {
      return String.format("Shape %s scales from Width: " + this.oldWidth + ", Height: "
                      + this.oldHeight + " to Width: " + this.newWidth + ", Height: "
                      + this.newHeight + " from t=" + start + "s to t=" + end + "s",
              this.shape.getName());
    }
    else {
      return String.format("Shape %s scales from X radius: " + this.oldWidth + ", Y radius: "
                      + this.oldHeight + " to X radius: " + this.newWidth + ", Y radius: "
                      + this.newHeight + " from t=" + start + "s to t=" + end + "s",
              this.shape.getName());
    }
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
  public boolean overlap(ScaleTransition t) {
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
   * Gets the oldHeight.
   * @return the previous height
   */
  public double getOldHeight() {
    return oldHeight;
  }

  /**
   * Gets the oldWidth.
   * @return the previous width
   */
  public double getOldWidth() {
    return oldWidth;
  }

  /**
   * Gets the newHeight.
   * @return the new height
   */
  public double getNewHeight() {
    return newHeight;
  }

  /**
   * Gets the oldWidth.
   * @return the new width
   */
  public double getNewWidth() {
    return newWidth;
  }
}
