package cs3500.animator.provider.transition;

import cs3500.animator.provider.property.ShapeProperty;

/**
 * Represents a Transition.
 */

public interface Transition<K extends ShapeProperty<?>> {

  /**
   * Get the start value of the transition.
   * @return start value of transition.
   */
  K getStartValue();

  /**
   * Get end value of transition.
   */
  K getEndValue();

  /**
   * Get start time of transition.
   */
  int getStartTime();

  /**
   * Get end time of transition.
   */
  int getEndTime();

  /**
   * Returns a written description of the transition. Time to convert ticks to seconds.
   */
  String getDesc(String name, int time);
}
