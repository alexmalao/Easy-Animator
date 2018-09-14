package cs3500.animator.provider.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs3500.animator.provider.model.AnimatedShape;
import cs3500.animator.provider.property.ShapeColor;
import cs3500.animator.provider.property.ShapeDimensions;
import cs3500.animator.provider.property.ShapePosn;
import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.transition.Transition;
import cs3500.animator.provider.view.interpol.Interpolation;
import cs3500.animator.provider.view.interpol.InterpolationFactory;

/**
 * The DrawPanel is part of a ViewHybrid object. The Hybrid View contains a control panel which
 * determines what should be drawn, which is passed to the Draw Panel for rendering.
 */
class DrawPanel extends JPanel {

  private static final long serialVersionUID = -8229270123626353403L;

  private Timer timer;
  private int maxX = 0;
  private int maxY = 0;
  private int tick;
  private int speed;
  private static final int FRAMERATE = 60;
  private LinkedHashMap<String, AnimatedShape> shapes;
  private Collection<String> drawFilter;
  private boolean shouldLoop = false;

  /**
   * Constructs a draw panel with essential properties for the animation.
   *
   * @param shapes  a Linked map of shapes to be drawn.
   * @param speed   a speed for the animation.
   * @param maxTime the last tick.
   */
  DrawPanel(LinkedHashMap<String, AnimatedShape> shapes, int speed, int maxTime) {
    this.speed = speed;
    this.shapes = shapes;
    this.setBackground(Color.WHITE);
    timer = new Timer(getTimerDelay(), e -> {
      tick++;
      if (tick < maxTime) {
        repaint();
        timer.setDelay(getTimerDelay());
        timer.setRepeats(true);
        resize();
      } else {
        init();
        if (this.shouldLoop) {
          this.start();
        } else {
          timer.stop();
        }
      }
    });
    resize();
    init();
  }

  private void init() {
    this.tick = 1;
    this.repaint();
  }

  /**
   * Stops the playback timer.
   */
  void haltPlayback() {
    this.timer.stop();
  }

  /**
   * Starts the playback timer from beginning.
   */
  void resumePlayback() {
    this.timer.restart();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawShapes(g);
    resize();
    revalidate();
  }

  private int getTimerDelay() {
    return 1000 / this.speed;
  }

  private void resize() {
    Dimension size = getPreferredSize();
    setPreferredSize(new Dimension(getWidth(), getHeight()));
  }

  /**
   * Initializes the timer and ticking behavior.
   */
  public void start() {
    this.tick = 1;
    timer.setDelay(getTimerDelay());
    timer.start();
  }

  /**
   * Updates the playback speed.
   *
   * @param speed the given integer value.
   */
  void setPlaybackSpeed(int speed) {
    this.speed = speed;
  }

  @SuppressWarnings("unchecked")
  void drawShape(Graphics g, AnimatedShape shape, String name) {
    if (drawFilter != null && !this.drawFilter.contains(name)) {
      return;
    }
    int startTime = shape.getShowTime();
    int endTime = shape.getHideTime();
    if (tick < startTime || tick >= endTime) {
      return;
    }
    ShapePosn currentPosn;
    ShapeColor currentColor;
    ShapeDimensions currentDimens;

    currentPosn = (ShapePosn) shape.getLastValue(ShapePosn.class, tick);
    currentColor = (ShapeColor) shape.getLastValue(ShapeColor.class, tick);
    currentDimens = (ShapeDimensions) shape.getLastValue(ShapeDimensions.class, tick);

    java.util.List<Transition> toTween = getTransitionsAt(shape, tick);

    for (Transition t : toTween) {
      Interpolation interpol = InterpolationFactory.createInterpolation(
              t.getStartValue().getClass());
      ShapeProperty nextProperty = (ShapeProperty) interpol.interpolate(
              t.getStartValue(), t.getEndValue(),
              t.getStartTime(), t.getEndTime(), tick);
      if (nextProperty instanceof ShapePosn) {
        currentPosn = (ShapePosn) nextProperty;
      } else if (nextProperty instanceof ShapeColor) {
        currentColor = (ShapeColor) nextProperty;
      } else if (nextProperty instanceof ShapeDimensions) {
        currentDimens = (ShapeDimensions) nextProperty;
      }
    }

    int x = Math.round(currentPosn.getValue().getX());
    int y = Math.round(currentPosn.getValue().getY());
    int w = Math.round(currentDimens.getValue().getX());
    int h = Math.round(currentDimens.getValue().getY());
    if (x > maxX) {
      maxX = x;
    }
    if (y > maxY) {
      maxY = y;
    }
    g.setColor(currentColor.getValue());
    if (shape.getInitialShape().getName().equals("oval")) {
      g.fillOval(x - w / 2, y - h / 2, w * 2, h * 2);
    } else if (shape.getInitialShape().getName().equals("rectangle")) {
      g.fillRect(x, y, w, h);
    }
  }

  /**
   * Grabs all transitions of a given shape at a given time.
   *
   * @param shape an AnimatedShape object.
   * @param time  an int time value.
   * @return a List of transitions.
   */
  List<Transition> getTransitionsAt(AnimatedShape shape, int time) {
    List<Transition> transitions = shape.getTransitions();
    List<Transition> current = new LinkedList<>();
    for (Transition t : transitions) {
      if (time >= t.getStartTime() && time <= t.getEndTime()) {
        current.add(t);
      }
    }
    return current;
  }

  /**
   * Draw all shapes.
   */
  void drawShapes(Graphics g) {
    for (Map.Entry<String, AnimatedShape> shape : shapes.entrySet()) {
      drawShape(g, shape.getValue(), shape.getKey());
    }
  }

  /**
   * Updates the shape mapping to the given linked map.
   *
   * @param shapes a Linked map of alias -> shape.
   */
  void setShapes(LinkedHashMap<String, AnimatedShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Updates the drawing filter to the collection of aliases.
   *
   * @param names a collection of String aliases.
   */
  void setDrawFilter(Collection<String> names) {
    this.drawFilter = names;
  }

  /**
   * Updates whether or not the draw window should loop.
   *
   * @param loop a boolean flag.
   */
  void setShouldLoop(boolean loop) {
    this.shouldLoop = loop;
  }
}
