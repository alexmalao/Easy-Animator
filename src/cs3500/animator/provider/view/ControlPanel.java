package cs3500.animator.provider.view;

import static javax.swing.BoxLayout.PAGE_AXIS;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

/**
 * Control panel handles delegation of animation properties. Contains buttons to
 * start, pause, resume animations, a slider to modify speed, and a selection
 * window for selecting animated shapes. A checkbox to enable loopback is also
 * contained here. When the user clicks the button to export to svg, a save file
 * dialog is opened.
 *
 */

public class ControlPanel extends JPanel {
  private static final long serialVersionUID = -963181293130641680L;
  private JList<String> shapeList;
  private List<JButton> buttons = new ArrayList<>();
  private ListSelectionModel listSelectionModel;
  private static final int FPS_MIN = 1;
  private static final int FPS_MAX = 60;
  private static final int FPS_INIT = 60;
  private JSlider frameSpeed;
  private List<String> selectedAliases = new ArrayList<>();
  private JCheckBox loopCheckbox = new JCheckBox("Loop Playback");

  /**
   * Constructor for a ControlPanel.
   *
   * @param shapeAliases
   *            the given Collection of Strings.
   */
  public ControlPanel(Collection<String> shapeAliases) {
    JButton playBtn = new JButton("play");
    JButton pauseBtn = new JButton("pause");
    JButton resumeBtn = new JButton("resume");
    JButton exportBtn = new JButton("export svg");
    buttons.add(playBtn);
    buttons.add(pauseBtn);
    buttons.add(resumeBtn);
    buttons.add(exportBtn);

    JLabel exportLabel = new JLabel("Export svg with current configuration:",
            exportBtn.getIcon(), JLabel.LEFT);
    JLabel speedLabel = new JLabel("Playback FPS:", JLabel.LEADING);
    JLabel selectLabel = new JLabel("Select the shapes to show during playback:");

    this.shapeList = new JList<>();
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    for (String alias : shapeAliases) {
      listModel.addElement(alias);
    }
    shapeList.setModel(listModel);
    shapeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    JScrollPane listPane = new JScrollPane(shapeList);
    listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.listSelectionModel = shapeList.getSelectionModel();

    listSelectionModel.addListSelectionListener((listSelectionEvent) -> {
      selectedAliases.clear();
      ListSelectionModel lsm = (ListSelectionModel) listSelectionEvent.getSource();
      int minIndex = lsm.getMinSelectionIndex();
      int maxIndex = lsm.getMaxSelectionIndex();
      for (int i = minIndex; i <= maxIndex; i++) {
        if (lsm.isSelectedIndex(i)) {
          selectedAliases.add(listModel.get(i));
        }
      }
    });

    frameSpeed = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
    frameSpeed.setMajorTickSpacing(10);
    frameSpeed.setMinorTickSpacing(1);
    frameSpeed.setPaintTicks(true);
    frameSpeed.setPaintLabels(true);

    playBtn.setActionCommand("PLAY");
    pauseBtn.setActionCommand("PAUSE");
    resumeBtn.setActionCommand("RESUME");
    exportBtn.setActionCommand("EXPORT");
    this.setLayout(new BoxLayout(this, PAGE_AXIS));
    this.add(playBtn);
    this.add(pauseBtn);
    this.add(resumeBtn);
    this.add(speedLabel);
    this.add(frameSpeed);
    this.add(loopCheckbox);
    this.add(selectLabel);
    this.add(listPane);
    this.add(exportLabel);
    this.add(exportBtn);
  }

  void addListSelectListener(ListSelectionListener listener) {
    this.listSelectionModel.addListSelectionListener(listener);
  }

  /**
   * Finds the buttons in the ControlPanel.
   *
   * @return List
   */

  List<JButton> getButtons() {
    return buttons;
  }

  /**
   * Sets the Slider Listener.
   *
   * @param listener
   *            the given ChangeListener.
   */

  void setSliderListener(ChangeListener listener) {
    frameSpeed.addChangeListener(listener);
  }

  /**
   * Gets the list of Shapes.
   *
   * @return JList
   */

  JList<String> getShapeList() {
    return shapeList;
  }

  /**
   * Returns the list selection model.
   *
   * @return ListSelectionModel
   */

  ListSelectionModel getSelectionModel() {
    return listSelectionModel;
  }

  /**
   * Returns the selected Aliases.
   *
   * @return List
   */
  List<String> getAliasSelection() {
    return selectedAliases;
  }

  boolean shouldLoop() {
    return this.loopCheckbox.isSelected();
  }

  void setCheckboxEventListener(ItemListener l) {
    this.loopCheckbox.addItemListener(l);
  }
}
