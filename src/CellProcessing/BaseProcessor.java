package CellProcessing;
import cells.Cell;

public abstract class BaseProcessor {
	
	public void processCells(Cell[][] cells) {
		updateCellStates(cells);
		transitionCellStates(cells);
	}
	
	public abstract void updateCellStates(Cell[][] cells);
	 
	public void transitionCellStates(Cell[][] cells){
		for(int row=0;row<cells.length;row++) {
			for(int col=0;col<cells.length;col++) {
				cells[row][col].transitionState();
			}
		}
	}

}
