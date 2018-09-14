import java.io.*;
import java.util.*;

public class FlightMap {

    String originCity;
    ArrayList<String> cities;
    ArrayList<Flight> directFlights;

    public FlightMap( String s ) {
	originCity = s;
	cities = new ArrayList<String>();
	directFlights = new ArrayList<Flight>();
    }

    public static addCity( String c ) {
	cities.add(c);
    }

    public static addFlight( Flight f ) {
	directFlights.add(f);
    }
}
