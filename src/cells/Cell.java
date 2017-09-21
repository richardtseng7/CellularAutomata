package cells;

import javafx.geometry.Point2D;

import java.util.List;

public abstract class Cell {

    private final List<Cell> neighbors;
    /* ints come from X<ML for each simulation type?? */
    private final Point2D gridLocation;
    private int currentState;
    private int nextState;

    public Cell(int x, int y) {
        neighbors = null;
        gridLocation = new Point2D(x, y);
    }

    public abstract void updateState() ;

}