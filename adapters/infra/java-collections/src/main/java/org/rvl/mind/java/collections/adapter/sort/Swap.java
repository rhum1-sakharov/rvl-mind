package org.rvl.mind.java.collections.adapter.sort;

import java.util.List;

public class Swap {

  public List<Integer> apply(List<Integer> list, int indexA, int indexB) {
    Integer tmp = list.get(indexA);
    list.set(indexA, list.get(indexB));
    list.set(indexB, tmp);
    return list;
  }

}
