package cells;

import resources.PropertiesGetter;

import java.util.Properties;
import java.util.Random;

public class FireCell extends Cell {

    private enum State {
        BURNING,
        TREE,
        EMPTY
    }

    private double catchFireThreshold;

    private Random catchFireDecisionMaker = new Random();

    public FireCell(Object initialState) {
        catchFireThreshold = PropertiesGetter.getDefaultCatchFireProbability();
        super(initialState);
    }

    public void calculateNextState() {
        State currentState = (State) getCurrentState();
        if (currentState == State.TREE) {
            for (Cell neighbor : getNeighbors()) {
                if (neighbor.getCurrentState() == State.BURNING) {
                    setNextState(catchFireOrNot());
                }
            }
        } else if (currentState == State.BURNING) {
            setNextState(State.EMPTY);
        }
    }

    private State catchFireOrNot() {
        if (catchFireDecisionMaker.nextDouble() < catchFireThreshold) {
            return State.BURNING;
        } else {
            return State.TREE;
        }
    }

    public void setCatchFireProbability(double catchFireProbability) {
        this.catchFireProbability = catchFireProbability;
    }
}