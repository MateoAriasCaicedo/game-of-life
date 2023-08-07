package gol;

// ------- Recommended game parameters -------:
// Valid parameters:
// w=20 h=10 s=250 g=0 p=111001#0101
// Different arguments order:
// h=10 w=10 g=0 s=250 p=1#1#1#1#1#1#1#1#1#1
// Different population:
// h=10 w=10 g=0 s=250 p=#####11111#1#1
// Random population:
// h=20 w=10 g=0 s=250 p=rnd

// ------- Invalid game parameters -------
// Invalid types:
// w=A h=B s=C g=D p=ABC
// Invalid width:
// w=5 h=10 s=250 g=0 p=1#1#1#1#1#1#1#1#1#1#1
// Invalid height:
// w=10 h=5 s=250 g=0 p=1#1#1#1#1#1#1#1#1#1#1
// Invalid speed:
// w=10 h=10 s=249 g=0 p=1#1#1#1#1#1#1#1#1#1#1
// Invalid generations:
// w=10 h=10 s=250 g=-1 p=1#1#1#1#1#1#1#1#1#1#1
// Invalid population:
// w=10 h=10 s=250 g=0 p=12#1
// w=10 h=10 s=250 g=0 p=11111111111#1
// w=10 h=10 s=250 g=0 p=1#1#1#1#1#1#1#1#1#1#1
// Not value for width and height:
// w= h= s=250 g=0 p=11#1

import gol.printers.ErrorPrinter;
import gol.printers.WelcomePrinter;

public class GameOfLife {

  public static void main(String[] args) {
    WelcomePrinter.printWelcome("¡This is The Game of Life!");
    var gameConfiguration = new GameConfiguration(args);
    if (gameConfiguration.hasValidConfiguration()) {
      gameConfiguration.fillGameGrid();
      gameConfiguration.printConfiguration();
      var grid = new GolGrid(gameConfiguration.gameGrid);
      var generator = new GolGeneratorImpl(grid);
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
