import java.io.*;

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
		}
		else {
		    
		}
	    }
	} catch (FileNotFoundException fnfe) {
	    System.out.println("Unable to open file");
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
    }
}
