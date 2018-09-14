The main method requires 5 parts:
-if <File Name>
-speed <Speed> (0 to 1000)
-iv <View Type> (svg, interactive, visual, txt)
-o <Output File> (defaults to System.out)
-provider <Use Provider Views?> (true, false)

AnimatorOperations is the interface used for models that implement it. It has the functionality for adding shapes and transitions,
and getting the animation state.

AnimatorModel is the model that implements the AnimatorOperations, and uses IShape as its generic type. It only stores a list of shapes.

IShape is the interface for representing shapes and allows a few basic getters and the toString method that creates a String of the shape.
It also allows adding transitions to them.

Shape (abstract class) implements IShape and allows for adding transitions while throwing exceptions where overlapping.
Shape has fields: name, appears, disappears, color, and transitions.
Oval and Rectangle both extend Shape while overriding the toString method.

ITransition is  the interface for representing transitions and has getters while also allowing to add shapes.
It also compares overlaps by using double dispatch for all of its implementing classes.
It has a toString method to convert to String.
Finally, ITransition extends Comparable so it can be sorted using Collections.

Transition (abstract class) implements ITransition and has abstract functionality.
Transition has fields: startTime, endTime, and shape.
MoveTransition, ScaleTransition, and ColorTransition all extend Transition while overriding the toString method.
Overlap for these methods all have class specific functionality.

AnimatorView is the interface used for the three separate views. It has one method animate that modifies a field of the view that allows the animation to be displayed.

ModelAdapter is the adapter that restricts access to the view. It only has getters that allow viewing the shapes and transitions, without any form of mutation.

AnimatorText is the view that implements AnimatorView, it has an appendable and a model as a field. It changes appendable to be the text view.
AnimatorSVG is the view that implements AnimatorView, it has an appendable and a model as a field. It changes appendable to be the text SVG formatted view.
AnimatorVisual is the view that implements AnimatorView, it has an AnimatorPanel and a JScrollPanel.
AnimatorHybrid is the view that implements AnimatorView, ItemListener, and ActionListener. It has an AnimatorPanel and menu items
for all of the interactive options.
AnimatorPanel is the JPanel where all of the painting of the animation works out. Now has a HashMap of shape names to booleans, allowing
for hiding and viewing certain shapes.

ViewFactory is the class that creates a view based on the String inputs: "svg", "text", "visual", and "interactive".

IController and CommandController are controllers that have the go method, allowing it to use a view.

ShapeVisitor is the visitor that works on shapes, it has an apply method, and an overloaded visit method for every shape.
ShapeSVGVisitor returns the svg formatted string based on the shape, rectangle or oval.
ShapeVisualVisitor returns the Graphic after being updated with the shape drawing.
ShapeSVGVisitor works similar to SVG visitor but instead uses base as a start time and resets all of the attributes at the end.

TransitionVisitor is the visitor that works on transitions, it has an apply method, and an overloaded visit method for every transition.
TransitionSVGVisitor returns a svg formatted string based on the transition.
TransitionOvalVisualVisitor returns an Oval based on this transition modifying the given shape.
TransitionRectVisualVisitor returns an Rectangle based on this transition modifying the given shape.
TransitionSVGLoopVisitor works similar to the normal SVG visitor but instead uses base as a start time.

EasyAnimator is the main class that parses command line inputs and creates the animation to the specified output file.
