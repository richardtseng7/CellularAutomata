package cells;

import utilities.PropertiesGetter;

/**
 * Cell used to represent Sharks in the WaTor World simulation. Since Fish and Sharks share
 * reproductive behavior, Shark extends Fish. Rules
 *      1. If there are N/S/E/W adjacent cells in the fish state, the shark randomly moves to one
 *         of these cells and devours the fish. Shark cells gain a certain amount of energy when
 *         they devour fish.
 *      2. If there are no adjacent cells in the fish state, the shark moves to a random N/S/E/W
 *         adjacent cell in the empty state.
 *      3. If no N/S/E/W adjacent cell is in either the fish or water states, the shark stays put.
 *      4. As with the fish, if the shark has survived a certain number of time steps (chronons),
 *         it may reproduce by leaving behind a cell in the shark state (as opposed to the usual
 *         empty state).
 *      5. If the shark cell runs out of energy, it dies and leaves an empty cell behind.
 *
 * @author Ben Schwennesen
 */
public class SharkCell extends FishCell {

    /*
     * Energy loss per chronon and energy gain per fish are not final since we might want to add
     * sliders to adjust them
     */
    private int energyLossPerChronon;
    private int energyGainPerFish;
    private int energyLevel;

    public SharkCell(State initialState) {
        super(initialState);
        setEnergyLossPerChronon(PropertiesGetter.getDefaultEnergyLossPerChronon());
        setEnergyGainPerFish(PropertiesGetter.getDefaultEnergyGainPerFish());
        setEnergyLevel(PropertiesGetter.getSharkDefaultInitialEnergyLevel());
    }

    @Override
    public void calculateNextState() {
        int numberFishNeighbors = countNeighborsInState(State.FISH);
        if (numberFishNeighbors > 0) {
            this.gainEnergy();
            incrementChrononsSurvived();
            setNextState(reproduceIfPossible());
        } else {
            this.loseEnergy();
            if (this.energyLevel <= 0) {
                setNextState(State.WATER);
            }
            // if there are no fish around a shark it behaves like a fish
            super.calculateNextState();
        }
    }

    /** Lose a certain amount of energy each chronon a shark doesn't devour a fish */
    private void loseEnergy() {
        energyLevel -= energyLossPerChronon;
    }

    /** Gain a certain amount of energy each time a shark devours a fish */
    private void gainEnergy() {
        energyLevel += energyGainPerFish;
    }

    public void setEnergyLossPerChronon(int energyLossPerChronon) {
        this.energyLossPerChronon = energyLossPerChronon;
    }

    public void setEnergyGainPerFish(int energyGainPerFish) {
        this.energyGainPerFish = energyGainPerFish;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }
}
