package gol;

public class GolGrid {
  int[][] grid;

  public GolGrid(int[][] grid) {
    this.grid = grid;
  }

  private static void reviveCell(int rowIndex, int columnIndex, int[][] grid) {
    grid[rowIndex][columnIndex] = 1;
  }

  private void killCell(int rowIndex, int columnIndex, int[][] grid) {
    grid[rowIndex][columnIndex] = 0;
  }

  private boolean isAlive(int rowIndex, int columnIndex) {
    return grid[rowIndex][columnIndex] == 1;
  }

  public void updateGridLife() {
    int[][] nextGenerationGrid = new int[grid.length][grid[0].length];
    for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
      for (int columnIndex = 0; columnIndex < grid[rowIndex].length; columnIndex++) {
        if (isAlive(rowIndex, columnIndex) && canDie(rowIndex, columnIndex)) {
          killCell(rowIndex, columnIndex, nextGenerationGrid);
        } else if (!isAlive(rowIndex, columnIndex) && canRevive(rowIndex, columnIndex)) {
            reviveCell(rowIndex, columnIndex, nextGenerationGrid);
        } else {
          nextGenerationGrid[rowIndex][columnIndex] = grid[rowIndex][columnIndex];
        }
      }
    }
    grid = nextGenerationGrid;
  }

  private boolean canRevive(int cellRowIndex, int cellColumnIndex) {
    return countAliveNeighbours(cellRowIndex, cellColumnIndex) == 3;
  }

  private boolean canDie(int cellRowIndex, int cellColumnIndex) {
    int neighborsCount = countAliveNeighbours(cellRowIndex, cellColumnIndex);
    return neighborsCount < 2 || neighborsCount > 3;
  }

  private int countAliveNeighbours(int cellRowIndex, int cellColumnIndex) {
    return rightValue(cellRowIndex, cellColumnIndex)
        + downRightValue(cellRowIndex, cellColumnIndex)
        + downValue(cellRowIndex, cellColumnIndex)
        + downLeftValue(cellRowIndex, cellColumnIndex)
        + leftValue(cellRowIndex, cellColumnIndex)
        + topLeftValue(cellRowIndex, cellColumnIndex)
        + topValue(cellRowIndex, cellColumnIndex)
        + topRightValue(cellRowIndex, cellColumnIndex);
  }

  private int rightValue(int cellRowIndex, int cellColumnIndex) {
    try {
      return grid[cellRowIndex][++cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private int leftValue(int cellRowIndex, int cellColumnIndex) {
    try {
      return grid[cellRowIndex][--cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private int topValue(int cellRowIndex, int cellColumnIndex) {
    try {
      return grid[++cellRowIndex][cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private int downValue(int cellRowIndex, int cellColumnIndex) {
    try {
      return grid[--cellRowIndex][cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private int topLeftValue(int cellRowIndex, int cellColumnIndex) {
    try {
      return grid[++cellRowIndex][--cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private int topRightValue(int cellRowIndex, int cellColumnIndex) {
    try {
      return grid[++cellRowIndex][++cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private int downRightValue(int cellRowIndex, int cellColumnIndex) {
    try {
      return grid[--cellRowIndex][++cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  private int downLeftValue(int cellRowIndex, int cellColumnIndex) {
    try {
      return grid[--cellRowIndex][--cellColumnIndex];
    } catch (ArrayIndexOutOfBoundsException exception) {
      return 0;
    }
  }

  public String toRendererFormat() {
    String string = "";
    for (int[] row : grid) {
      for (int value : row) {
        if (value == 0) string += ".";
        else string += "X";
      }
      string += "\n";
    }
    return string;
  }

  public int[][] getGrid() {
    return grid;
  }
}
