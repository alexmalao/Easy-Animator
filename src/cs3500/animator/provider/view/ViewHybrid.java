package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs3500.animator.provider.model.AnimatedShape;
import cs3500.animator.provider.model.EasyAnimOperations;

/**
 * A Hybrid view is a contained view which allows the client to interface
 * directly with the animation. The view is comprised of two parts: A DrawPanel
 * and a ControlPanel. The client may modify animation properties using the
 * Control Panel, which are directly reflected in the DrawPanel.
 *
 */
public class ViewHybrid extends JFrame
    implements ViewControllable, ChangeListener, ListSelectionListener, ComponentListener {

  private ViewConfigurable delegateSvg;
  private int speed = 60;
  private EasyAnimOperations model;
  private DrawPanel renderPanel;
  private LinkedHashMap<String, AnimatedShape> shapes;
  private int lastTick;
  private List<JButton> buttons = new ArrayList<JButton>();
  private JFileChooser dialog = new JFileChooser();
  private ControlPanel cp;

  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;

  /**
   * Default constructor setups the SVG delegation and the component listener.
   * Every other initialization is handled directly through setters.
   */
  ViewHybrid() {
    this.addComponentListener(this);
    this.delegateSvg = ViewFactory.newViewFilterable(ViewFactory.ViewType.SVG);
    dialog.setFileFilter(new FileNameExtensionFilter("SVG", "svg", "SVG"));
  }

  @Override
  public void pause() {
    this.renderPanel.haltPlayback();
  }

  @Override
  public void start() {
    this.renderPanel.start();
  }

  @Override
  public void resume() {
    this.renderPanel.resumePlayback();
  }

  @Override
  public void setModel(EasyAnimOperations theModel) {
    this.delegateSvg.setModel(theModel);
    this.model = theModel;
    shapes = new LinkedHashMap<String, AnimatedShape>(model.getAnimatedShapes());
    lastTick = getEndTick();
  }

  @Override
  public void setOutput(Writer output) throws IllegalAccessError, IOException {
    this.delegateSvg.setOutput(output);
  }

  @Override
  public void runView() throws IOException, IllegalStateException {

    GridLayout layout = new GridLayout(1, 2);

    this.renderPanel = new DrawPanel(shapes, speed, lastTick);
    this.renderPanel.setPlaybackSpeed(speed);
    JScrollPane scrPane = new JScrollPane(renderPanel);
    scrPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    setLayout(layout);

    cp = getControlPanel();
    cp.addListSelectListener(this);
    cp.setCheckboxEventListener((e) -> {
      JCheckBox cb = (JCheckBox) e.getItem();
      this.renderPanel.setShouldLoop(cb.isSelected());
    });

    renderPanel.setDrawFilter(cp.getAliasSelection());
    buttons = cp.getButtons();
    cp.setSliderListener(this);
    this.add(scrPane);
    this.add(cp);
    this.setSize(new Dimension(WIDTH, HEIGHT));
    this.pack();

    setTitle("Easy Animator");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }

  private ControlPanel getControlPanel() {
    ControlPanel retVal = new ControlPanel(model.getAnimatedShapes().keySet());
    return retVal;
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public void exportSvg() throws IOException {
    this.delegateSvg.setSpeed(speed);
    this.delegateSvg.setModel(model);
    this.delegateSvg.setShouldLoop(cp.shouldLoop());
    int retVal = dialog.showSaveDialog(this);
    if (retVal == JFileChooser.APPROVE_OPTION) {
      this.setOutput(new FileWriter(dialog.getSelectedFile()));
    }
    Map<String, AnimatedShape> selected = new LinkedHashMap<>();
    for (String alias : cp.getAliasSelection()) {
      selected.put(alias, model.getAnimatedShape(alias));
    }
    this.delegateSvg.setShapes(selected);
    this.delegateSvg.runView();
  }

  @Override
  public void addActionListener(ActionListener listener) {
    for (int i = 0; i < buttons.size(); i++) {
      JButton button = buttons.get(i);
      button.addActionListener(listener);
    }
  }

  private int getEndTick() {
    Map<String, AnimatedShape> shapes = model.getAnimatedShapes();
    int finalTime = 0;
    for (Map.Entry entry : shapes.entrySet()) {
      AnimatedShape shape = (AnimatedShape) entry.getValue();
      int next = shape.getHideTime();
      if (next > finalTime) {
        finalTime = next;
      }
    }
    return finalTime;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    int speed = (int) source.getValue();
    this.renderPanel.setPlaybackSpeed(speed);
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    renderPanel.setDrawFilter(new ArrayList<>(cp.getAliasSelection()));
    renderPanel.repaint();
  }

  @Override
  public void componentResized(ComponentEvent e) {
    this.repaint();
    renderPanel.repaint();
  }

  @Override
  public void componentMoved(ComponentEvent e) {
    /**
     * Does nothing.
     */
  }

  @Override
  public void componentShown(ComponentEvent e) {
    /**
     * Does nothing.
     */
  }

  @Override
  public void componentHidden(ComponentEvent e) {
    /**
     * Does nothing.
     */
  }
}
