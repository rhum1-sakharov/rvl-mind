package org.rvl.mind.java.collections.adapter.sort;

import static com.google.common.truth.Truth.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuickSortTest {

  private Swap swap = new Swap();
  private QuickSort quickSort = new QuickSort(swap);

  @Test
  void whenSort_thenListIsSorted() {
    // given
    List<Integer> unsortedList = new ArrayList<>();
    unsortedList.add(40);
    unsortedList.add(30);
    unsortedList.add(40);
    unsortedList.add(60);
    unsortedList.add(50);
    unsortedList.add(70);
    unsortedList.add(40);
    unsortedList.add(90);

    // when
    List<Integer> sortedList = quickSort.sort(unsortedList);

    // then
    assertThat(sortedList).containsExactly(30, 40, 40, 40, 50, 60, 70, 90).inOrder();
  }

}