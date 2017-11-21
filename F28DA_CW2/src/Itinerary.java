import org.jgrapht.GraphPath;

import java.util.List;

public class Itinerary implements IItinerary {

    private GraphPath path;
    private String[] codes;

    public Itinerary(GraphPath path, String[] codes)
    {
        this.path = path;
        this.codes = codes;
    }

    /**
     * Returns the list of airports codes composing the itinerary
     */
    @Override
    public List<String> getStops() {
        return path.getVertexList();
    }

    /**
     * Returns the list of flight codes composing the itinerary
     */
    @Override
    public List<String> getFlights()  {
        List<String> flights = null;
        FlightsReader reader = new FlightsReader(codes);
        for (String[] set : reader.getRoutes())
        {
            for (int i = 1; i < path.getVertexList().size(); i++)
            {
                if (set[1].equals(path.getVertexList().get(i-1)) && set[3].equals(path.getVertexList().get(i)))
                    flights.add(set[0]);
            }
        }
        return null;
    }

    /**
     * Returns the number of connections of the itinerary
     */
    @Override
    public int totalHop() {
        return path.getEdgeList().size();
    }

    /**
     * Returns the total cost of the itinerary
     */
    @Override
    public int totalCost() {
        return (int) path.getWeight();
    }

    /**
     * Returns the total time in flight of the itinerary
     */
    @Override
    public int airTime() {
        return 0;
    }

    /**
     * Returns the total time in connection of the itinerary
     */
    @Override
    public int connectingTime() {
        return 0;
    }

    /**
     * Returns the total travel time of the itinerary
     */
    @Override
    public int totalTime() {
        return 0;
    }
}
