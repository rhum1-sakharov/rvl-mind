package org.rvl.mind.algorithm.strings;

import static com.google.common.truth.Truth.*;

import org.junit.jupiter.api.Test;

class OneAwayTest {

  @Test
  void givenAAndBGt_whenCompare_thenReturnFalse() {
    String a = "pale";
    String b = "paleee";

    OneAway oneAway = new OneAway();
    boolean result = oneAway.isOneAway(a, b);

    assertThat(result).isFalse();
  }

  @Test
  void givenAAndBLt_whenCompare_thenReturnFalse() {
    String a = "pale";
    String b = "pa";

    OneAway oneAway = new OneAway();
    boolean result = oneAway.isOneAway(a, b);

    assertThat(result).isFalse();
  }

  @Test
  void givenList_whenCompare_thenReturnBoolean() {
    String[] arrA = new String[] { "pale", "pales", "pale", "pale" };
    String[] arrB = new String[] { "ple", "pale", "bale", "bake" };
    OneAway oneAway = new OneAway();

    assertThat(oneAway.isOneAway(arrA[0], arrB[0])).isTrue();
    assertThat(oneAway.isOneAway(arrA[1], arrB[1])).isTrue();
    assertThat(oneAway.isOneAway(arrA[2], arrB[2])).isTrue();
    assertThat(oneAway.isOneAway(arrA[3], arrB[3])).isFalse();

  }

}