package org.rvl.mind.algorithm.pathfinding.astar;

public class Node {

  public Node parent;
  public int x, y;

  // G
  public int startToCurrent;

  // H = heuristic
  public int currentToEnd;

  public Node(Node parent, int x, int y, int startToCurrent, int currentToEnd) {
    this.parent = parent;
    this.x = x;
    this.y = y;
    this.startToCurrent = startToCurrent;
    this.currentToEnd = currentToEnd;
  }

  public Node(Node parent, int x, int y) {
    this.parent = parent;
    this.x = x;
    this.y = y;
  }

  /**
   * f = G + H
   * 
   * @return
   */
  public int getCost() {
    return startToCurrent + currentToEnd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Node node = (Node) o;

    if (x != node.x)
      return false;
    return y == node.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  @Override
  public String toString() {
    return "Node{" + ", x=" + x + ", y=" + y + ", cost=" + getCost() +  ", g=" + startToCurrent + ", h=" + currentToEnd +

           '}';
  }
}
