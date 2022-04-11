package org.rvl.mind.algorithm.pathfinding.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class AStar {

  private PriorityQueue<Node> openNodes;
  private List<Node> closeNodes;
  private List<Node> path;
  private String[][] maze;
  private Node currentNode;
  private Node endNode;

  public AStar(String[][] maze, int xStart, int yStart, int xEnd, int yEnd) {
    this.openNodes = new PriorityQueue<>(maze.length * maze[0].length, new NodeComparator());
    this.closeNodes = new ArrayList<>();
    this.path = new ArrayList<>();
    this.maze = maze;
    this.currentNode = new Node(null, xStart, yStart, 0, 0);
    this.endNode = new Node(null, xEnd, yEnd, 0, 0);

  }

  public String[][] getMaze() {
    return maze;
  }


  public void findPath() {
    this.openNodes.add(currentNode);
    while (!this.openNodes.isEmpty()) {
      currentNode = this.openNodes.poll();
      closeNodes.add(currentNode);

      if (isEndNode()) {
        flagExplored();
        flagPath(currentNode);
        break;
      }

      exploreAdjacentNodes(currentNode);
    }
  }

  private boolean isEndNode() {
    return currentNode.x == endNode.x && currentNode.y == endNode.y;
  }

  private void flagPath(Node node) {
    Node currentNode = node;
    int count=0;

    while (Objects.nonNull(currentNode.parent)) {
      maze[currentNode.x][currentNode.y] = "*";
      currentNode = currentNode.parent;
      count++;
    }
    maze[currentNode.x][currentNode.y] = "*";
    System.out.println("path.length= "+count);
  }

  private void flagExplored() {
    closeNodes.forEach(n -> maze[n.x][n.y] = "?");
  }

  public List<Node> exploreAdjacentNodes(Node node) {
    List<Node> children = new ArrayList<>();

    for (int x = -1; x <= 1; x++) {
      for (int y = -1; y <= 1; y++) {

        int newX = node.x + x;
        int newY = node.y + y;

        if ((x != 0 && y != 0) // skip diagonal movement
            || (x == 0 && y == 0) // skip current node
            || (newX < 0 || newX >= maze.length) // x boundaries
            || (newY < 0 || newY >= maze[0].length) // y boundaries
            || !isWalkable(newX, newY) // skip not walkable node
        ) {
          continue;
        }

        Node adjacentNode = new Node(node, newX, newY);
        children.add(adjacentNode);

        if (closeNodes.contains(adjacentNode)) {
          continue;
        }

        adjacentNode.startToCurrent = currentNode.startToCurrent + 1;
        adjacentNode.currentToEnd = heuristicManhattanDistance(adjacentNode.x, adjacentNode.y);

        boolean found = false;
        for (Node oNode : openNodes) {
          if (oNode.equals(adjacentNode) && adjacentNode.startToCurrent > oNode.startToCurrent) {
            found = true;
            break;
          }
        }
        if (found) {
          continue;
        }
        openNodes.add(adjacentNode);
      }
    }

    return children;
  }

  public boolean isWalkable(int x, int y) {
    return maze[x][y].equals("#") ? false : true;
  }

  private int heuristicManhattanDistance(int dx, int dy) {
    return Math.abs(this.endNode.x - dx) + Math.abs(this.endNode.y - dy);
  }

}
