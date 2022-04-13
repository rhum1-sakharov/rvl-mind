package org.rvl.mind.algorithm.sort;

import static com.google.common.truth.Truth.*;

import org.junit.jupiter.api.Test;

class CountingSortTest {

  private CountingSort countingSort = new CountingSort();

  @Test
  void givenArr_whenCountArr_thenReturnCountArr() {
    // given
    int[] arr = new int[] { 1, 4, 1, 2, 7, 5, 2 };

    // when
    int[] countArr = countingSort.countArr(arr);

    // then
    int[] expectedCountArr = new int[] { 0, 2, 2, 0, 1, 1, 0 ,1};
    assertThat(countArr).isEqualTo(expectedCountArr);
  }

  @Test
  void givenCountArr_whenSumCountArr_thenReturnSortedArr() {
    // given
    int[] countArr = new int[] { 0, 2, 2, 0, 1, 1, 0, 1, 0, 0 };

    // when
    int[] sumCountArr = countingSort.sumCountArr(countArr);

    // then
    int[] expectedCountArr = new int[] {0,2,4,4,5,6,6,7,7,7 };
    assertThat(sumCountArr).isEqualTo(expectedCountArr);
  }

  @Test
  void givenArr_whenOutputArr_thenReturnSortedArr() {
    // given
    int[] arr = new int[] { 1, 4, 1, 2, 7, 5, 2 };
    int[] sumCountArr = new int[] {0,2,4,4,5,6,6,7 };

    // when
    int[] sortedArr = countingSort.outputArr(arr,sumCountArr);

    // then
    int[] expectedSortedArr = new int[] { 1,1,2,2,4,5,7 };
    assertThat(sortedArr).isEqualTo(expectedSortedArr);
  }
}