package cs3500.animator.control;

import java.awt.Color;

import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.ITransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.ScaleTransition;


/**
 * The visitor for turning transitions into an new updated Oval.
 */
public class TransitionOvalVisualVisitor implements TransitionVisitor<Oval> {

  private final int curTick;
  private final Oval updatedShape;

  /**
   * Constructs a transitionOvalVisualVisitor based on the ticks.
   * @param curTick the current tick
   * @param updatedShape the shape that will be updated and returned
   */
  public TransitionOvalVisualVisitor(int curTick, Oval updatedShape) {
    this.curTick = curTick;
    this.updatedShape = updatedShape;
  }

  /**
   * Applies this visitor onto the transition to specify the class to work on.
   * @param trans the transition for this visitor to work on
   * @return the return object of the visitor
   */
  public Oval apply(ITransition trans) {
    return trans.accept(this);
  }

  /**
   * Visits the move transition.
   * @param move the move transition for this visitor to work on
   * @return the return object of the visitor
   */
  public Oval visit(MoveTransition move) {
    Oval finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
            updatedShape.getDisappears(), updatedShape.getColor(), updatedShape.getCenterX(),
            updatedShape.getCenterY(), updatedShape.getRadiusX(), updatedShape.getRadiusY());
    if (move.getEndTime() <= curTick) {
      finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
              updatedShape.getDisappears(), updatedShape.getColor(), move.getNewPosnX(),
              move.getNewPosnY(), updatedShape.getRadiusX(), updatedShape.getRadiusY());
    }
    else if (move.getEndTime() > curTick && curTick >= move.getStartTime()) {
      double timeParts = (double) (curTick - move.getStartTime())
              / (double) (move.getEndTime() - move.getStartTime());
      double posnX = timeParts * (move.getNewPosnX() - move.getOldPosnX())
              + move.getOldPosnX();
      double posnY = timeParts * (move.getNewPosnY() - move.getOldPosnY())
              + move.getOldPosnY();
      finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
              updatedShape.getDisappears(), updatedShape.getColor(), posnX,
              posnY, updatedShape.getRadiusX(), updatedShape.getRadiusY());
    }
    return finalShape;
  }

  /**
   * Visits the scale transition.
   * @param scale the scale transition for this visitor to work on
   * @return the return object of the visitor
   */
  public Oval visit(ScaleTransition scale) {
    Oval finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
            updatedShape.getDisappears(), updatedShape.getColor(), updatedShape.getCenterX(),
            updatedShape.getCenterY(), updatedShape.getRadiusX(), updatedShape.getRadiusY());
    if (scale.getEndTime() <= curTick) {
      finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
              updatedShape.getDisappears(), updatedShape.getColor(), updatedShape.getCenterX(),
              updatedShape.getCenterY(), scale.getNewWidth(), scale.getNewHeight());
    }
    else if (scale.getEndTime() > curTick && curTick >= scale.getStartTime()) {
      double timeParts = (double) (curTick - scale.getStartTime())
              / (double) (scale.getEndTime() - scale.getStartTime());
      double width = timeParts * (scale.getNewWidth() - scale.getOldWidth())
              + scale.getOldWidth();
      double height = timeParts * (scale.getNewHeight() - scale.getOldHeight())
              + scale.getOldHeight();
      finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
              updatedShape.getDisappears(), updatedShape.getColor(), updatedShape.getCenterX(),
              updatedShape.getCenterY(), width, height);
    }
    return finalShape;
  }

  /**
   * Visits the color transition.
   * @param color the color transition for this visitor to work on
   * @return the return object of the visitor
   */
  public Oval visit(ColorTransition color) {
    Oval finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
            updatedShape.getDisappears(), updatedShape.getColor(), updatedShape.getCenterX(),
            updatedShape.getCenterY(), updatedShape.getRadiusX(), updatedShape.getRadiusY());
    if (color.getEndTime() <= curTick) {
      finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
              updatedShape.getDisappears(), color.getNewColor(), updatedShape.getCenterX(),
              updatedShape.getCenterY(), updatedShape.getRadiusX(), updatedShape.getRadiusY());
    }
    else if (color.getEndTime() > curTick && curTick >= color.getStartTime()) {
      double timeParts = (double) (curTick - color.getStartTime())
              / (double) (color.getEndTime() - color.getStartTime());
      double colorR = timeParts * (color.getNewColor().getRed() - color.getOldColor().getRed())
              + color.getOldColor().getRed();
      double colorG = timeParts * (color.getNewColor().getGreen() - color.getOldColor().getGreen())
              + color.getOldColor().getGreen();
      double colorB = timeParts * (color.getNewColor().getBlue() - color.getOldColor().getBlue())
              + color.getOldColor().getBlue();
      finalShape = new Oval(updatedShape.getName(), updatedShape.getAppears(),
              updatedShape.getDisappears(),
              new Color((int) colorR, (int) colorG, (int) colorB), updatedShape.getCenterX(),
              updatedShape.getCenterY(), updatedShape.getRadiusX(), updatedShape.getRadiusY());
    }
    return finalShape;
  }
}
