package cs3500.animator.provider.property;

/**
 * Represents a ShapePropertyAbstract.
 */

public abstract class ShapePropertyAbstract<K> implements ShapeProperty<K> {
  protected K val;

  /**
   * Constructor for ShapePropertyAbstract.
   * @param val the given K.
   */

  public ShapePropertyAbstract(K val) {
    this.val = val;
  }

  @Override
  public K getValue() {
    return val;
  }

  @Override
  public abstract String toString();
}
