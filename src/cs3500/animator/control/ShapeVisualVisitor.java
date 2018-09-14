package cs3500.animator.control;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import cs3500.animator.model.IShape;
import cs3500.animator.model.ITransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;

/**
 * The visitor for turning shapes into an Swing image.
 */
public class ShapeVisualVisitor implements ShapeVisitor<Graphics> {

  private final int curTick;
  private final Graphics g;

  /**
   * Constructs a ShapeVisualVisitor based on the ticks and given time.
   * @param curTick the current tick
   * @param g the graphics
   */
  public ShapeVisualVisitor(int curTick, Graphics g) {
    this.curTick = curTick;
    this.g = g;
  }

  /**
   * Applies this visitor onto the shape to specify the class to work on.
   * @param shape the shape for this visitor to work on
   * @return the return object of the visitor
   */
  public Graphics apply(IShape shape) {
    return shape.accept(this);
  }

  /**
   * Visits the oval.
   * @param oval the oval for this visitor to work on
   * @return the return object of the visitor
   */
  public Graphics visit(Oval oval) {
    Oval updatedShape = new Oval(oval.getName(), oval.getAppears(), oval.getDisappears(),
            oval.getColor(), oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(),
            oval.getRadiusY());

    List<ITransition> transitions = oval.getTransitions();
    Collections.sort(transitions);

    for (ITransition trans : transitions) {
      TransitionOvalVisualVisitor visitO = new TransitionOvalVisualVisitor(curTick, updatedShape);
      updatedShape = visitO.apply(trans);
    }

    // If this exists at the current tick, draw it.
    if (curTick >= updatedShape.getAppears() && curTick <= updatedShape.getDisappears()) {
      // After the transition has been applied to the oval, change the color of the graphics brush.
      g.setColor(updatedShape.getColor());
      // Then draw the oval in the given position.
      g.fillOval((int)(updatedShape.getCenterX() - updatedShape.getRadiusX()),
          (int)(updatedShape.getCenterY() - updatedShape.getRadiusY()),
          (int)(updatedShape.getRadiusX() * 2), (int)(updatedShape.getRadiusY()) * 2);
    }
    return g;
  }

  /**
   * Visits the rectangle.
   * @param rect the rectangle for this visitor to work on
   * @return the return object of the visitor
   */
  public Graphics visit(Rectangle rect) {
    Rectangle updatedShape = new Rectangle(rect.getName(), rect.getAppears(), rect.getDisappears(),
            rect.getColor(), rect.getLeftCornerX(), rect.getLeftCornerY(), rect.getWidth(),
            rect.getHeight());

    List<ITransition> transitions = rect.getTransitions();
    Collections.sort(transitions);

    for (ITransition trans : transitions) {
      TransitionRectVisualVisitor visitR = new TransitionRectVisualVisitor(curTick, updatedShape);
      updatedShape = visitR.apply(trans);
    }

    // If this exists at the current tick, draw it.
    if (curTick >= updatedShape.getAppears() && curTick <= updatedShape.getDisappears()) {
      // After the transition has been applied to the oval, change the color of the graphics brush.
      g.setColor(updatedShape.getColor());
      // Then draw the oval in the given position.
      g.fillRect((int)(updatedShape.getLeftCornerX()),
          (int)(updatedShape.getLeftCornerY()),
          (int)(updatedShape.getWidth()), (int)(updatedShape.getHeight()));
    }
    return g;
  }
}
