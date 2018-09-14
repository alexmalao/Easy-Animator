import org.junit.Test;

import java.awt.event.ItemEvent;

import static org.junit.Assert.assertEquals;

/**
 * Tests for AnimatorHybrid.
 */
public class AnimatorHybridTest {

  // tests the itemListener for checkBox
  @Test
  public void itemListenerExample1() {
    HybridMock input = new HybridMock();
    input.itemStateChanged(new ItemEvent(input.visibleCheckBox.get(0),
        0, input.visibleCheckBox.get(0), 0));

    assertEquals("select Shape1\n", input.output.toString());
  }

  // tests the itemListener for checkBox
  @Test
  public void itemListenerExample2() {
    HybridMock input = new HybridMock();
    input.itemStateChanged(new ItemEvent(input.visibleCheckBox.get(0),
            0, input.visibleCheckBox.get(0), 1));

    assertEquals("deselect Shape1\n", input.output.toString());
  }

  // tests the itemListener for checkBox
  @Test
  public void itemListenerExample3() {
    HybridMock input = new HybridMock();
    input.itemStateChanged(new ItemEvent(input.visibleCheckBox.get(1),
            0, input.visibleCheckBox.get(0), 0));
    input.itemStateChanged(new ItemEvent(input.visibleCheckBox.get(1),
            0, input.visibleCheckBox.get(0), 1));

    assertEquals("select Shape2\n"
            + "deselect Shape2\n", input.output.toString());
  }

  // tests the itemListener for checkBox
  @Test
  public void itemListenerExample4() {
    HybridMock input = new HybridMock();
    input.itemStateChanged(new ItemEvent(input.visibleCheckBox.get(1),
            0, input.visibleCheckBox.get(1), 0));
    input.itemStateChanged(new ItemEvent(input.visibleCheckBox.get(1),
            0, input.visibleCheckBox.get(1), 1));
    input.itemStateChanged(new ItemEvent(input.visibleCheckBox.get(0),
            0, input.visibleCheckBox.get(0), 0));
    input.itemStateChanged(new ItemEvent(input.visibleCheckBox.get(0),
            0, input.visibleCheckBox.get(0), 1));

    assertEquals("select Shape2\n"
            + "deselect Shape2\n"
            + "select Shape1\n"
            + "deselect Shape1\n", input.output.toString());
  }

  // tests the itemListener for loop
  @Test
  public void itemListenerExample5() {
    HybridMock input = new HybridMock();
    input.itemStateChanged(new ItemEvent(input.loopCheckBox,
            0, input.loopCheckBox, 0));

    assertEquals("loop\n", input.output.toString());
  }

  // tests the itemListener for loop
  @Test
  public void itemListenerExample6() {
    HybridMock input = new HybridMock();
    input.itemStateChanged(new ItemEvent(input.loopCheckBox,
            0, input.loopCheckBox, 0));
    input.itemStateChanged(new ItemEvent(input.loopCheckBox,
            0, input.loopCheckBox, 1));

    assertEquals("loop\n"
            + "loop\n", input.output.toString());
  }

  // Test the start button execute an action on click.
  @Test
  public void testStartActionCommand() {
    HybridMock view = new HybridMock();
    view.startMenuItem.doClick();
    assertEquals("Start button pressed.\n", view.output.toString());
  }

  // Test the pause button executes an action on click..
  @Test
  public void testPauseActionCommand() {
    HybridMock view = new HybridMock();
    view.pauseMenuItem.doClick();
    assertEquals("Pause button pressed.\n", view.output.toString());
  }

  // Test the restart button executes an action on click.
  @Test
  public void testRestartActionCommand() {
    HybridMock view = new HybridMock();
    view.restartMenuItem.doClick();
    assertEquals("Restart button pressed.\n", view.output.toString());
  }

  // Test the change speed button executes an action on click.
  @Test
  public void testChangeSpeedActionCommand() {
    HybridMock view = new HybridMock();
    view.changeSpeedMenuItem.doClick();
    assertEquals("Speed change button pressed.\n", view.output.toString());
  }

  // Test the export button executes an action on click.
  @Test
  public void testExportActionCommand() {
    HybridMock view = new HybridMock();
    view.exportMenuItem.doClick();
    assertEquals("Export svg button pressed.\n", view.output.toString());
  }
}