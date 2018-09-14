package cs3500.animator.provider.controller;

import java.io.IOException;
import java.io.Writer;
import cs3500.animator.provider.view.View;

/**
 * Interface for the Controller implementation.
 */
public interface Controller {

  /**
   * Updates the view to the given view.
   * 
   * @param view a valid View object.
   */
  public void setView(View view);

  /**
   * Updates the speed to the given integer.
   * 
   * @param speed a new speed value.
   * @throws IllegalArgumentException if the given speed is negative.
   */
  public void setSpeed(int speed);

  /**
   * Launches the program.
   *
   */
  public void launchProgram() throws IOException;

  /**
   * Grabs the writer for this view controller's output.
   * 
   * @return a Writer object.
   */
  public Writer getWriter();
}
