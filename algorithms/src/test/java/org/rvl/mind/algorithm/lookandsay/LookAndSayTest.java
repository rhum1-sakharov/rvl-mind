package org.rvl.mind.algorithm.lookandsay;

import static com.google.common.truth.Truth.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class LookAndSayTest {

  @Test
  void given6Size_whenBuild_thenReturn6Sequence() {
    LookAndSay lookAndSay = new LookAndSay();
    String[] result = lookAndSay.build(6);
    Arrays.stream(result).forEach(System.out::println);
    String[] expected = new String[] { "1", "11", "21", "1211", "111221","312211" };
    assertThat(result).isEqualTo(expected);


  }

}