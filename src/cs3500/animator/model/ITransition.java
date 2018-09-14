package cs3500.animator.model;

import cs3500.animator.control.TransitionVisitor;

/**
 * This is a representation for transition. It is able to modify shapes and act as commands.
 */
public interface ITransition extends Comparable<ITransition> {

  /**
   * Gets the start time of this transition.
   * @return the start time of this transition
   */
  int getStartTime();

  /**
   * Gets the end time of this transition.
   * @return the end time of this transition
   */
  int getEndTime();

  /**
   * Adds the shape to the transition.
   * @param shape the shape to be added
   */
  void addShape(IShape shape);

  /**
   * Converts this transition to a string representation.
   * @param ticks the amount of ticks per second
   * @return the string representation of the transition
   */
  String toString(int ticks);

  /**
   * Compares the two transitions based on their start times.
   * @param t the transition to be compared with this transition
   */
  int compareTo(ITransition t);

  /**
   * Checks whether this transition overlaps with the given transition.
   * @param t the given transition
   * @return whether the transitions overlap
   */
  boolean overlap(ITransition t);

  /**
   * Checks whether this transition overlaps with the given move transition.
   * @param t the given move transition
   * @return whether the transitions overlap
   */
  boolean overlap(MoveTransition t);

  /**
   * Checks whether this transition overlaps with the given scale transition.
   * @param t the given scale transition
   * @return whether the transitions overlap
   */
  boolean overlap(ScaleTransition t);

  /**
   * Checks whether this transition overlaps with the given color transition.
   * @param t the given color transition
   * @return whether the transitions overlap
   */
  boolean overlap(ColorTransition t);

  /**
   * Accepts the visitor and does the function on this object.
   * @param visitor the visitor to be used on this shape
   * @param <R> the return type of the visitor
   * @return the return object of the visitor
   */
  <R> R accept(TransitionVisitor<R> visitor);
}
