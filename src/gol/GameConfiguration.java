package gol;

import gol.constants.ErrorReturns;
import gol.constants.Identifiers;
import gol.readers.ArgReaders;
import gol.validations.ArgValidations;

import java.util.Objects;
import java.util.Random;

/** The game configuration class with the configuration values for starting the game. */
public class GameConfiguration {
  /** The width value for the game grid. */
  public final int width;
  /** The height value for the game grid. */
  public final int height;
  /** The game generations change speed in milliseconds. */
  public final int speed;
  /** The number of times in which the game grid wil evolve. */
  public final int generations;
  /** The string value for the alive cells in the grid. */
  private final String population;
  /** The game matrix with the values for the alive and dead cells in the columns and rows. */
  public GolGrid gameGrid;

  /**
   * Starts the game configuration with the given arguments array. Does not make any type of
   * validation.
   *
   * @param gameArguments The game arguments array.
   */
  GameConfiguration(String[] gameArguments) {
    width = ArgReaders.readNumericArgument(gameArguments, Identifiers.WIDTH_NAME);
    height = ArgReaders.readNumericArgument(gameArguments, Identifiers.HEIGHT_NAME);
    speed = ArgReaders.readNumericArgument(gameArguments, Identifiers.SPEED_NAME);
    generations = ArgReaders.readNumericArgument(gameArguments, Identifiers.GENERATIONS_NAME);
    String pop = ArgReaders.readStringArg(gameArguments, Identifiers.POPULATION_NAME);
    population = Objects.equals(pop, "rnd") ? generateRandomPopulation(width, height) : pop;
  }

  /**
   * Generates a random string value for the population.
   *
   * @param width The grid width.
   * @param height The height width.
   * @return Returns the generated population string.
   */
  public static String generateRandomPopulation(int width, int height) {
    if (width == ErrorReturns.intErrorReturn || height == ErrorReturns.intErrorReturn) return "";
    StringBuilder population = new StringBuilder();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        population.append(generateRandomNumber(0, 2));
      }
      if (i < height - 1) population.append("#");
    }
    return population.toString();
  }

  /**
   * Generates random numbers between the min and the max.
   *
   * @param min The minimum value (inclusive).
   * @param max The max value (exclusive).
   * @return Returns the generated random number between the minimum and maximum value.
   */
  private static int generateRandomNumber(int min, int max) {
    return new Random().nextInt(max) + min;
  }

  /**
   * Checks if the configuration has valid types and values.
   *
   * @return Returns true if the configuration has valid values, false if not.
   */
  public boolean hasValidConfiguration() {
    return hasValidTypes() && hasValidValues();
  }

  /**
   * Checks if the game configuration has valid types.
   *
   * @return Returns true if the configuration has valid types, false if not.
   */
  public boolean hasValidTypes() {
    return width != ErrorReturns.intErrorReturn
        && height != ErrorReturns.intErrorReturn
        && generations != ErrorReturns.intErrorReturn
        && speed != ErrorReturns.intErrorReturn
        && !Objects.equals(population, ErrorReturns.stringErrorReturn);
  }

  /**
   * Checks if the given configuration values has the valid values for the game.
   *
   * @return Returns true if the configuration has valid values, false if not.
   */
  public boolean hasValidValues() {
    return ArgValidations.validateGameArguments(width, height, generations, speed, population);
  }

  /**
   * Fills the game grid with the configuration population value. This method should only be used
   * when the configuration is validated.
   */
  public void fillGameGrid() {
    int[][] newGrid = new int[height][width];
    gameGrid = new GolGrid(newGrid, width, height);
    if (Objects.equals(population, "rnd")) {
      gameGrid.fillGridWithPopulation(generateRandomPopulation(width, height));
    } else {
      gameGrid.fillGridWithPopulation(population);
    }
  }

  /** Prints the game configuration values in console. */
  public void printConfiguration() {
    System.out.println("The width is: " + width);
    System.out.println("The height is: " + height);
    System.out.println("The generations are: " + generations);
    System.out.println("The speed is: " + speed);
    System.out.println("The population is: " + population);
    System.out.println("The game grid is:");
    printGameGrid();
  }

  /** Prints the game grid in console. */
  public void printGameGrid() {
    for (int[] row : gameGrid.grid) {
      for (int value : row) {
        System.out.print(value);
      }
      System.out.println();
    }
  }
}
