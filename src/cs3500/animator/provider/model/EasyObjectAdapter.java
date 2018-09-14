package cs3500.animator.provider.model;

import cs3500.animator.model.IShape;
import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.property.posn.Posn;

/**
 * The adapter for wrapping an IShape to work with the EasyObject interface.
 */
public class EasyObjectAdapter implements EasyObject {

  private IShape baseShape;

  /**
   * Constructor for creating an EasyObjectAdapter.
   * @param baseShape the baseShape to work with the adapter
   */
  public EasyObjectAdapter(IShape baseShape) {
    this.baseShape = baseShape;
  }

  /**
   * Gives a string representation of the properties of this shape.
   *
   * @return string description.
   */
  public String getPropertyDescription() {
    return this.baseShape.toString(1);
  }

  /**
   * Returns the specified property of the shape.
   *
   * @param property The class of the property to retrieve.
   * @param <A> Property type.
   * @return the property.
   */
  public <A extends ShapeProperty> A getProperty(Class<A> property) {
    return (A) new ShapePropertyVisitor((Class<ShapeProperty>)property).apply(this.baseShape);
  }

  /**
   * Returns a type-friendly name of the object.
   *
   * @return name of object.
   */
  public String getName() {
    return new ShapeNameVisitor().apply(baseShape);
  }

  /**
   * Returns an SVG-friendly name for an attribute on the shape.
   *
   * @param attrib attribute to get the name of
   * @return the svg-friendly attribute.
   */
  public String getSvgAttrib(EasyObject.ObjectAttrib attrib) {
    ShapeAttribVisitor visitor = new ShapeAttribVisitor(attrib);
    return visitor.apply(this.baseShape);
  }

  /**
   * Checks if this shape contains the given posn.
   *
   * @param p the posn to check.
   * @return true if the posn is within the boundary of the shape.
   */
  public boolean containsPos(Posn p) {
    return false;
  }

  enum ObjectAttrib {
    X, Y, WIDTH, HEIGHT, COLOR, NAME
  }
}
