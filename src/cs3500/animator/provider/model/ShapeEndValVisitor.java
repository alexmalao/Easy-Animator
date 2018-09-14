package cs3500.animator.provider.model;

import cs3500.animator.control.TransitionVisitor;
import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.ITransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.provider.property.ShapeColor;
import cs3500.animator.provider.property.ShapeDimensions;
import cs3500.animator.provider.property.ShapePosn;
import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.property.posn.Posn;

/**
 * Visitor that returns the end time of a shape transition.
 */
public class ShapeEndValVisitor implements TransitionVisitor<ShapeProperty<?>> {

  /**
   * Applies the visitor.
   * @param trans the transition for this visitor to work on
   */
  @Override
  public ShapeProperty<?> apply(ITransition trans) {
    return trans.accept(this);
  }

  /**
   * Visits the move transition.
   * @param move the move transition for this visitor to work on
   */
  @Override
  public ShapeProperty<?> visit(MoveTransition move) {
    return new ShapePosn(new Posn((float)move.getNewPosnX(), (float)move.getNewPosnY()));
  }

  /**
   * Visits the scale transition.
   * @param scale the scale transition for this visitor to work on
   */
  @Override
  public ShapeProperty<?> visit(ScaleTransition scale) {
    return new ShapeDimensions(new Posn((float)scale.getNewHeight(), (float)scale.getNewWidth()));
  }

  /**
   * Visits the color transition.
   * @param color the color transition for this visitor to work on
   */
  @Override
  public ShapeProperty<?> visit(ColorTransition color) {
    return new ShapeColor(color.getNewColor());
  }
}
