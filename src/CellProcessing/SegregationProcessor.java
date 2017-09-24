package CellProcessing;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import cells.Cell;
import cells.SegregationCell.State;

public class SegregationProcessor extends BaseProcessor{
	ArrayList<Point2D> emptyCells= new ArrayList<>();
	
	public SegregationProcessor(Cell[][]cells) {
		initializeEmptyCells(cells);
	}
	
	public void updateCellStates(Cell[][] cells) {
		for(int row=0;row<cells.length;row++) {
			for(int col=0;col<cells[row].length;col++) {
				Cell curCell = cells[row][col];
				curCell.calculateNextState();
				if(curCell.getCurrentState()!=State.EMPTY && curCell.getNextState()==State.EMPTY){
					Point2D randomLoc = emptyCells.get(getRandomIndex());
					cells[(int)randomLoc.getX()][(int)randomLoc.getY()].setNextState(curCell.getCurrentState());
				}
			}
		}
	}
	
	private void initializeEmptyCells(Cell[][] cells) {
		for(int row=0;row<cells.length;row++) {
			for(int col=0;col<cells[row].length;col++) {
				if(cells[row][col].getCurrentState()==State.EMPTY)
					emptyCells.add(new Point2D.Double(row,col));
			}
		}
	}
	
	private int getRandomIndex() {
		Random rand = new Random();
		return rand.nextInt(emptyCells.size());
	}
}
