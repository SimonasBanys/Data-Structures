import org.jgrapht.GraphPath;

import java.time.LocalTime;
import java.util.List;

public class Itinerary implements IItinerary {

    private GraphPath path;
    private List<String> route;
    private List<String[]> times;

    public Itinerary(GraphPath path, List<String> route, List<String[]> times)
    {
        this.path = path;
        this.route = route;
        this.times = times;
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
        return route;
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
        int total;
        LocalTime c1, c2, totalT, totalFlight;
        totalFlight = LocalTime.of(0,0);
        for (int i = 0; i < times.size(); i++) {
                c1 = LocalTime.of(Integer.parseInt(times.get(i)[0])/100, Integer.parseInt(times.get(i)[0])%100);
                c2 = LocalTime.of(Integer.parseInt(times.get(i)[1])/100, Integer.parseInt(times.get(i)[1])%100);
                totalT = LocalTime.of(c2.plusHours(-c1.getHour()).getHour(), c2.plusMinutes(-c1.getMinute()).getMinute());
                totalFlight = LocalTime.of((totalFlight.plusHours(totalT.getHour()).getHour()), (totalFlight.plusMinutes(totalT.getMinute()).getMinute()));
        }
        total = totalFlight.getHour()*100 + totalFlight.getMinute();
        return total;
    }

    /**
     * Returns the total time in connection of the itinerary
     */
    @Override
    public int connectingTime() {
        int total = 0;
        LocalTime cbeg, cend, totalT;
        cend = LocalTime.of(Integer.parseInt(times.get(0)[1])/100, Integer.parseInt(times.get(0)[1])%100);
        for (int i = 1; i < times.size(); i++) {
                cbeg = LocalTime.of(Integer.parseInt(times.get(i)[0])/100, Integer.parseInt(times.get(i)[0])%100);
                totalT = LocalTime.of(cbeg.plusHours(-cend.getHour()).getHour(), cbeg.plusMinutes(-cend.getMinute()).getMinute());
                cend = LocalTime.of(Integer.parseInt(times.get(i)[1])/100, Integer.parseInt(times.get(i)[1])%100);
                total += totalT.getHour()*100 + totalT.getMinute();
            }
        return total;
    }

    /**
     * Returns the total travel time of the itinerary
     */
    @Override
    public int totalTime() {
        int total, air, conn;
        air = this.airTime();
        conn = this.connectingTime();
        if ((air%100 + conn%100) >= 60)
            total = (air/100 + conn/100 + 1)*100 + Math.abs((air%100)-(60-(conn%100)));
        else total = air + conn;
        return total;
    }
}
