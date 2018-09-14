package cs3500.animator.provider.view;

import java.io.IOException;

import cs3500.animator.provider.model.EasyAnimOperations;

/**
 * All views contain shared properties. These are abstracted away from
 * individual View implementations into the AbstractView class.
 *
 */
abstract class ViewAbstract implements View {
  protected EasyAnimOperations<?, ?> model;
  protected Integer speed;

  @Override
  public void setModel(EasyAnimOperations theModel) {
    this.model = theModel;
  }

  @Override
  public void runView() throws IOException, IllegalStateException {
    if (this.model == null) {
      throw new IllegalStateException("Model needs to be set before View can be run");
    }
    if (speed == null) {
      throw new IllegalStateException("Speed must be set before view can run");
    }
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }
}
