package gol.printers;

import gol.constants.ColorCodes;

/** This class is used for printing errors and exceptions in console. */
public class ErrorPrinter {
  /**
   * Prints a red message in the console indicating an execution error.
   *
   * @param message The error message to print in the console.
   */
  public static void printError(String message) {
    System.out.println(ColorCodes.RED_COLOR + "There occurred an error:");
    System.out.println(message + ColorCodes.WHITE_COLOR);
  }

  /**
   * Prints a cyan message in the console indicating a game exception.
   *
   * @param message The exception message to print in the console.
   */
  public static void printException(String message) {
    System.out.println(ColorCodes.YELLOW_COLOR + "There occurred a game exception:");
    System.out.println(message + ColorCodes.WHITE_COLOR);
  }
}
