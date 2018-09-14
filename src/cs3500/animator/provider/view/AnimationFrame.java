package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import cs3500.animator.provider.model.AnimatedShape;

/**
 * Represents a frame for animated views.
 */
class AnimationFrame extends JFrame {

  private static final long serialVersionUID = 3012154535701198151L;
  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;

  /**
   * Constructs a new animation window that runs the animation to completion,
   * then closes.
   */
  AnimationFrame(LinkedHashMap<String, AnimatedShape> shapes, int speed, int endTick) {
    DrawPanel panel = new DrawPanel(shapes, speed, endTick);
    this.setLayout(new BorderLayout());
    JScrollPane scrPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.setPreferredSize(new Dimension(AnimationFrame.WIDTH, AnimationFrame.HEIGHT));
    this.add(scrPane);

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(AnimationFrame.WIDTH, AnimationFrame.HEIGHT));
    setTitle("Easy Animator");
    this.pack();
    setVisible(true);
    panel.start();
  }
}
