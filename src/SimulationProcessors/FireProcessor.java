package SimulationProcessors;

public class FireProcessor extends BaseProcessor{

	/** Sets the next state of all fire cells */
	public void updateCellStates() {
		for(int row=0;row<currentCells.length;row++) {
			for(int col=0;col<currentCells[row].length;col++) {
				currentCells[row][col].calculateNextState();
			}
		}
	}

}
