package cs3500.animator.provider.view;

import java.awt.Color;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cs3500.animator.provider.model.AnimatedShape;
import cs3500.animator.provider.model.EasyObject;
import cs3500.animator.provider.property.ShapeColor;
import cs3500.animator.provider.property.ShapeDimensions;
import cs3500.animator.provider.property.ShapePosn;
import cs3500.animator.provider.property.ShapeProperty;
import cs3500.animator.provider.property.posn.Posn;
import cs3500.animator.provider.transition.Transition;
import cs3500.animator.provider.view.svg.SvgTranslatorTransition;
import cs3500.animator.provider.view.svg.TranslateTransitionColor;
import cs3500.animator.provider.view.svg.TranslateTransitionDimensions;
import cs3500.animator.provider.view.svg.TranslateTransitionPosn;

/**
 * An SVG view is a view representation using an SVG file format.
 *
 */
public class ViewSvg extends ViewAbstract {

  private Writer output;

  /**
   * Sets the output of the view.
   *
   * @param output where to output the view.
   */

  @Override
  public void setOutput(Writer output) throws IOException {
    this.output = output;
  }

  /**
   * Runs the view.
   */
  @Override
  public void runView() throws IOException, UnsupportedOperationException {
    super.runView();
    try {
      this.generateXML();
    } catch (ParserConfigurationException | TransformerException e) {
      e.printStackTrace();
    }
    // this.output.close();
  }

  protected void shapesToXML(Document doc, Element rootSvgElement) {
    Map<String, AnimatedShape> shapes = this.model.getAnimatedShapes();
    for (AnimatedShape shape : shapes.values()) {
      shapeToXML(doc, rootSvgElement, shape);
    }
  }

  protected void shapeToXML(Document doc, Element rootSvgElement, AnimatedShape shape) {
    EasyObject initial = shape.getInitialShape();
    ShapeProperty<Posn> startPosn = initial.getProperty(ShapePosn.class);
    ShapeProperty<Posn> startDimensions = initial.getProperty(ShapeDimensions.class);
    ShapeProperty<Color> startColor = initial.getProperty(ShapeColor.class);
    Float x = startPosn.getValue().getX();
    Float y = startPosn.getValue().getY();
    Float width = startDimensions.getValue().getX();
    Float height = startDimensions.getValue().getY();
    Color color = startColor.getValue();

    Element nextShape = null;
    // Adding relevant attributes (posn, color, scale)
    nextShape = doc.createElement(initial.getSvgAttrib(EasyObject.ObjectAttrib.NAME));
    nextShape.setAttribute(initial.getSvgAttrib(EasyObject.ObjectAttrib.X), x.toString());
    nextShape.setAttribute(initial.getSvgAttrib(EasyObject.ObjectAttrib.Y), y.toString());
    nextShape.setAttribute(initial.getSvgAttrib(EasyObject.ObjectAttrib.WIDTH), width.toString());
    nextShape.setAttribute(initial.getSvgAttrib(EasyObject.ObjectAttrib.HEIGHT), height.toString());
    nextShape.setAttribute("fill", WebUtils.toRGBCode(color));
    rootSvgElement.appendChild(nextShape);
    List<Transition> transitions = shape.getTransitions();

    for (Transition t : transitions) {
      List<Element> animations;
      SvgTranslatorTransition translatorTransition = getTranslator(doc, t, initial);
      if (translatorTransition == null) {
        continue;
      }
      translatorTransition.setSpeed(this.speed);
      // TODO: ensure this is allowed
      translatorTransition.setTimeModifier("");
      animations = translatorTransition.getTranslatedDom();
      for (Element anim : animations) {
        nextShape.appendChild(anim);
      }
    }
  }

  private void generateXML() throws ParserConfigurationException, TransformerException {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = docFactory.newDocumentBuilder();

    // root elements
    Document doc = builder.newDocument();
    Element rootSvgElement = doc.createElement("svg");
    rootSvgElement.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    rootSvgElement.setAttribute("version", "1.1");
    doc.appendChild(rootSvgElement);

    shapesToXML(doc, rootSvgElement);

    // Send result to output stream.
    DOMSource source = new DOMSource(doc);
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    StreamResult result = new StreamResult(this.output);
    transformer.transform(source, result);
  }

  @SuppressWarnings("unchecked")
  protected SvgTranslatorTransition getTranslator(Document doc, Transition t, EasyObject shape) {
    Class type = t.getStartValue().getClass();
    if (type.equals(ShapePosn.class)) {
      return new TranslateTransitionPosn(doc, shape, t);
    } else if (type.equals(ShapeDimensions.class)) {
      return new TranslateTransitionDimensions(doc, shape, t);
    } else if (type.equals(ShapeColor.class)) {
      return new TranslateTransitionColor(doc, shape, t);
    }
    return null;
  }
}
