package org.rvl.mind.java.collections.langton.ant;

public class LangtonAntApp {

  public enum SquareType {
    BLACK("#"), WHITE(".");

    private final String sign;

    SquareType(String sign) {
      this.sign = sign;
    }

    @Override
    public String toString() {
      return sign;
    }
  }

  public enum Rotation {
    LEFT, RIGHT;
  }

  public enum Direction {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private final String direction;

    Direction(String direction) {
      this.direction = direction;
    }

    @Override
    public String toString() {
      return direction;
    }

    public static Direction fromString(String direction) {
      for (Direction d : Direction.values()) {
        if (d.direction.equalsIgnoreCase(direction)) {
          return d;
        }
      }
      return null;
    }

  }

  public static void main(String args[]) {
    try {
      int W = 140;
      int H = 140;
      int x = 70;
      int y = 70;
      String direction = "E";
      int T = 12000;

      System.out.println(String.format("W=%s, H=%s, x=%s, y=%s, direction=%s, T=%s",
                                       W,
                                       H,
                                       x,
                                       y,
                                       direction,
                                       T));

      String[][] matrix = createMatrix(H, W);

      for (int t = 0; t < T; t++) {

        SquareType squareType = getSquareType(matrix[x][y]);
        Rotation rotation = getRotation(squareType);
        invertSquareType(matrix, x, y);
        direction = getNewDirection(Direction.fromString(direction), rotation).toString();
        x = newX(direction, x, H);
        y = newY(direction, y, W);
      }

      for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
          System.out.print(matrix[i][j]);
        }
        System.out.println();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static int newX(String direction, int x, int H) {
    switch (Direction.fromString(direction)) {
    case NORTH:
      x = x - 1;
      if (x < 0) {
        x = 0;
      }
      break;
    case SOUTH:
      x = x + 1;
      if (x > H - 1) {
        x = H - 1;
      }
      break;
    default:
      break;
    }
    return x;
  }

  public static int newY(String direction, int y, int W) {
    switch (Direction.fromString(direction)) {
    case EAST:
      y = y + 1;
      if (y > W - 1) {
        y = W - 1;
      }
      break;
    case WEST:
      y = y - 1;
      if (y < 0) {
        y = 0;
      }
      break;
    default:
      break;
    }
    return y;
  }

  public static Direction getNewDirection(Direction direction, Rotation rotation) {

    Direction newDirection = null;

    if (rotation == Rotation.LEFT) {
      switch (direction) {
      case NORTH:
        newDirection = Direction.WEST;
        break;
      case EAST:
        newDirection = Direction.NORTH;
        break;
      case SOUTH:
        newDirection = Direction.EAST;
        break;
      case WEST:
        newDirection = Direction.SOUTH;
        break;
      }

    } else {
      switch (direction) {
      case NORTH:
        newDirection = Direction.EAST;
        break;
      case EAST:
        newDirection = Direction.SOUTH;
        break;
      case SOUTH:
        newDirection = Direction.WEST;
        break;
      case WEST:
        newDirection = Direction.NORTH;
        break;
      }
    }

    return newDirection;
  }

  public static void invertSquareType(String[][] matrix, int x, int y) {
    matrix[x][y] = SquareType.BLACK.toString().equals(matrix[x][y]) ? SquareType.WHITE.toString()
                                                                    : SquareType.BLACK.toString();
  }

  public static SquareType getSquareType(String square) {
    if (SquareType.BLACK.toString().equals(square)) {
      return SquareType.BLACK;
    }
    return SquareType.WHITE;
  }

  public static Rotation getRotation(SquareType squareType) {
    if (SquareType.WHITE == squareType) {
      return Rotation.LEFT;
    }
    return Rotation.RIGHT;
  }

  public static String[][] createMatrix(int H, int W) {
    String[][] matrix = new String[H][W];
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        matrix[i][j] = ".";
      }
    }
    return matrix;
  }
}
