import static org.junit.Assert.assertEquals;

import cs3500.animator.model.ColorTransition;
import cs3500.animator.model.MoveTransition;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleTransition;
import cs3500.animator.control.TransitionRectVisualVisitor;
import java.awt.Color;
import org.junit.Test;

/**
 * A class to test the methods of TransitionReactVisualVisitor.
 */
public class TransitionRectVisualVisitorTest {

  // test visit move transition
  @Test
  public void testVisitMoveTransition() {
    // Create an oval
    Rectangle oval = new Rectangle("O", 0, 10, new Color(0, 0, 0), 10, 10, 10, 10);
    // Create the visitor
    TransitionRectVisualVisitor visitor = new TransitionRectVisualVisitor(1, oval);
    // Create the move transition
    MoveTransition move = new MoveTransition(0, 1, 10, 10, 11, 11);
    // Do the transition.
    Rectangle newRectangle = visitor.visit(move);
    // Check that the oval has moved
    assertEquals(11, (int)newRectangle.getLeftCornerX());
    assertEquals(11, (int)newRectangle.getLeftCornerY());
  }

  // test visit scale transition
  @Test
  public void testVisitScaleTransition() {
    // Create an oval
    Rectangle oval = new Rectangle("O", 0, 10, new Color(0, 0, 0), 10, 10, 10, 10);
    // Create the visitor
    TransitionRectVisualVisitor visitor = new TransitionRectVisualVisitor(1, oval);
    // Create the scale transition
    ScaleTransition scale = new ScaleTransition(0, 1, 10, 10, 11, 11);
    // Do the transition
    Rectangle newRectangle = visitor.visit(scale);
    // Check that the oval has scaled
    assertEquals(11, (int)newRectangle.getWidth());
    assertEquals(11, (int)newRectangle.getHeight());
  }

  // test visit color transition
  @Test
  public void testVisitColorTransition() {
    // Create an oval
    Rectangle oval = new Rectangle("O", 0, 10, new Color(0, 0, 0), 10, 10, 10, 10);
    // Create the visitor
    TransitionRectVisualVisitor visitor = new TransitionRectVisualVisitor(1, oval);
    // Create the color transition
    ColorTransition color = new ColorTransition(0, 1, new Color(0, 0 , 0), new Color(1, 1, 1));
    // Do the transition
    Rectangle newRectangle = visitor.visit(color);
    // Check that the oval has changed color
    assertEquals(new Color(1, 1, 1), newRectangle.getColor());
  }
}