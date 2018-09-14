package cs3500.animator.model;

import cs3500.animator.control.TransitionVisitor;

/**
 * This is a transition for moving a shape in the animation.
 */
public class MoveTransition extends Transition {

  private double oldPosnX;
  private double oldPosnY;
  private double newPosnX;
  private double newPosnY;

  /**
   * Constructs a move transition with all of its fields.
   * @param startTime the time the transition starts
   * @param endTime the time the transition ends
   * @param oldPosnX the old x position
   * @param oldPosnY the old y position
   * @param newPosnX the new x position
   * @param newPosnY the new y position
   */
  public MoveTransition(int startTime, int endTime, double oldPosnX, double oldPosnY,
                        double newPosnX, double newPosnY) {
    super(startTime, endTime);
    this.oldPosnX = oldPosnX;
    this.oldPosnY = oldPosnY;
    this.newPosnX = newPosnX;
    this.newPosnY = newPosnY;
  }

  /**
   * Converts this transition into a string.
   * @param ticks the amount of ticks per second
   * @return the string representation of this transition
   */
  public String toString(int ticks) {

    double start = startTime / (double) ticks;
    double end = endTime / (double) ticks;
    return String.format("Shape %s moves from (" + this.oldPosnX + "," + this.oldPosnY
            + ") to (" + this.newPosnX + "," + this.newPosnY + ") from t=" + start + "s to t="
            + end + "s",
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
  public boolean overlap(MoveTransition t) {
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
   * Gets the oldPosnX.
   * @return the old x position
   */
  public double getOldPosnX() {
    return oldPosnX;
  }

  /**
   * Gets the oldPosnY.
   * @return the old y position
   */
  public double getOldPosnY() {
    return oldPosnY;
  }

  /**
   * Gets the newPosnX.
   * @return the new x position
   */
  public double getNewPosnX() {
    return newPosnX;
  }

  /**
   * Gets the newPosnY.
   * @return the new y position
   */
  public double getNewPosnY() {
    return newPosnY;
  }
}
