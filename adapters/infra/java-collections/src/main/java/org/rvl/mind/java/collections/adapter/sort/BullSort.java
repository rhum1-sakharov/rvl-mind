package org.rvl.mind.java.collections.adapter.sort;

import java.util.List;

import org.rvl.mind.port.sort.Sort;

public class BullSort implements Sort {

  private final Swap swap;

  public BullSort(Swap swap) {
    this.swap = swap;
  }

  @Override
  public List<Integer> sort(List<Integer> list) {

    int lastIndex = list.size() - 1;
    boolean hasPermuted = true;

    while (hasPermuted) {
      hasPermuted = false;
      for (int i = 0; i < lastIndex ; i++) {
        if (list.get(i) > list.get(i + 1)) {
          swap.apply(list, i, i + 1);
          hasPermuted = true;
        }
      }
     lastIndex = lastIndex - 1;
    }

    return list;
  }

  @Override
  public List<Integer> sort(List<Integer> list, Direction direction) {
    return null;
  }

  public List<Integer> swap(List<Integer> list, int indexA, int indexB) {
    Integer tmp = list.get(indexA);
    list.set(indexA, list.get(indexB));
    list.set(indexB, tmp);
    return list;
  }
}
