package cs3500.animator.provider.view;

import java.util.Map;

import cs3500.animator.provider.model.AnimatedShape;

/**
 * An interface which allows shape filtering.
 * 
 * @see ControllableSvg
 */
public interface ViewConfigurable extends View {

  /**
   * Updates the collection of shapes to be the given mapping of aliases to
   * shapes.
   *
   * @param shapes
   *            a Map of String to Animated shape objects.
   */
  void setShapes(Map<String, AnimatedShape> shapes);

  /**
   * Set whether or not the view should loop.
   */
  void setShouldLoop(boolean shouldLoop);
}
