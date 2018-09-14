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
import cs3500.animator.util.ModelAdapter;
import cs3500.animator.view.AnimatorText;
import cs3500.animator.view.AnimatorView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the text output of the AnimatorText class.
 */
public class AnimatorTextTest {

  // tests the text output
  @Test
  public void animatorTextExample1() {
    AnimatorModel model = new AnimatorModel();
    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorText(new ModelAdapter(model), app);
    svg.animate(1);
    assertEquals("Shapes:", app.toString());
  }

  // tests the text output
  @Test
  public void animatorTextExample2() {
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    Transition t1 = new MoveTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    Transition t2 = new MoveTransition(20, 70, 500.0, 100.0,
            500.0, 400.0);
    Transition t3 = new ColorTransition(50, 80, new Color(0, 0, 1),
            new Color(0, 1, 0));
    Transition t4 = new ScaleTransition(51, 70, 100.0, 50.0,
            100.0, 25.0);
    Transition t5 = new MoveTransition(70, 100, 300.0, 300.0,
            200.0, 200.0);
    model.addShape(rect);
    model.addShape(oval);
    model.addTransition(t1, "R");
    model.addTransition(t2, "C");
    model.addTransition(t3, "C");
    model.addTransition(t4, "R");
    model.addTransition(t5, "R");
    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorText(new ModelAdapter(model), app);
    svg.animate(1);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\n"
            + "Disappears at t=100.0s\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=6.0s\n"
            + "Disappears at t=100.0s\n"
            + "\n"
            + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n"
            + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n"
            + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50.0s to t=80.0s\n"
            + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from "
            + "t=51.0s to t=70.0s\n"
            + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70.0s to t=100.0s",
            app.toString());
  }

  // tests the text output
  @Test
  public void animatorTextExample3() {
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    Transition t1 = new MoveTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    Transition t2 = new MoveTransition(20, 70, 500.0, 100.0,
            500.0, 400.0);
    Transition t3 = new ColorTransition(50, 80, new Color(0, 0, 1),
            new Color(0, 1, 0));
    Transition t4 = new ScaleTransition(51, 70, 100.0, 50.0,
            100.0, 25.0);
    Transition t5 = new MoveTransition(70, 100, 300.0, 300.0,
            200.0, 200.0);
    model.addShape(rect);
    model.addShape(oval);
    model.addTransition(t1, "R");
    model.addTransition(t2, "C");
    model.addTransition(t3, "C");
    model.addTransition(t4, "R");
    model.addTransition(t5, "R");
    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorText(new ModelAdapter(model), app);
    svg.animate(3);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=0.3333333333333333s\n"
            + "Disappears at t=33.333333333333336s\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=2.0s\n"
            + "Disappears at t=33.333333333333336s\n"
            + "\n"
            + "Shape R moves from (200.0,200.0) to (300.0,300.0) from"
            + " t=3.3333333333333335s to t=16.666666666666668s\n"
            + "Shape C moves from (500.0,100.0) to (500.0,400.0) from"
            + " t=6.666666666666667s to t=23.333333333333332s\n"
            + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0)"
            + " from t=16.666666666666668s to t=26.666666666666668s\n"
            + "Shape R scales from Width: 50.0, Height: 100.0 to Width:"
            + " 25.0, Height: 100.0 from t=17.0s to t=23.333333333333332s\n"
            + "Shape R moves from (300.0,300.0) to (200.0,200.0) from "
            + "t=23.333333333333332s to t=33.333333333333336s", app.toString());
  }

  // tests the text output
  @Test
  public void animatorTextExample4() {
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 200.0, 200.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 200.0, 200.0);
    Transition t1 = new ScaleTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    Transition t2 = new ScaleTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    model.addShape(rect);
    model.addShape(oval);
    model.addTransition(t1, "R");
    model.addTransition(t2, "C");
    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorText(new ModelAdapter(model), app);
    svg.animate(1);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 200.0, "
            + "Height: 200.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\n"
            + "Disappears at t=100.0s\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 200.0, Y radius: 200.0, "
            + "Color: (0.0,0.0,1.0)\n"
            + "Appears at t=6.0s\n"
            + "Disappears at t=100.0s\n"
            + "\n"
            + "Shape R scales from Width: 200.0, Height: 200.0 to Width: 300.0, "
            + "Height: 300.0 from t=10.0s to t=50.0s\n"
            + "Shape C scales from X radius: 200.0, Y radius: 200.0 to X radius: "
            + "300.0, Y radius: 300.0 from t=10.0s to t=50.0s", app.toString());
  }

  // tests the text output
  @Test
  public void animatorTextExample5() {
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 200.0, 200.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 200.0, 200.0);
    model.addShape(rect);
    model.addShape(oval);
    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorText(new ModelAdapter(model), app);
    svg.animate(100);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 200.0,"
            + " Height: 200.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=0.01s\n"
            + "Disappears at t=1.0s\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 200.0, Y radius: 200.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=0.06s\n"
            + "Disappears at t=1.0s\n", app.toString());
  }
}