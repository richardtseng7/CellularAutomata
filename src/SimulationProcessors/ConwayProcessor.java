package SimulationProcessors;


public class ConwayProcessor extends BaseProcessor{
	
	/**
	 * Calculates the next state of all Conway Cells
	 * */
	public void updateCellStates() {
		for(int row=0;row<currentCells.length;row++) {
			for(int col=0;col<currentCells[row].length;col++) {
				currentCells[row][col].calculateNextState();
			}
		}
		
	}
}
