package gol;

import gol.game.configuration.GameConfiguration;
import gol.game.generator.GolGeneratorImpl;
import gol.inputOutput.printers.ErrorPrinter;
import gol.inputOutput.printers.WelcomePrinter;

public class GameOfLife {

  public static void main(String[] args) {
    WelcomePrinter.printWelcome("This is The Game of Life!");
    GameConfiguration gameConfiguration = new GameConfiguration(args);

    if (gameConfiguration.hasValidConfiguration()) {
      gameConfiguration.fillGameGrid();
      gameConfiguration.printConfiguration();

      GolGenerator generator = new GolGeneratorImpl(gameConfiguration.getGameGrid());
      GolSettings settings =
          new GolSettings(
              gameConfiguration.getHeight(),
              gameConfiguration.getWidth(),
              gameConfiguration.getSpeed(),
              gameConfiguration.getGenerations());

      SwingRenderer.render(generator, settings);
    } else {
      ErrorPrinter.printError("The game could not start.");
    }
  }
}
