package cs3500.animator.provider.view.interpol;

import cs3500.animator.provider.property.ShapeColor;
import cs3500.animator.provider.property.ShapeDimensions;
import cs3500.animator.provider.property.ShapeProperty;

/**
 * Factory to create an Interpolation based on a given ShapeProperty.
 * 
 * @author Anand
 *
 */

public class InterpolationFactory {

  /**
   * Creates an interpolation.
   */

  public static <T extends ShapeProperty> Interpolation createInterpolation(Class<T> c) {
    if (c.equals(ShapeColor.class)) {
      return new ColorInterpolation();
    } else if (c.equals(ShapeDimensions.class)) {
      return new ScaleInterpolation();
    }
    return new MoveInterpolation();
  }
}
