package org.rvl.mind.usecase.sort;

import java.util.Collections;
import java.util.List;

import org.rvl.mind.port.sort.Sort;

public class SortUE {

  private final Sort sortPT;

  public SortUE(Sort sortPT) {
    this.sortPT = sortPT;
  }

  public List<Integer> execute(List<Integer> list) {
    return Collections.emptyList();
  }
}
