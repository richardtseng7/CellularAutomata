package SimulationProcessors;
import cells.Cell;

public abstract class BaseProcessor {
	
	public void processCells(Cell[][] cells) {
		updateCellStates(cells);
		transitionCellStates(cells);
	}
	
	public abstract void updateCellStates(Cell[][] cells);
	 
	public abstract void transitionCellStates(Cell[][] cells);

}
