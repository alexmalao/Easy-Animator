package cs3500.animator.view;

import cs3500.animator.control.ShapeSVGLoopVisitor;
import cs3500.animator.control.ShapeSVGVisitor;
import cs3500.animator.model.IShape;
import cs3500.animator.util.ModelAdapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Represents a hybrid view that has a visual animation in addition to being able to export
 * a SVG file. In addition, it has the functionality of user interaction, allowing multiple
 * supported features including pausing, restarting, and making shapes invisible.
 */
public class AnimatorHybrid extends JFrame
        implements AnimatorView, ActionListener, ItemListener, ChangeListener {

  private Timer timer;

  private AnimatorPanel animatorPanel;
  private JCheckBoxMenuItem loopCheckBox;
  private JCheckBoxMenuItem rainbowBox;
  private ArrayList<JCheckBoxMenuItem> visibleCheckBox;
  private JSlider timeSlide;

  private Color bgColor;

  /**
   * Create a new AnimatorVisual with the given model.
   * @param model the model to be associated with this view.
   */
  public AnimatorHybrid(ModelAdapter model) {
    super();

    this.setTitle("EasyAnimator");
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the menu bar and the menus
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);
    JMenu editMenu = new JMenu("Edit");
    menuBar.add(editMenu);
    JMenu visibleMenu = new JMenu("Shape Visibility");
    menuBar.add(visibleMenu);

    // Add the menu bar to the frame.
    this.setJMenuBar(menuBar);

    // Create the AnimatorPanel
    this.animatorPanel = new AnimatorPanel(model);
    animatorPanel.setPreferredSize(new Dimension(1000, 1000));

    // Add the AnimatorPanel to the JScrollPane
    JScrollPane scrollPane = new JScrollPane(animatorPanel);
    scrollPane.setPreferredSize(new Dimension(500, 500));

    // Add the JScrollPane to the JFrame
    this.add(scrollPane);

    // Add the start button.
    JMenuItem startMenuItem = new JMenuItem("Start");
    startMenuItem.setActionCommand("start");
    startMenuItem.addActionListener(this);
    editMenu.add(startMenuItem);

    // Add the pause button.
    JMenuItem pauseMenuItem = new JMenuItem("Pause");
    pauseMenuItem.setActionCommand("pause");
    pauseMenuItem.addActionListener(this);
    editMenu.add(pauseMenuItem);

    // Add the restart button.
    JMenuItem restartMenuItem = new JMenuItem("Restart");
    restartMenuItem.setActionCommand("restart");
    restartMenuItem.addActionListener(this);
    editMenu.add(restartMenuItem);

    editMenu.addSeparator();

    // Add the change speed button.
    JMenuItem changeSpeedMenuItem = new JMenuItem("Change speed");
    changeSpeedMenuItem.setActionCommand("speed");
    changeSpeedMenuItem.addActionListener(this);
    editMenu.add(changeSpeedMenuItem);

    // Add the change background color button.
    JMenuItem changeBGColor = new JMenuItem("Change color");
    changeBGColor.setActionCommand("color");
    changeBGColor.addActionListener(this);
    editMenu.add(changeBGColor);

    // Add the export button.
    JMenuItem exportMenuItem = new JMenuItem("Export");
    exportMenuItem.setActionCommand("export");
    exportMenuItem.addActionListener(this);
    fileMenu.add(exportMenuItem);

    editMenu.addSeparator();

    // Add the loop checkbox.
    loopCheckBox = new JCheckBoxMenuItem("Loop");
    loopCheckBox.setSelected(false);
    loopCheckBox.addItemListener(this);
    editMenu.add(loopCheckBox);

    // Add the rainbow checkbox.
    rainbowBox = new JCheckBoxMenuItem("Rainbow");
    rainbowBox.setSelected(false);
    rainbowBox.addItemListener(this);
    editMenu.add(rainbowBox);

    // Add the visible shapes checkBox
    visibleCheckBox = new ArrayList<JCheckBoxMenuItem>();
    for (IShape shape : animatorPanel.getModel().getShapes()) {
      JCheckBoxMenuItem shapeBox = new JCheckBoxMenuItem(shape.getName());
      shapeBox.setSelected(true);
      shapeBox.addItemListener(this);
      visibleCheckBox.add(shapeBox);
      visibleMenu.add(shapeBox);
    }

    bgColor = Color.white;

    timeSlide = new JSlider(JSlider.HORIZONTAL, 0, finalTick(), finalTick() / 10);
    timeSlide.setMajorTickSpacing(finalTick() / 10);
    timeSlide.setPaintTicks(true);
    timeSlide.setPaintLabels(true);
    timeSlide.addChangeListener(this);
    this.add(timeSlide, BorderLayout.SOUTH);

    // Pack the contents.
    this.pack();
  }

  /**
   * Constructor for setting the backgroudn color.
   * @param model
   * @param bgColor
   */
  public AnimatorHybrid(ModelAdapter model, Color bgColor) {
    this(model);

    this.bgColor = bgColor;
  }

  @Override
  public void animate(int tempo) {
    this.setVisible(true);
    final int FINAL_TICK = finalTick();

    // Calculate the delay of the timer based on the given tempo.
    int delay = 1000 / tempo;
    ActionListener listener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        onTick(animatorPanel.getCurrentTick() + 1);

        if (loopCheckBox.getState() && animatorPanel.getCurrentTick() > FINAL_TICK) {
          animatorPanel.setCurrentTick(0);
        }
      }
    };
    timer = new Timer(delay, listener);
    timer.start();
  }

  /**
   * Draw the state of the animator at the given tick.
   * @param tick the current tick.
   */
  private void onTick(int tick) {
    animatorPanel.removeAll();
    animatorPanel.setCurrentTick(tick);
    animatorPanel.revalidate();
    animatorPanel.repaint();

    animatorPanel.setBackground(bgColor);
    this.timeSlide.setValue(animatorPanel.getCurrentTick());
    if (rainbowBox.getState()) {
      bgColor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "start":
        // start
        timer.start();
        break;
      case "pause":
        // pause action
        timer.stop();
        break;
      case "restart":
        // restart action
        animatorPanel.setCurrentTick(0);
        break;
      case "speed":
        // Get a new speed from the user
        try {
          int tempo = 1000 / Integer.parseInt(JOptionPane.showInputDialog("Please input the new "
              + "speed"));
          // Set the new delay of the timer
          if (tempo >= 1) {
            timer.setDelay(tempo);
          } else {
            JOptionPane.showMessageDialog(null, "Invalid speed.", "WARNING", JOptionPane
                .ERROR_MESSAGE);
          }
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "Invalid number format.", "WARNING", JOptionPane
              .ERROR_MESSAGE);
        }

        break;
      case "color":
        // Get a new background color from the user
        try {
          int red = Integer.parseInt(JOptionPane.showInputDialog("Please input the new red color "
                  + "(0 to 255):"));
          int green = Integer.parseInt(JOptionPane.showInputDialog(
                  "Please input the new green color "
                  + "(0 to 255):"));
          int blue = Integer.parseInt(JOptionPane.showInputDialog("Please input the new blue color "
                  + "(0 to 255):"));
          if (red < 256 && red >= 0 && green < 256 && green >= 0 && blue < 256 && blue >= 0) {
            this.bgColor = new Color(red, green, blue);
            animatorPanel.setBackground(bgColor);
          } else {
            JOptionPane.showMessageDialog(null, "Invalid color.",
                    "WARNING", JOptionPane
                    .ERROR_MESSAGE);
          }
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "Invalid number format.",
                  "WARNING", JOptionPane
                  .ERROR_MESSAGE);
        }

        break;
      case "export":
        // export action
        String fileName = JOptionPane.showInputDialog("Please input where you would like to save "
            + "this svg to");
        double duration = finalTick() * timer.getDelay();
        OutputStreamWriter outputWriter = null;
        try {
          outputWriter =
              new OutputStreamWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException fnfe) {
          JOptionPane.showMessageDialog(null, "Error creating file "
              + fileName, "ERROR", JOptionPane.ERROR_MESSAGE);
          break;
        }

        String view = "<svg style=\"background-color: "
                + String.format("#%02x%02x%02x", bgColor.getRed(),
                bgColor.getGreen(), bgColor.getBlue())
                + ";\" width=\"1000\" height=\"1000\" version=\"1.1\"\n"
                + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";

        if (loopCheckBox.getState()) {
          view += "<rect>\n     <animate id=\"base\" begin=\"0;base.end\" dur=\"" + duration
                          + "ms\" attributeName=\"visibility\" from=\"hide\" "
              + "to=\"hide\"/>\n</rect>\n";

          ShapeSVGLoopVisitor shapeV = new ShapeSVGLoopVisitor(1000 / timer.getDelay());
          for (IShape shape : animatorPanel.getModel().getShapes()) {
            if (animatorPanel.getVisibleShapes().get(shape.getName())) {
              view += shapeV.apply(shape) + "\n\n";
            }
          }
        }
        else {
          ShapeSVGVisitor shapeV = new ShapeSVGVisitor(1000 / timer.getDelay());
          for (IShape shape : animatorPanel.getModel().getShapes()) {
            if (animatorPanel.getVisibleShapes().get(shape.getName())) {
              view += shapeV.apply(shape) + "\n\n";
            }
          }
        }

        view += "</svg>";

        try {
          outputWriter.append(view);
          outputWriter.flush();
        } catch (IOException ioe) {
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
      return;
    }
    // Prevents checking all the visible shapes if it is the checkBox option.
    if (source == rainbowBox) {
      return;
    }

    for (JCheckBoxMenuItem item : visibleCheckBox) {
      if (source == item) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
          animatorPanel.setShapeVisiblility(item.getText(), false);
        }
        else {
          animatorPanel.setShapeVisiblility(item.getText(), true);
        }
      }
    }
  }

  /**
   * Returns the final tick of the animation.
   * @return the final tick
   */
  private int finalTick() {

    int lastTick = 0;
    for (IShape shape : animatorPanel.getModel().getShapes()) {
      if (shape.getDisappears() > lastTick) {
        lastTick = shape.getDisappears();
      }
    }
    return lastTick;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    this.onTick(source.getValue());
  }
}
