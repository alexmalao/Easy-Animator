package cs3500.animator.provider.view.svg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.provider.model.EasyObject;
import cs3500.animator.provider.property.ShapePosn;
import cs3500.animator.provider.property.posn.Posn;
import cs3500.animator.provider.transition.Transition;

/**
 * Represents a TranslateTransitionPosn.
 * Handles transitions for Posns in SVG.
 */

public class TranslateTransitionPosn extends SvgTranslatorTransition<ShapePosn> {

  /**
   * Constructor for TranslateTransitionPosn.
   *
   * @param doc the given document.
   * @param shape the given EasyObject.
   * @param toTranslate the given toTranslate.
   */

  public TranslateTransitionPosn(Document doc, EasyObject shape,
                                 Transition<ShapePosn> toTranslate) {
    super(doc, shape, toTranslate);
  }

  @Override

  public List<Element> getTranslatedDom() {
    List<Element> list = new ArrayList<Element>();
    Element elemX = doc.createElement("animate");
    Element elemY = doc.createElement("animate");
    Posn startPosn = toTranslate.getStartValue().getValue();
    Posn endPosn = toTranslate.getEndValue().getValue();
    Float startTime = (float) toTranslate.getStartTime();
    Float endTime = (float) toTranslate.getEndTime();
    elemX.setAttribute("fill", "freeze");
    elemY.setAttribute("fill", "freeze");
    this.formatAnimation(elemX, this.animShape.getSvgAttrib(EasyObject.ObjectAttrib.X),
            startPosn.getX().toString(), endPosn.getX().toString(),
            startTime, endTime);
    this.formatAnimation(elemY, this.animShape.getSvgAttrib(EasyObject.ObjectAttrib.Y),
            startPosn.getY().toString(), endPosn.getY().toString(),
            startTime, endTime);
    list.add(elemX);
    list.add(elemY);
    return list;
  }
}
