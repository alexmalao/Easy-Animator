package cs3500.animator.view;

import cs3500.animator.model.IShape;
import cs3500.animator.util.ModelAdapter;
import cs3500.animator.control.ShapeVisualVisitor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * A panel component to be packed into the AnimatorVisual frame.
 */
public class AnimatorPanel extends JPanel {

  private final ModelAdapter model;
  private int currentTick;
  private HashMap<String, Boolean> visibleShapes;

  /**
   * Create a new AnimatorPanel at the 0th tick using the given model.
   * @param model the model associated with this view
   */
  AnimatorPanel(ModelAdapter model) {
    super();
    this.setPreferredSize(new Dimension(500, 500));
    this.model = model;
    this.currentTick = 0;
    this.setBackground(Color.WHITE);

    HashMap<String, Boolean> visibles = new HashMap<String, Boolean>();
    for (IShape shape : model.getShapes()) {
      visibles.put(shape.getName(), true);
    }

    this.visibleShapes = visibles;
  }

  /**
   * Gets the ModelAdapter.
   * @return the ModelAdapter
   */
  public ModelAdapter getModel() {
    return model;
  }

  /**
   * Set the current tick of the panel.
   * @param tick the current tick
   * @throws IllegalArgumentException if a tick less than 0 is given.
   */
  public void setCurrentTick(int tick) throws IllegalArgumentException {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick can not be negative.");
    }
    currentTick = tick;
  }

  /**
   * Return the current tick.
   */
  public int getCurrentTick() {
    return this.currentTick;
  }

  /**
   * Gets the visible shapes hashmap from the AnimatorPanel.
   * @return the visibleShapes HashMap
   */
  public HashMap<String, Boolean> getVisibleShapes() {
    return visibleShapes;
  }

  /**
   * Sets the visibility of a specific shape based on name.
   * @param shapeName the name of the shape as a String
   * @param visible the visibility of the shape
   */
  public void setShapeVisiblility(String shapeName, boolean visible) {
    this.visibleShapes.replace(shapeName, visible);
  }

  /**
   * Paints the shapes on to the Graphics.
   * @param g the graphics to paint the components onto
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    ShapeVisualVisitor visitor = new ShapeVisualVisitor(currentTick, g);
    for (IShape shape : model.getShapes()) {
      if (visibleShapes.get(shape.getName())) {
        visitor.apply(shape);
      }
    }
  }
}
