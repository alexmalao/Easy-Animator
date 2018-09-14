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
 * Tests the use of builder to correctly add shapes and transitions.
 */
public class BuilderTest {

  // TODO: ADD MORE TESTS!

  // tests the builder that it works the same as the model
  @Test
  public void builderExample1() {
    AnimatorModel.Builder build = new AnimatorModel.Builder();
    build.addRectangle("R", 200.0f, 200.0f, 50.0f, 100.0f, 1.0f,
            0.0f, 0.0f, 1, 100);
    build.addOval("C", 500.0f, 100.0f, 60.0f, 30.0f, 0.0f,
            0.0f, 1.0f, 6, 100);
    build.addMove("R", 200.0f, 200.0f, 300.0f, 300.0f,
            10, 50);
    build.addMove("C", 500.0f, 100.0f, 500.0f, 400.0f,
            20, 70);
    build.addColorChange("C", 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 50, 80);
    build.addScaleToChange("R", 100.0f, 50.0f,
            100.0f, 25.0f, 51, 70);
    build.addMove("R", 300.0f, 300.0f,
            200.0f, 200.0f, 70, 100);
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(255, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 255),
            500.0, 100.0, 60.0, 30.0);
    Transition t1 = new MoveTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    Transition t2 = new MoveTransition(20, 70, 500.0, 100.0,
            500.0, 400.0);
    Transition t3 = new ColorTransition(50, 80, new Color(0, 0, 255),
            new Color(0, 255, 0));
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
    AnimatorView svg = new AnimatorText(new ModelAdapter(build.build()), app);
    svg.animate(1);

    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (255.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: "
                    + "(0.0,0.0,255.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n"
                    + "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) "
                    + "from t=50.0s to t=80.0s\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 100.0 from t=51.0s to t=70.0s\n"
                    + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70.0s to t=100.0s",
            app.toString());
  }

  // tests adding multiple transitions (it does not add duplicates, rather it just ignores)
  @Test
  public void builderExample2() {
    AnimatorModel.Builder build = new AnimatorModel.Builder();
    build.addRectangle("R", 200.0f, 200.0f, 50.0f, 100.0f, 1.0f,
            0.0f, 0.0f, 1, 100);
    build.addOval("C", 500.0f, 100.0f, 60.0f, 30.0f, 0.0f,
            0.0f, 1.0f, 6, 100);
    build.addMove("R", 200.0f, 200.0f, 300.0f, 300.0f,
            10, 50);
    build.addMove("R", 200.0f, 200.0f, 300.0f, 300.0f,
            10, 50);
    build.addMove("R", 200.0f, 200.0f, 300.0f, 300.0f,
            10, 50);

    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorText(new ModelAdapter(build.build()), app);
    svg.animate(1);

    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (255.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: "
                    + "(0.0,0.0,255.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s",
            app.toString());
  }

  // tests adding multiple transitions (it does not add duplicates, rather it just ignores)
  @Test
  public void builderExample3() {
    AnimatorModel.Builder build = new AnimatorModel.Builder();
    build.addRectangle("R", 200.0f, 200.0f, 50.0f, 100.0f, 1.0f,
            0.0f, 0.0f, 1, 100);
    build.addOval("C", 500.0f, 100.0f, 60.0f, 30.0f, 0.0f,
            0.0f, 1.0f, 6, 100);
    build.addColorChange("C", 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 50, 80);
    build.addColorChange("C", 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 50, 80);
    build.addColorChange("C", 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 50, 80);
    build.addColorChange("C", 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 50, 80);

    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorText(new ModelAdapter(build.build()), app);
    svg.animate(1);

    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (255.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: "
                    + "(0.0,0.0,255.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Shape C changes color from (0.0,0.0,255.0) to "
                    + "(0.0,255.0,0.0) from t=50.0s to t=80.0s",
            app.toString());
  }
}