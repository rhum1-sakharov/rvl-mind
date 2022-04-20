package org.rvl.mind.algorithm.spirale;

public class Spirale2D {

  private final int n;

  public Spirale2D(int n) {
    this.n = n;
  }

  public int[][] greedyBuild() {
    int[][] m = new int[n][n];

    int[] dirRow = new int[] { 0, 1, 0, -1 };
    int[] dirCol = new int[] { 1, 0, -1, 0 };

    int dir = 0, val = 0, row = 0, col = 0, limit = n * n;
    while (val++ < limit) {
      m[row][col] = val;
      row += dirRow[dir];
      col += dirCol[dir];
      if (isInvalid(m, row, col)) {
        row -= dirRow[dir];
        col -= dirCol[dir];
        dir = (dir+1) % 4 ;
        row += dirRow[dir];
        col += dirCol[dir];
      }

    }
    return m;
  }

  private boolean isInvalid(int[][] m, int row, int col) {
    return row < 0 || col < 0 || row >= n || col >= n || m[row][col] !=0;
  }

  public int[][] build() {
    int[][] matrix = new int[n][n];

    boolean horizontal = true;
    boolean direction = true;
    int whenToRotate = n;
    int whenToChangeDirection = 1;
    int countRotation = 1;
    int diffRotation = 1;

    int x = 0;
    int y = 0;
    for (int i = 1; i <= n * n; i++) {

      matrix[x][y] = i;

      if (i == whenToRotate) {

        horizontal = !horizontal;

        if (whenToChangeDirection % 2 == 0) {
          direction = !direction;
        }

        whenToRotate = whenToRotate + n - diffRotation;
        if (countRotation % 2 == 0) {
          diffRotation++;
        }

        countRotation++;
        whenToChangeDirection++;
      }

      if (horizontal) {
        y = direction ? y + 1 : y - 1;
      } else {
        x = direction ? x + 1 : x - 1;
      }
    }

    return matrix;
  }

}
