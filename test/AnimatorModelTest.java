
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.model.Shape;
import cs3500.animator.model.Transition;

/**
 * Runs tests on the animatormodel class.
 */
public class AnimatorModelTest {

  //TODO: add tests for the getters instead

  // tests overlapping
  @Test(expected = IllegalArgumentException.class)
  public void addTransitionException1() {
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    Transition t1 = new MoveTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    Transition t2 = new MoveTransition(20, 70, 500.0, 100.0,
            500.0, 400.0);
    model.addShape(rect);
    model.addShape(oval);
    model.addTransition(t1, "R");
    model.addTransition(t2, "R");
  }

  // tests overlapping
  @Test(expected = IllegalArgumentException.class)
  public void addTransitionException2() {
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    Transition t1 = new ScaleTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    Transition t2 = new ScaleTransition(49, 70, 500.0, 100.0,
            500.0, 400.0);
    model.addShape(rect);
    model.addShape(oval);
    model.addTransition(t1, "R");
    model.addTransition(t2, "R");
  }

  // tests overlapping
  @Test(expected = IllegalArgumentException.class)
  public void addTransitionException3() {
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    Transition t1 = new ColorTransition(10, 50, new Color(5, 5, 5),
            new Color(5, 5, 5));
    Transition t2 = new ColorTransition(20, 70, new Color(5, 5, 5),
            new Color(5, 5, 5));
    model.addShape(rect);
    model.addShape(oval);
    model.addTransition(t1, "C");
    model.addTransition(t2, "C");
  }
}