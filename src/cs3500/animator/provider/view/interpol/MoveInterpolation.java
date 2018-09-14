package cs3500.animator.provider.view.interpol;

import cs3500.animator.provider.property.ShapePosn;
import cs3500.animator.provider.property.posn.Posn;

/**
 * Interpolation across coordinates to create movement.
 * 
 * @author Anand
 *
 */
public class MoveInterpolation extends Interpolation<ShapePosn> {

  @Override
  public ShapePosn interpolate(ShapePosn start, ShapePosn end, double a, double b, double t) {
    Posn startVal = start.getValue();
    Posn endVal = end.getValue();

    float newX = (float) apply(a, b, startVal.getX(), endVal.getX(), t);
    float newY = (float) apply(a, b, startVal.getY(), endVal.getY(), t);

    return new ShapePosn(new Posn(newX, newY));
  }
}
