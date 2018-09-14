package cs3500.animator.provider.view.interpol;

import cs3500.animator.provider.property.ShapeDimensions;
import cs3500.animator.provider.property.posn.Posn;

/**
 * Interpolation across dimensions to create scaling.
 * 
 * @author Anand
 *
 */
public class ScaleInterpolation extends Interpolation<ShapeDimensions> {

  @Override
  public ShapeDimensions interpolate(ShapeDimensions start, ShapeDimensions end, double a,
      double b, double t) {
    Posn startVal = start.getValue();
    Posn endVal = end.getValue();

    float newX = (float) apply(a, b, startVal.getX(), endVal.getX(), t);
    float newY = (float) apply(a, b, startVal.getY(), endVal.getY(), t);

    return new ShapeDimensions(new Posn(newX, newY));
  }
}
