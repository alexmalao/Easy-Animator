package cs3500.animator.provider.model;

import cs3500.animator.control.ShapeVisitor;
import cs3500.animator.control.TransitionOvalVisualVisitor;
import cs3500.animator.control.TransitionRectVisualVisitor;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ITransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import java.util.Collections;
import java.util.List;

/**
 * The visitor for turning shapes into a shape at the current time.
 */
public class ShapeTimeVisitor implements ShapeVisitor<IShape> {

  private final int curTick;

  /**
   * Constructs a ShapeTimeVisitor based on the ticks and given time.
   * @param curTick the current tick
   */
  public ShapeTimeVisitor(int curTick) {
    this.curTick = curTick;
  }

  /**
   * Applies this visitor onto the shape to specify the class to work on.
   * @param shape the shape for this visitor to work on
   * @return the return object of the visitor
   */
  public IShape apply(IShape shape) {
    return shape.accept(this);
  }

  /**
   * Visits the oval.
   * @param oval the oval for this visitor to work on
   * @return the return object of the visitor
   */
  public IShape visit(Oval oval) {
    Oval updatedShape = new Oval(oval.getName(), oval.getAppears(), oval.getDisappears(),
            oval.getColor(), oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(),
            oval.getRadiusY());

    List<ITransition> transitions = oval.getTransitions();
    Collections.sort(transitions);

    for (ITransition trans : transitions) {
      TransitionOvalVisualVisitor visitO = new TransitionOvalVisualVisitor(curTick, updatedShape);
      updatedShape = visitO.apply(trans);
    }

    return updatedShape;
  }

  /**
   * Visits the rectangle.
   * @param rect the rectangle for this visitor to work on
   * @return the return object of the visitor
   */
  public IShape visit(Rectangle rect) {
    Rectangle updatedShape = new Rectangle(rect.getName(), rect.getAppears(), rect.getDisappears(),
            rect.getColor(), rect.getLeftCornerX(), rect.getLeftCornerY(), rect.getWidth(),
            rect.getHeight());

    List<ITransition> transitions = rect.getTransitions();
    Collections.sort(transitions);

    for (ITransition trans : transitions) {
      TransitionRectVisualVisitor visitR = new TransitionRectVisualVisitor(curTick, updatedShape);
      updatedShape = visitR.apply(trans);
    }

    return updatedShape;
  }
}
