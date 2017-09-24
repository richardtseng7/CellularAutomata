package cells;

/**
 * Cell used in Conway's Game of Life simulation. The cell has only two states -- dead and
 * live. State transition rules for the cell: (pulled from Wikipedia):
 *      1. A live cell with less than two live neighbors dies (underpopulation).
 *      2. A live cell with more than three live neighbors dies (overpopulation).
 *      3. A dead cell with exactly three live neighbors reanimates (reproduction).
 *      4. Otherwise, the cell remains in the same state.
 *
 * @author Ben Schwennesen
 */
public class ConwayCell extends Cell {

    public enum State {
        DEAD,
        LIVE
    }

    public ConwayCell(State initialState) {
        super(initialState);
    }

    public void calculateNextState() {
        int liveNeighborCount = countNeighborsInState(State.LIVE);
        if (getCurrentState() == State.LIVE && (liveNeighborCount < 2 || liveNeighborCount > 3)) {
            setNextState(State.DEAD);
        } else if (getCurrentState() == State.DEAD && liveNeighborCount == 3) {
            setNextState(State.LIVE);
        } else {
            setNextState(getCurrentState());
        }
    }

    /* FOR TESTING

    public static void main(String[] args) {
        ConwayCell test = new ConwayCell(State.LIVE);
        test.addNeighbors(new ConwayCell(State.DEAD));
        test.calculateNextState();
        System.out.println(test.getCurrentState() + " " + test.getNextState());
    } */
}