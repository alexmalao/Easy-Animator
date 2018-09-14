package cs3500.animator.provider.model;

import cs3500.animator.model.ITransition;
import cs3500.animator.provider.transition.TransitionAdapter;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IShape;
import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.transition.Transition;

/**
 * Acts as a wrapper for the IShape and adapts it for the providers code.
 */
public class AnimatedShapeAdapter implements AnimatedShape {

  IShape baseShape;

  /**
   * Constructor for AnimatedShapeAdapter.
   * @param baseShape the base shape to be adapted for
   */
  public AnimatedShapeAdapter(IShape baseShape) {
    this.baseShape = baseShape;
  }

  /**
   * Returns a description of the animated object.
   * //NOTE: added speed parameter for translating to seconds
   * @param speed the speed at which to describe the animation.
   * @return the description.
   */
  public String getObjectDescription(int speed) {
    return this.baseShape.toString(speed);
  }

  /**
   * Returns the transitions this shape undergoes.
   * @return the list of transitions
   */
  public List<Transition> getTransitions() {
    List<ITransition> iShapeTransitions = baseShape.getTransitions();
    return convertTransitions(iShapeTransitions);
  }

  /**
   * Create a List of Transition from the given List of ITransition.
   * @param iShapeTransitions the transitions to be converted
   * @return the List of Transition objects
   */
  private List<Transition> convertTransitions(List<ITransition> iShapeTransitions) {
    List<Transition> adaptedList = new ArrayList<>();
    for (ITransition transition: iShapeTransitions) {
      adaptedList.add(new TransitionAdapter(transition));
    }
    return adaptedList;
  }

  /**
   * Gets the initial state of the shape as an EasyObject.
   * @return the shape.
   */
  public EasyObject getInitialShape() {
    return new EasyObjectAdapter(this.baseShape);
  }

  /**
   * Grabs the start time of the shape.
   *
   * @return an integer.
   */
  public int getShowTime() {
    return baseShape.getAppears();
  }

  /**
   * Grabs the hide time of the shape.
   *
   * @return an integer.
   */
  public int getHideTime() {
    return baseShape.getDisappears();
  }

  /**
   * Gets the last keyframe value of the specified property at the given time.
   * //NOTE: Added this to interface, as requested by instructor.
   */
  public <A extends ShapeProperty> ShapeProperty getLastValue(Class<A> transitionType,
                                                              int startTime) {
    // TODO this
    IShape updatedShape = new ShapeTimeVisitor(startTime).apply(baseShape);
    return new ShapePropertyVisitor((Class<ShapeProperty>) transitionType).apply(updatedShape);
  }
}