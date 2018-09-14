package cs3500.animator.model;

/**
 * This is a representation for transition. It is able to modify shapes and act as commands.
 */
public abstract class Transition implements ITransition {

  protected int startTime;
  protected int endTime;
  protected IShape shape;

  /**
   * Constructs a transition with the end time.
   * @param startTime the time when the transition starts
   * @param endTime the time when the transition ends
   */
  public Transition(int startTime, int endTime) {
    if (startTime > endTime) {
      throw new IllegalArgumentException("Invalid time interval.");
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.shape = null;
  }

  /**
   * Gets the start time of this transition.
   * @return the start time of this transition
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the end time of this transition.
   * @return the end time of this transition
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Adds the shape to the transition.
   * @param shape the shape to be added
   */
  public void addShape(IShape shape) {
    this.shape = shape;
  }

  /**
   * Converts this transition to a string representation.
   * @param ticks the amount of ticks per second
   * @return the string representation of the transition
   */
  public abstract String toString(int ticks);

  /**
   * Checks whether this transition overlaps with the given transition.
   * @param t the given transition
   * @return whether the transitions overlap
   */
  public abstract boolean overlap(ITransition t);

  /**
   * Checks whether this transition overlaps with the given move transition.
   * @param t the given move transition
   * @return whether the transitions overlap
   */
  public boolean overlap(MoveTransition t) {
    return false;
  }

  /**
   * Checks whether this transition overlaps with the given scale transition.
   * @param t the given scale transition
   * @return whether the transitions overlap
   */
  public boolean overlap(ScaleTransition t) {
    return false;
  }

  /**
   * Checks whether this transition overlaps with the given color transition.
   * @param t the given color transition
   * @return whether the transitions overlap
   */
  public boolean overlap(ColorTransition t) {
    return false;
  }

  /**
   * Compares the two transitions based on their start times.
   * @param t the transition to be compared with this transition
   */
  public int compareTo(ITransition t) {
    return this.startTime - t.getStartTime();
  }

  /**
   * Gets the shape of the transition.
   * @return the shape the transition works on.
   */
  public IShape getShape() {
    return shape;
  }
}
