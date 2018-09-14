package cs3500.animator.provider.property;

import cs3500.animator.provider.property.posn.Posn;

/**
 * Represents the position of a shape.
 */
public class ShapePosn extends ShapePropertyAbstract<Posn> {
  /**
   * Creates a new ShapePosn with the given values.
   * @param val initial value.
   */
  public ShapePosn(Posn val) {
    super(val);
  }

  @Override
  public String toString() {
    return "(" + this.val.getX() + "," + this.val.getY() + ")";
  }

  @Override
  public String getDescVerb() {
    return "moves";
  }
}
