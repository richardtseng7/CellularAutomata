package CellProcessing;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import cells.Cell;

public class SegregationProcessor extends BaseProcessor{
	ArrayList<Point2D> emptyCells= new ArrayList<>();
	public SegregationProcessor(Cell[][]cells) {
		initializeEmptyCells(cells);
	}
	
	@Override
	public void updateCellStates(Cell[][] cells) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transitionCellStates(Cell[][] cells) {
		// TODO Auto-generated method stub
		
	}
	
	private void initializeEmptyCells(Cell[][] cells) {
		for(int row=0;row<cells.length;row++) {
			for(int col=0;col<cells[row].length;col++) {
				/*if(cells[row][col].getCurrentState()==State.EM)
				emptyCells.add(new Point2D.Double(row,col));*/
			}
		}
	}

}
