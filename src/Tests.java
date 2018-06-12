import datastructure.Graph;
import datastructure.SimpleGraph;

import java.util.Set;

public class Tests {
    public static void main(String[] args) {
        //testNodeEquality();
        testAddEdge();
      /*  Graph<Character, Integer> charGraph = new SimpleGraph<>('A');
        charGraph.addEdge('A', 'B', 8);
        charGraph.addEdge('A', 'D', 6);
        charGraph.addEdge('B', 'C', 9);
        System.out.println(charGraph);*/
    }

    public static void testToString(Graph graph) {
        System.out.println(graph.toString());
        printSizeAndOrder(graph);
    }

    public static void testGetAdjacentNodes(Graph graph) {
        Set<Graph.Edge> edgesToNodeR = graph.getNeighbors('R');
        Set<Graph.Edge> edgesToNodeG = graph.getNeighbors('G');
        Set<Graph.Edge> edgesToNodeL = graph.getNeighbors('L');
        System.out.println(edgesToNodeR.toString());
        System.out.println(edgesToNodeG.toString());
        System.out.println(edgesToNodeL.toString());
    }

    public static void testGetEdgeWeight(Graph graph) {
        System.out.println("\nWeight between A to Z: "+ graph.getWeight('A', 'Z'));
        System.out.println("Weight between R to L: "+ graph.getWeight('R', 'L'));
        System.out.println("Weight between L to M: "+ graph.getWeight('L', 'M'));
    }

    public static void testIsAdjacent(Graph graph) {
        System.out.println(graph.isAdjacent('R', 'W') ?
                "Nodes R and W are adjacent." : "Nodes R and W are NOT adjacent.");

        System.out.println(graph.isAdjacent('L', 'M') ?
                "Nodes L and M are adjacent." : "Nodes L and M are NOT adjacent.");

        System.out.println(graph.isAdjacent('T', 'R') ?
                "Nodes T and R are adjacent." : "Nodes T and R are NOT adjacent.");

        System.out.println(graph.isAdjacent('T', 'G') ?
                "Nodes T and G are adjacent." : "Nodes T and G are NOT adjacent.");

        System.out.println(graph.isAdjacent('G', 'Q') ?
                "Nodes G and Q are adjacent." : "Nodes G and Q are NOT adjacent.");
    }

    public static void testAddEdge() {
        Graph<Character, Double> testGraph = new SimpleGraph<>('G');
        testGraph.addNode('R');
        testGraph.addNode('M');
        testGraph.addNode('T');
        System.out.println("Order: "+testGraph.getOrder());
        testGraph.addEdge('A', 'Z', 26.0);
        testGraph.addEdge('G', 'Q', 15.3);
        testGraph.addEdge('R', 'W',23.8);
        testGraph.addEdge('R', 'X',62.7);
        testGraph.addEdge('R', 'L',342.21);
        testGraph.addEdge('L', 'M',715.9);
        testGraph.addEdge('R', 'S',91.0);
        testGraph.addEdge('T', 'M',16.9);
        testToString(testGraph);
        testGetAdjacentNodes(testGraph);
        testGetEdgeWeight(testGraph);
        testIsAdjacent(testGraph);
        testRemoveNode(testGraph, 'R');
        testRemoveNode(testGraph, 'Q');
        testRemoveNode(testGraph, 'P');
        testRemoveEdge(testGraph);
    }

    public static void testRemoveNode(Graph graph, Comparable key) {
        System.out.println("pre node removal: ");
        printSizeAndOrder(graph);
        System.out.println("Removing node: " +key.toString()+" ...");
        graph.removeNode(key);
        System.out.println("post node removal: ");
        printSizeAndOrder(graph);
    }

    public static void printSizeAndOrder(Graph graph) {
        System.out.println("Order: "+ graph.getOrder());
        System.out.println("Size: "+graph.getSize());
    }

    public static void testRemoveEdge(Graph graph) {
        System.out.println("\nRemoving edge between L and M");
        graph.removeEdge('L', 'M');
        System.out.println(graph);
        printSizeAndOrder(graph);

        System.out.println("\nRemoving edge between M and T");
        graph.removeEdge('M', 'T');
        System.out.println(graph);
        printSizeAndOrder(graph);
    }

    public static void testNodeEquality() {
        Character char1 = 'S';
        Character char2 = 'S';
        Graph.Node<Character> char1Node = new Graph.Node<>(char1);
        Graph.Node<Character> char2Node = new Graph.Node<>(char2);
        Double x5A= 12.310000000;
        Double x5B = 12.37;
        Graph.Node<Double> double1Node = new Graph.Node<>(x5A);
        Graph.Node<Double> double2Node = new Graph.Node<>(x5B);
        System.out.println(double1Node.equals(double2Node) ? "doubles A and B are equal" : "doubles A and B NOT equals.");
        Graph<Character, Integer> sampGraph1 = new SimpleGraph<>('s');
    }
}