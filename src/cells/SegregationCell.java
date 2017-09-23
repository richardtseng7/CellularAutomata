package cells;

/**
 * The cell used in Schelling's Model of Segregation, as described at
 * http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/.
 */
public class SegregationCell extends Cell {

    private enum State {
        BLUE,
        RED,
        EMPTY
    }

    private double satisfactionThreshold;

    public SegregationCell(Object initialState, double satisfactionThreshold) {
        super(initialState);
        setSatisfactionThreshold(satisfactionThreshold);
    }

    public void calculateNextState() {
        State currentState = (State) getCurrentState();
        if (currentState == State.EMPTY) {
            return;
        }
        int numberOfSameStateNeighbors = countNeighborsInState(currentState);
        int numberOfNeighbors = getNumberOfNeighbors();
        double percentageOfSameNeighbors = numberOfSameStateNeighbors / numberOfNeighbors;
        if (percentageOfSameNeighbors < satisfactionThreshold) {
            setNextState(State.EMPTY);
        }
    }
    
    public void setSatisfactionThreshold(double satisfactionThreshold) {
        this.satisfactionThreshold = satisfactionThreshold;
    }
}