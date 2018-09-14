package cs3500.animator.provider.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.animator.model.AnimatorOperations;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ITransition;
import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.transition.Transition;

/**
 * Acts as a wrapper for the AnimatorModel to make it implement the interface
 * for the EasyAnimOperations interface.
 */
public class EasyAnimModelAdapter implements EasyAnimOperations<IShape, ITransition> {

  private AnimatorOperations model;

  /**
   * Constructor for EasyAnimModelAdapter.
   * @param model takes an AnimatorOperations model to be used for the adapter.
   */
  public EasyAnimModelAdapter(AnimatorOperations model) {
    this.model = model;
  }

  /**
   * Adds a shape to animate with the given alias.
   * @param shape The shape to add to the scene.
   * @param alias The name to give the shape to address it later.
   * @param show the time to show the object.
   * @param hide the time to hide the object.
   * @return The same EasyAnimOperations.
   * @throws IllegalArgumentException upon bad/repeated alias.
   */
  public EasyAnimOperations<IShape, ITransition> addShape(String alias, IShape shape,
                                                          int show, int hide)
          throws IllegalArgumentException {
    return this;
  }

  /**
   * Returns an animated shape from it's alias.
   * @return the animated shape.
   */
  public AnimatedShape getAnimatedShape(String alias) {

    for (IShape shape : this.model.getShapes()) {
      if (shape.getName().equals(alias)) {
        return new AnimatedShapeAdapter(shape);
      }
    }

    return null;
  }

  /**
   * Returns all of the animated shapes in the model, mapped to their aliases.
   * @return Map of shapes.
   *     NOTE: Changed type from previous version to a clearer AnimatedShape
   */
  public Map<String, AnimatedShape> getAnimatedShapes() {
    Map<String, AnimatedShape> result = new HashMap<String, AnimatedShape>();
    List<IShape> shapes = this.model.getShapes();
    for (IShape shape : shapes) {
      result.put(shape.getName(), new AnimatedShapeAdapter(shape));
    }

    return result;
  }

  /**
   * Adds a tweening transition to the shape.
   * @param alias name of the shape to add the transition to.
   * @param startTime start time of transition.
   * @param endTime end time of transition.
   * @param endValue value to transition to.
   * @return the list of transitions
   * @throws IllegalArgumentException On: bad alias, bad property, overlapping property animations,
   *         bad end value type.
   */
  public Transition addTransition(
          String alias, ShapeProperty<?> endValue, int startTime, int endTime)
          throws IllegalArgumentException {
    return null;
  }

  /**
   * Returns a text description of the model.
   * @param speed translate the ticks to seconds.
   * @return text description.
   */
  public String getDescription(int speed) {
    String str = "Shapes:\n";
    ArrayList<ITransition> transitions = new ArrayList<ITransition>();
    for (IShape shape : model.getShapes()) {
      str += shape.toString(speed) + "\n\n";
      transitions.addAll(shape.getTransitions());
    }

    Collections.sort(transitions);

    for (ITransition t : transitions) {
      str += t.toString(speed) + "\n";
    }

    str = str.substring(0, str.length() - 1);

    return str;
  }
}
