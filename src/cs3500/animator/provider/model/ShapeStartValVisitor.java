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
 * The visitor for getting the start value of the shape transition.
 */
public class ShapeStartValVisitor implements TransitionVisitor<ShapeProperty<?>> {

  /**
   * Applies this visitor on the given transition.
   * @param trans the transition for this visitor to work on
   * @return a Shape Property modified by the transition
   */
  @Override
  public ShapeProperty<?> apply(ITransition trans) {
    return trans.accept(this);
  }

  /**
   * Visits move transition.
   * @param move the move transition for this visitor to work on
   * @return ShapePosn property
   */
  @Override
  public ShapeProperty<?> visit(MoveTransition move) {
    return new ShapePosn(new Posn((float)move.getOldPosnX(), (float)move.getOldPosnY()));
  }

  /**
   * Visits scale transition.
   * @param scale the scale transition for this visitor to work on
   * @return ShapeDimensions property
   */
  @Override
  public ShapeProperty<?> visit(ScaleTransition scale) {
    return new ShapeDimensions(new Posn((float)scale.getOldHeight(), (float)scale.getOldWidth()));
  }

  /**
   * Visits color transition.
   * @param color the color transition for this visitor to work on
   * @return ShapeColor property
   */
  @Override
  public ShapeProperty<?> visit(ColorTransition color) {
    return new ShapeColor(color.getOldColor());
  }
}
