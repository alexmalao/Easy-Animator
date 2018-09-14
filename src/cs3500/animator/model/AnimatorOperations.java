package cs3500.animator.model;

import java.util.List;

/**
 * This is the interface of the Animator model. It is parameterized over the
 * shape type.  An Animator model represents a single animation as a list of shapes, which then
 * in turn hold the list of transitions.
 */
public interface AnimatorOperations {

  /**
   * Adds the shape to the model.
   * @param shape the shape to be added to the model
   */
  void addShape(IShape shape);

  /*
  Removed the getAnimationState method and moved it to the text view.
   */

  /**
   * Adds the transition to the model.
   * @param transition the transition to be added
   * @param shapeName the name of the shape for the transition
   */
  void addTransition(ITransition transition, String shapeName);

  // The following method was added to implement the visitor pattern on the model.
  /**
   * Get the list of shapes that have been added to the model.
   * @return the list of shapes
   */
  List<IShape> getShapes();
}
