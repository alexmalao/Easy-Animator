package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import cs3500.animator.provider.model.AnimatedShape;
import cs3500.animator.provider.model.EasyAnimOperations;

/**
 * An animated view uses Swing GUI capabilities to construct and run the view
 * model. Each animation is displayed in a JFrame.
 *
 */
public class ViewAnimated extends JFrame implements View {

  private EasyAnimOperations model;
  private int speed;
  private int lastTick;
  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;

  @Override
  public void setModel(EasyAnimOperations theModel) {
    this.model = theModel;
    this.lastTick = getEndTick();
  }

  @Override
  public void setOutput(Writer output) {
    throw new UnsupportedOperationException("Animated views have no output");
  }

  @Override
  public void runView() {
    LinkedHashMap<String, AnimatedShape> shapes = new LinkedHashMap<>(model.getAnimatedShapes());
    DrawPanel panel = new DrawPanel(shapes, speed, lastTick);
    this.setLayout(new BorderLayout());
    JScrollPane scrPane = new JScrollPane(panel);
    scrPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrPane);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setSize(new Dimension(WIDTH, HEIGHT));
    setTitle("Easy Animator");
    this.pack();
    setVisible(true);
    panel.start();
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * Finds the final tick of the animation.
   *
   * @return int
   */

  private int getEndTick() {
    Map<String, AnimatedShape> shapes = model.getAnimatedShapes();
    int finalTime = 0;
    for (Map.Entry entry : shapes.entrySet()) {
      AnimatedShape shape = (AnimatedShape) entry.getValue();
      int next = shape.getHideTime();
      if (next > finalTime) {
        finalTime = next;
      }
    }
    return finalTime;
  }

  /**
   * Finds the final second of the animation.
   *
   * @return float
   */
  private float getEndSeconds() {
    return (float) getEndTick() / (float) this.speed;
  }
}
