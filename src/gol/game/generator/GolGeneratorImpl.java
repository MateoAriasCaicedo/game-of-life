package gol.game.generator;

import gol.GolGenerator;
import gol.game.GolGrid;

/** Implementation of the GolGenerator interface. */
public class GolGeneratorImpl implements GolGenerator {
  private final GolGrid grid;

  public GolGeneratorImpl(GolGrid grid) {
    this.grid = grid;
  }

  @Override
  public String getNextGenerationAsString(long l) {
    String generationStringFormat = grid.toRendererFormat();
    grid.updateGridLife();
    return generationStringFormat;
  }
}

