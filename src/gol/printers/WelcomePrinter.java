package gol.printers;

import gol.constants.ColorCodes;

/** This class is used for printing welcomes and greetings in console. */
public class WelcomePrinter {
  /**
   * Prints a welcome and custom yellow message in the console indicating a welcoming to the
   * program.
   *
   * @param message The welcome message to print in the console.
   */
  public static void printWelcome(String message) {
    System.out.println(ColorCodes.CYAN_COLOR + "¡Welcome!");
    System.out.println(message + ColorCodes.WHITE_COLOR);
  }
}
