
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.model.Shape;
import cs3500.animator.model.Transition;

import static org.junit.Assert.assertEquals;

/**
 * Runs tests on the shape class.
 */
public class TransitionTest {

  // tests constructor exception for transitions
  @Test(expected = IllegalArgumentException.class)
  public void transitionException1() {
    new ScaleTransition(0, -1, 50.0,
            100.0, 10.0, 10.0);
  }

  // tests constructor exception for transitions
  @Test(expected = IllegalArgumentException.class)
  public void transitionException2() {
    new MoveTransition(2, 1, 50.0,
            100.0, 10.0, 10.0);
  }

  // tests constructor exception for transitions
  @Test(expected = IllegalArgumentException.class)
  public void transitionException3() {
    new ColorTransition(200, 100, new Color(10, 10, 10),
            new Color(10,10, 10));
  }

  // tests getStartTime
  @Test
  public void getStartTimeExample1() {
    Transition t = new ScaleTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(0, t.getStartTime());
  }

  // tests getStartTime
  @Test
  public void getStartTimeExample2() {
    Transition t = new MoveTransition(-1, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(-1, t.getStartTime());
  }

  // tests getStartTime
  @Test
  public void getStartTimeExample3() {
    Transition t = new ColorTransition(50, 100, new Color(10, 10, 10),
            new Color(10,10, 10));
    assertEquals(50, t.getStartTime());
  }

  // tests getEndTime
  @Test
  public void getEndTimeExample1() {
    Transition t = new ScaleTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
  }

  // tests getEndTime
  @Test
  public void getEndTimeExample2() {
    Transition t = new MoveTransition(-1, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
  }

  // tests getEndTime
  @Test
  public void getEndTimeExample3() {
    Transition t = new ColorTransition(50, 100, new Color(10, 10, 10),
            new Color(10,10, 10));
    assertEquals(100, t.getEndTime());
  }

  // tests toString and addShape
  @Test
  public void toStringExample1() {
    Transition t = new ScaleTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
    Shape shape = new Oval("C", 6, 100,
            new Color(0, 0, 1), 500.0, 100.0,
            60.0, 30.0);
    t.addShape(shape);
    assertEquals("Shape C scales from X radius: 100.0, Y radius:"
            + " 50.0 to X radius: 10.0, Y radius: 10.0 from t=0.0s to t=2.0s", t.toString(1));
  }

  // tests toString and addShape
  @Test
  public void toStringExample2() {
    Transition t = new ScaleTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
    Shape shape =  new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    t.addShape(shape);
    assertEquals("Shape RectalMan scales from Width: 100.0, "
            + "Height: 50.0 to Width: 10.0, Height: 10.0 from t=0.0s to t=2.0s", t.toString(1));
  }

  // tests toString and addShape
  @Test
  public void toStringExample3() {
    Transition t = new MoveTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
    Shape shape = new Oval("C", 6, 100,
            new Color(0, 0, 1), 500.0, 100.0,
            60.0, 30.0);
    t.addShape(shape);
    assertEquals("Shape C moves from (50.0,100.0) "
            + "to (10.0,10.0) from t=0.0s to t=2.0s", t.toString(1));
  }

  // tests toString and addShape
  @Test
  public void toStringExample4() {
    Transition t = new MoveTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
    Shape shape =  new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    t.addShape(shape);
    assertEquals("Shape RectalMan moves from (50.0,100.0) "
            + "to (10.0,10.0) from t=0.0s to t=2.0s", t.toString(1));
  }

  // tests toString and addShape
  @Test
  public void toStringExample5() {
    Transition t = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    assertEquals(2, t.getEndTime());
    Shape shape = new Oval("C", 6, 100,
            new Color(0, 0, 1), 500.0, 100.0,
            60.0, 30.0);
    t.addShape(shape);
    assertEquals("Shape C changes color from (50.0,50.0,50.0) to"
            + " (50.0,50.0,50.0) from t=0.0s to t=2.0s", t.toString(1));
  }

  // tests toString and addShape
  @Test
  public void toStringExample6() {
    Transition t = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    assertEquals(2, t.getEndTime());
    Shape shape =  new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    t.addShape(shape);
    assertEquals("Shape RectalMan changes color from "
            + "(50.0,50.0,50.0) to (50.0,50.0,50.0) from t=0.0s to t=2.0s", t.toString(1));
  }

  // tests toString and addShape
  @Test
  public void toStringExample7() {
    Transition t = new ScaleTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
    Shape shape = new Oval("C", 6, 100,
            new Color(0, 0, 1), 500.0, 100.0,
            60.0, 30.0);
    t.addShape(shape);
    assertEquals("Shape C scales from X radius: 100.0, Y radius: 50.0 to "
            + "X radius: 10.0, Y radius: 10.0 from t=0.0s to t=1.0s", t.toString(2));
  }

  // tests toString and addShape
  @Test
  public void toStringExample8() {
    Transition t = new ScaleTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
    Shape shape =  new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    t.addShape(shape);
    assertEquals("Shape RectalMan scales from Width: 100.0, Height: 50.0 to Width: 10.0, "
            + "Height: 10.0 from t=0.0s to t=0.6666666666666666s", t.toString(3));
  }

  // tests toString and addShape
  @Test
  public void toStringExample9() {
    Transition t = new MoveTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
    Shape shape = new Oval("C", 6, 100,
            new Color(0, 0, 1), 500.0, 100.0,
            60.0, 30.0);
    t.addShape(shape);
    assertEquals("Shape C moves from (50.0,100.0) "
            + "to (10.0,10.0) from t=0.0s to t=0.5s", t.toString(4));
  }

  // tests toString and addShape
  @Test
  public void toStringExample10() {
    Transition t = new MoveTransition(0, 2, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t.getEndTime());
    Shape shape =  new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    t.addShape(shape);
    assertEquals("Shape RectalMan moves from (50.0,100.0) "
            + "to (10.0,10.0) from t=0.0s to t=0.4s", t.toString(5));
  }

  // tests toString and addShape
  @Test
  public void toStringExample11() {
    Transition t = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    assertEquals(2, t.getEndTime());
    Shape shape = new Oval("C", 6, 100,
            new Color(0, 0, 1), 500.0, 100.0,
            60.0, 30.0);
    t.addShape(shape);
    assertEquals("Shape C changes color from (50.0,50.0,50.0) to"
            + " (50.0,50.0,50.0) from t=0.0s to t=0.2s", t.toString(10));
  }

  // tests toString and addShape
  @Test
  public void toStringExample12() {
    Transition t = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    assertEquals(2, t.getEndTime());
    Shape shape =  new Rectangle("RectalMan", 1, 2,
            new Color(1, 100, 100), 200.0, 200.0,
            50.0, 100.0);
    t.addShape(shape);
    assertEquals("Shape RectalMan changes color from "
            + "(50.0,50.0,50.0) to (50.0,50.0,50.0) from t=0.0s to t=0.1s", t.toString(20));
  }

  // tests comparing two transitions
  @Test
  public void compareExample1() {
    Transition t1 = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    Transition t2 = new MoveTransition(5, 6, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(-5, t1.compareTo(t2));
  }

  // tests comparing two transitions
  @Test
  public void compareExample2() {
    Transition t1 = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    Transition t2 = new MoveTransition(-2, 6, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(2, t1.compareTo(t2));
  }

  // tests comparing two transitions
  @Test
  public void compareExample3() {
    Transition t1 = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    Transition t2 = new MoveTransition(0, 6, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(0, t1.compareTo(t2));
  }

  // tests overlap for two transitions
  @Test
  public void overlapExample1() {
    Transition t1 = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    Transition t2 = new MoveTransition(0, 6, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(false, t1.overlap(t2));
  }

  // tests overlap for two transitions
  @Test
  public void overlapExample2() {
    Transition t1 = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    Transition t2 = new ScaleTransition(0, 6, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(false, t1.overlap(t2));
  }

  // tests overlap for two transitions
  @Test
  public void overlapExample3() {
    Transition t1 = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    Transition t2 = new ColorTransition(1, 3, new Color(50, 50, 50),
            new Color(50, 50, 50));
    assertEquals(true, t1.overlap(t2));
  }

  // tests overlap for two transitions
  @Test
  public void overlapExample4() {
    Transition t1 = new ColorTransition(0, 2, new Color(50, 50, 50),
            new Color(50, 50, 50));
    Transition t2 = new ColorTransition(2, 5, new Color(50, 50, 50),
            new Color(50, 50, 50));
    assertEquals(false, t1.overlap(t2));
  }

  // tests overlap for two transitions
  @Test
  public void overlapExample5() {
    Transition t1 = new MoveTransition(0, 6, 50.0,
            100.0, 10.0, 10.0);
    Transition t2 = new ScaleTransition(0, 6, 50.0,
            100.0, 10.0, 10.0);
    assertEquals(false, t1.overlap(t2));
  }
}