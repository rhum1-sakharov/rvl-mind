package org.rvl.mind.algorithm.graph;

import static com.google.common.truth.Truth.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GraphTest {

  private static final String[] VERTEX_NAMES = { "Bob", "Marcel", "Tom", "Ludivine", "Rom", "Marc", "John" };

  private Graph graph;

  @BeforeEach
  void setUp() {
    graph = new Graph();
  }

  @ParameterizedTest
  @MethodSource("provideVertices")
  void givenVertices_whenAddVertex_thenGraphHasVertices(List<String> labels, Map<Vertex, List<Vertex>> expectedVertexMap) {
    // when
    labels.forEach(label -> graph.addVertex(label));

    // then
    assertThat(graph.getAdjVertices()).containsExactlyEntriesIn(expectedVertexMap);
  }

  @Test
  void givenEdge_whenAddEdge_thenGraphHasEdge() {
    // given
    graph.addVertex(VERTEX_NAMES[0]);
    graph.addVertex(VERTEX_NAMES[3]);

    // when
    graph.addEdge(VERTEX_NAMES[0], VERTEX_NAMES[3]);

    // then
    Map<Vertex, List<Vertex>> expectedVertexMap = Map.of(new Vertex(VERTEX_NAMES[0]), List.of(new Vertex(VERTEX_NAMES[3])), new Vertex(VERTEX_NAMES[3]), List.of(new Vertex(VERTEX_NAMES[0])));
    assertThat(graph.getAdjVertices()).containsExactlyEntriesIn(expectedVertexMap);
  }

  @Test
  void givenEdges_whenRemoveEdge_thenGraphHasEdgeRemoved() {
    // given
    graph.addVertex(VERTEX_NAMES[0]);
    graph.addVertex(VERTEX_NAMES[3]);
    graph.addEdge(VERTEX_NAMES[0], VERTEX_NAMES[3]);

    // when
    graph.removeEdge(VERTEX_NAMES[0], VERTEX_NAMES[3]);

    // then
    Map<Vertex, List<Vertex>> expectedVertexMap = Map.of(new Vertex(VERTEX_NAMES[0]), Collections.emptyList(), new Vertex(VERTEX_NAMES[3]), Collections.emptyList());
    assertThat(graph.getAdjVertices()).containsExactlyEntriesIn(expectedVertexMap);
  }

  @Test
  void givenVertices_whenRemoveVertex_thenGraphHasVertexRemoved() {
    // given
    graph.addVertex(VERTEX_NAMES[0]);
    graph.addVertex(VERTEX_NAMES[3]);
    graph.addEdge(VERTEX_NAMES[0], VERTEX_NAMES[3]);

    // when
    graph.removeVertex(VERTEX_NAMES[0]);

    // then
    Map<Vertex, List<Vertex>> expectedVertexMap = Map.of(new Vertex(VERTEX_NAMES[3]), Collections.emptyList());
    assertThat(graph.getAdjVertices()).containsExactlyEntriesIn(expectedVertexMap);
  }

  @Test
  void givenGraph_whenExplore_thenReturnVerticesExplored() {
    // given
    graph = givenGraph();

    // when
    Set<String> result = graph.explore(VERTEX_NAMES[0]);

    // then
    assertThat(result.toString()).isEqualTo("[Bob, Tom, Ludivine, Rom, Marcel]");
  }

  private Graph givenGraph() {
    Graph graph = new Graph();

    Arrays.asList(VERTEX_NAMES).forEach(label -> graph.addVertex(label));

    graph.addEdge(VERTEX_NAMES[0], VERTEX_NAMES[1]);
    graph.addEdge(VERTEX_NAMES[0], VERTEX_NAMES[2]);
    graph.addEdge(VERTEX_NAMES[1], VERTEX_NAMES[2]);
    graph.addEdge(VERTEX_NAMES[2], VERTEX_NAMES[3]);
    graph.addEdge(VERTEX_NAMES[3], VERTEX_NAMES[4]);
    graph.addEdge(VERTEX_NAMES[4], VERTEX_NAMES[1]);

    return graph;
  }

  private static Stream<Arguments> provideVertices() {
    Map<Vertex, List<Vertex>> expectedVertexMap1 = Map.of(new Vertex(VERTEX_NAMES[0]), Collections.emptyList(), new Vertex(VERTEX_NAMES[2]), Collections.emptyList());

    Map<Vertex, List<Vertex>> expectedVertexMap2 = Map.of(new Vertex(VERTEX_NAMES[2]), Collections.emptyList());

    return Stream.of(Arguments.of(List.of(VERTEX_NAMES[0], VERTEX_NAMES[2]), expectedVertexMap1), Arguments.of(Collections.emptyList(), Map.of()), Arguments.of(List.of(VERTEX_NAMES[2]), expectedVertexMap2));
  }

}