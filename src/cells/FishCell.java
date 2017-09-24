package cells;

import utilities.PropertiesGetter;

/**
 * Cell used to represent Fish in the WaTor World simulation, including (by extension) Sharks.
 * State transition rules for Fish are:
 *      1. If there are N/S/E/W adjacent cells in the water state (that is, neighboring
 *         unoccupied cells), the fish randomly moves to one of these cells.
 *      2. If no adjacent cells are in the water state, the state does not change.
 *      3. When the fish moves, if it has survived a certain number of time steps (chronons), it
 *         may reproduce by leaving behind a cell in the fish state (as opposed to the usual
 *         empty state).
 *
 * @author Ben Schwennesen
 */
public class FishCell extends WaTorCell {

    private int chrononsNeededToReproduce;
    private int numberOfChrononsSurvivedSinceLastReproduction;

    public FishCell(State initialState) {
        super(initialState);
        setChrononsNeededToReproduce(PropertiesGetter.getDefaultChrononsNeededToReproduce());
        setNumberOfChrononsSurvivedSinceLastReproduction(0);
    }

    @Override
    public void calculateNextState() {
        incrementChrononsSurvived();
        setNextState(moveToEmptyNeighborIfPossible());
    }

    protected State moveToEmptyNeighborIfPossible() {
        int numberEmptyNeighbors = countNeighborsInState(State.WATER);
        if (numberEmptyNeighbors > 0) {
            return reproduceIfPossible();
        } else {
            return State.FISH;
        }
    }

    protected State reproduceIfPossible() {
        if (this.numberOfChrononsSurvivedSinceLastReproduction < chrononsNeededToReproduce) {
            return State.WATER;
        } else {
            this.numberOfChrononsSurvivedSinceLastReproduction = 0;
            // return a NEW shark or fish with default energy, zero chronons survived
            return State.valueOf(((State)getCurrentState()).name());
        }
    }

    /*
     * "Move" another cell into this cell by replacing this cell's status info with that of the
     * cell moving into it.
     *
     * @param neighbor - the cell moving into this one
     *
    public void assumeStatusOf(FishCell neighbor) {
        this.numberOfChrononsSurvivedSinceLastReproduction = neighbor
                .numberOfChrononsSurvivedSinceLastReproduction;
    }*/

    public void incrementChrononsSurvived() {
        numberOfChrononsSurvivedSinceLastReproduction++;
    }

    public void setChrononsNeededToReproduce(int chrononsNeededToReproduce) {
        this.chrononsNeededToReproduce = chrononsNeededToReproduce;
    }

    public void setNumberOfChrononsSurvivedSinceLastReproduction(int chrononsSurvived) {
        this.numberOfChrononsSurvivedSinceLastReproduction = chrononsSurvived;
    }
}
