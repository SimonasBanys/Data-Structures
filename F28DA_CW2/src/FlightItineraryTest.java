import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FlightItineraryTest {

    FlightItinerary fi = new FlightItinerary();
    FlightsReader fr;


    @Before
    public void initialize() {
        try {
            fr = new FlightsReader(FlightsReader.AIRLINECODS);
            fi.populate(fr.getAirlines(), fr.getAirports(), fr.getRoutes());
            fi.setRoutes(fr.getRoutes());
        } catch (FileNotFoundException | FlightItineraryException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test1() {
        try {

            IItinerary i = fi.leastCost("EDI", "DXB");
            assertEquals(2,i.totalHop());
            assertEquals(357,i.totalCost());
        } catch (FlightItineraryException e) {
            fail();
        }
    }

}