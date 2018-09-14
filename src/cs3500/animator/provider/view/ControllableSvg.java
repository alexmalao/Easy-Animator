package cs3500.animator.provider.view;

import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cs3500.animator.provider.model.AnimatedShape;
import cs3500.animator.provider.model.EasyObject;
import cs3500.animator.provider.property.ShapeColor;
import cs3500.animator.provider.property.ShapeDimensions;
import cs3500.animator.provider.property.ShapePosn;
import cs3500.animator.provider.transition.Transition;
import cs3500.animator.provider.view.svg.SvgTranslatorTransition;
import cs3500.animator.provider.view.svg.TranslateTransitionColor;
import cs3500.animator.provider.view.svg.TranslateTransitionDimensions;
import cs3500.animator.provider.view.svg.TranslateTransitionPosn;


/**
 * A Filterable SVG enables modifiability to the collection of shapes in the
 * representative SVG file. When an animation is exported, only selected shapes
 * are delegated to the exported SVG.
 */
public class ControllableSvg extends ViewSvg implements ViewConfigurable {

  private Map<String, AnimatedShape> shapes;
  private boolean shouldLoop;

  /**
   * Default constructor initializes the map of filtered shapes to be no
   * shapes. This is consistent with the animation behavior.
   */

  public ControllableSvg() {
    super();
    this.shapes = new LinkedHashMap<>();
  }

  public void setShouldLoop(boolean shouldLoop) {
    this.shouldLoop = shouldLoop;
  }

  @Override
  public void setShapes(Map<String, AnimatedShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  protected void shapesToXML(Document doc, Element rootSvgElement) {
    if (this.shouldLoop) {
      Element dummyShape = doc.createElement("rect");
      Element dummyAnim = doc.createElement("animate");
      dummyAnim.setAttribute("id", "dummybase");
      dummyAnim.setAttribute("begin", "0; dummybase.end");
      dummyAnim.setAttribute("end",
              Float.toString((float) this.getEndTick() / (float) this.speed));
      dummyAnim.setAttribute("attributeName", "visibility");
      dummyAnim.setAttribute("from", "hide");
      dummyAnim.setAttribute("to", "hide");
      dummyShape.appendChild(dummyAnim);
      rootSvgElement.appendChild(dummyShape);
    }

    for (AnimatedShape shape : shapes.values()) {
      super.shapeToXML(doc, rootSvgElement, shape);
    }
  }

  @Override
  protected SvgTranslatorTransition getTranslator(Document doc, Transition t, EasyObject shape) {
    Class type = t.getStartValue().getClass();
    SvgTranslatorTransition translator = null;
    if (type.equals(ShapePosn.class)) {
      translator = new TranslateTransitionPosn(doc, shape, t);
    } else if (type.equals(ShapeDimensions.class)) {
      translator =  new TranslateTransitionDimensions(doc, shape, t);
    } else if (type.equals(ShapeColor.class)) {
      translator =  new TranslateTransitionColor(doc, shape, t);
    }
    if (translator != null) {
      translator.setTimeModifier("dummybase.begin+");
    }
    return translator;
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
}
