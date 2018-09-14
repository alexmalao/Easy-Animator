package cs3500.animator.control;

import cs3500.animator.model.AnimatorOperations;
import cs3500.animator.view.AnimatorView;

public class CommandController implements IController {

  private AnimatorView view;

  /**
   * Create a new CommandController.
   * @param model the model associated with this controller
   * @param view the view associated with this controller
   */
  public CommandController(AnimatorOperations model, AnimatorView view) {
    AnimatorOperations model1 = model;
    this.view = view;
  }

  @Override
  public void start(int speed) {
    view.animate(speed);
  }
}
