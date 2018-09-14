package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Outward-interface which enables client-controllable view operations.
 *
 */
public interface ViewControllable extends View {

  /**
   * Pauses the view in in it's playback state.
   */
  void pause();

  /**
   * Start the running of the animation.
   */
  void start();

  /**
   * Resume playback after has been paused.
   */
  void resume();

  /**
   * Set the speed of the animation playback.
   */
  void setSpeed(int speed);

  /**
   * Export the view as an SVG.
   */
  void exportSvg() throws IOException;

  /**
   * Adds an action listener to the ViewControllable.
   *
   * @param listener
   *            the given ActionListener
   */

  void addActionListener(ActionListener listener);
}
