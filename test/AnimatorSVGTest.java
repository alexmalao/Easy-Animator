import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorOperations;
import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.model.Shape;
import cs3500.animator.model.Transition;
import cs3500.animator.util.ModelAdapter;
import cs3500.animator.view.AnimatorSVG;
import cs3500.animator.view.AnimatorView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the text output of AnimatorSVG.
 */
public class AnimatorSVGTest {

  // tests the svg
  @Test
  public void animatorSVGExample1() {
    AnimatorOperations model = new AnimatorModel();
    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorSVG(new ModelAdapter(model), app);
    svg.animate(1);
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "</svg>", app.toString());
  }

  // tests the svg
  @Test
  public void animatorSVGExample2() {
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
    AnimatorView svg = new AnimatorSVG(new ModelAdapter(model), app);
    svg.animate(1);
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\""
            + " fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" "
            + "attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" "
            + "attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" fill="
            + "\"rgb(0,0,1)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
            + "attributeName=\"cx\" from=\"500\" to=\"500\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
            + "attributeName=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"30000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,1)\" to=\"rgb(0,1,0)\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "\n"
            + "</svg>", app.toString());
  }

  // tests the svg
  @Test
  public void animatorSVGExample3() {
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
    AnimatorView svg = new AnimatorSVG(new ModelAdapter(model), app);
    svg.animate(3);
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"333.3333333333333ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"33333.333333333336ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"3333.3333333333335ms\" "
            + "dur=\"13333.333333333334ms\" attributeName=\"x\" from=\"200\" to=\"300\" "
            + "fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"3333.3333333333335ms\" "
            + "dur=\"13333.333333333334ms\" attributeName=\"y\" from=\"200\" to=\"300\" "
            + "fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"17000.0ms\" dur=\"6333.333333333332ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"17000.0ms\" dur=\"6333.333333333332ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"23333.333333333332ms\" "
            + "dur=\"10000.000000000004ms\" attributeName=\"x\" from=\"300\" to=\"200\" "
            + "fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"23333.333333333332ms\" "
            + "dur=\"10000.000000000004ms\" attributeName=\"y\" from=\"300\" to=\"200\" "
            + "fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"33333.333333333336ms\" "
            + "dur=\"1ms\" attributeName=\"visibility\" from=\"visible\" to=\"hidden\" "
            + "fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"6666.666666666667ms\" "
            + "dur=\"16666.666666666664ms\" attributeName=\"cx\" from=\"500\" to=\"500\" "
            + "fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"6666.666666666667ms\" "
            + "dur=\"16666.666666666664ms\" attributeName=\"cy\" from=\"100\" to=\"400\" "
            + "fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"16666.666666666668ms\" "
            + "dur=\"10000.0ms\" attributeName=\"fill\" from=\"rgb(0,0,1)\" to=\"rgb(0,1,0)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "\n"
            + "</svg>", app.toString());
  }

  // tests the svg
  @Test
  public void animatorSVGExample4() {
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
    AnimatorView svg = new AnimatorSVG(new ModelAdapter(model), app);
    svg.animate(1);
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"200.0\" height=\"200.0\""
            + " fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"width\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"height\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"200.0\" ry=\"200.0\" "
            + "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"rx\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"ry\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "\n"
            + "</svg>", app.toString());
  }

  // tests the svg
  @Test
  public void animatorSVGExample5() {
    AnimatorModel model = new AnimatorModel();
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 200.0, 200.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 200.0, 200.0);
    model.addShape(rect);
    model.addShape(oval);
    Appendable app = new StringBuilder();
    AnimatorView svg = new AnimatorSVG(new ModelAdapter(model), app);
    svg.animate(100);
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"200.0\" height=\"200.0\" "
            + "fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"10.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"200.0\" ry=\"200.0\" "
            + "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"60.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1ms\" "
            + "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "\n"
            + "</svg>", app.toString());
  }
}