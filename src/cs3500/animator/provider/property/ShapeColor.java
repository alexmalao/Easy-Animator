package cs3500.animator.provider.property;

import java.awt.Color;

/**
 * Represents the color of a shape.
 */
public class ShapeColor extends ShapePropertyAbstract<Color> {
  /**
   * Creates a new ShapeColor of the given value.
   * @param val initial color
   */
  public ShapeColor(Color val) {
    super(val);
  }

  @Override
  public String toString() {
    float[] colors = this.val.getColorComponents(new float[3]);
    return "(" + colors[0] + "," + colors[1] + "," + colors[2] + ")";
  }

  @Override
  public String getDescVerb() {
    return "changes color";
  }
}
