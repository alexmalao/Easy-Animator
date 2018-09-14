package cs3500.animator.provider.property;

/**
 * Interface for ShapeProperties.
 */

public interface ShapeProperty<K> {

  /**
   * Returns a string representation of the property.
   * @return string representation.
   */
  @Override
  String toString();

  /**
   * Get a string representation of the verb used to describe the transition.
   */
  String getDescVerb();

  /**
   * Returns the value represented by this property.
   * @return the value.
   */
  K getValue();
}
