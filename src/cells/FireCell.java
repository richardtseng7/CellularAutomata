package cells;

import utilities.PropertiesGetter;

import java.util.Random;

/**
 * Cell used in the spreading-fire simulation described at
 * http://nifty.stanford.edu/2007/shiflet-fire/. State transition rules for the cell:
 *      1. An empty cell remains empty at all future stages.
 *      2. A cell in the burning state tranisitions to the empty state.
 *      3. A cell in the tree state with N/S/W/E adjacent cells in the burning state transitions
 *         to the burning state with probability 'catchFireThreshold', otherwise it remains in the
 *         tree state.
 *
 * @author Ben Schwennesen
 */
public class FireCell extends Cell {

    public enum State {
        BURNING,
        TREE,
        EMPTY
    }

    private double catchFireThreshold;

    private Random catchFireDecisionMaker = new Random();

    public FireCell(State initialState) {
        super(initialState);
        setCatchFireProbability(PropertiesGetter.getDefaultCatchFireProbability());
    }

    public void calculateNextState() {
        State currentState = (State) getCurrentState();
        if (currentState == State.TREE) {
            for (Cell neighbor : getNeighbors()) {
                if (neighbor.getCurrentState() == State.BURNING) {
                    setNextState(catchFireOrNot());
                    break;
                }
            }
        } else if (currentState == State.BURNING) {
            setNextState(State.EMPTY);
        } else {
            // current state is EMPTY
            setNextState(currentState);
        }
    }

    private State catchFireOrNot() {
        if (catchFireDecisionMaker.nextDouble() < catchFireThreshold) {
            return State.BURNING;
        } else {
            return State.TREE;
        }
    }

    public void setCatchFireProbability(double catchFireThreshold) {
        this.catchFireThreshold = catchFireThreshold;
    }

    /* FOR TESTING

    public static void main(String[] args) {
        FireCell test1 = new FireCell(State.TREE);
        FireCell test2 = new FireCell(State.BURNING);
        test1.setCatchFireProbability(1.0);
        test1.addNeighbors(test2);

        test1.calculateNextState();
        System.out.println(test1.getCurrentState() + " " + test1.getNextState());
    } */
}