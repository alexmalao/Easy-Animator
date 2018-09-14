package cs3500.animator.provider.model;

import cs3500.animator.control.ShapeVisitor;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.provider.property.ShapeColor;
import cs3500.animator.provider.property.ShapeDimensions;
import cs3500.animator.provider.property.ShapePosn;
import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.property.posn.Posn;

/**
 * The visitor for creating the shapes properties.
 */
public class ShapePropertyVisitor implements ShapeVisitor<ShapeProperty> {

  private Class<ShapeProperty> property;

  /**
   * Constructs a shape proeprty visitor.
   * @param property the type of property to be made.
   */
  public ShapePropertyVisitor(Class<ShapeProperty> property) {
    this.property = property;
  }

  /**
   * Applies the visitor on the shape.
   * @param shape the shape for this visitor to work on
   * @return the shape property
   */
  @Override
  public ShapeProperty apply(IShape shape) {
    return shape.accept(this);
  }

  /**
   * Visits the oval shape.
   * @param oval the oval for this visitor to work on
   * @return the shape property
   */
  @Override
  public ShapeProperty visit(Oval oval) {
    if (property.equals(ShapePosn.class)) {
      return new ShapePosn(new Posn((float) oval.getCenterX(), (float) oval.getCenterY()));
    }
    if (property.equals(ShapeDimensions.class)) {
      return new ShapeDimensions(new Posn((float) oval.getRadiusX(), (float) oval.getRadiusY()));
    }
    if (property.equals(ShapeColor.class)) {
      return new ShapeColor(oval.getColor());
    }

    return null;
  }

  /**
   * Visits the rectangle shape.
   * @param rect the rectangle for this visitor to work on
   */
  @Override
  public ShapeProperty visit(Rectangle rect) {
    if (property.equals(ShapePosn.class)) {
      return new ShapePosn(new Posn((float) rect.getLeftCornerX(),
              (float) rect.getLeftCornerY()));
    }
    if (property.equals(ShapeDimensions.class)) {
      return new ShapeDimensions(new Posn((float) rect.getWidth(),
              (float) rect.getHeight()));
    }
    if (property.equals(ShapeColor.class)) {
      return new ShapeColor(rect.getColor());
    }

    return null;
  }
}
