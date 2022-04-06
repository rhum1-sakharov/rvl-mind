package org.rvl.mind.java.collections.adapter.sort;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BenchmarkSortTest {

  private List<Integer> unsortedHugeList;
  private final int MAX_RANGE = 50000;
  private Swap swap = new Swap();
  private BullSort bullSort = new BullSort(swap);
  private QuickSort quickSort = new QuickSort(swap);

  @BeforeEach
  void setUp() {
    unsortedHugeList = new ArrayList<>();
    for (int i = 1; i <= MAX_RANGE; i++) {
      unsortedHugeList.add(randomNumber(MAX_RANGE));
    }
  }

  @Test
  void givenBullSort_whenSort() {
    // when
    bullSort.sort(unsortedHugeList);

    System.out.println(unsortedHugeList);
  }

  @Test
  void givenQuickSort_whenSort() {
    // when
    quickSort.sort(unsortedHugeList);

    System.out.println(unsortedHugeList);
  }

  private int randomNumber(int maxRange) {
    double randNumber = Math.random();
    return (int) (randNumber * maxRange);
  }

}
