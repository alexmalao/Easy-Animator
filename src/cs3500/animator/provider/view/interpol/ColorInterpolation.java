package cs3500.animator.provider.view.interpol;

import cs3500.animator.provider.property.ShapeColor;
import java.awt.Color;

/**
 * Interpolation across ShapeColors.
 * 
 * @author Anand
 *
 */
public class ColorInterpolation extends Interpolation<ShapeColor> {

  @Override
  public ShapeColor interpolate(ShapeColor start, ShapeColor end, double a, double b, double t) {
    float[] startRGB = start.getValue().getColorComponents(null);
    float[] endRGB = end.getValue().getColorComponents(null);

    float newR = (float) apply(a, b, startRGB[0], endRGB[0], t);
    float newG = (float) apply(a, b, startRGB[1], endRGB[1], t);
    float newB = (float) apply(a, b, startRGB[2], endRGB[2], t);
    return new ShapeColor(new Color(newR, newG, newB));
  }

}
