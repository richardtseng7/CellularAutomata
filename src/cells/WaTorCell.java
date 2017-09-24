package cells;

import utilities.PropertiesGetter;

/**
 * Cell used in the WaTor World (also known as predator-prey) simulation. The class is only
 * used to represent cells in the Water/Empty state. Cells in the Fish or Shark states must
 * use the extended classes FishCell or SharkCell. State transition rules for the Water state:
 *      1. If the cell is in the water state, it only changes states if a shark or fish moves
 *         into it.
 *
 * @author Ben Schwennesen
 */
public class WaTorCell extends Cell {

    public enum State {
        FISH,
        SHARK,
        WATER;
    }


    public WaTorCell(State initialState) {
        super(initialState);
    }

    /**
     * Determine the next state of this cell. Overridden by the extending classes FishCell and
     * SharkCell.
     */
    public void calculateNextState() {
        // only state the non-overridden method is used on is Water
        setNextState(getCurrentState());
    }
}