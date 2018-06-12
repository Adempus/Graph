import datastructure.Graph;
import datastructure.SimpleGraph;

public class Main {
    public static void main(String[] args) {
        City newYork = new City("New York", "US");
        City chicago = new City("Chicago", "US");
        City detroit = new City("Detroit", "US");
        City la = new City("Los Angeles", "US");

        newYork.setPopulation(8000000);
        chicago.setPopulation(2700500);
        detroit.setPopulation(672795);
        la.setPopulation(3900000);

        Graph<City, Double> cityGraph = new SimpleGraph<>(newYork);
        getSizeAndOrder(cityGraph);
        System.out.println(cityGraph.isComplete());
        cityGraph.addEdge(newYork, chicago, 790.0);
        getSizeAndOrder(cityGraph);
        System.out.println(cityGraph.isComplete());
        cityGraph.addEdge(newYork, detroit, 614.0);
        getSizeAndOrder(cityGraph);
        System.out.println(cityGraph.isComplete());
        cityGraph.addEdge(detroit, chicago, 614.0);
        cityGraph.addNode(la);
        getSizeAndOrder(cityGraph);
        System.out.println(cityGraph.isComplete());
        cityGraph.addEdge(la, chicago, 2000.0);
        cityGraph.addEdge(la, newYork, 3000.0);
        getSizeAndOrder(cityGraph);
        System.out.println(cityGraph.isComplete());
        cityGraph.addEdge(la, detroit, 1000.0);
        getSizeAndOrder(cityGraph);
        System.out.println(cityGraph.isComplete());
    }

    public static void getSizeAndOrder(Graph graph) {
        System.out.println("Size : "+graph.getSize());
        System.out.println("Order : "+graph.getOrder()+"\n");
    }
}