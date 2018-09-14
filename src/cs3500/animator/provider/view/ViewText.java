package cs3500.animator.provider.view;

import java.io.IOException;
import java.io.Writer;

/**
 * A textual view parses text files into valid animations.
 *
 */
public class ViewText extends ViewAbstract {
  private Writer output;

  @Override
  public void setOutput(Writer output) throws IOException {
    this.output = output;
  }

  @Override
  public void runView() throws IOException, IllegalStateException {
    super.runView();
    this.output.append(this.model.getDescription(this.speed));
  }
}
