package gol.game.configuration;

import gol.game.GolGrid;
import gol.game.constants.ErrorReturns;
import gol.game.constants.Identifiers;
import gol.inputOutput.readers.ArgumentReader;
import gol.game.validations.ArgumentValidator;
import gol.utils.RandomGenerator;
import gol.utils.Logger;

import java.util.Objects;

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
        population.append(RandomGenerator.generateRandomNumber(0, 2));
      }
      if (i < height - 1) population.append("#");
    }
    return population.toString();
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
    Logger.logConfigurationValue("The width is", width);
    Logger.logConfigurationValue("The height is", height);
    Logger.logConfigurationValue("The generations are", generations);
    Logger.logConfigurationValue("The speed is", speed);
    Logger.logConfigurationValue("The population is", population);
    printGameGrid();
  }

  /** Prints the game grid in console. */
  public void printGameGrid() {
    for (int[] row : gameGrid.getGrid()) {
      for (int value : row) {
        Logger.logGridCellValue(value);
      }
      Logger.logNewLine();
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
