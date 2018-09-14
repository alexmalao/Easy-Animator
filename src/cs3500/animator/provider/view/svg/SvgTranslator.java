package cs3500.animator.provider.view.svg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Represents a SVGTranslator.
 * Abstract class for SVGTranslators.
 */

public abstract class SvgTranslator<K> {
  protected K toTranslate;
  protected Document doc;

  /**
   * Constructor for svgtranslator.
   */

  public SvgTranslator(Document doc, K toTranslate) {
    this.doc = doc;
    this.toTranslate = toTranslate;
  }

  /**
   * Abstract method for getTranslatedDom.
   * @return List
   */

  public abstract List<Element> getTranslatedDom();
}
