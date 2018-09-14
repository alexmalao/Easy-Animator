package cs3500.animator.util;

import java.util.List;

import cs3500.animator.model.AnimatorOperations;
import cs3500.animator.model.IShape;

/*
 This was added to ensure that all views can only use a ModelAdapter, which restricts
 mutating the model completely.
 */
/**
 * Used to restrict the views from doing anything but grabbing the shapes from an
 * AnimatorOperations interface.
 */
public class ModelAdapter {

  private AnimatorOperations model;

  /**
   * Constructs a ModelAdapter with the given model.
   * @param model the model to be passed tho the model adapter
   */
  public ModelAdapter(AnimatorOperations model) {
    this.model = model;
  }

  /**
   * Get the list of shapes that have been added to the model.
   * @return the list of shapes
   */
  public List<IShape> getShapes() {
    return model.getShapes();
  }
}
