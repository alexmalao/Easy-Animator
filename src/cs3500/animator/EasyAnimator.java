package cs3500.animator;

import cs3500.animator.control.CommandController;
import cs3500.animator.control.IController;
import cs3500.animator.model.AnimatorModel.Builder;
import cs3500.animator.model.AnimatorOperations;
import cs3500.animator.provider.controller.Controller;
import cs3500.animator.provider.controller.ProviderController;
import cs3500.animator.provider.model.EasyAnimModelAdapter;
import cs3500.animator.provider.model.EasyAnimOperations;
import cs3500.animator.provider.view.View;
import cs3500.animator.provider.view.ViewFactory.ViewType;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.ViewFactory;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * The main class to be run for an animation.
 */
public final class EasyAnimator {

  /**
   * The main method for the animation.
   * @param args the arguments for the animation.
   */
  public static void main(String[] args) {
    // Parse the inputs from the arguments.
    String inputName = parseInputName(args);
    String viewType = parseViewType(args);
    OutputStream output = parseOutput(args);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
    int speed = parseSpeed(args);
    boolean useProvider = parseProvider(args);

    // Build the model from the input file name and the model builder.
    Builder builder = new Builder();
    AnimationFileReader animationFileReader = new AnimationFileReader();
    AnimatorOperations model;

    // deals with background color
    Scanner sc = null;
    Color bgColor = Color.white;
    try {
      sc = new Scanner(new FileInputStream(inputName));
    } catch (FileNotFoundException e) {
      // should never run.
    }

    while (sc.hasNext()) {
      if (sc.next().equals("color")) {
        bgColor = new Color(sc.nextFloat(),
                sc.nextFloat(),
                sc.nextFloat());
        break;
      }
    }

    try {
      model = animationFileReader.readFile(inputName, builder);
    } catch (FileNotFoundException e) {
      // Should never run.
      model = null;
    }

    // If using the provider code
    if (useProvider) {
      // Create the model adapter
      EasyAnimOperations modelAdapter = new EasyAnimModelAdapter(model);

      // Create the view.
      ViewType viewTypeEnum = convertViewType(viewType);
      View providerView = cs3500.animator.provider.view.ViewFactory.newView(viewTypeEnum);
      providerView.setModel(modelAdapter);

      // Create the controller.
      Controller providerController = new ProviderController(outputStreamWriter);
      providerController.setView(providerView);
      providerController.setSpeed(speed);

      // Run the controller.
      try {
        providerController.launchProgram();
      } catch (IOException e) {
        // do nothing?
      }
    }
    else {
      // Create the view.
      ViewFactory viewFactory = new ViewFactory();
      AnimatorView view = viewFactory.getView(viewType, model, outputStreamWriter,
              bgColor);

      // Create the controller.
      IController controller = new CommandController(model, view);

      // Run the controller.
      controller.start(speed);
    }

    // Flush out the output.
    try {
      outputStreamWriter.flush();
    } catch (IOException e) {
      // Do nothing.
    }
  }

  /**
   * Parse out the input name from the given args.  If there is none throw an
   * IllegalArgumentException.
   * @param args the args to be parsed
   * @return the name of the input file
   * @throws IllegalArgumentException if there is no input file name
   */
  private static String parseInputName(String[] args) throws IllegalArgumentException {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-if")) {
        if ((i + 1) < args.length) {
          return args[i + 1];
        }
        else {
          throw new IllegalArgumentException("There is no input file following -if");
        }
      }
    }
    throw new IllegalArgumentException("-if not used to specify input file");
  }

  /**
   * Parse out the view type from the given args.  If there is none throw an
   * IllegalArgumentException.
   * @param args the args to be parsed
   * @return the view type
   * @throws IllegalArgumentException if there is no view type
   */
  private static String parseViewType(String[] args) throws IllegalArgumentException {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-iv")) {
        if ((i + 1) < args.length) {
          return  args[i + 1];
        }
        else {
          throw new IllegalArgumentException("There is no view type following -iv");
        }
      }
    }
    throw new IllegalArgumentException("-iv is not used to specify the view type");
  }

  /**
   * Parse out the output file name from the given args.  If there is none specified, default to
   * System.out.
   * @param args the args to be parsed
   * @return the output file name
   * @throws IllegalArgumentException if there is no output file following -o
   */
  private static OutputStream parseOutput(String[] args) throws IllegalArgumentException {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-o")) {
        if ((i + 1) < args.length) {
          try {
            return new FileOutputStream(args[i + 1]);
          } catch (FileNotFoundException e) {
            // do nothing
          }
        }
        else {
          throw new IllegalArgumentException("There is no output file following -o");
        }
      }
    }
    return System.out;
  }

  /**
   * Parse out the speed from the given args.  If there is none specified, default to 1.
   * @param args the args to be parsed
   * @return the tick speed
   * @throws IllegalArgumentException if there is no speed following -speed
   */
  private static int parseSpeed(String[] args) throws IllegalArgumentException {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-speed")) {
        if ((i + 1) < args.length) {
          return Integer.parseInt(args[i + 1]);
        }
        else {
          throw new IllegalArgumentException("There is no speed following -speed");
        }
      }
    }
    return 1;
  }

  /**
   * Parse out whether or not to use the provider view implementations.  Specified with the
   * -provider tag followed by true or false.  If not specified, default to false.
   * @param args the args to be parsed
   * @return whether or not to use the provider view implementations.
   * @throws IllegalArgumentException if there is no value following -provider
   */
  private static boolean parseProvider(String[] args) throws IllegalArgumentException {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-provider")) {
        if ((i + 1) < args.length) {
          return args[i + 1].equals("true");
        }
        else {
          throw new IllegalArgumentException("The value after -provider should be true or false");
        }
      }
    }
    return false;
  }

  /**
   * Convert the string view type to the enum view type as defined in the provider ViewFactory
   * class.
   * @param viewType the string to be converted
   * @return the view type enum, null if an invalid string is passed
   */
  private static ViewType convertViewType(String viewType) {
    switch (viewType) {
      case "text":
        return ViewType.TEXT;
      case "visual":
        return ViewType.ANIM;
      case "svg":
        return ViewType.SVG;
      case "interactive":
        return ViewType.HYBRID;
      default:
        // Should never run.
        return null;
    }
  }
}
