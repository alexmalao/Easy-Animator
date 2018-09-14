
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ITransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.model.Shape;

import static org.junit.Assert.assertEquals;

/**
 * Runs tests on the shape class.
 */
public class ShapeTest {

  // tests toString on the rectangle class
  @Test
  public void ovalToStringExample1() {
    Shape oval = new Oval("C", 6, 100,
            new Color(0, 0, 1), 500.0, 100.0,
            60.0, 30.0);
    assertEquals("Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=6.0s\n"
            + "Disappears at t=100.0s", oval.toString(1));
  }

  // tests toString on the rectangle class
  @Test
  public void ovalToStringExample2() {
    Shape oval = new Oval("OvalManWho", 1, 1,
            new Color(255, 255, 255), 500.0, 100.0,
            0.0, 0.0);
    assertEquals("Name: OvalManWho\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 0.0, Y radius: 0.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=1.0s\n"
            + "Disappears at t=1.0s", oval.toString(1));
  }

  // tests toString on the rectangle class
  @Test
  public void ovalToStringExample3() {
    Shape oval = new Oval("C", 6, 100,
            new Color(0, 0, 1), 500.0, 100.0,
            60.0, 30.0);
    assertEquals("Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=3.0s\n"
            + "Disappears at t=50.0s", oval.toString(2));
  }

  // tests toString on the rectangle class
  @Test
  public void ovalToStringExample4() {
    Shape oval = new Oval("OvalManWho", 1, 1,
            new Color(255, 255, 255), 500.0, 100.0,
            0.0, 0.0);
    assertEquals("Name: OvalManWho\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 0.0, Y radius: 0.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=0.01s\n"
            + "Disappears at t=0.01s", oval.toString(100));
  }

