package cs3500.animator.provider.property;

import cs3500.animator.provider.property.posn.Posn;

/**
 * Represents the dimensions of a shape.
 */

public class ShapeDimensions extends ShapePropertyAbstract<Posn> {

  /**
   * Creates a new ShapeDimensions with the given values.
   * @param val initial value.
   */

  public ShapeDimensions(Posn val) {
    super(val);
  }

  @Override
  public String toString() {
    return "(" + this.val.getX() + "," + this.val.getY() + ")";
  }

  @Override
  public String getDescVerb() {
    return "scales";
  }
}
