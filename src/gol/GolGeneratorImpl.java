package gol;

public class GolGeneratorImpl implements GolGenerator {
  private final GolGrid grid;

  public GolGeneratorImpl(GolGrid grid) {
    this.grid = grid;
  }

  @Override
  public String getNextGenerationAsString(long l) {
    String lGenerationString = grid.toRendererFormat();
    grid.updateGridLife();
    return lGenerationString;
  }
}
;
