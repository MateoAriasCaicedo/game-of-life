package gol.inputOutput.readers;

import gol.game.constants.ErrorReturns;
import gol.inputOutput.printers.ErrorPrinter;

import java.util.Optional;

/** This class is used for reading the arguments from the args array. */
public class ArgumentReader {
  /**
   * Method for reading a value from the given arguments array. If the argument is repeated, not
   * found or can't be parsed to integer, it prints an error in the console.
   *
   * @param arguments The arguments array.
   * @param argumentIdentifier The identifier for the needed argument value.
   * @return Returns the integer value for the given argument in the arguments array. If the
   *     argument is repeated, not found, or can't be parsed to integer, it returns -1.
   */
  public static int readNumericArgument(String[] arguments, char argumentIdentifier) {
    Optional<String> argument = readArgumentValue(arguments, argumentIdentifier);
    if (argument.isEmpty()) {
      return ErrorReturns.intErrorReturn;
    } else {
      try {
        return Integer.parseInt(argument.get());
      } catch (NumberFormatException exception) {
        ErrorPrinter.printError("The value of " + argumentIdentifier + " has to be an integer");
        return ErrorReturns.intErrorReturn;
      }
    }
  }

  /**
   * Method for reading a value from the given arguments array. If the argument is repeated or not
   * found, it prints an error in the console.
   *
   * @param arguments The arguments array.
   * @param argumentIdentifier The identifier for the needed argument value.
   * @return Returns string value for the given argument in the array of arguments. If the argument
   *     is repeated or not found, it returns null.
   */
  public static String readStringArg(String[] arguments, char argumentIdentifier) {
    return readArgumentValue(arguments, argumentIdentifier).orElse(ErrorReturns.stringErrorReturn);
  }

  /**
   * Reads the string value for the given argument identifier. Will print an error in the console if
   * any.
   *
   * @param arguments The arguments array.
   * @param argumentIdentifier The identifier for the needed argument value.
   * @return Returns a string optional. The optional will contain the argument value if found and
   *     valid, and will be empty if not. If the argument is repeated, returns null.
   */
  private static Optional<String> readArgumentValue(String[] arguments, char argumentIdentifier) {
    String argument = findNonRepeatedArgument(arguments, argumentIdentifier);
    if (argument == null) {
      return Optional.empty();
    } else if (argument.length() <= 2) {
      ErrorPrinter.printError("There where not value for " + argumentIdentifier);
      return Optional.empty();
    } else {
      return Optional.of(argument.substring(2));
    }
  }

  /**
   * Finds a no repeated argument in the given array of arguments. If the argument is repeated, it
   * throws an error.
   *
   * @param arguments The arguments array.
   * @param argumentIdentifier The searched argument identifier.
   * @return Returns the argument if found and is not repeated. If is repeated, returns null.
   */
  private static String findNonRepeatedArgument(String[] arguments, char argumentIdentifier) {
    String searchedArgument = null;
    int counter = 0;
    for (String arg : arguments) {
      if (arg.charAt(0) == argumentIdentifier) {
        counter++;
        searchedArgument = arg;
      }
    }
    if (counter > 1) {
      ErrorPrinter.printError("There can not be repeated arguments");
      return null;
    } else return searchedArgument;
  }
}
