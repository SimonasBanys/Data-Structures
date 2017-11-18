import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;


public class FlightItinerary implements IFlightItinerary, IItinerary
{
    SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> g = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

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
        double w = 0;
        for (String[] set : airports) {
            g.addVertex(set[0]);
        }
        for (String[] set : routes)
        {
            w = Double.parseDouble(set[5]);
            e = g.addEdge(set[1], set[3]);
            g.getEdgeWeight(e);
        }
        System.out.print(g);
        return true;
    }

    @Override
    public IItinerary leastCost(String to, String from) throws FlightItineraryException {
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
        FlightsReader reader = new FlightsReader(FlightsReader.AIRLINECODES);
        this.populate(reader.getAirlines(), reader.getAirports(), reader.getRoutes());
        System.out.print(leastCost("EDI", "KUL"));
    }


    /** Main class **/
    public static void main(String[] args) throws FileNotFoundException, FlightItineraryException
    {
        FlightItinerary flight = new FlightItinerary();
        flight.partA();
    }


}
