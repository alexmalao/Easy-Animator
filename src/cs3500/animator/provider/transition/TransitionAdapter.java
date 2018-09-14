package cs3500.animator.provider.transition;

import cs3500.animator.control.TransitionVisitor;
import cs3500.animator.model.ITransition;
import cs3500.animator.provider.model.ShapeEndValVisitor;
import cs3500.animator.provider.model.ShapeStartValVisitor;
import cs3500.animator.provider.property.ShapeProperty;

/**
 * Acts as a wrapper for ITransition and adapts it to the providers code.
 */
public class TransitionAdapter implements Transition {

  ITransition baseTransition;

  /**
   * Construct a new TransitionAdapter with the given base ITransition.
   * @param baseTransition the ITransition to be used as the base
   */
  public TransitionAdapter(ITransition baseTransition) {
    this.baseTransition = baseTransition;
  }

  @Override
  public ShapeProperty<?> getStartValue() {
    // Use the ShapeStartValVisitor to get the start value.
    TransitionVisitor<ShapeProperty<?>> visitor = new ShapeStartValVisitor();
    return visitor.apply(baseTransition);
  }

  @Override
  public ShapeProperty<?> getEndValue() {
    // Use the ShapeEndValVisitor to get the start value.
    TransitionVisitor<ShapeProperty<?>> visitor = new ShapeEndValVisitor();
    return visitor.apply(baseTransition);
  }

  @Override
  public int getStartTime() {
    return baseTransition.getStartTime();
  }

  @Override
  public int getEndTime() {
    return baseTransition.getEndTime();
  }

  @Override
  public String getDesc(String name, int time) {
    return baseTransition.toString(time);
  }
}
