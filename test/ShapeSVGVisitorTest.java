import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.model.Transition;
import cs3500.animator.control.ShapeSVGVisitor;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ShapeSVGVisitor.
 */
public class ShapeSVGVisitorTest {

  // tests visitRectangle
  @Test
  public void rectangleExample1() {
    ShapeSVGVisitor visitor = new ShapeSVGVisitor(1);
    Rectangle rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    assertEquals("<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>", visitor.visit(rect));
  }

  // tests visitRectangle
  @Test
  public void rectangleExample2() {
    ShapeSVGVisitor visitor = new ShapeSVGVisitor(1);
    Rectangle rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    assertEquals("<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</rect>", visitor.apply(rect));
  }

  // tests visitRectangle
  @Test
  public void rectangleExample3() {
    ShapeSVGVisitor visitor = new ShapeSVGVisitor(1);
    Rectangle rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    Transition t1 = new MoveTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    Transition t4 = new ScaleTransition(51, 70, 100.0, 50.0,
            100.0, 25.0);
    rect.addTransition(t1);
    rect.addTransition(t4);
    assertEquals("<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n"
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
            + "</rect>", visitor.visit(rect));
  }

  // tests visitRectangle
  @Test
  public void rectangleExample4() {
    ShapeSVGVisitor visitor = new ShapeSVGVisitor(1);
    Rectangle rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    Transition t1 = new MoveTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    Transition t4 = new ScaleTransition(51, 70, 100.0, 50.0,
            100.0, 25.0);
    rect.addTransition(t1);
    rect.addTransition(t4);
    assertEquals("<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(1,0,0)\" visibility=\"hidden\" >\n"
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
            + "</rect>", visitor.apply(rect));
  }

  // tests visitOval
  @Test
  public void ovalExample1() {
    ShapeSVGVisitor visitor = new ShapeSVGVisitor(1);
    Oval oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    assertEquals("<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</ellipse>", visitor.visit(oval));
  }

  // tests visitOval
  @Test
  public void ovalExample2() {
    ShapeSVGVisitor visitor = new ShapeSVGVisitor(1);
    Oval oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    assertEquals("<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "</ellipse>", visitor.apply(oval));
  }

  // tests visitOval
  @Test
  public void ovalExample3() {
    ShapeSVGVisitor visitor = new ShapeSVGVisitor(1);
    Oval oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    Transition t2 = new MoveTransition(20, 70, 500.0, 100.0,
            500.0, 400.0);
    Transition t3 = new ColorTransition(50, 80, new Color(0, 0, 1),
            new Color(0, 1, 0));
    oval.addTransition(t2);
    oval.addTransition(t3);
    assertEquals("<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
            + "attributeName=\"cx\" from=\"500\" to=\"500\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
            + "attributeName=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"30000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,1)\" to=\"rgb(0,1,0)\" fill=\"freeze\" />\n"
            + "</ellipse>", visitor.visit(oval));
  }

  // tests visitOval
  @Test
  public void ovalExample4() {
    ShapeSVGVisitor visitor = new ShapeSVGVisitor(1);
    Oval oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    Transition t2 = new MoveTransition(20, 70, 500.0, 100.0,
            500.0, 400.0);
    Transition t3 = new ColorTransition(50, 80, new Color(0, 0, 1),
            new Color(0, 1, 0));
    oval.addTransition(t2);
    oval.addTransition(t3);
    assertEquals("<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,1)\" visibility=\"hidden\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"1ms\" attributeName="
            + "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
            + "attributeName=\"cx\" from=\"500\" to=\"500\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
            + "attributeName=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"30000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,1)\" to=\"rgb(0,1,0)\" fill=\"freeze\" />\n"
            + "</ellipse>", visitor.apply(oval));
  }
}