package fileProcessing;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cells.Cell;

public class test {
	
	public static final String path = "data/Fire.xml";
	public static Map<String, String> myParameters = new HashMap<String, String>();
	public static String[][] myGrid;
	
	public static void main(String[] args) {
		//In our real implementation, will be using a FileChooser
		File f = new File(path);
		XMLParser p = new XMLParser(f);
		myParameters = p.getParameters();
		myGrid = p.getGrid();
		printStatus();
	}
	
	private static void printStatus() {
		//print all parameters in HashMap
		for (Map.Entry<String,String> entry : myParameters.entrySet()) {
			  String key = entry.getKey();
			  String value = entry.getValue();
			  System.out.println(key + ": " + value);
		}
		//print out grid
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				
				System.out.print(myGrid[i][j]);
				
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
}