package org.rvl.mind.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class Graph {

  private final Map<Vertex, List<Vertex>> adjVertices = new LinkedHashMap<>();

  void addVertex(String label) {
    adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
  }

  void removeVertex(String label) {
    Vertex v = new Vertex(label);
    adjVertices.values().forEach(e -> e.remove(v));
    adjVertices.remove(v);
  }

  void addEdge(String label1, String label2) {
    Vertex v1 = new Vertex(label1);
    Vertex v2 = new Vertex(label2);
    adjVertices.get(v1).add(v2);
    adjVertices.get(v2).add(v1);
  }

  void removeEdge(String label1, String label2) {
    Vertex v1 = new Vertex(label1);
    Vertex v2 = new Vertex(label2);
    List<Vertex> eV1 = adjVertices.get(v1);
    List<Vertex> eV2 = adjVertices.get(v2);
    if (Objects.nonNull(eV1)) {
      eV1.remove(v2);
    }
    if (Objects.nonNull(eV2)) {
      eV2.remove(v1);
    }
  }

  public Map<Vertex, List<Vertex>> getAdjVertices() {
    return adjVertices;
  }

  public Set<String> explore(String root) {
    Set<String> visitedVertices = new LinkedHashSet<>();
    Stack<String> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      String label = stack.pop();
      if (!visitedVertices.contains(label)) {
        visitedVertices.add(label);
        adjVertices.get(new Vertex(label)).forEach(child -> stack.push(child.getLabel()));
      }
    }

    return visitedVertices;
  }
}
