package cs3500.animator.model;

import java.util.List;

import cs3500.animator.control.ShapeVisitor;

/**
 * This is a representation of the shape. It holds all the necessary
 * functionality for a shape.
 */
public interface IShape {

  /**
   * Converts this shape to a string representation.
   * @param ticks the amount of ticks per second
   * @return the string representation of the shape
   */
  String toString(int ticks);

  /**
   * Gets the name of the shape.
   * @return the name of the shape
   */
  String getName();

  /**
   * Adds a transition to the shapes.
   * @param t the transition to be added to the shape
   */
  void addTransition(ITransition t);

  /**
   * Gets the transitions from the shape.
   * @return the transitions from the shape
   */
  List<ITransition> getTransitions();

  /**
   * Gets the time that the shape appears.
   * @return the time the shape appears
   */
  public int getAppears();

  /**
   * Gets the time that the shape disappears.
   * @return the time the shape disappears
   */
  public int getDisappears();

  /**
   * Accepts the visitor and does the function on this object.
   * @param visitor the visitor to be used on this shape
   * @param <R> the return type of the visitor
   * @return the return object of the visitor
   */
  <R> R accept(ShapeVisitor<R> visitor);
}
