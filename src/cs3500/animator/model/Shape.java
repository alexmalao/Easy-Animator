package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is an abstract representation of the shape. The only concrete fields
 * include the shape's name and the time of appearance and disappearance.
 */
public abstract class Shape implements IShape {

  protected String name;
  protected int appears;
  protected int disappears;
  protected Color color;
  protected List<ITransition> transitions;

  /**
   * Creates a shape based on the name and times of appearance and disappearance.
   * @param name the name of the shape
   * @param appears when the shape appears
   * @param disappears when the shape disappears
   */
  public Shape(String name, int appears, int disappears, Color color) {
    if (appears > disappears) {
      throw new IllegalArgumentException("Not valid time interval");
    }

    this.name = name;
    this.appears = appears;
    this.disappears = disappears;
    this.color = color;
    this.transitions = new ArrayList<ITransition>();
  }

  /**
   * Converts this shape to a string representation.
   * @param ticks the amount of ticks per second
   * @return the string representation of the shape
   */
  public abstract String toString(int ticks);

  /**
   * Gets the name of the shape.
   * @return the name of the shape
   */
  public String getName() {
    return this.name;
  }

  /**
   * Adds a transition to the shapes.
   * @param t the transition to be added to the shape
   */
  public void addTransition(ITransition t) {
    for (ITransition trans : this.transitions) {
      if (t.overlap(trans)) {
        throw new IllegalArgumentException("Overlapping transition intervals");
      }
    }

    t.addShape(this);

    this.transitions.add(t);
    Collections.sort(this.transitions);
  }

  /**
   * Gets the transitions from the shape.
   * @return the transitions from the shape
   */
  public List<ITransition> getTransitions() {
    return this.transitions;
  }

  /*
  Below getters are added for visitor the visitor pattern.
   */

  /**
   * Gets the time that the shape appears.
   * @return the time the shape appears
   */
  public int getAppears() {
    return appears;
  }

  /**
   * Gets the time that the shape disappears.
   * @return the time the shape disappears
   */
  public int getDisappears() {
    return disappears;
  }

  /**
   * Gets the color of the shape.
   * @return the color of the shape
   */
  public Color getColor() {
    return color;
  }
}
