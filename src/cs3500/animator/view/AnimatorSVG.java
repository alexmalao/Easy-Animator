package cs3500.animator.view;

import java.io.IOException;

import cs3500.animator.model.IShape;
import cs3500.animator.util.ModelAdapter;
import cs3500.animator.control.ShapeSVGVisitor;

/**
 * The view to display the animation as a string output.
 */
public class AnimatorSVG implements AnimatorView {

  /**
   * Creates a Animator for Text View for the inputted model.
   * @param model the model to be associated with this view
   * @param ap the appendable associated with this view
   */
  public AnimatorSVG(ModelAdapter model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  private final ModelAdapter model;
  private final Appendable ap;

  /**
   * Animates the AnimatorOperations and adds the view to an appendable or new window.
   * @param tempo the amount of ticks per second
   */
  public void animate(int tempo) {
    String view = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    ShapeSVGVisitor shapeV = new ShapeSVGVisitor(tempo);
    for (IShape shape : model.getShapes()) {
      view += shapeV.apply(shape) + "\n\n";
    }

    view += "</svg>";

    try {
      ap.append(view);
    } catch (IOException e) {
      // do nothing
    }
  }
}
