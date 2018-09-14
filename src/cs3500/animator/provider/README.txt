DESIGN
------------------------
The design of this animator follows an MVC pattern.


MODEL -- cs3500.animator.model
------------------------
A Model must handle two aspects: shapes and their transitions. Each shape must be an EasyObject, and extend EasyShapeAbstract (an
abstract class which guarantees behaviors for all shapes). For example, rectangular and elliptical shapes are EasyShapeRect and EasyShapeOvals,
respectively.

Each shape has different shape properties (cs3500.animator.property) and associated transitions which mutate a shape (cs3500.animator.transition).
The model contains a mapping of aliases (each shape has a name) to an AnimatedShape, which is a Shape with a collection of transitions.

To add shapes and transition types, new classes must be added to support them. While this can be considered a design flaw, we felt the
tradeoffs were worth this design approach. Otherwise, more effort would have to be done in the scope of this project to parse valid
transition states and shapes defined by the user, and it could easily become convoluted.

VIEW -- cs3500.animator.view
------------------------
There are four types of views defined by the project specifications. All views are marked by a View interface, and shared behavior is abstracted
away into the ViewAbstract class.

TEXT - text views are quite literally strings of text which define shapes and transitions. > ViewText class

ANIMATED - animated views are constructed using the in-built Java Swing API. AnimationFrames are our implementation of a JFrame to
render an animation. AnimationFrame, ViewAnimated .... Interpolation is handled by the given formulas in cs3500.animator.view.interpol

SVG - SVG views (Scalable Vector Graphics) are XML schema-encoded renderings. ViewSvg class handles the XML generation before running the view.

HYBRID - Hybrid views are the interesting ones. Hybrid views are controllable. We created two new interfaces, 
ViewControllable and ViewFilterable (which extend the View interface). If a user wants to define more views of these natures, it becomes
trivial by implementing these two interfaces. Hybrid views contain two panels: A ControlPanel to modify rendering properties and a
DrawPanel to render these properties using Swing. 
ViewFilterable becomes of use when we want to export the animation to SVG. We need to filter out all shapes that are not being drawn
when sent to the SVG exportation, which is now a ControllableSvg (extends ViewSvg).

Views can be easily constructed using the ViewFactory class.

CONTROLLER -- cs3500.animator.controller
------------------------
A ControllerFactory is used to construct controllers for each view type. Each controller must implement the relevant Controller interface.
The controllers we need have some shared behavior, which is abstracted into the AbstractController. By default, a controller is a BaseController.
Animated, Text, and Svg views all can be shoehorned into this controller type. Hybrid controllers, however, need to initialize the button
mappings, and therefore a HybridController can be instantiated separately.


USAGE 
------------------------
The main class takes inputs on speed, view type, and other flags to pass through the controller.
Flags:
-o [output-file-path]
-speed [speed-integer]
-iv hybrid|svg|text|visual
-if [input-file-path]

USING THE HYBRID VIEW
------------------------
The Hybrid view is used for manipulating the images for svg exporting. Use the speed slider to change the fps
from 0 to 60, which controls the speed of the animation.
Use play to start/restart the animation
Use pause to pause the animation mid-play
Use resume to unpause
Use the loop checkbox to trigger the animation to loop
The export button will prom[t the user to export an svg with these settings