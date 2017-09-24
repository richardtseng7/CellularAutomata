package SimulationProcessors;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import cells.Cell;
import cells.SegregationCell.State;

public class SegregationProcessor extends BaseProcessor{
	/* All empty cells currently in the grid */	
	private ArrayList<Point2D> emptyCells;
	
	/** Sets the next state of each cell to the appropriate next state as defined by the SegregationCell rules.
	 * 
	 * <p>
	 * This method handles the case when a cell needs to "change" locations. In order to test if this happened, the current state
	 * of a cell must not be empty and it's next state must be empty. When this happens the cells is "moved" to a next state.
	 * 
	 * */
	public void updateCellStates() {
		initializeEmptyCells();
		for(int row=0;row<currentCells.length;row++) {
			for(int col=0;col<currentCells[row].length;col++) {
				Cell curCell = currentCells[row][col];
				curCell.calculateNextState();
				if(curCell.getCurrentState()!=State.EMPTY && curCell.getNextState()==State.EMPTY){
					switchCellToRandomLoc(curCell.getCurrentState());
				}
			}
		}
	}
	
	/**
	 * Finds all the empty cells in the grid and populate their locations in the 
	 * emptyCells ArrayList.
	 * 
	 * */
	private void initializeEmptyCells() {
		/* Reinitialize emptyCells */
		emptyCells = new ArrayList<Point2D>();
		/* Loop through all cells */
		for(int row=0;row<currentCells.length;row++) {
			for(int col=0;col<currentCells[row].length;col++) {
				if(currentCells[row][col].getCurrentState()==State.EMPTY) // if the currentState of the cell is empty
					emptyCells.add(new Point2D.Double(row,col)); // add cell to emptyCells list
			}
		}
	}
	
	/**
	 * Sets the next state of a previously empty cell to the current state
	 * of a cell that needs to move.
	 * 
	 * @param newState New state that will be designated to the previously empty cell.
	 *       		   This should also be the current state of a cell that needs to switch locations.
	 * */
	private void switchCellToRandomLoc(Object newState) {
		/* Get random location */
		Point2D randomLoc = getRandomPoint();
		/* set the random location's next state to that of old cell's current state*/
		currentCells[(int)randomLoc.getX()][(int)randomLoc.getY()].setNextState(newState);
		/* remove the previously empty cell from the list because it is no longer empty */
		emptyCells.remove(randomLoc);
	}
	
	/**
	 * Get a random location from the currently empty cells
	 * 
	 * @return A random, empty location
	 * */
	private Point2D getRandomPoint() {
		return emptyCells.get(getRandomIndex());
	}
	
	/**
	 * Get a random index of currently empty cells
	 * 
	 * @return a random index within the size of the empty cell array
	 * */
	private int getRandomIndex() {
		Random rand = new Random();
		return rand.nextInt(emptyCells.size());
	}
}
