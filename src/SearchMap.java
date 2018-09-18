import java.io.*;
import java.util.*;

public class SearchMap {

    public static void main(String [] args) {
	String inputFile = args[0];
	String outputFile = args[1];

	String originCity = null;
	String line = null;

	FlightMap fm = null;

	try {
	    FileReader fileReader = new FileReader(inputFile);
	    BufferedReader buff = new BufferedReader(fileReader);

	    while( (line = buff.readLine()) != null ){
		String[] data = line.split(" ");
		if( data.length == 1 ) {
		    originCity = data[0];
		    fm = new FlightMap(originCity);
		}
		else {
		    Flight newFlight = new Flight(data[0], data[1], data[2]);
		    fm.addFlight(newFlight);
		}
	    }

	    buff.close();

	    ArrayList<String[]> output = fm.getMap();
	    
	    FileWriter fw = new FileWriter(outputFile);
	    BufferedWriter bw = new BufferedWriter(fw);

	    for( String[] row : output ){
		for( String column : row ){
		    bw.write(column + " " );
		}
		bw.newLine();
	    }

	    bw.close();
	    
	} catch (FileNotFoundException fnfe) {
	    System.out.println("Unable to open file");
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
    }
}
