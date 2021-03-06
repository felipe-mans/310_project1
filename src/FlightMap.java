import java.io.*;
import java.util.*;

public class FlightMap {

    public String originCity;
    public ArrayList<String> cities;
    public ArrayList<Flight> allFlights;

    //Constructor
    public FlightMap( String s ) {
	originCity = s;
	cities = new ArrayList<String>();
	allFlights = new ArrayList<Flight>();
    }

    // Keep track of all cities in map
    public void addCity( String c ) {
	cities.add(c);
    }

    //Keep track of all possible flights
    public void addFlight( Flight f ) {
	allFlights.add(f);
    }

    //Return String output of path from origin city to destination
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
    
    //Return total price from origin city to destination
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

    //Return data for output file writing. Each String array is a new line
    public ArrayList<String[]> getMap() {
	ArrayList<String[]> output = new ArrayList<String[]>();
	ArrayList<Flight> flightPaths = new ArrayList<Flight>();
	String[] destinations = new String[allFlights.size()];

	int count = 0;
	String currCity = originCity;

	//Add direct flights from origin city to possible destinations
	for (Flight f : allFlights) {
	    if (f.origin.equals(currCity)) {
		destinations[count] = f.destination;
		flightPaths.add(f);
		count = count + 1;
	    }
	}

	int check = 0;

	//Find other flights using direct flights as a connection (recursively)
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

	//Format flight information to string arrays
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
