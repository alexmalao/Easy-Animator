import static org.junit.Assert.assertEquals;

import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Oval;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.control.TransitionOvalVisualVisitor;
import java.awt.Color;
import org.junit.Test;

/**
 * A class to test the methods of TransitionOvalVisualVisitor.
 */
public class TransitionOvalVisualVisitorTest {

  // test visit move transition
  @Test
  public void testVisitMoveTransition() {
    // Create an oval
    Oval oval = new Oval("O", 0, 10, new Color(0, 0, 0), 10, 10, 10, 10);
    // Create the visitor
    TransitionOvalVisualVisitor visitor = new TransitionOvalVisualVisitor(1, oval);
    // Create the move transition
    MoveTransition move = new MoveTransition(0, 1, 10, 10, 11, 11);
    // Do the transition.
    Oval newOval = visitor.visit(move);
    // Check that the oval has moved
    assertEquals(true, newOval.getCenterX() == 11);
    assertEquals(true, newOval.getCenterY() == 11);
  }

  // test visit scale transition
  @Test
  public void testVisitScaleTransition() {
    // Create an oval
    Oval oval = new Oval("O", 0, 10, new Color(0, 0, 0), 10, 10, 10, 10);
    // Create the visitor
    TransitionOvalVisualVisitor visitor = new TransitionOvalVisualVisitor(1, oval);
    // Create the scale transition
    ScaleTransition scale = new ScaleTransition(0, 1, 10, 10, 11, 11);
    // Do the transition
    Oval newOval = visitor.visit(scale);
    // Check that the oval has scaled
    assertEquals(true, newOval.getRadiusX() == 11);
    assertEquals(true, newOval.getRadiusY() == 11);
  }

  // test visit color transition
  @Test
  public void testVisitColorTransition() {
    // Create an oval
    Oval oval = new Oval("O", 0, 10, new Color(0, 0, 0), 10, 10, 10, 10);
    // Create the visitor
    TransitionOvalVisualVisitor visitor = new TransitionOvalVisualVisitor(1, oval);
    // Create the color transition
    ColorTransition color = new ColorTransition(0, 1, new Color(0, 0 , 0), new Color(1, 1, 1));
    // Do the transition
    Oval newOval = visitor.visit(color);
    // Check that the oval has changed color
    assertEquals(true, newOval.getColor().equals(new Color(1, 1, 1)));
  }
}