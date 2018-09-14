package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.util.TweenModelBuilder;

/**
 * Represents the animator model that keeps the world states.  The world represents a single
 * animation.  This is represented as a list of IShapes.
 */
public final class AnimatorModel implements AnimatorOperations {

  /**
   * The builder used for AnimatorModel that allows adding shapes and transitions.
   */
  public static final class Builder implements TweenModelBuilder<AnimatorOperations> {

    private final AnimatorOperations model;

    /**
     * Constructs a builder with an empty AnimatorModel inside.
     */
    public Builder() {
      this.model = new AnimatorModel();
    }

    /**
     * Add a new oval to the model with the given specifications.
     *
     * @param name        the unique name given to this shape
     * @param cx          the x-coordinate of the center of the oval
     * @param cy          the y-coordinate of the center of the oval
     * @param xRadius     the x-radius of the oval
     * @param yRadius     the y-radius of the oval
     * @param red         the red component of the color of the oval
     * @param green       the green component of the color of the oval
     * @param blue        the blue component of the color of the oval
     * @param startOfLife the time tick at which this oval appears
     * @param endOfLife   the time tick at which this oval disappears
     * @return the builder object
     */
    public TweenModelBuilder<AnimatorOperations> addOval(String name, float cx, float cy,
                                                         float xRadius, float yRadius, float red,
                                                         float green, float blue, int startOfLife,
                                                         int endOfLife) {
      try {
        model.addShape(new Oval(name, startOfLife, endOfLife, new Color(red, green, blue),
                cx, cy, xRadius, yRadius));
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      return this;
    }

    /**
     * Add a new rectangle to the model with the given specifications.
     *
     * @param name        the unique name given to this shape
     * @param lx          the x-coordinate of the lower left corner of the
     *                    rectangle
     * @param ly          the y-coordinate of the lower left corner of the
     *                    rectangle
     * @param width       the width of the rectangle
     * @param height      the height of the rectangle
     * @param red         the red component of the color of the rectangle
     * @param green       the green component of the color of the rectangle
     * @param blue        the blue component of the color of the rectangle
     * @param startOfLife the time tick at which this rectangle appears
     * @param endOfLife   the time tick at which this rectangle disappears
     * @return the builder object
     */
    public TweenModelBuilder<AnimatorOperations> addRectangle(String name, float lx, float ly,
                                                              float width, float height, float red,
                                                              float green, float blue,
                                                              int startOfLife, int endOfLife) {
      try {
        model.addShape(new Rectangle(name, startOfLife, endOfLife, new Color(red, green, blue),
                lx, ly, width, height));
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      return this;
    }

    /**
     * Move the specified shape to the given position during the given time
     * interval.
     *
     * @param name      the unique name of the shape to be moved
     * @param moveFromX the x-coordinate of the initial position of this shape.
     *                  What this x-coordinate represents depends on the shape.
     * @param moveFromY the y-coordinate of the initial position of this shape.
     *                  what this y-coordinate represents depends on the shape.
     * @param moveToX   the x-coordinate of the final position of this shape. What
     *                  this x-coordinate represents depends on the shape.
     * @param moveToY   the y-coordinate of the final position of this shape. what
     *                  this y-coordinate represents depends on the shape.
     * @param startTime the time tick at which this movement should start
     * @param endTime   the time tick at which this movement should end
     * @return the builder object
     */
    public TweenModelBuilder<AnimatorOperations> addMove(String name, float moveFromX,
                                                         float moveFromY, float moveToX,
                                                         float moveToY, int startTime,
                                                         int endTime) {
      try {
        model.addTransition(new MoveTransition(startTime, endTime,
                moveFromX, moveFromY, moveToX, moveToY), name);
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      return this;
    }

    /**
     * Change the color of the specified shape to the new specified color in the
     * specified time interval.
     *
     * @param name      the unique name of the shape whose color is to be changed
     * @param oldR      the r-component of the old color
     * @param oldG      the g-component of the old color
     * @param oldB      the b-component of the old color
     * @param newR      the r-component of the new color
     * @param newG      the g-component of the new color
     * @param newB      the b-component of the new color
     * @param startTime the time tick at which this color change should start
     * @param endTime   the time tick at which this color change should end
     * @return the builder object
     */
    public TweenModelBuilder<AnimatorOperations> addColorChange(String name, float oldR,
                                                                float oldG, float oldB,
                                                                float newR, float newG,
                                                                float newB, int startTime,
                                                                int endTime) {
      try {
        model.addTransition(new ColorTransition(startTime, endTime,
                new Color(oldR, oldG, oldB), new Color(newR, newG, newB)), name);
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      return this;
    }


    /**
     * Change the x and y extents of this shape from the specified extents to the
     * specified target extents. What these extents actually mean depends on the
     * shape, but these are roughly the extents of the box enclosing the shape.
     *
     * @param name the unique name of the shape that needs to be scaled
     * @param fromSx the original x-size of the shape
     * @param fromSy the original y-size of the shape
     * @param toSx the new s-size of the shape
     * @param toSy the new y-size of the shape
     * @param startTime the time tick at which this color change should start
     * @param endTime   the time tick at which this color change should end
     * @return the builder object
     */
    public TweenModelBuilder<AnimatorOperations> addScaleToChange(String name, float fromSx,
                                                                  float fromSy,float toSx,
                                                                  float toSy, int startTime,
                                                                  int endTime) {
      try {
        model.addTransition(new ScaleTransition(startTime, endTime,
                fromSx, fromSy, toSx, toSy), name);
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      return this;
    }

    /**
     * Return the model built so far.
     *
     * @return the model that was constructed so far
     */
    public AnimatorOperations build() {
      return this.model;
    }
  }

  private List<IShape> shapeList;

  /**
   * Creates a new Animator Model with initialized fields.
   */
  public AnimatorModel() {
    this.shapeList = new ArrayList<IShape>();
  }

  //TODO: IF THIS ADDS MORE THAN 1 OF SAME NAME, ILLEGALARGUMENTEXCEPTION
  /**
   * Adds the shape to the model.
   * @param shape the shape to be added to the model.
   * @throws IllegalArgumentException if a shape name is already used
   */
  public void addShape(IShape shape) {
    this.shapeList.add(shape);
  }

  /*
  Changed from giving the shapeIndex to adding to the transition based on shape name.
  This is because the builder doesn't have access to the shapeList, therefore can't iterate through
  it to find the correct name.
   */
  /**
   * Adds the transition to the model.
   * @param transition the transition to be added
   * @param shapeName the index of the shape for the transition
   * @throws IllegalArgumentException if a transition overlaps
   */
  public void addTransition(ITransition transition, String shapeName) {
    for (int idx = 0; idx < shapeList.size(); idx++) {
      if (this.shapeList.get(idx).getName().equals(shapeName)) {
        this.shapeList.get(idx).addTransition(transition);
        break;
      }
    }
  }

  /**
   * Gets the list of shapes from the model.
   * @return the list of shapes
   */
  public List<IShape> getShapes() {
    return shapeList;
  }
}
