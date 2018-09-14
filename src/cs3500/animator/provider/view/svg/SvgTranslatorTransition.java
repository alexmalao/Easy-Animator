package cs3500.animator.provider.view.svg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

import cs3500.animator.provider.model.EasyObject;
import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.transition.Transition;


/**
 * Represents a svgtranslatortransition.
 * Abstract class for SVGTranslatorTransitions
 */
public abstract class SvgTranslatorTransition<K extends ShapeProperty<?>>
        extends SvgTranslator<Transition<K>> {
  protected EasyObject animShape;
  protected int speed;
  private String modifier = "";

  /**
   * Constructor for svgtranslatortransition.
   */

  public SvgTranslatorTransition(Document doc, EasyObject shape, Transition<K> toTranslate) {
    super(doc, toTranslate);
    this.animShape = shape;
    this.speed = 1;
  }

  /**
   * Sets the speed of the Transition to the given speed.
   * @param speed the given speed.
   */

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public abstract List<Element> getTranslatedDom();

  /**
   * Formats the animation.
   * @param elem the given Element.
   * @param attrib the given attributes.
   * @param beginVal the given beginVal.
   * @param endVal the given endVal.
   * @param startTime the given start time.
   * @param endTime the given end time.
   * @return Element
   */

  protected Element formatAnimation(Element elem, String attrib, String beginVal, String endVal,
                                    Float startTime, Float endTime) {

    Float startTimeSpeed = startTime / this.speed;
    Float durSpeed = (endTime - startTime) / this.speed;
    elem.setAttribute("attributeType", "XML");
    elem.setAttribute("attributeName", attrib);
    elem.setAttribute("to", endVal);
    elem.setAttribute("begin", modifier + startTimeSpeed.toString() + "s");
    elem.setAttribute("dur", durSpeed.toString() + "s");
    return elem;
  }

  public void setTimeModifier(String prefix) {
    this.modifier = prefix;
  }
}
