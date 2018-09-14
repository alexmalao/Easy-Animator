package cs3500.animator.view;

import java.awt.*;

import cs3500.animator.model.AnimatorOperations;
import cs3500.animator.util.ModelAdapter;

/**
 * A factory class to create an animator view.
 */
public class ViewFactory {

  /**
   * Return a new view of the appropriate view type with the given model and appendable.
   * @param viewType the type of view to return
   * @param model the model to be connected to the view
   * @param ap the appendable to be connected to the view
   * @param bgColor the background color
   * @return the new view
   */
  public AnimatorView getView(String viewType,
                              AnimatorOperations model, Appendable ap, Color bgColor) {
    switch (viewType) {
      case "text":
        return new AnimatorText(new ModelAdapter(model), ap);
      case "visual":
        return new AnimatorVisual(new ModelAdapter(model));
      case "svg":
        return new AnimatorSVG(new ModelAdapter(model), ap);
      case "interactive":
        return new AnimatorHybrid(new ModelAdapter(model), bgColor);
      default:
        // Should never run.
        return null;
    }
  }
}
