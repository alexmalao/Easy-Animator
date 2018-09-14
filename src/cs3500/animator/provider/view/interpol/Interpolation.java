package cs3500.animator.provider.view.interpol;

/**
 * A linear interpolation over a generic type.
 * 
 * @author Anand
 *
 * @param <T> the Type of Interpolation.
 */
public abstract class Interpolation<T> {

  /**
   * The linear interpolation function.
   * 
   * @param a the double start time.
   * @param b the double end time.
   * @param aVal the value of the start time.
   * @param bVal the value of the end time.
   * @param t the time to create an interpolation for.
   * @return a linear interpolated value.
   * @throws IllegalArgumentException if any time is negative or the end time is smaller than the
   *         start time.
   */
  public double apply(double a, double b, double aVal, double bVal, double t) {
    double diff = b - a;
    if (diff <= 0) {
      throw new IllegalArgumentException("Must provide end time after start time!");
    }
    if (a < 0 || b < 0 || t < 0) {
      throw new IllegalArgumentException("Non-negative times only!");
    }
    double partialA = aVal * ((b - t) / diff);
    double partialB = bVal * ((t - a) / diff);
    return partialA + partialB;
  }

  /**
   * Creates an Interpolation across a T by feeding in a start and end type, and creating an
   * intermediary type at the given time {@code t}.
   * 
   * @param start the starting T
   * @param end the ending T.
   * @param a the double start time.
   * @param b the double end time.
   * @param t the time to create an interpolation of
   * @return an interpolated T.
   */
  public abstract T interpolate(T start, T end, double a, double b, double t);
}
