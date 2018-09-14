package cs3500.animator.view;

/**
 * Represents the animator that views a specific AnimatorOperations model.
 */
public interface AnimatorView {

  /**
   * Animates the AnimatorOperations and adds the view to an appendable or new window.
   * @param tempo the amount of ticks per second
   */
  void animate(int tempo);
}
