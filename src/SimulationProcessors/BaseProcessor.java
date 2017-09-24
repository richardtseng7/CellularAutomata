package SimulationProcessors;

import cells.Cell;

public abstract class BaseProcessor {
	
	public void processCells(Cell[][]cells) {
		updateCellStates(cells);
		transitionCells(cells);
	}

	abstract public void transitionCells(Cell[][] cells);

	abstract public void updateCellStates(Cell[][] cells);
}
