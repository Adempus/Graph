import datastructure.Graph;
import datastructure.SimpleGraph;

public class Main {
    public static void main(String[] args) {
        City newYork = new City("New York", "US");
        City chicago = new City("Chicago", "US");
        City detroit = new City("Detroit", "US");
        newYork.setPopulation(8000000);

        Graph<City, Double> cityGraph = new SimpleGraph<>(newYork);
        System.out.println(cityGraph.size());
    }
}