package org.rvl.mind.algorithm.spirale;

public class SpiraleUlham {

  public int[][] build(int n) {
    int[][] m = new int[n][n];

    int i = n / 2 -1;
    int[] dirRow = new int[] {0,  -1, 0, 1 };
    int[] dirCol = new int[] {1,  0, -1, 0 };
    int r = 2, c = 2;
    int dir= 0;

    int diff=1;
    int whenToRotate=2;
    int count=2;

    for (int cursor = 1; cursor <= n * n; cursor++) {
      m[r][c] = cursor ;

      if(cursor>1 && cursor==whenToRotate){
        dir = (dir+1)%4;
        whenToRotate = cursor + diff;
        if(count%2==0){
          diff++;
        }
        count++;
      }

      r += dirRow[dir];
      c += dirCol[dir];

    }

    return m;
  }

}
