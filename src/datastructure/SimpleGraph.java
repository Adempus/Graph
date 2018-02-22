package datastructure;

import java.util.*;

public class SimpleGraph<T, V extends Comparable<V>> extends Graph<T, V>
{
    public SimpleGraph() { super(); }

    public SimpleGraph(T initialItem) {
        this(new Node<>(initialItem));
    }

    public SimpleGraph(Node<T> initialNode) {
        super(initialNode);
    }

    @Override
    public void addNode(Node<T> newNode) {
        if (!nodeExists(newNode))
            adjacencyList.put(newNode, new HashSet<>());
    }

    @Override
    public void addEdge(Node<T> fromNode, Node<T> toNode, V weight) {
        if (!nodeExists(fromNode)) addNode(fromNode);
        if (!nodeExists(toNode))   addNode(toNode);

        int pairsMade = 0;
        Node<T> origin = getNode(fromNode.getItem()),
                  dest = getNode(toNode.getItem());
        for (Map.Entry<Node<T>, Set<Edge<V>>> entry : adjacencyList.entrySet())
        {
            if (pairsMade < 2) {
                if (entry.getKey().equals(origin)) {
                    entry.getValue().add(new Edge<>(origin, dest, weight));
                    pairsMade++;
                } else if (entry.getKey().equals(dest)) {
                    entry.getValue().add(new Edge<>(dest, origin, weight));
                    pairsMade++;
                }
            } else break;
        }
    }

    @Override
    public void removeNode(Node<T> node) {
        if (!nodeExists(node)) return;

        if (adjacencyList.get(node).isEmpty()) { // if node has no neighbors
            adjacencyList.remove(node);          // then just remove it; simple graph.
            return;
        } else {
            for (Set<Edge<V>> allEdges : adjacencyList.values()) {
                allEdges.removeIf(edge -> edge.getAdjacent().equals(node));
            }
            adjacencyList.remove(node);
        }
    }

    @Override
    public boolean isAdjacent(Node<T> origin, Node<T> dest) {
        if (origin == null || dest == null) return false;

        for (Map.Entry<Node<T>, Set<Edge<V>>> entry : adjacencyList.entrySet()) {
            if (entry.getKey().equals(origin)) {
                return entry.getValue().stream().anyMatch((Edge<V> e) -> e.getAdjacent().equals(dest));
            }
        }
        return false;
    }

    //TODO:
    @Override
    public void removeEdge(Node<T> origin, Node<T> dest) {

    }
}