package cs3500.animator.provider.view;

import static cs3500.animator.provider.view.ViewFactory.ViewType.HYBRID;
import static cs3500.animator.provider.view.ViewFactory.ViewType.SVG;

/**
 * Factory class to construct Views. Also supports constructing a View directly
 * as Filterable and Controllable views.
 *
 */
public class ViewFactory {
  public enum ViewType {
    ANIM, TEXT, SVG, HYBRID
  }

  /**
   * Returns a ViewControllable. This operation is only supported for Hybrid
   * view types.
   *
   * @param type
   *            the given type.
   * @return a ViewControllable.
   * @throws UnsupportedOperationException
   *             if the type is not a Hybrid.
   */
  public static ViewControllable newViewControllable(ViewType type) {
    if (type == HYBRID) {
      return new ViewHybrid();
    }
    else {
      throw new UnsupportedOperationException("Text and SVG views are not " +
              "valid ViewControllables.");
    }
  }

  /**
   * Returns a ViewConfigurable. This is only supported for SVGs.
   *
   * @param type
   *            the given ViewType.
   * @return a ViewConfigurable object.
   * @throws UnsupportedOperationException
   *             if the type is not SVG.
   */
  public static ViewConfigurable newViewFilterable(ViewType type) {
    if (type == SVG) {
      return new ControllableSvg();
    }
    else {
      throw new UnsupportedOperationException("Text and Hybrid views are not Filterable.");
    }
  }

  /**
   * Returns the view.
   *
   * @param type
   *            the given type.
   */

  public static View newView(ViewType type) {
    switch (type) {
      case SVG: {
        return new ViewSvg();
      }
      case ANIM: {
        return new ViewAnimated();
      }
      case TEXT: {
        return new ViewText();
      }
      case HYBRID: {
        return new ViewHybrid();
      }
      default:
        throw new IllegalArgumentException("Invalid View Type: Cannot create");
    }
  }
}
