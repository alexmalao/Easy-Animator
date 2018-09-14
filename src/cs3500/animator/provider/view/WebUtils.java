package cs3500.animator.provider.view;

import java.awt.Color;

/**
 * Utility class for web-based features.
 */
public class WebUtils {
  /**
   * Transforms a color into a web-usable string.
   * @param color the color to convert.
   * @return RGB hex string.
   */
  public static String toRGBCode( Color color ) {
    String red = Integer.toHexString(color.getRed());
    String green = Integer.toHexString(color.getGreen());
    String blue = Integer.toHexString(color.getBlue());

    return "#" +
            (red.length() == 1 ? "0" + red : red) +
            (green.length() == 1 ? "0" + green : green) +
            (blue.length() == 1 ? "0" + blue : blue);
  }
}
