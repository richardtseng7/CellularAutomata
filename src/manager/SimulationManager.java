package manager;

import Display.Display;
import SimulationProcessors.*;
import cells.Cell;

public class SimulationManager {
	private BaseProcessor processor;
	private Display display;
	private String simulationType = "";
	private Cell[][] cells;
	
	private void startupSimulation() {
		getCellsFromXML();
		// set default display
		setProcessor();
	}
	
	private void getCellsFromXML() {
		//setCells(XMLParser.getCells());
	}
	
	private void setCells(Cell[][] c) {
		cells = c;
	}
	
	private void setProcessor() {
		if(simulationType.contentEquals("Conway"))
			processor = new ConwayProcessor();
		else if(simulationType.contentEquals("Fire"))
			processor = new FireProcessor();
		else if(simulationType.contentEquals("Segregation"))
			processor = new SegregationProcessor();
		/*else if(simulationType.contentEquals("WaTorWorld"))
			processor = new ConwayProcessor();*/
		else {
			System.out.println(String.format("Invalid simulation type %s\n", simulationType));
		}
	}
	
	private void step() {
		/* Set the cells to their next state */
		this.setCells(processor.processCells(cells));
		/* Display updated cells */
		display.updateCells(cells);
	}
	
	
}
