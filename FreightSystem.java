import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//Heuristic cost:
//For a certain graph, traverse every element in jobsRemaining.
//Runtime cost: O(n).
public class FreightSystem {
	
	public static void main(String[] args) {
		FreightSystem freightsystem = new FreightSystem();
		GraphSystem graphSystem = new GraphSystem();
		Scanner scanInput = freightsystem.scanFile(args[0]);
		
		while(scanInput.hasNextLine()) {
			String readInput = scanInput.nextLine();
			String[] input = readInput.split(" ");
			if(input[0].equals("Unloading")) {
				graphSystem.unloadingCost(input);
			}
			else if(input[0].equals("Cost")) {
				graphSystem.travelCost(input);
			}
			else if(input[0].equals("Job")) {
				graphSystem.newJob(input);
			}
		}
		scanInput.close();
		graphSystem.calculateRoute();
	}
	
	/**
	 * Read the input file as a scanner.
	 * @param input The input file name.
	 * @return The scanner object of the input file.
	 */
	public Scanner scanFile(String input) {
	    Scanner sc = null;
	    try
	    {
	    	sc = new Scanner(new FileReader(input));   
	    }
	    catch (FileNotFoundException e) {
	    	System.err.println("File not found");
	    }
        return sc;
	}
}
