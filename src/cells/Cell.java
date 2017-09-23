package cells;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract cell object extended once for each simulation implemented. The purpose of the class
 * is to store the state that each cell in a simulation is in, as well as to store, calculate,
 * and transition to the *next* state the cell should be in. The next state the cell should be in
 * is determined according to the rules of each individual simulation; that is, the logic for
 * calculating the next state is implemented in each subclass.
 *
 * @author Ben Schwennesen
 */
public abstract class Cell {

    /**
     * Stores a list of this cells neighbors. Depending on the location of this cell, neighbors
     * will have a size of 3 (in corner), 5 (along side), or 8 (in center).
     */
    private final List<Cell> neighbors;

    /**
     * Enum object representing the state this cell is in at the current step of the simulation.
     */
    private Object currentState;

    /**
     * Enum object representing the state this cell should be in the next step of the simulation,
     * after calling transitionState().
     */
    private Object nextState;

    /**
     * Construct a new cell: instantiate the neighbors list. The neighbors list cannot be
     * populated here since all the cells in the simulation must be created first.
     */
    public Cell(Object initialState) {
        neighbors = new ArrayList<>();
        this.currentState = initialState;
    }

    /**
     * Calculate the next state of this cell, as determined by the rules for each type of
     * simulation. The method will analyze the states of the cell's neighboring cells, stored in
     * the field 'neighbors', and will determine the state the cell should transition to when
     * transitionState() is called.
     */
    public abstract void calculateNextState();

    /**
     * Update the state of this cell after 'nextState' has been calculated for each cell. This
     * must be done separately from calculateNextState(), since we need to calculate the next
     * state each cell will be in before we transition them, otherwise the updated state for some
     * cells would be used in the calculation of other.
     */
    public void transitionState() {
        currentState = nextState;
    }

    /**
     * Get a count of how many adjacent cells are in a particular state.
     *
     * @param state - the enum Object representing the state of which a neighbor count is needed
     * @return the number of neighbors of this cell currently in the passed state
     */
    public int countNeighborsInState(Enum state) {
        int stateCount = 0;
        for (Cell neighbor : getNeighbors()) {
            if (neighbor.getCurrentState() == state) {
                stateCount++;
            }
        }
        return stateCount;
    }

    /**
     * Once all cells have been created, each cell stores its own neighbors so that it can perform
     * the own calculation of its next state, according to the rules of the current simulation.
     *
     * @param adjacentCells - some number of cells adjacent to this cell in the on-screen grid;
     *                      there should be either 2, 3, 4, 5, or 8 adjacent cells (uses varargs)
     */
    public void addNeighbors(Cell... adjacentCells) {
        for (Cell neighbor : adjacentCells) {
            neighbors.add(neighbor);
        }
    }

    /**
     * Get the list of neighbors of this cell. Used by subclasses when calculating their next
     * states according to the rules of the extending cell's simulation.
     *
     * @return an list of cells adjacent to this cell in the on-screen grid
     */
    public List<Cell> getNeighbors() {
        return neighbors;
    }

    /**
     * Get the number of neighbors of this cell.
     *
     * @return an integer representing the number of cells adjacent to this cell in the grid
     */
    public int getNumberOfNeighbors() {
        return neighbors.size();
    }

    /**
     * Get the current state of this cell. Used for determining the next state of this cell's
     * neighbors, according to the rules of the extending cell's simulation.
     *
     * @return an enum Object representing the current state of this cell
     */
    public Object getCurrentState() {
        return currentState;
    }

    /**
     * Get the next state of the cell.
     *
     * @return nextState - the enum Object representing the state the cell should transition to at
     *                  the next step of the simulation
     */
    public Object getNextState() { return nextState; }

    /**
     * Set the next state of the cell.
     *
     * @param nextState - the enum Object representing the state the cell should transition to at
     *                  the next step of the simulation
     */
    public void setNextState(Object nextState) {
        this.nextState = nextState;
    }




}