package cells;

import resources.PropertiesGetter;

/**
 * Cell used in the WaTor World (also known as predator-prey) simulation. State transition rules for
 * the cell:
 *      1. If the cell is in the fish state (that is, it's occupied by a fish):
 *          a. If there are N/S/E/W adjacent cells in the water state (that is, neighboring
 *             unoccupied cells), the fish randomly moves to one of these cells.
 *          b. If no adjacent cells are in the water state, the state does not change.
 *          c. When the fish moves, if it has survived a certain number of time steps (chronons), it
 *             may reproduce by leaving behind a cell in the fish state (as opposed to the usual
 *             empty state).
 *      2. If the cell is in the shark state:
 *          a. If there are N/S/E/W adjacent cells in the fish state, the shark randomly moves to
 *             one of these cells and devours the fish. Shark cells gain a certain amount of
 *             energy when they devour fish.
 *          b. If there are no adjacent cells in the fish state, the shark moves to a random
 *             N/S/E/W adjacent cell in the empty state.
 *          c. If no N/S/E/W adjacent cell is in either the fish or water states, the shark stays
 *             put.
 *          d. As with the fish, if the shark has survived a certain number of time steps
 *             (chronons), it may reproduce by leaving behind a cell in the shark state (as opposed
 *             to the usual empty state).
 *          e. If the shark cell runs out of energy, it dies and leaves an empty cell behind.
 *      3. If the cell is in the water state, it only changes states if a shark or fish moves
 *         into it.
 *
 * @author Ben Schwennesen
 */
public class WaTorCell extends Cell {

    private enum State {
        FISH,
        SHARK,
        WATER;

        private int numberOfChrononsSurvivedSinceLastReproduction = 0;
        private int energyLossPerChronon = PropertiesGetter.getDefaultEnergyLossPerChronon();
        private int energyGainPerFish = PropertiesGetter.getDefaultEnergyGainPerFish();
        private int energyLevel = PropertiesGetter.getSharkDefaultInitialEnergyLevel();

        private void incrementChrononsSurvived() {
            numberOfChrononsSurvivedSinceLastReproduction++;
        }

        /** lose a certain amount of energy each chronon a shark doesn't devour a fish */
        private void loseEnergy() {
            energyLevel -= energyLossPerChronon;
        }

        /** gain a certain amount of energy each time a shark devours a fish */
        private void gainEnergy() {
            energyLevel += energyGainPerFish;
        }
    }


    private int chrononsNeededToReproduce;


    public WaTorCell(Object initialState, int chrononsNeededToReproduce) {
        super(initialState);
        setChrononsNeededToReproduce(chrononsNeededToReproduce);
    }

    public void calculateNextState() {
        State currentState = (State) getCurrentState();
        if (currentState == State.FISH) {
            setNextState(determineNextStateForFish(currentState));
        } else if (currentState == State.SHARK) {
            setNextState(determineNextStateForShark(currentState));
        }
    }

    private State determineNextStateForFish(State fish) {
        fish.incrementChrononsSurvived();
        return moveToEmptyNeighborIfPossible(fish);
    }

    private State determineNextStateForShark(State shark) {
        shark.incrementChrononsSurvived();
        int numberFishNeighbors = countNeighborsInState(State.FISH);
        if (numberFishNeighbors > 0) {
            shark.gainEnergy();
            return reproduceIfPossible(shark);
        } else {
            shark.loseEnergy();
            if (shark.energyLevel <= 0) {
                return State.WATER;
            }
            return moveToEmptyNeighborIfPossible(shark);
        }
    }

    private State moveToEmptyNeighborIfPossible(State animal) {
        int numberEmptyNeighbors = countNeighborsInState(State.WATER);
        if (numberEmptyNeighbors > 0) {
            return reproduceIfPossible(animal);
        } else {
            return animal;
        }
    }

    private State reproduceIfPossible(State animal) {
        if (animal.numberOfChrononsSurvivedSinceLastReproduction < chrononsNeededToReproduce) {
            return State.WATER;
        } else {
            animal.numberOfChrononsSurvivedSinceLastReproduction = 0;
            // return a NEW shark or fish with default energy, zero chronons survived
            return State.valueOf(animal.name());
        }
    }

    public void setChrononsNeededToReproduce(int chrononsNeededToReproduce) {
        this.chrononsNeededToReproduce = chrononsNeededToReproduce;
    }

    /* FOR TESTING

    public static void main(String[] args) {
        WaTorCell test = new WaTorCell(State.SHARK, 5);
        test.addNeighbors(new WaTorCell(State.SHARK, 5), new WaTorCell(State.FISH, 5));
        test.calculateNextState();
        System.out.println(test.getCurrentState() + " " + test.getNextState());
    } */
}