package cs3500.animator.provider.view;

import java.io.IOException;
import java.io.Writer;

import cs3500.animator.provider.model.EasyAnimOperations;

/**
 * A View is an animatable interface. Views can be implemented in several
 * different ways. Currently, text views (from text and svg files), and swing
 * animations may be represented. A hybrid view is a combination of these parts.
 *
 */
public interface View {
  /**
   * Set the model for the view to render.
   *
   * @param theModel
   *            the model.
   */
  public void setModel(EasyAnimOperations theModel);

  /**
   * Set the output to the view, to be parsed by implementations. Typically a
   * file name.
   *
   * @param output
   *            where to output the view.
   * @throws UnsupportedOperationException
   *             if this method isn't implemented on a specific view.
   * @throws IOException
   *             if the output given is invalid to write to.
   */
  public void setOutput(Writer output) throws UnsupportedOperationException, IOException;

  /**
   * Runs the view to completion, after all other values are initiated.
   *
   * @throws UnsupportedOperationException
   *             if the model or speed wasn't initialized.
   * @throws IOException
   *             if can't write to output.
   */
  public void runView() throws IOException, UnsupportedOperationException;

  /**
   * Set the speed of the animation.
   */
  public void setSpeed(int speed);
}
