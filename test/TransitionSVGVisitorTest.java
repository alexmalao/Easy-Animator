import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.model.Shape;
import cs3500.animator.control.TransitionSVGVisitor;

import static org.junit.Assert.assertEquals;

/**
 * Tests the TransitionSVGVisitor.
 */
public class TransitionSVGVisitorTest {

  // tests visit moveTransition
  @Test
  public void moveExample1() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    MoveTransition move = new MoveTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    assertEquals("    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n",
            visitor.visit(move));
  }

  // tests visit moveTransition
  @Test
  public void moveExample2() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    MoveTransition move = new MoveTransition(10, 50, 200.0, 200.0,
            300.0, 300.0);
    assertEquals("    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
                    + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" "
                    + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n",
            visitor.apply(move));
  }

  // tests visit moveTransition
  @Test
  public void moveExample3() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    MoveTransition move = new MoveTransition(20, 70, 500.0, 100.0,
            500.0, 400.0);
    assertEquals("    <animate attributeType=\"xml\" begin=\"20000.0ms\" "
                    + "dur=\"50000.0ms\" attributeName=\"x\" from=\"500\" to=\"500\" "
                    + "fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
                    + "attributeName=\"y\" from=\"100\" to=\"400\" fill=\"freeze\" />\n",
            visitor.visit(move));
  }

  // tests visit moveTransition
  @Test
  public void moveExample4() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    MoveTransition move = new MoveTransition(20, 70, 500.0, 100.0,
            500.0, 400.0);
    assertEquals("    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
                    + "attributeName=\"x\" from=\"500\" to=\"500\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"50000.0ms\" "
                    + "attributeName=\"y\" from=\"100\" to=\"400\" fill=\"freeze\" />\n",
            visitor.apply(move));
  }

  // tests visit scaleTransition
  @Test
  public void scaleExample1() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    ScaleTransition scale = new ScaleTransition(51, 70, 100.0, 50.0,
            100.0, 25.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    scale.addShape(oval);
    assertEquals("    <animate attributeType=\"xml\" begin=\"51000.0ms\" "
            + "dur=\"19000.0ms\" attributeName=\"rx\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
            + "attributeName=\"ry\" from=\"100\" to=\"100\" fill=\"freeze\" />\n",
            visitor.visit(scale));
  }

  // tests visit scaleTransition
  @Test
  public void scaleExample2() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    ScaleTransition scale = new ScaleTransition(51, 70, 100.0, 50.0,
            100.0, 25.0);
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    scale.addShape(rect);
    assertEquals("    <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n",
            visitor.visit(scale));
  }

  // tests visit scaleTransition
  @Test
  public void scaleExample3() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    ScaleTransition scale = new ScaleTransition(51, 70, 100.0, 50.0,
            100.0, 25.0);
    Shape oval = new Oval("C", 6, 100, new Color(0, 0, 1),
            500.0, 100.0, 60.0, 30.0);
    scale.addShape(oval);
    assertEquals("    <animate attributeType=\"xml\" begin=\"51000.0ms\" "
                    + "dur=\"19000.0ms\" attributeName=\"rx\" from=\"50\" to=\"25\" "
                    + "fill=\"freeze\" />\n    <animate attributeType=\"xml\" begin=\"51000.0ms\""
                    + " dur=\"19000.0ms\" attributeName=\"ry\" from=\"100\" to=\"100\" "
                    + "fill=\"freeze\" />\n",
            visitor.apply(scale));
  }

  // tests visit scaleTransition
  @Test
  public void scaleExample4() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    ScaleTransition scale = new ScaleTransition(51, 70, 100.0, 50.0,
            100.0, 25.0);
    Shape rect = new Rectangle("R", 1, 100, new Color(1, 0, 0),
            200.0, 200.0, 50.0, 100.0);
    scale.addShape(rect);
    assertEquals("    <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
                    + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" "
                    + "attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n",
            visitor.apply(scale));
  }

  // tests visitColorTransition
  @Test
  public void colorExample1() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    ColorTransition color = new ColorTransition(50, 80, new Color(0, 0, 1),
            new Color(0, 1, 0));
    assertEquals("    <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"30000.0ms\""
            + " attributeName=\"fill\" from=\"rgb(0,0,1)\" to=\"rgb(0,1,0)\" fill=\"freeze\" />\n",
            visitor.visit(color));
  }

  // tests visitColorTransition
  @Test
  public void colorExample2() {
    TransitionSVGVisitor visitor = new TransitionSVGVisitor(1);
    ColorTransition color = new ColorTransition(50, 80, new Color(0, 0, 1),
            new Color(0, 1, 0));
    assertEquals("    <animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"30000.0ms\""
                    + " attributeName=\"fill\" from=\"rgb(0,0,1)\" to=\"rgb(0,1,0)\" "
                    + "fill=\"freeze\" />\n",
            visitor.visit(color));
  }
}