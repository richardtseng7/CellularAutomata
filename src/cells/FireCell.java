package cells;

public class FireCell extends Cell {

    private enum State {
        BURNING,
        TREE,
        EMPTY
    }

    public FireCell(Object initialState) {
        super(initialState);
    }

    public void calculateNextState() {
        // fill will rule checking for Spreading of Fire
    }
}