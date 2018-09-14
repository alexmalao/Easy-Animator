package cs3500.animator.control;

import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.ITransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.ScaleTransition;

/**
 * The visitor for turning transitions into an svg image file.
 */
public class TransitionSVGLoopVisitor implements TransitionVisitor<String> {

  private final int ticks;

  /**
   * Constructs a transitionSVGVisitor based on the ticks.
   * @param ticks the ticks per second.
   */
  public TransitionSVGLoopVisitor(int ticks) {
    this.ticks = ticks;
  }

  /**
   * Applies this visitor onto the transition to specify the class to work on.
   * @param trans the transition for this visitor to work on
   * @return the return object of the visitor
   */
  public String apply(ITransition trans) {
    return trans.accept(this);
  }

  /**
   * Visits the move transition.
   * @param move the move transition for this visitor to work on
   * @return the return object of the visitor
   */
  public String visit(MoveTransition move) {
    String item = "";
    double start = (move.getStartTime() / (double) ticks) * 1000;
    double duration = (move.getEndTime() / (double) ticks) * 1000 - start;

    String x = "x";
    String y = "y";
    if (move.getShape() instanceof Oval) {
      x = "cx";
      y = "cy";
    }

    item += String.format("    <animate attributeType=\"xml\" begin=\"base.begin+" + start
                    + "ms\" dur=\""
                    + duration
                    + "ms\" attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
            x, (int) move.getOldPosnX(), (int) move.getNewPosnX());
    item += String.format("    <animate attributeType=\"xml\" begin=\"base.begin+" + start
                    + "ms\" dur=\""
                    + duration
                    + "ms\" attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
            y, (int) move.getOldPosnY(), (int) move.getNewPosnY());
    return item;
  }

  /**
   * Visits the scale transition.
   * @param scale the scale transition for this visitor to work on
   * @return the return object of the visitor
   */
  public String visit(ScaleTransition scale) {
    String item = "";
    double start = (scale.getStartTime() / (double) ticks) * 1000;
    double duration = (scale.getEndTime() / (double) ticks) * 1000 - start;

    String width = "width";
    String height = "height";
    if (scale.getShape() instanceof Oval) {
      width = "rx";
      height = "ry";
    }

    item += String.format("    <animate attributeType=\"xml\" begin=\"base.begin+" + start
                    + "ms\" dur=\""
                    + duration
                    + "ms\" attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
            width, (int) scale.getOldWidth(), (int) scale.getNewWidth());
    item += String.format("    <animate attributeType=\"xml\" begin=\"base.begin+" + start
                    + "ms\" dur=\""
                    + duration
                    + "ms\" attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
            height, (int) scale.getOldHeight(), (int) scale.getNewHeight());
    return item;
  }

  /**
   * Visits the color transition.
   * @param color the color transition for this visitor to work on
   * @return the return object of the visitor
   */
  public String visit(ColorTransition color) {
    String item = "";
    double start = (color.getStartTime() / (double) ticks) * 1000;
    double duration = (color.getEndTime() / (double) ticks) * 1000 - start;
    item += String.format("    <animate attributeType=\"xml\" begin=\"base.begin+" + start
                    + "ms\" dur=\""
                    + duration
                    + "ms\" attributeName=\"fill\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
            String.format("rgb(%d,%d,%d)", color.getOldColor().getRed(),
                    color.getOldColor().getGreen(), color.getOldColor().getBlue()),
            String.format("rgb(%d,%d,%d)", color.getNewColor().getRed(),
                    color.getNewColor().getGreen(), color.getNewColor().getBlue()));
    return item;
  }
}
