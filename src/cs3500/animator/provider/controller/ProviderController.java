package cs3500.animator.provider.controller;

import cs3500.animator.provider.view.ViewHybrid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Writer;

import cs3500.animator.provider.view.View;

/**
 * The controller used for interacting between the user, view, and model.
 */
public class ProviderController implements Controller {

  private Writer writer;
  private View view;

  /**
   * The constructor for provider controller.
   * @param writer the output writer used to write the output string
   */
  public ProviderController(Writer writer) {
    this.writer = writer;
  }

  /**
   * Updates the view to the given view.
   *
   * @param view a valid View object.
   */
  public void setView(View view) {
    this.view = view;
    try {
      this.view.setOutput(this.writer);
    } catch (UnsupportedOperationException e) {
      // do nothing
    } catch (IOException e) {
      // do nothing
    }
  }

  /**
   * Updates the speed to the given integer.
   *
   * @param speed a new speed value.
   * @throws IllegalArgumentException if the given speed is negative.
   */
  public void setSpeed(int speed) {
    this.view.setSpeed(speed);
  }

  /**
   * Launches the program.
   *
   */
  public void launchProgram() throws IOException {
    this.view.runView();

    if (view instanceof ViewHybrid) {
      ButtonListener listener = new ButtonListener();
      System.out.println("Created ButtonListener");
      ((ViewHybrid) view).addActionListener(listener);
    }
  }

  /**
   * Grabs the writer for this view controller's output.
   *
   * @return a Writer object.
   */
  public Writer getWriter() {
    return this.writer;
  }

  public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "PLAY":
          System.out.println("play");
          ((ViewHybrid)view).start();
          break;
        case "PAUSE":
          ((ViewHybrid)view).pause();
          break;
        case "RESUME":
          ((ViewHybrid)view).resume();
          break;
        case "EXPORT":
          try {
            ((ViewHybrid)view).exportSvg();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
          break;
        default:
          // Do nothing
          break;
      }
    }
  }
}
