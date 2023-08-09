package gol;

import gol.constants.GameValues;

public class GolGrid {
  final int width;
  final int height;
  int[][] grid;

  public GolGrid(int[][] grid, int width, int height) {
    this.grid = grid;
    this.width = width;
    this.height = height;
  }

  private static void reviveRowCell(int columnIndex, int[] row) {
    row[columnIndex] = 1;
  }

  private static void killRowCell(int columnIndex, int[] row) {
    row[columnIndex] = 0;
  }

  private static boolean isAlive(int columnIndex, int[] row) {
    return row[columnIndex] == 1;
  }

  private static boolean canRevive(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    return countAliveNeighbours(cellRowIndex, cellColumnIndex, grid) == 3;
  }

  private static boolean canDie(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    int neighborsCount = countAliveNeighbours(cellRowIndex, cellColumnIndex, grid);
    return neighborsCount < 2 || neighborsCount > 3;
  }

  private static int countAliveNeighbours(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    return rightValue(cellRowIndex, cellColumnIndex, grid)
        + downRightValue(cellRowIndex, cellColumnIndex, grid)
        + downValue(cellRowIndex, cellColumnIndex, grid)
        + downLeftValue(cellRowIndex, cellColumnIndex, grid)
        + leftValue(cellRowIndex, cellColumnIndex, grid)
        + topLeftValue(cellRowIndex, cellColumnIndex, grid)
        + topValue(cellRowIndex, cellColumnIndex, grid)
        + topRightValue(cellRowIndex, cellColumnIndex, grid);
  }

  private static int rightValue(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    try {
      return grid[cellRowIndex][++cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private static int leftValue(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    try {
      return grid[cellRowIndex][--cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private static int topValue(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    try {
      return grid[++cellRowIndex][cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private static int downValue(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    try {
      return grid[--cellRowIndex][cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private static int topLeftValue(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    try {
      return grid[++cellRowIndex][--cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private static int topRightValue(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    try {
      return grid[++cellRowIndex][++cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private static int downRightValue(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    try {
      return grid[--cellRowIndex][++cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private static int downLeftValue(int cellRowIndex, int cellColumnIndex, int[][] grid) {
    try {
      return grid[--cellRowIndex][--cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private static int[] updateRowLife(int[][] grid, int rowIndex, int width) {
    int[] currentRow = grid[rowIndex];
    int[] updatedRow = new int[width];
    for (int columnIndex = 0; columnIndex < width; columnIndex++) {
      if (isAlive(columnIndex, currentRow) && canDie(rowIndex, columnIndex, grid)) {
        killRowCell(columnIndex, updatedRow);
      } else if (!isAlive(columnIndex, currentRow) && canRevive(rowIndex, columnIndex, grid)) {
        reviveRowCell(columnIndex, updatedRow);
      } else {
        updatedRow[columnIndex] = currentRow[columnIndex];
      }
    }
    return updatedRow;
  }

  private static void appendDeadCharacter(StringBuilder builder) {
    builder.append(GameValues.DEAD_CELL_CHAR);
  }

  private static void appendAliveCharacter(StringBuilder builder) {
    builder.append(GameValues.ALIVE_CELL_CHAR);
  }

  private static void appendNewRowCharacter(StringBuilder builder) {
    builder.append(GameValues.NEW_ROW_CHAR);
  }

  private static void appendCorrespondingCharacter(int value, StringBuilder builder) {
    if (value == GameValues.DEAD_CELL_INT) {
      appendDeadCharacter(builder);
    } else {
      appendAliveCharacter(builder);
    }
  }

  public void updateGridLife() {
    int[][] nextGenerationGrid = new int[height][width];
    for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
      nextGenerationGrid[rowIndex] = updateRowLife(grid, rowIndex, width);
    }
    grid = nextGenerationGrid;
  }

  public void fillGridWithPopulation(String population) {
    int currentRow = 0;
    int currentRowColumn = 0;
    for (int i = 0; i < population.length(); i++) {
      if (population.charAt(i) == '#') {
        currentRow++;
        currentRowColumn = 0;
      } else {
        grid[currentRow][currentRowColumn] =
            (population.charAt(i) == '1'
                ? GameValues.ALIVE_CELL_INT
                : GameValues.DEAD_CELL_INT);
        currentRowColumn++;
      }
    }
  }

  public String toRendererFormat() {
    StringBuilder rendererFormat = new StringBuilder();
    for (int[] row : grid) {
      for (int value : row) appendCorrespondingCharacter(value, rendererFormat);
      appendNewRowCharacter(rendererFormat);
    }
    return rendererFormat.toString();
  }
}
