package org.rvl.mind.java.collections.adapter.sort;

import static com.google.common.truth.Truth.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BullSortTest {

  private Swap swap = new Swap();
  private BullSort bullSort = new BullSort(swap);
  private List<Integer> unsortedList;
  private List<Integer> unsortedHugeList;
  private final int MAX_RANGE = 50000;

  @BeforeEach
  void setUp() {
    unsortedList = new ArrayList<>();
    unsortedList.add(17);
    unsortedList.add(9);
    unsortedList.add(25);
    unsortedList.add(48);
    unsortedList.add(34);
  }

  @Test
  void giveUnsortedList_whenSort_thenReturnListInAscendingOrder() {
    // when
    List<Integer> result = bullSort.sort(unsortedList);

    // then
    List<Integer> expectedList = List.of(9, 17, 25, 34, 48);
    assertThat(result).isEqualTo(expectedList);
  }

}
