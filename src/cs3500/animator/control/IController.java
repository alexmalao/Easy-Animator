package cs3500.animator.control;

/**
 * The controller interface for the animator.  Gives the animator control over commands.
 */
public interface IController {

  /**
   * Start the program, i.e. give control to the controller
   */
  void start(int startSpeed);

}
