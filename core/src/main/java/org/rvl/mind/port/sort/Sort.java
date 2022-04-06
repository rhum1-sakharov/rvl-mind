package org.rvl.mind.port.sort;

import java.util.List;

public interface Sort {

  enum Direction {
    ASC, DIR
  };


  /**
   * Sorted by ascending order
   * @param list
   * @return
   */
  List<Integer> sort(List<Integer> list);

  List<Integer> sort(List<Integer> list, Direction direction);

}
