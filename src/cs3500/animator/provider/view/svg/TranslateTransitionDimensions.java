package cs3500.animator.provider.view.svg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.provider.model.EasyObject;
import cs3500.animator.provider.property.ShapeDimensions;
import cs3500.animator.provider.property.posn.Posn;
import cs3500.animator.provider.transition.Transition;

/**
 * Represents a TranslateTransitionDimensions
 * Handles transitions for shape dimensions in SVG.
 */

public class TranslateTransitionDimensions extends SvgTranslatorTransition<ShapeDimensions> {
  public TranslateTransitionDimensions(Document doc, EasyObject shape,
                                       Transition<ShapeDimensions> toTranslate) {
    super(doc, shape, toTranslate);
  }

  @Override

  public List<Element> getTranslatedDom() {
    List<Element> list = new ArrayList<Element>();
    Element elemX = doc.createElement("animate");
    Element elemY = doc.createElement("animate");
    elemX.setAttribute("fill", "freeze");
    elemY.setAttribute("fill", "freeze");
    Posn startPosn = toTranslate.getStartValue().getValue();
    Posn endPosn = toTranslate.getEndValue().getValue();
    Float startTime = (float) toTranslate.getStartTime();
    Float endTime = (float) toTranslate.getEndTime();
    this.formatAnimation(elemX, animShape.getSvgAttrib(EasyObject.ObjectAttrib.WIDTH),
            startPosn.getX().toString(), endPosn.getX().toString(),
            startTime, endTime);
    this.formatAnimation(elemY, animShape.getSvgAttrib(EasyObject.ObjectAttrib.HEIGHT),
            startPosn.getY().toString(), endPosn.getY().toString(),
            startTime, endTime);
    list.add(elemX);
    list.add(elemY);
    return list;
  }
}
