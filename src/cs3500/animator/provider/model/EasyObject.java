package cs3500.animator.provider.model;

import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.property.posn.Posn;

/**
 * Represents an object to be animated. NOTE: publicized getName() for implementing views.
 */
public interface EasyObject {
  /**
   * Gives a string representation of the properties of this shape.
   * 
   * @return string description.
   */
  String getPropertyDescription();

  /**
   * Returns the specified property of the shape.
   * 
   * @param property The class of the property to retrieve.
   * @param <A> Property type.
   * @return the property.
   */
  <A extends ShapeProperty> A getProperty(Class<A> property);

  /**
   * Returns a type-friendly name of the object.
   * 
   * @return name of object.
   */
  String getName();

  /**
   * Returns an SVG-friendly name for an attribute on the shape.
   * 
   * @param attrib attribute to get the name of
   * @return the svg-friendly attribute.
   */
  String getSvgAttrib(ObjectAttrib attrib);

  /**
   * Checks if this shape contains the given posn.
   * 
   * @param p the posn to check.
   * @return true if the posn is within the boundary of the shape.
   */
  boolean containsPos(Posn p);

  enum ObjectAttrib {
    X, Y, WIDTH, HEIGHT, COLOR, NAME
  }
}
