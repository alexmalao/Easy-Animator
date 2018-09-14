package cs3500.animator.provider.view.svg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.provider.model.EasyObject;
import cs3500.animator.provider.property.ShapeColor;
import cs3500.animator.provider.transition.Transition;
import cs3500.animator.provider.view.WebUtils;

/**
 * Represents a TranslateTransitionColor.
 * Handles transitions for colors in SVG.
 */

public class TranslateTransitionColor extends SvgTranslatorTransition<ShapeColor> {

  /**
   * Constructor.
   */

  public TranslateTransitionColor(Document doc, EasyObject shape,
                                  Transition<ShapeColor> toTranslate) {
    super(doc, shape, toTranslate);
  }

  @Override
  public List<Element> getTranslatedDom() {
    List<Element> list = new ArrayList<Element>();
    Element elemColor = doc.createElement("animate");
    elemColor.setAttribute("fill", "freeze");
    Color startColor = this.toTranslate.getStartValue().getValue();
    Color endColor = this.toTranslate.getEndValue().getValue();
    Float startTime = (float) toTranslate.getStartTime();
    Float endTime = (float) toTranslate.getEndTime();
    this.formatAnimation(elemColor, "fill", WebUtils.toRGBCode(startColor),
            WebUtils.toRGBCode(endColor), startTime, endTime);
    list.add(elemColor);
    return list;
  }
}
