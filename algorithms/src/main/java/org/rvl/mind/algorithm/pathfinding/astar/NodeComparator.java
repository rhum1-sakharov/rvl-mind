package org.rvl.mind.algorithm.pathfinding.astar;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
  @Override
  public int compare(Node o1, Node o2) {
    return o1.getCost() - o2.getCost();
  }
}
