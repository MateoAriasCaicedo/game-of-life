package gol.game.configuration;

import gol.game.GolGrid;
import gol.game.constants.ErrorReturns;
import gol.game.constants.Identifiers;
import gol.inputOutput.readers.ArgumentReader;
import gol.game.validations.ArgumentValidator;

import java.util.Objects;
import java.util.Random;

/** The game configuration class with the configuration values for starting the game. */
public class GameConfiguration {
  private final int width;
  private final int height;
  private final int speed;
  private final int generations;
  private final String population;
  private GolGrid gameGrid;

  /**
   * Starts the game configuration with the given arguments array. Does not make any type of
   * validation.
   *
   * @param gameArguments The game arguments array.
   */
  public GameConfiguration(String[] gameArguments) {
    width = ArgumentReader.readNumericArgument(gameArguments, Identifiers.WIDTH_NAME);
    height = ArgumentReader.readNumericArgument(gameArguments, Identifiers.HEIGHT_NAME);
    speed = ArgumentReader.readNumericArgument(gameArguments, Identifiers.SPEED_NAME);
    generations = ArgumentReader.readNumericArgument(gameArguments, Identifiers.GENERATIONS_NAME);
    String pop = ArgumentReader.readStringArg(gameArguments, Identifiers.POPULATION_NAME);
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
    return ArgumentValidator.validateGameArguments(width, height, generations, speed, population);
  }

  /**
   * Fills the game grid with the configuration population value. This method should only be used
   * when the configuration is validated.
   */
  public void fillGameGrid() {
    int[][] newGrid = new int[height][width];
    gameGrid = new GolGrid(newGrid, width, height);
    if (Objects.equals(population, "rnd")) {
      gameGrid.fillGridWithPopulationString(generateRandomPopulation(width, height));
    } else {
      gameGrid.fillGridWithPopulationString(population);
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
    for (int[] row : gameGrid.getGrid()) {
      for (int value : row) {
        System.out.print(value);
      }
      System.out.println();
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getSpeed() {
    return speed;
  }

  public int getGenerations() {
    return generations;
  }

  public GolGrid getGameGrid() {
    return gameGrid;
  }
}
