package cs3500.animator.view;

import cs3500.animator.util.ModelAdapter;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * Represents the animation using a Javax Swing Frame and related panels.
 */
public class AnimatorVisual extends JFrame implements AnimatorView {

  private AnimatorPanel animatorPanel;

  /**
   * Create a new AnimatorVisual with the given model.
   * @param model the model to be associated with this view.
   */
  public AnimatorVisual(ModelAdapter model) {
    super();
    this.setTitle("EasyAnimator");
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the AnimatorPanel
    this.animatorPanel = new AnimatorPanel(model);
    animatorPanel.setPreferredSize(new Dimension(1000, 1000));

    // Add the AnimatorPanel to the JScrollPane
    JScrollPane scrollPane = new JScrollPane(animatorPanel);
    scrollPane.setPreferredSize(new Dimension(500, 500));

    // Add the JScrollPane to the JFrame
    this.add(scrollPane);

    // Pack the contents.
    this.pack();
  }

  /**
   * Animates the view allowing for user inputs.
   * @param tempo the amount of ticks per second
   */
  @Override
  public void animate(int tempo) {
    this.setVisible(true);

    // Calculate the delay of the timer based on the given tempo.
    int delay = 1000 / tempo;
    ActionListener listener = new ActionListener() {
      private int tick = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        onTick(tick);
        tick++;
      }
    };
    new Timer(delay, listener).start();
  }

  /**
   * Draw the state of the animator at the given tick.
   * @param tick the current tick.
   */
  private void onTick(int tick) {
    animatorPanel.removeAll();
    animatorPanel.setCurrentTick(tick);
    animatorPanel.revalidate();
    animatorPanel.repaint();
  }
}
