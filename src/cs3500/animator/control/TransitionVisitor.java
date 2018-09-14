package cs3500.animator.control;

import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.ITransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.ScaleTransition;

/**
 * An interface for a transition visitor parametrized over the return type.
 */
public interface TransitionVisitor<R> {

  /**
   * Applies this visitor onto the transition to specify the class to work on.
   * @param trans the transition for this visitor to work on
   * @return the return object of the visitor
   */
  R apply(ITransition trans);

  /**
   * Visits the move transition.
   * @param move the move transition for this visitor to work on
   * @return the return object of the visitor
   */
  R visit(MoveTransition move);

  /**
   * Visits the scale transition.
   * @param scale the scale transition for this visitor to work on
   * @return the return object of the visitor
   */
  R visit(ScaleTransition scale);

  /**
   * Visits the color transition.
   * @param color the color transition for this visitor to work on
   * @return the return object of the visitor
   */
  R visit(ColorTransition color);
}
