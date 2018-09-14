package cs3500.animator.provider.model;

import cs3500.animator.control.ShapeVisitor;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;

/**
 * Returns the name of the type of shape.
 */
public class ShapeNameVisitor implements ShapeVisitor<String> {

  /**
   * Applies the visitor on the shape.
   * @param shape the shape for this visitor to work on
   * @return the type of shape
   */
  @Override
  public String apply(IShape shape) {
    return shape.accept(this);
  }

  /**
   * Returns oval.
   * @param oval the oval for this visitor to work on
   * @return "oval"
   */
  @Override
  public String visit(Oval oval) {
    return "oval";
  }

  /**
   * Returns rectangle.
   * @param rect the rectangle for this visitor to work on
   * @return "rectangle".
   */
  @Override
  public String visit(Rectangle rect) {
    return "rectangle";
  }
}
