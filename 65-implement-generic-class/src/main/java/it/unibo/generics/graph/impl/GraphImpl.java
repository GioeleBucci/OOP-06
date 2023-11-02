package it.unibo.generics.graph.impl;

import java.util.*;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<T> implements Graph<T> {

  private final Map<T, Set<T>> data = new HashMap<>();

  public GraphImpl() {
  }

  public GraphImpl(Set<T> nodes) {
    for (T node : nodes) {
      addNode(node);
    }
  }

  public void addNode(T node) {
    data.putIfAbsent(node, new HashSet<>());
  }

  public void addEdge(T source, T target) {
    if (!data.containsKey(source) || !data.containsKey(target)) {
      throw new IllegalArgumentException("source/target: non-existing node");
    }
    data.get(source).add(target);
  }

  public Set<T> nodeSet() {
    return Set.copyOf(data.keySet());
  }

  public Set<T> linkedNodes(T node) {
    return Set.copyOf(data.get(node));
  }

  /* BFS algorithm */
  public List<T> getPath(T source, T dest) {
    if (!data.containsKey(source) || !data.containsKey(dest)) {
      throw new IllegalArgumentException("Invalid arguments (source/destination nodes do not exist)");
    }
    Set<T> visited = new HashSet<>();
    HashMap<T, T> parent = new HashMap<>(); // maps every node to his parent
    Queue<T> queue = new LinkedList<>(); // visit queue

    queue.add(source);
    parent.put(source, null); // source has no parent
    visited.add(source);

    while (!queue.isEmpty()) {
      T current = queue.poll();
      if (current == dest) {
        // destination node found, build shortest path and return it
        List<T> shortestPath = new ArrayList<>();
        /* we use the parent map to navigate backwards towards the starting point */
        while (dest != null) {
          shortestPath.add(0, dest);
          T prevNode = parent.get(dest);
          dest = prevNode;
        }
        return shortestPath;
      }

      Set<T> neighbors = data.get(current);
      for (T elem : neighbors) {
        if (!visited.contains(elem)) {
          visited.add(elem);
          parent.put(elem, current);
          queue.add(elem);
        }
      }
    }
    // if no path is found return an empty list
    return new ArrayList<>();
  }

  public String toString() {
    return "GraphImpl [data=" + data + "]";
  }

}
