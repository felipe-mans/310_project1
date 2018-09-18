import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlightMap {

    private FlightMap fmTest;

    public void setup() {
	fmTest = new FlightMap("f");
    }

    @Test
    public void testAddCity() {
	String newCity = "m";
	fmTest.addCity(newCity);
	assertEquals("city added",'c', fmTest.cities.get(0));
	assertEquals("size of cities", 1, fmTest.cities.size());
    }

    public void testAddFlight() {
	Flight newFlight = new Flight("f", "m", "100");
	fmTest.addFlight(newFlight);
	assertEquals("size of flights", 1, fmTest.flights.size());

    }
}
