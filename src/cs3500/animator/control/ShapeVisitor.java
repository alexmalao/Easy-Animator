package cs3500.animator.control;

import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;

/**
 * The visitor to return specific outputs based on the different types of shape.
 */
public interface ShapeVisitor<R> {

  /**
   * Applies this visitor onto the shape to specify the class to work on.
   * @param shape the shape for this visitor to work on
   * @return the return object of the visitor
   */
  R apply(IShape shape);

  /**
   * Visits the oval.
   * @param oval the oval for this visitor to work on
   * @return the return object of the visitor
   */
  R visit(Oval oval);

  /**
   * Visits the rectangle.
   * @param rect the rectangle for this visitor to work on
   * @return the return object of the visitor
   */
  R visit(Rectangle rect);
}
