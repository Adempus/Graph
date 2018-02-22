package datastructure;

import java.util.*;

public abstract class Graph<T, V extends Comparable<V>>
{
    protected Map<Node<T>, Set<Edge<V>>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public Graph(Node<T> initialNode) {
        this();
        addNode(initialNode);
    }

    /** overloaded convenience methods for basic abstractions */

    public void addNode(T item) {
        addNode(new Node<>(item));
    }

    public void addEdge(T origin, T dest, V weight) {
        addEdge(new Node<>(origin), new Node<>(dest), weight);
    }

    public void removeNode(T node) {
        removeNode(new Node<>(node));
    }

    public void removeEdge(T origin, T dest) {
        removeEdge(new Node<>(origin), new Node<>(dest));
    }

    public boolean isAdjacent(T origin, T dest) {
        return isAdjacent(getNode(origin), getNode(dest));
    }

    public boolean nodeExists(Node<T> node) {
        return adjacencyList.containsKey(node);
    }

    public int size() {
        return this.adjacencyList.size();
    }

    public Node<T> getNode(T item) {
        if(!nodeExists(new Node<>(item))) return null;

        for (Map.Entry<Node<T>, Set<Edge<V>>> entry : adjacencyList.entrySet()) {
            if (entry.getKey().getItem().equals(item)) return entry.getKey();
        }
        return null;
    }

    public V getWeight(T item1, T item2) {
        return getWeight(getNode(item1), getNode(item2));
    }

    public V getWeight(Node<T> origin, Node<T> dest) {
        if (!nodeExists(origin) || !nodeExists(dest)) return null;

        Set<Edge<V>> edges = getNeighbors(origin);
        for (Edge<V> edge : edges) {
            if (edge.getAdjacent().equals(dest)) return edge.getWeight();
        }
        return null;
    }

    public Set<Edge<V>> getNeighbors(T item) {
        return getNeighbors(getNode(item));
    }

    public Set<Edge<V>> getNeighbors(Node<T> node) {
        return (node == null) ? null : adjacencyList.get(node);
    }

    public abstract void addNode(Node<T> node);
    public abstract void addEdge(Node<T> origin, Node<T> dest, V weight);
    public abstract void removeNode(Node<T> node);
    public abstract void removeEdge(Node<T> origin, Node<T> dest);
    public abstract boolean isAdjacent(Node<T> origin, Node<T> dest);

    @Override
    public String toString() {
        StringBuilder graphString = new StringBuilder();
        for (Map.Entry<Node<T>, Set<Edge<V>>> node : adjacencyList.entrySet()) {
            Node<T> key = node.getKey();
            Set<Edge<V>> value = node.getValue();
            String text = ""+key.toString()+" -> "+value.toString()+"\n";
            graphString.append(text);
        }
        return graphString.toString();
    }

    public static class Node<T> {
        private T item;
        private Comparable cost;

        public Node(T item) {
            setItem(item);
            setCost(Double.POSITIVE_INFINITY);
        }

        public T getItem() { return this.item; }
        public void setItem(T newItem) { this.item = newItem; }
        public void setCost(Comparable newCost) { this.cost = newCost; }
        public Comparable getCost() { return this.cost; }

        @Override
        public boolean equals(Object o) {
            return o instanceof Node && ((Node) o).item.equals(this.item);
        }
        @Override
        public int hashCode() {
            return item.toString().hashCode();
        }
        @Override
        public String toString() {
            return item.toString();
        }
    }

    public static class Edge<V extends Comparable> implements Comparable<Edge> {
        private Node originNode;
        private V weight;
        private Node adjacentNode;

        public Edge(Node origin, Node adjacent, V weight) {
            this.originNode = origin;
            this.adjacentNode = adjacent;
            this.weight = weight;
        }

        public V getWeight() { return weight; }
        public Node getOrigin() { return originNode; }
        public Node getAdjacent() { return adjacentNode; }

        @Override
        public String toString() {
            return "Edge["+originNode.toString()+
                    " to " +adjacentNode.toString()+" ("+weight.toString()+")] ";
        }
        @Override
        public int compareTo(Edge o) {
            if (o.getWeight() instanceof Number) {
                return this.weight.compareTo((V) o.getWeight());
            }
            return -1;
        }
    }
}