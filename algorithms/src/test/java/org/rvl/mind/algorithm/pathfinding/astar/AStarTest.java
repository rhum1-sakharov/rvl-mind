package org.rvl.mind.algorithm.pathfinding.astar;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.PriorityQueue;

import static com.google.common.truth.Truth.assertThat;

class AStarTest {

    private static final int rows = 100;
    private static final int cols = 100;
    private static final String WALL = "#";
    private static final String SPACE = ".";
    private static final String START = "S";
    private static final String END = "E";
    private static final int X_START = 90;
    private static final int Y_START = 90;
    private static final int X_END = 4;
    private static final int Y_END = 7;
    private AStar aStar;

    //  @BeforeEach
    //  void setUp() {
    //
    //    String[][] matrix = new String[rows][cols];
    //    matrix[X_START][Y_START] = START;
    //    matrix[X_END][Y_END] = END;
    //
    //    for (int i = 0; i < rows; i++) {
    //      for (int j = 0; j < cols; j++) {
    //        if ((i == X_START && j == Y_START) || (i == X_END && j == Y_END)) {
    //          continue;
    //        }
    //        if (i > 2 && i < 10 && j >= 8 && j <= 10) {
    //          matrix[i][j] = WALL;
    //        } else {
    //          matrix[i][j] = SPACE;
    //        }
    //      }
    //    }
    //
    //    aStar = new AStar(matrix, X_START, Y_START, X_END, Y_END);
    //  }

    @Test
    void test() {

        String[][] maze = new String[][]{
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", "#", "#", "#", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", "#", "#", "#", ".", ".", "."},
                {".", "E", "#", ".", ".", ".", ".", "."},
                {".", ".", "#", ".", "S", ".", ".", "."},
                {".", ".", "#", ".", ".", ".", ".", "."},
                {".", ".", "#", ".", ".", ".", ".", "#"},
                {".", ".", "#", "#", "#", "#", "#", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
        };
        aStar = new AStar(maze, 5, 4, 4, 1);

        aStar.findPath();

        printMatrix();
    }

    @Disabled
    void givenCurrentNode_whenGetAdjacentNodes_thenReturnAdjacentNodes() {
        // given
        Node node = new Node(null, X_START, Y_START);

        // when
        List<Node> adjacentNodes = aStar.exploreAdjacentNodes(node);

        // then
        List<Node> expectedNodes = List.of(new Node(node, X_START - 1, Y_START),
                new Node(node, X_START, Y_START - 1),
                new Node(node, X_START, Y_START + 1),
                new Node(node, X_START + 1, Y_START));
        assertThat(adjacentNodes).containsExactlyElementsIn(expectedNodes);
    }

    @Disabled
    void givenAWall_whenGetAdjacentNodes_thenReturnAdjacentNodes() {
        // given
        Node node = new Node(null, X_START, Y_START);
        aStar.getMaze()[X_START][Y_START - 1] = WALL;

        // when
        List<Node> adjacentNodes = aStar.exploreAdjacentNodes(node);

        // then
        List<Node> expectedNodes = List.of(new Node(node, X_START - 1, Y_START),
                new Node(node, X_START, Y_START + 1),
                new Node(node, X_START + 1, Y_START));
        assertThat(adjacentNodes).containsExactlyElementsIn(expectedNodes);
    }

    @Test
    void testPriorityQueue() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue(5, new NodeComparator());

        Node parent = new Node(null, 0, 0, 0, 0);
        Node node1 = new Node(null, 0, 0, 1, 5);
        Node node2 = new Node(null, 1, 1, 1, 1);

        priorityQueue.add(node1);
        priorityQueue.add(node2);

        System.out.println(node1.getCost());
        System.out.println(node2.getCost());

        Node poll = priorityQueue.poll();
        System.out.println(poll.getCost());
    }

    @Test
    void test2PriorityQueue() {
        PriorityQueue<Node2> pq = new PriorityQueue();
        pq.add(new Node2(2, 3));
        pq.add(new Node2(5, 4));
        pq.add(new Node2(1, 1));

        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());

    }


    private static class Node2 implements Comparable<Node2> {
        Node parent;
        int x, y, g, h;

        public Node2(int g, int h) {
            this.g = g;
            this.h = h;
        }

        int getCost() {
            return g + h;
        }

        @Override
        public int compareTo(Node2 o) {
            return this.getCost() - o.getCost();
        }

        @Override
        public String toString() {
            return "Node2{" +
                    "g=" + g +
                    ", h=" + h +
                    '}';
        }
    }

    private void printMatrix() {
        for (int i = 0; i < aStar.getMaze().length; i++) {
            for (int j = 0; j < aStar.getMaze()[0].length; j++) {
                System.out.print(aStar.getMaze()[i][j]);
            }
            System.out.println("");
        }
    }

}