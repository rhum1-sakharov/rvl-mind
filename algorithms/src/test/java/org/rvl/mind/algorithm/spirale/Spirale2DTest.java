package org.rvl.mind.algorithm.spirale;

import static com.google.common.truth.Truth.*;

import org.junit.jupiter.api.Test;

class Spirale2DTest {

  @Test
  void givenSize3_whenBuild_thenReturnSpirale() {
    // given
    int size = 3;
    Spirale2D spirale2D = new Spirale2D(size);

    // when
    int[][] spirale = spirale2D.build();

    // then
    int[][] expectedSpirale = new int[][] { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
    assertThat(spirale).isEqualTo(expectedSpirale);
  }

  @Test
  void givenSize4_whenBuild_thenReturnSpirale() {
    // given
    int size = 4;
    Spirale2D spirale2D = new Spirale2D(size);

    // when
    int[][] spirale = spirale2D.build();

    // then
    int[][] expectedSpirale = new int[][] { { 1, 2, 3, 4 }, { 12, 13, 14, 5 }, { 11, 16, 15, 6 },
                                            { 10, 9, 8, 7 } };
    assertThat(spirale).isEqualTo(expectedSpirale);
  }

  @Test
  void givenSize7_whenBuild_thenReturnSpirale() {
    // given
    int size = 7;
    Spirale2D spirale2D = new Spirale2D(size);

    // when
    int[][] spirale = spirale2D.build();

    // then
    //formatter off
    int[][] expectedSpirale = new int[][] {
        { 1, 2, 3, 4 ,5,6,7},
        { 24, 25, 26, 27 ,28,29,8},
        { 23, 40, 41, 42 ,43,30,9},
        { 22, 39, 48, 49 ,44,31,10},
        { 21, 38, 47, 46 ,45,32,11},
        { 20, 37, 36, 35 ,34,33,12},
        { 19, 18, 17, 16 ,15,14,13},
    };
    // formatter onÒ
    assertThat(spirale).isEqualTo(expectedSpirale);
  }

  @Test
  void givenSize7_whenGreedyBuild_thenReturnSpirale() {
    // given
    int size = 7;
    Spirale2D spirale2D = new Spirale2D(size);

    // when
    int[][] spirale = spirale2D.greedyBuild();

    // then
    //formatter off
    int[][] expectedSpirale = new int[][] {
        { 1, 2, 3, 4 ,5,6,7},
        { 24, 25, 26, 27 ,28,29,8},
        { 23, 40, 41, 42 ,43,30,9},
        { 22, 39, 48, 49 ,44,31,10},
        { 21, 38, 47, 46 ,45,32,11},
        { 20, 37, 36, 35 ,34,33,12},
        { 19, 18, 17, 16 ,15,14,13},
    };
    // formatter onÒ
    assertThat(spirale).isEqualTo(expectedSpirale);
  }

}