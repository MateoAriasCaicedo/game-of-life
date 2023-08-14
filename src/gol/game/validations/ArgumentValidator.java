package gol.game.validations;

import gol.game.constants.ValidValues;
import gol.inputOutput.printers.ErrorPrinter;

/** Class with the methods for validating the argument values. */
public class ArgumentValidator {
  /**
   * Method for validating all the game arguments. Prints if any validation goes wrong.
   *
   * @param width The width argument value of the grid.
   * @param height The height argument value of the grid.
   * @param generations The generations argument value.
   * @param speed The speed argument value.
   * @param population The population argument value.
   * @return The boolean result of the validation, true if the validation went fine, false if not.
   */
  public static boolean validateGameArguments(
      int width, int height, int generations, int speed, String population) {
    return (validateWidth(width)
        && validateHeight(height)
        && validateGenerations(generations)
        && validateSpeed(speed)
        && isValidPopulation(population, width, height));
  }

  /**
   * Validates if the given width meets the given requirements for its value.
   *
   * @param width The width argument value of the grid.
   * @return The boolean result of the validation, true if the validation went fine, false if not.
   */
  public static boolean validateWidth(int width) {
    if (containsValue(width, ValidValues.VALID_WIDTH_VALUES)) {
      return true;
    } else {
      String errorMessage = "The width value has to be 10, 20, 40 or 80, but " + width + " given";
      ErrorPrinter.printException(errorMessage);
      return false;
    }
  }

  /**
   * Validates if the given height meets the given requirements for its value.
   *
   * @param height The height argument value of the grid.
   * @return The boolean result of the validation, true if the validation went fine, false if not.
   */
  public static boolean validateHeight(int height) {
    // Check if the height value is one of the valid ones.
    if (containsValue(height, ValidValues.VALID_HEIGHT_VALUES)) {
      return true;
    } else {
      String errorMessage = "The height value has to be 10, 20 or 40, but " + height + " given";
      ErrorPrinter.printException(errorMessage);
      return false;
    }
  }

  /**
   * Validates if the given generations meets the given requirements for its value.
   *
   * @param generations The generations argument value.
   * @return The boolean result of the validation, true if the validation went fine, false if not.
   */
  public static boolean validateGenerations(int generations) {
    if (generations >= 0) {
      return true;
    } else {
      String errorMessage =
          "The generations value has to be zero or greater than zero, but "
              + generations
              + " given";
      ErrorPrinter.printException(errorMessage);
      return false;
    }
  }

  /**
   * Validates if the given speed meets the given requirements for its value.
   *
   * @param speed The speed argument value.
   * @return The boolean result of the validation, true if the validation went fine, false if not.
   */
  public static boolean validateSpeed(int speed) {
    if (speed >= 250 && speed <= 1000) {
      return true;
    } else {
      String errorMessage =
          "The speed value has to be between 250 and 1000, but " + speed + " given";
      ErrorPrinter.printException(errorMessage);
      return false;
    }
  }

  /**
   * Method for validating if the population value meets the requirements for the population.
   *
   * @param population The string value for the population.
   * @param width The value for the grid width.
   * @param height The value for the grid height.
   * @return The boolean value of the validation. If true, the validation went fine, if not, the
   *     validation went wrong.
   */
  public static boolean isValidPopulation(String population, int width, int height) {
    if (countCharRepetitions(population, '#') + 1 > height) {
      ErrorPrinter.printException("Invalid population, it has more rows that the square height");
      return false;
    }
    String[] populationArr = population.split("#");
    for (String rowPopulation : populationArr) {
      if (!isValidRowPopulation(rowPopulation, width)) {
        return false;
      }
    }
    return true;
  }

  private static int countCharRepetitions(String string, char character) {
    int counter = 0;
    for (int i = 0; i < string.length(); i++) {
      if (string.charAt(i) == character) counter++;
    }
    return counter;
  }

  /**
   * Validates if the given row population is valid.
   *
   * @param rowPopulation The string value for the row population.
   * @param width The width value for the grid width.
   * @return The boolean value of the validation. If true, the validation went fine, if not, the
   *     validation went wrong.
   */
  private static boolean isValidRowPopulation(String rowPopulation, int width) {
    if (rowPopulation.length() > width) {
      ErrorPrinter.printException("Invalid population, it has more columns than the grid width");
      return false;
    }
    for (int i = 0; i < rowPopulation.length(); i++) {
      if (!(rowPopulation.charAt(i) == '1' || rowPopulation.charAt(i) == '0')) {
        ErrorPrinter.printException("Invalid population, it has values different than zero or one");
        return false;
      }
    }
    return true;
  }

  /**
   * @param value The value that wants to be searched.
   * @param validValues The set of values where the value will be searched.
   * @return The boolean value for the search. If true, the value is found, if not it is false.
   */
  private static boolean containsValue(int value, int[] validValues) {
    for (int validValue : validValues) {
      if (value == validValue) return true;
    }
    return false;
  }
}
