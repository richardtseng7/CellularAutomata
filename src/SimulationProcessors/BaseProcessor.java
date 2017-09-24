package SimulationProcessors;
import cells.Cell;

public abstract class BaseProcessor {
	
	/** Array of the current state of each cell */
	protected Cell[][] currentCells;
	
	/** 
	 * Default constructor is empty
	 * */
	public BaseProcessor() {
		
	}
	
	/**
	 * Calls the method necessary to update the cell to its next state
	 * 
	 * @param cells 2D array of cells representing the previous state of the cells
	 * @return the updated state of each cell
	 * */
	public Cell[][] processCells(Cell[][] cells) {
		currentCells = cells;
		updateCellStates();
		transitionCellStates();
		return currentCells;
	}
	
	/**
	 * Abstract method that will be defined in each child class. Updates the nextState in each individual
	 * cell.
	 * 
	 * 
	 * */
	public abstract void updateCellStates();
	 
	/**
	 * Transitions each cell to it next state as defined within the cell.
	 * */
	public void transitionCellStates(){
		for(int row=0;row<currentCells.length;row++) {
			for(int col=0;col<currentCells.length;col++) {
				currentCells[row][col].transitionState();
			}
		}
	}

}
