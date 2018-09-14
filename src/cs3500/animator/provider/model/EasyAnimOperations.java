package cs3500.animator.provider.model;

import java.util.Map;

import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.transition.Transition;

/**
 * Represents the Model for an Easy Animator program. NOTE: Previous specification did not
 * include getAnimatedShapes(), which was included for View implementation.
 * @param <S> The type of shape base used for the implementation
 * @param <T> The type of transition base used for the implementation.
 *     NOTE: Added four getter methods for the dimensions of all the shapes.
 *           This is fitting to control on the model as it can be tracked as
 *           shapes and transitions are added later, rather
 *           than iterating over shape properties.
 */
public interface EasyAnimOperations<S, T> {

  /**
   * Adds a shape to animate with the given alias.
   * @param shape The shape to add to the scene.
   * @param alias The name to give the shape to address it later.
   * @param show the time to show the object.
   * @param hide the time to hide the object.
   * @return The same EasyAnimOperations.
   * @throws IllegalArgumentException upon bad/repeated alias.
   */
  EasyAnimOperations<S, T> addShape(String alias, S shape, int show, int hide)
          throws IllegalArgumentException;

  /**
   * Returns an animated shape from it's alias.
   * @return the animated shape.
   */
  AnimatedShape getAnimatedShape(String alias) ;

  /**
   * Returns all of the animated shapes in the model, mapped to their aliases.
   * @return Map of shapes.
   *     NOTE: Changed type from previous version to a clearer AnimatedShape
   */
  Map<String, AnimatedShape> getAnimatedShapes();

  /**
   * Adds a tweening transition to the shape.
   * @param alias name of the shape to add the transition to.
   * @param startTime start time of transition.
   * @param endTime end time of transition.
   * @param endValue value to transition to.
   * @return the list of transitions
   * @throws IllegalArgumentException On: bad alias, bad property, overlapping property animations,
   *         bad end value type.
   */
  Transition addTransition(
          String alias, ShapeProperty<?> endValue, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Returns a text description of the model.
   * @param speed translate the ticks to seconds.
   * @return text description.
   */
  String getDescription(int speed);
}
