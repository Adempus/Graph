package datastructure;

import java.util.*;

public abstract class Graph<T, V extends Comparable<V>>
{
    protected Map<Node<T>, Set<Edge<V>>> adjacencyList;
    private int size;

    public Graph() { this.adjacencyList = new HashMap<>(); }

    public Graph(Node<T> initialNode) {
        this();
        addNode(initialNode);
    }

    /** overloaded convenience methods for abstract operations. */

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
        Node<T> org = new Node<>(origin);
        Node<T> des= new Node<>(dest);
        if (isAdjacent(org, des)) {
            removeEdge(new Node<>(origin), new Node<>(dest));
            decrementSize();
        }
    }

    public boolean isAdjacent(T origin, T dest) {
        return isAdjacent(getNode(origin), getNode(dest));
    }

    public V getWeight(T item1, T item2) {
        return getWeight(getNode(item1), getNode(item2));
    }

    /**
     *  @param  node - a node in the graph to check for.
     *  @return true if the node exists in this graph, false otherwise.
     */
    public boolean nodeExists(Node<T> node) {
        return node != null && adjacencyList.containsKey(node);
    }

    /**
     *  @return the number of nodes in this graph.
     */
    public int getOrder() {
        return this.adjacencyList.size();
    }

    /**
     *  @param  item - the object held as a reference in this graph.
     *  @return a node containing the object reference item.
     */
    public Node<T> getNode(T item) {
        if(!nodeExists(new Node<>(item))) return null;

        for (Map.Entry<Node<T>, Set<Edge<V>>> entry : adjacencyList.entrySet()) {
            if (entry.getKey().getItem().equals(item)) return entry.getKey();
        }
        return null;
    }

    /**
     *  @param  origin -the node adjacent to destination.
     *  @param  dest   -the node adjacency to origin.
     *  @return Comparable of type <V> representing a quantifiable edge value between two nodes.
     */
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

    /** @return the number of edges in this graph. */
    public int getSize() {
        return this.size;
    }

    protected abstract void addNode(Node<T> node);
    protected abstract void addEdge(Node<T> origin, Node<T> dest, V weight);
    protected abstract void removeNode(Node<T> node);
    protected abstract void removeEdge(Node<T> origin, Node<T> dest);
    protected abstract boolean isAdjacent(Node<T> origin, Node<T> dest);
    public abstract boolean isComplete();
    protected void incrementSize() { this.size++; }
    protected void decrementSize() { this.size--; }
    @Override
    public String toString() {
        StringBuilder graphString = new StringBuilder();
        for (Map.Entry<Node<T>, Set<Edge<V>>> node : adjacencyList.entrySet()) {
            String text = ""+node.getKey().toString()+" -> "+node.getValue().toString()+"\n";
            graphString.append(text);
        }
        return graphString.toString();
    }

    /** Represents an object-containing node of type <T> as a reference in the graph.
     */
    public static class Node<T> {
        private T item;
        private Comparable cost;
        private int degree;

        public Node(T item) {
            setItem(item);
            setCost(Double.POSITIVE_INFINITY);
        }

        public T getItem() { return this.item; }

        public void setItem(T newItem) { this.item = newItem; }

        public void setCost(Comparable newCost) { this.cost = newCost; }

        public Comparable getCost() { return this.cost; }

        public int getDegree() { return this.degree; }

        protected void incrementDegree() { this.degree++; }

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

    /** Represents a single connection, and weight value between two nodes in a graph. **/
    public static class Edge<V extends Comparable> implements Comparable<Edge>
    {
        private Node originNode;
        private V weight;
        private Node adjacentNode;

        public Edge(Node origin, Node adjacent, V weight) {
            this.originNode = origin;
            this.adjacentNode = adjacent;
            this.weight = weight;
            this.originNode.incrementDegree();
            this.adjacentNode.incrementDegree();
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