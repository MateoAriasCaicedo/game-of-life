package gol;

import gol.printers.ErrorPrinter;
import gol.printers.WelcomePrinter;

public class GameOfLife {

  public static void main(String[] args) {
    WelcomePrinter.printWelcome("¡This is The Game of Life!");
    var gameConfiguration = new GameConfiguration(args);
    if (gameConfiguration.hasValidConfiguration()) {
      gameConfiguration.fillGameGrid();
      gameConfiguration.printConfiguration();
      var generator = new GolGeneratorImpl(gameConfiguration.gameGrid);
      var settings =
          new GolSettings(
              gameConfiguration.width,
              gameConfiguration.height,
              gameConfiguration.speed,
              gameConfiguration.generations);
      SwingRenderer.render(generator, settings);
    } else {
      ErrorPrinter.printError("The game could not start.");
    }
  }
}
