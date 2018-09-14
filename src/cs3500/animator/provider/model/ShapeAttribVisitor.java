package cs3500.animator.provider.model;

import cs3500.animator.control.ShapeVisitor;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;

/**
 * The visitor for creating the shapes attributes.
 */
public class ShapeAttribVisitor implements ShapeVisitor<String> {

  private EasyObject.ObjectAttrib attrib;

  /**
   * Constructos a Shape Attribute Visitor.
   */
  public ShapeAttribVisitor(EasyObject.ObjectAttrib attrib) {
    this.attrib = attrib;
  }

  /**
   * Applies the visitor.
   * @param shape the shape for this visitor to work on
   */
  @Override
  public String apply(IShape shape) {
    return shape.accept(this);
  }

  /**
   * Visits the oval.
   * @param oval the oval for this visitor to work on
   */
  @Override
  public String visit(Oval oval) {
    switch (attrib) {
      case X:
        return "cx";
      case Y:
        return "cy";
      case WIDTH:
        return "rx";
      case HEIGHT:
        return "ry";
      case COLOR:
        return "fill";
      case NAME:
        return "ellipse";
      default:
        return "";
    }
  }

  /**
   * Visits the rectangle.
   * @param rect the rectangle for this visitor to work on
   */
  @Override
  public String visit(Rectangle rect) {
    switch (attrib) {
      case X:
        return "x";
      case Y:
        return "y";
      case WIDTH:
        return "width";
      case HEIGHT:
        return "height";
      case COLOR:
        return "fill";
      case NAME:
        return "rect";
      default:
        return "";
    }
  }
}
