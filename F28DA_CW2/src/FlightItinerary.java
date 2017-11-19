import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


public class FlightItinerary implements IFlightItinerary, IItinerary
{
    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> g = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    private Scanner scan = new Scanner(System.in);


    /** Implementation of interfaces **/
    @Override
    public List<String> getStops() {
        return null;
    }

    @Override
    public List<String> getFlights() {
        return null;
    }

    @Override
    public int totalHop() {
        return 0;
    }

    @Override
    public int totalCost() {
        return 0;
    }

    @Override
    public int airTime() {
        return 0;
    }

    @Override
    public int connectingTime() {
        return 0;
    }

    @Override
    public int totalTime() {
        return 0;
    }



    @Override
    public boolean populate(HashSet<String[]> airlines, HashSet<String[]> airports, HashSet<String[]> routes) {
        DefaultWeightedEdge e;
        for (String[] set : airports)
            g.addVertex(set[0]);

        for (String[] set : routes) {
            e = g.addEdge(set[1], set[3]);
            if (e != null)
                g.setEdgeWeight(e, Double.parseDouble(set[5]));
        }
        return true;
    }

    @Override
    public IItinerary leastCost(String to, String from) throws FlightItineraryException {
        DijkstraShortestPath path = new DijkstraShortestPath(g);
        System.out.println("Shortest (i.e. cheapest) path:");
        System.out.println(path.getPath(from, to));
        System.out.println("Cost of the shortest (i.e. cheapest) path = " + path.getPathWeight(from, to));
        return null;
    }

    @Override
    public IItinerary leastHop(String to, String from) throws FlightItineraryException {
        return null;
    }

    @Override
    public IItinerary leastCost(String to, String from, List<String> excluding) throws FlightItineraryException {
        return null;
    }

    @Override
    public IItinerary leastHop(String to, String from, List<String> excluding) throws FlightItineraryException {
        return null;
    }

    @Override
    public String leastCostMeetUp(String at1, String at2) throws FlightItineraryException {
        return null;
    }

    @Override
    public String leastHopMeetUp(String at1, String at2) throws FlightItineraryException {
        return null;
    }

    @Override
    public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlightItineraryException {
        return null;
    }



    /** Part A **/
    private void partA() throws FileNotFoundException, FlightItineraryException
    {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graphA = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // adding vertices to the graph
        graphA.addVertex("Edinburgh");
        graphA.addVertex("Heathrow");
        graphA.addVertex("Dubai");
        graphA.addVertex("Sydney");
        graphA.addVertex("Kuala Lumpur");

        // adding edges to the vertices. Since edges are one directional they need to be added twice with opposite source and target vertices
        graphA.setEdgeWeight(graphA.addEdge("Edinburgh", "Heathrow"), 80);
        graphA.setEdgeWeight(graphA.addEdge("Heathrow", "Edinburgh"), 80);
        graphA.setEdgeWeight(graphA.addEdge("Heathrow", "Dubai"), 130);
        graphA.setEdgeWeight(graphA.addEdge("Dubai", "Heathrow"), 130);
        graphA.setEdgeWeight(graphA.addEdge("Heathrow", "Sydney"), 570);
        graphA.setEdgeWeight(graphA.addEdge("Sydney", "Heathrow"), 570);
        graphA.setEdgeWeight(graphA.addEdge("Dubai", "Kuala Lumpur"), 170);
        graphA.setEdgeWeight(graphA.addEdge("Kuala Lumpur", "Dubai"), 170);
        graphA.setEdgeWeight(graphA.addEdge("Edinburgh", "Dubai"), 190);
        graphA.setEdgeWeight(graphA.addEdge("Dubai", "Edinburgh"), 190);
        graphA.setEdgeWeight(graphA.addEdge("Kuala Lumpur", "Sydney"), 150);
        graphA.setEdgeWeight(graphA.addEdge("Sydney", "Kuala Lumpur"), 150);


        String start, end;
        System.out.println("Please enter the start airport:");
        start = scan.nextLine();
        System.out.println("Please enter the destination airport:");
        end = scan.nextLine();
        DijkstraShortestPath path = new DijkstraShortestPath(graphA);
        System.out.println("Shortest (i.e. cheapest) path:");
        System.out.println(path.getPath(start, end).toString());
        System.out.println("Cost of the shortest (i.e. cheapest) path = " + path.getPathWeight(start, end));
    }


    private void partB() throws FileNotFoundException, FlightItineraryException
    {
        FlightsReader reader = new FlightsReader(FlightsReader.AIRLINECODES);
        this.populate(reader.getAirlines(), reader.getAirports(), reader.getRoutes());
        String start, end;
        System.out.println("Please enter the start airport:");
        start = scan.nextLine();
        System.out.println("Please enter the destination airport:");
        end = scan.nextLine();
        for (String[] set : reader.getAirports())
        {
            if (set[1].equals(start))
                start = set[0];
            if (set[1].equals(end))
                end = set[0];
        }
        leastCost(end, start);
    }

    /** Main class **/
    public static void main(String[] args) throws FileNotFoundException, FlightItineraryException
    {
        FlightItinerary flight = new FlightItinerary();
        flight.partA();
        flight.partB();
    }


}
