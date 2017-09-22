package cells;

import resources.PropertiesGetter;

/**
 * The cell used in Conway's Game of Life simulation. This cell has only two states -- dead and
 * live. Rules this cell follows in transitioning states (pulled from Wikipedia):
 *      1. A live cell with less than two live neighbors dies (underpopulation).
 *      2. A live cell with more than three live neighbors dies (overpopulation).
 *      3. A dead cell with exactly three live neighbors reanimates (reproduction).
 *      4. Otherwise, the cell remains in the same state.
 *
 * @author Ben Schwennesen
 */
public class ConwayCell extends Cell {

    /** Dead and live are the two types of states in the Game of Life */
    private enum States {
        DEAD,
        LIVE
    }

    /**
     * Construct a Game of Life/Conway cell. Conway cells do not require extra instantiation logic
     * aside from that performed in the basic abstract cell.
     */
    public ConwayCell(Object initialState) {
        super(initialState);
    }

    /**
     * Determine the next state for the cell according to the rules of the Game of Life
     * simulation and the states of the cell's neighbors.
     */
    public void calculateNextState() {
        int liveNeighborCount = countNeighborsInState(States.LIVE);
        if (getCurrentState() == States.LIVE && (liveNeighborCount < 2 || liveNeighborCount > 3)) {
            setNextState(States.DEAD);
        } else if (getCurrentState() == States.DEAD && liveNeighborCount == 3) {
            setNextState(States.LIVE);
        }
        // otherwise, next state is already equal to current state, hence no need to update it
    }
}