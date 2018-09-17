import java.io.*;
import java.util.*;

public class FlightMap {

    String originCity;
    ArrayList<String> cities;
    ArrayList<Flight> allFlights;

    public FlightMap( String s ) {
	originCity = s;
	cities = new ArrayList<String>();
	allFlights = new ArrayList<Flight>();
    }

    public void addCity( String c ) {
	cities.add(c);
    }

    public void addFlight( Flight f ) {
	allFlights.add(f);
    }

    public String findPathFrom( String destination, ArrayList<Flight> flightPaths ){
	if( destination.equals(originCity) ){
	    return originCity;
	}
	else {
	    for( Flight f : flightPaths ){
		if( f.destination.equals(destination) ){
		    String prev = f.origin;
		    return findPathFrom(prev, flightPaths) + "," + destination;
		}
	    }
	}
	return null;
    }

    public int findTotalPrice( String destination, ArrayList<Flight> flightPaths ){
	int totalPrice = 0;
	if( destination.equals(originCity) )
	    return 0;
	else
	    for( Flight f : flightPaths )
		if( f.destination.equals(destination) ){
		    String prev = f.origin;
		    int flightPrice = Integer.parseInt(f.price);
		    return findTotalPrice(prev, flightPaths) + flightPrice;
		}
	return 0;
    }

    public ArrayList<String[]> getMap() {
	ArrayList<String[]> output = new ArrayList<String[]>();
	ArrayList<Flight> flightPaths = new ArrayList<Flight>();
	String[] destinations = new String[allFlights.size()];

	int count = 0;
	String currCity = originCity;

	for (Flight f : allFlights) {
	    if (f.origin.equals(currCity)) {
		destinations[count] = f.destination;
		flightPaths.add(f);
		count = count + 1;
	    }
	}

	int check = 0;

	while( check < count ){
	    currCity = destinations[check];

	    for( Flight f : allFlights ){
		if( f.origin.equals(currCity) ){
		    if(!Arrays.asList(destinations).contains(f.destination)){
		      	destinations[count] = f.destination;
			flightPaths.add(f);
			count = count + 1;
		    }
		}
	    }
	    check = check + 1;
	}

	check = 0;

	String[] firstLine = {"Destination", "Flight Route from " + originCity, "Total Cost"};
	output.add(firstLine);

	for( String city : destinations ){
	    if( city != null ) {
		
		String[] currLine = new String[3];
		currLine[0] = city;
		currLine[1] = findPathFrom(city, flightPaths);
		currLine[2] = Integer.toString(findTotalPrice(city, flightPaths));

		output.add(currLine);
	    }
	}
	return output;
    }
	
}
