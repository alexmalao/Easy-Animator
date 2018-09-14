package cs3500.animator.control;

import cs3500.animator.model.IShape;
import cs3500.animator.model.ITransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;

/**
 * The visitor for turning shapes into an svg image file including loops.
 */
public class ShapeSVGLoopVisitor implements ShapeVisitor<String> {

  private final int ticks;

  /**
   * Constructs a shapeVisitor based on the ticks.
   * @param ticks the ticks per second.
   */
  public ShapeSVGLoopVisitor(int ticks) {
    this.ticks = ticks;
  }

  /**
   * Applies this visitor onto the shape to specify the class to work on.
   * @param shape the shape for this visitor to work on
   * @return the return object of the visitor
   */
  public String apply(IShape shape) {
    return shape.accept(this);
  }

  /**
   * Visits the oval.
   * @param oval the oval for this visitor to work on
   * @return the return object of the visitor
   */
  public String visit(Oval oval) {
    String item = "<ellipse id=\"" + oval.getName();
    item += "\" cx=\"" + oval.getCenterX();
    item += "\" cy=\"" + oval.getCenterY();
    item += "\" rx=\"" + oval.getRadiusX();
    item += "\" ry=\"" + oval.getRadiusY();
    item += "\" fill=\"" + String.format("rgb(%d,%d,%d)", oval.getColor().getRed(),
            oval.getColor().getGreen(), oval.getColor().getBlue());
    item += "\" visibility=\"hidden\" >\n";

    double start = (oval.getAppears() / (double) ticks) * 1000;
    item += "    <animate attributeType=\"xml\" begin=\"base.begin+" + start + "ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n";

    double end = (oval.getDisappears() / (double) ticks) * 1000;
    item += "    <animate attributeType=\"xml\" begin=\"base.begin+" + end + "ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n";

    TransitionSVGLoopVisitor transV = new TransitionSVGLoopVisitor(ticks);
    for (ITransition trans : oval.getTransitions()) {
      item += transV.apply(trans);
    }
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cx\" "
            + "to=\"" + oval.getCenterX() + "\" fill=\"freeze\" />\n";
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cy\" "
            + "to=\"" + oval.getCenterY() + "\" fill=\"freeze\" />\n";
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"rx\" "
            + "to=\"" + oval.getRadiusX() + "\" fill=\"freeze\" />\n";
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"ry\" "
            + "to=\"" + oval.getRadiusY() + "\" fill=\"freeze\" />\n";
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" "
            + "to=\"" + String.format("rgb(%d,%d,%d)", oval.getColor().getRed(),
            oval.getColor().getGreen(), oval.getColor().getBlue()) + "\" fill=\"freeze\" />\n";

    item += "</ellipse>";

    return item;
  }

  /**
   * Visits the rectangle.
   * @param rect the rectangle for this visitor to work on
   * @return the return object of the visitor
   */
  public String visit(Rectangle rect) {
    String item = "<rect id=\"" + rect.getName();
    item += "\" x=\"" + rect.getLeftCornerX();
    item += "\" y=\"" + rect.getLeftCornerY();
    item += "\" width=\"" + rect.getWidth();
    item += "\" height=\"" + rect.getHeight();
    item += "\" fill=\"" + String.format("rgb(%d,%d,%d)", rect.getColor().getRed(),
            rect.getColor().getGreen(), rect.getColor().getBlue());
    item += "\" visibility=\"hidden\" >\n";

    double start = (rect.getAppears() / (double) ticks) * 1000;
    item += "    <animate attributeType=\"xml\" begin=\"base.begin+" + start + "ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n";

    double end = (rect.getDisappears() / (double) ticks) * 1000;
    item += "    <animate attributeType=\"xml\" begin=\"base.begin+" + end + "ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n";

    TransitionSVGLoopVisitor transV = new TransitionSVGLoopVisitor(ticks);
    for (ITransition trans : rect.getTransitions()) {
      item += transV.apply(trans);
    }

    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"x\" "
            + "to=\"" + rect.getLeftCornerX() + "\" fill=\"freeze\" />\n";
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"y\" "
            + "to=\"" + rect.getLeftCornerY() + "\" fill=\"freeze\" />\n";
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"width\" "
            + "to=\"" + rect.getWidth() + "\" fill=\"freeze\" />\n";
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"height\" "
            + "to=\"" + rect.getHeight() + "\" fill=\"freeze\" />\n";
    item += "   <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" "
            + "to=\"" + String.format("rgb(%d,%d,%d)", rect.getColor().getRed(),
            rect.getColor().getGreen(), rect.getColor().getBlue()) + "\" fill=\"freeze\" />\n";

    item += "</rect>";

    return item;
  }
}