  // tests toString on the rectangle class
  @Test
  public void rectangleToStringExample1() {
    Shape rect = new Rectangle("R", 1, 100,
            new Color(1, 0, 0), 200.0, 200.0,
            50.0, 100.0);
    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\n"
            + "Disappears at t=100.0s", rect.toString(1));
  }

  // tests toString on the rectangle class
  @Test
  public void rectangleToStringExample2() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    assertEquals("Name: RectalMan\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: "
            + "(1.0,100.0,100.0)\n"
            + "Appears at t=1.0s\n"
            + "Disappears at t=2.0s", rect.toString(1));
  }

  // tests toString on the rectangle class
  @Test
  public void rectangleToStringExample3() {
    Shape rect = new Rectangle("R", 1, 100,
            new Color(1, 0, 0), 200.0, 200.0,
            50.0, 100.0);
    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=0.2s\n"
            + "Disappears at t=20.0s", rect.toString(5));
  }

  // tests toString on the rectangle class
  @Test
  public void rectangleToStringExample4() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    assertEquals("Name: RectalMan\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: "
            + "(1.0,100.0,100.0)\n"
            + "Appears at t=0.1s\n"
            + "Disappears at t=0.2s", rect.toString(10));
  }

  // tests getName on the rectangle class
  @Test
  public void shapeGetNameExample1() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    assertEquals("RectalMan", rect.getName());
  }

  // tests getName on the rectangle class
  @Test
  public void shapeGetNameExample2() {
    Shape oval = new Oval("OvalManWho", 1, 1,
            new Color(255, 255, 255), 500.0, 100.0,
            0.0, 0.0);
    assertEquals("OvalManWho", oval.getName());
  }

  // tests shape constructor exception
  @Test(expected = IllegalArgumentException.class)
  public void shapeException1() {
    Shape rect = new Rectangle("RectalMan", 1, 0,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
  }

  // tests shape constructor exception
  @Test(expected = IllegalArgumentException.class)
  public void shapeException2() {
    Shape rect = new Rectangle("RectalMan", 1, 4,
            new Color(1, 100, 100), 200.0, 200.0,
            -10.0, 100.0);
  }

  // tests shape constructor exception
  @Test(expected = IllegalArgumentException.class)
  public void shapeException3() {
    Shape rect = new Rectangle("RectalMan", 1, 4,
            new Color(1, 100, 100), 200.0, 200.0,
            100.0, -100.0);
  }

  // tests shape constructor exception
  @Test(expected = IllegalArgumentException.class)
  public void shapeException4() {
    Shape rect = new Rectangle("RectalMan", 1, 4,
            new Color(1, 100, 100), 200.0, 200.0,
            -10.0, -100.0);
  }

  // tests shape constructor exception
  @Test(expected = IllegalArgumentException.class)
  public void shapeException5() {
    Shape oval = new Oval("OvalManWho", 1, 0,
            new Color(255, 255, 255), 500.0, 100.0,
            0.0, 0.0);
  }

  // tests shape constructor exception
  @Test(expected = IllegalArgumentException.class)
  public void shapeException6() {
    Shape oval = new Oval("OvalManWho", 1, 5,
            new Color(255, 255, 255), 500.0, 100.0,
            -1.0, 0.0);
  }

  // tests shape constructor exception
  @Test(expected = IllegalArgumentException.class)
  public void shapeException7() {
    Shape oval = new Oval("OvalManWho", 1, 6,
            new Color(255, 255, 255), 500.0, 100.0,
            0.0, -2.0);
  }

  // tests shape constructor exception
  @Test(expected = IllegalArgumentException.class)
  public void shapeException8() {
    Shape oval = new Oval("OvalManWho", 1, 7,
            new Color(255, 255, 255), 500.0, 100.0,
            -10.0, -5.0);
  }

  // tests adding invalid transition exceptions
  @Test(expected = IllegalArgumentException.class)
  public void addTransitionException1() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    ITransition move = new MoveTransition(1, 5,0.0,
            0.0, 0.0, 0.0);
    ITransition move2 = new MoveTransition(2, 6,0.0,
            0.0, 0.0, 0.0);
    rect.addTransition(move);
    rect.addTransition(move2);

  }

  // tests adding invalid transition exceptions
  @Test(expected = IllegalArgumentException.class)
  public void addTransitionException2() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    ITransition move = new MoveTransition(1, 5,0.0,
            0.0, 0.0, 0.0);
    ITransition move2 = new MoveTransition(0, 3,0.0,
            0.0, 0.0, 0.0);
    rect.addTransition(move);
    rect.addTransition(move2);

  }

  // tests adding invalid transition exceptions
  @Test(expected = IllegalArgumentException.class)
  public void addTransitionException3() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    ITransition move = new MoveTransition(1, 5,0.0,
            0.0, 0.0, 0.0);
    ITransition move2 = new MoveTransition(3, 4,0.0,
            0.0, 0.0, 0.0);
    rect.addTransition(move);
    rect.addTransition(move2);

  }

  // tests adding invalid transition exceptions
  @Test(expected = IllegalArgumentException.class)
  public void addTransitionException4() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    ITransition move = new MoveTransition(1, 5,0.0,
            0.0, 0.0, 0.0);
    ITransition move2 = new MoveTransition(0, 6,0.0,
            0.0, 0.0, 0.0);
    rect.addTransition(move);
    rect.addTransition(move2);
  }

  // tests adding transitions
  @Test
  public void addTransitionExample1() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    ITransition move = new MoveTransition(1, 5,0.0,
            0.0, 0.0, 0.0);
    rect.addTransition(move);
    assertEquals("Shape RectalMan moves from (0.0,0.0) "
            + "to (0.0,0.0) from t=1.0s to t=5.0s", move.toString(1));
  }

  // tests adding transitions
  @Test
  public void addTransitionExample2() {
    Shape rect = new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    ITransition scale = new ScaleTransition(1, 5,0.0,
            0.0, 0.0, 0.0);
    rect.addTransition(scale);
    assertEquals("Shape RectalMan scales from Width: 0.0, "
            + "Height: 0.0 to Width: 0.0, Height: 0.0 from t=1.0s to t=5.0s", scale.toString(1));
  }
}