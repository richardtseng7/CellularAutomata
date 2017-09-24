package CellProcessing;

import cells.Cell;

public class FireProcessor extends BaseProcessor{

	public void processCells(Cell[][] cells) {
		// TODO Auto-generated method stub
		
	}

	public void updateCellStates(Cell[][] cells) {
		for(int row=0;row<cells.length;row++) {
			for(int col=0;col<cells[row].length;col++) {
				cells[row][col].calculateNextState();
			}
		}
		
	}

}
