package org.rvl.mind.java.collections.adapter.sort;

import static com.google.common.truth.Truth.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SwapTest {

  private final Swap swap = new Swap();
  private List<Integer> list;

  @BeforeEach
  void setUp() {
    list = new ArrayList<>();
    list.add(40);
    list.add(30);
    list.add(40);
    list.add(60);
    list.add(50);
    list.add(70);
    list.add(40);
    list.add(90);
  }

  @Test
  void whenApply_thenSwap() {
    // when
    swap.apply(list, 0, 3);

    // then
    assertThat(list).containsExactly(60, 30, 40, 40, 50, 70, 40, 90).inOrder();
  }

}