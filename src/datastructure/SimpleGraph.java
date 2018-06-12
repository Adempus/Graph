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

    /**
     *  Adds a one-to-one connection (edge) between two existing nodes in the graph.
     *  If a provided node isn't present, then it's added to the graph.
     *  @param fromNode - node to add edge from.
     *  @param toNode   - node to add edge to.
     */
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
            } else { break; }
        }
        incrementSize();
    }

    /**
     * removes an existing node from the graph.
     *  @param node -a node to remove
     */
    @Override
    public void removeNode(Node<T> node) {
        if (!nodeExists(node)) return;

        if (adjacencyList.get(node).isEmpty()) { // if node has no neighbors
            adjacencyList.remove(node);          // then just remove it; simple graph.
        } else {
            for (Set<Edge<V>> allEdges : adjacencyList.values()) {
                if (allEdges.removeIf(edge -> edge.getAdjacent().equals(node))) {
                    decrementSize();
                }
            }
            adjacencyList.remove(node);
        }
    }

    /**
     *  @param  origin -the node to verify adjacency to destination.
     *  @param  dest   -node to verify adjacency to origin.
     *  @return true if an edge exists between origin and dest; false otherwise.
     */
    @Override
    public boolean isAdjacent(Node<T> origin, Node<T> dest) {
        if (!nodeExists(origin) || !nodeExists(dest)) return false;
        return getNeighbors(origin).stream().anyMatch((Edge<V> e) -> e.getAdjacent().equals(dest));
    }

    @Override
    public boolean isComplete() {
        return this.getSize() == (getOrder()*(getOrder()-1))/2;
    }
    /**
     *  removes an edge connecting two adjacent nodes.
     *  @param origin -the node adjacent to destination.
     *  @param dest   -the node adjacent to origin.
     */
    @Override
    protected void removeEdge(Node<T> origin, Node<T> dest) {
        Set<Edge<V>> neighbors = getNeighbors(origin);
        if (neighbors.removeIf((Edge<V> e) -> e.getOrigin().equals(origin) &&
            e.getAdjacent().equals(dest)))
        {
            removeEdge(dest, origin);
        }
    }
}