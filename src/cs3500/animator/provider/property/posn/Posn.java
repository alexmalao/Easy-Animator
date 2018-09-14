package cs3500.animator.provider.property.posn;

import java.util.Objects;

/**
 * Represents a position in euclidean space. NOTE: Added a copy constructor.
 * //NOTE: Changed getters to boxed Floats to convert them to strings in views.
 */

public class Posn implements IPosn {
  private float x;
  private float y;

  /**
   * Creates a position at the specified coordinates.
   * @param x the x position
   * @param y the y position
   */

  public Posn(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor.
   *
   */

  public Posn(Posn other) {
    this.x = other.getX();
    this.y = other.getY();
  }

  @Override
  public Float getX() {
    return x;
  }

  @Override
  public Float getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Posn) {
      Posn posn = (Posn) o;
      return posn.getX().equals(this.getX()) && Objects.equals(posn.getY(), this.getY());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.getX().hashCode() + this.getY().hashCode();
  }
}
