package cs3500.animator.provider.model;

import java.util.List;

import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.transition.Transition;


/**
 * Represents a shape with a series of transitions on it. NOTE: added addTransition() to interface.
 * Was previously in all implementations but not included here. Also added getLastValue() for
 * the same reason.
 */

public interface AnimatedShape {

  /**
   * Returns a description of the animated object.
   * //NOTE: added speed parameter for translating to seconds
   * @param speed the speed at which to describe the animation.
   * @return the description.
   */

  String getObjectDescription(int speed);

  /**
   * Returns the transitions this shape undergoes.
   * @return the list of transitions
   */

  List<Transition> getTransitions();

  /**
   * Gets the initial state of the shape as an EasyObject.
   * @return the shape.
   */

  EasyObject getInitialShape();

  /**
   * Grabs the start time of the shape.
   *
   * @return an integer.
   */

  int getShowTime();

  /**
   * Grabs the hide time of the shape.
   *
   * @return an integer.
   */

  int getHideTime();

  /**
   * Gets the last keyframe value of the specified property at the given time.
   * //NOTE: Added this to interface, as requested by instructor.
   */

  <A extends ShapeProperty> ShapeProperty getLastValue(Class<A> transitionType,
                                                       int startTime);
}
