package org.rvl.mind.algorithm.spirale;

import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SpiraleUlhamTest {

  @Test
  void givenSize6_whenBuild_thenReturnSpirale(){
    SpiraleUlham spiraleUlham = new SpiraleUlham();
    int[][] spirale = spiraleUlham.build(5);
    int[][] expectedSpirale = new int[][] {
        { 17, 16, 15, 14, 13},
        { 18, 5, 4, 3,12},
        { 19, 6, 1, 2,11},
        { 20, 7, 8, 9,10},
        { 21, 22, 23, 24,25}
    };
    assertThat(spirale).isEqualTo(expectedSpirale);
  }

}