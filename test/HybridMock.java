import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Represents a hybrid view that has a visual animation in addition to being able to export
 * a SVG file. In addition, it has the functionality of user interaction, allowing multiple
 * supported features including pausing, restarting, and making shapes invisible.
 */
public class HybridMock implements ActionListener, ItemListener {

  public Appendable output;
  public JMenuBar menuBar;
  public JMenuItem startMenuItem;
  public JMenuItem pauseMenuItem;
  public JMenuItem restartMenuItem;
  public JMenuItem changeSpeedMenuItem;
  public JMenuItem exportMenuItem;
  public JCheckBoxMenuItem loopCheckBox;
  public ArrayList<JCheckBoxMenuItem> visibleCheckBox;

  /**
   * Create a new AnimatorVisual with the given model.
   */
  public HybridMock() {

    this.output = new StringBuilder();

    this.menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);
    JMenu timeMenu = new JMenu("Edit");
    menuBar.add(timeMenu);
    JMenu visibleMenu = new JMenu("Shape Visibility");
    menuBar.add(visibleMenu);

    // Add the start button.
    startMenuItem = new JMenuItem("Start");
    startMenuItem.setActionCommand("start");
    startMenuItem.addActionListener(this);
    timeMenu.add(startMenuItem);

    // Add the pause button.
    pauseMenuItem = new JMenuItem("Pause");
    pauseMenuItem.setActionCommand("pause");
    pauseMenuItem.addActionListener(this);
    timeMenu.add(pauseMenuItem);

    // Add the restart button.
    restartMenuItem = new JMenuItem("Restart");
    restartMenuItem.setActionCommand("restart");
    restartMenuItem.addActionListener(this);
    timeMenu.add(restartMenuItem);

    timeMenu.addSeparator();

    // Add the change speed button.
    changeSpeedMenuItem = new JMenuItem("Change speed");
    changeSpeedMenuItem.setActionCommand("speed");
    changeSpeedMenuItem.addActionListener(this);
    timeMenu.add(changeSpeedMenuItem);

    // Add the export button.
    exportMenuItem = new JMenuItem("Export");
    exportMenuItem.setActionCommand("export");
    exportMenuItem.addActionListener(this);
    fileMenu.add(exportMenuItem);

    timeMenu.addSeparator();

    // Add the loop checkbox.
    loopCheckBox = new JCheckBoxMenuItem("Loop");
    loopCheckBox.setSelected(false);
    loopCheckBox.addItemListener(this);
    timeMenu.add(loopCheckBox);

    // Add the visible shapes checkBox
    visibleCheckBox = new ArrayList<JCheckBoxMenuItem>();
    JCheckBoxMenuItem shapeBox1 = new JCheckBoxMenuItem("Shape1");
    shapeBox1.setSelected(true);
    shapeBox1.addItemListener(this);
    visibleCheckBox.add(shapeBox1);
    visibleMenu.add(shapeBox1);


    JCheckBoxMenuItem shapeBox2 = new JCheckBoxMenuItem("Shape2");
    shapeBox2.setSelected(true);
    shapeBox2.addItemListener(this);
    visibleCheckBox.add(shapeBox2);
    visibleMenu.add(shapeBox2);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "start":
        try {
          output.append("Start button pressed.\n");
        } catch (IOException e1) {
          // do nothing
        }
        break;
      case "pause":
        // pause action
        try {
          output.append("Pause button pressed.\n");
        } catch (IOException e1) {
          // do nothing
        }
        break;
      case "restart":
        // restart action
        try {
          output.append("Restart button pressed.\n");
        } catch (IOException e1) {
          // do nothing
        }
        break;
      case "speed":
        // Get a new speed from the user
        try {
          output.append("Speed change button pressed.\n");
        } catch (IOException e1) {
          // do nothing
        }
        break;
      case "export":
        // export action
        try {
          output.append("Export svg button pressed.\n");
        } catch (IOException e1) {
          // do nothing
        }
        break;
      default:
        // Should never run.
    }
  }

  /**
   * Checks if an itemstate is changed and modifies the hashmap accordingly.
   * @param e the ItemEvent that changes a selectable
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    Object source = e.getItemSelectable();

    // Prevents checking all the visible shapes if it is the checkBox option.
    if (source == loopCheckBox) {
      try {
        output.append("loop\n");
      } catch (IOException ioe) {
        // do nothing
      }
    }

    for (JCheckBoxMenuItem item : visibleCheckBox) {
      if (source == item) {
        if (e.getStateChange() == 1) {
          try {
            output.append("deselect " + item.getText() + "\n");
          } catch (IOException ioe) {
            // do nothing
          }
        }
        else {
          try {
            output.append("select " + item.getText() + "\n");
          } catch (IOException ioe) {
            // do nothing
          }
        }
      }
    }
  }
}
