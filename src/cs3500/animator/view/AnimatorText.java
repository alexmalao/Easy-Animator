package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import cs3500.animator.model.IShape;
import cs3500.animator.model.ITransition;
import cs3500.animator.util.ModelAdapter;

/**
 * The view to display the animation as a string output.
 */
public class AnimatorText implements AnimatorView {

  /**
   * Creates a Animator for Text View for the inputted model.
   * @param model the model to be displayed as text
   * @param ap the appendable to output the text view
   */
  public AnimatorText(ModelAdapter model, Appendable ap) {
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
    String str = "Shapes:\n";
    ArrayList<ITransition> transitions = new ArrayList<ITransition>();
    for (IShape shape : model.getShapes()) {
      str += shape.toString(tempo) + "\n\n";
      transitions.addAll(shape.getTransitions());
    }

    Collections.sort(transitions);

    for (ITransition t : transitions) {
      str += t.toString(tempo) + "\n";
    }

    str = str.substring(0, str.length() - 1);

    try {
      this.ap.append(str);
    } catch (IOException e) {
      // do nothing
    }
  }
}
