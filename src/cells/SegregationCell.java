package cells;

import resources.PropertiesGetter;

/**
 * Cell used in Schelling's Model of Segregation, as described at
 * http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/. State transition rules for
 * the cell:
 *      1. A cell in the RED (BLUE) state moves to the location of any cell in the grid currently
 *         in the empty state if the ratio of its neighbors that are also RED (BLUE) is less than
 *         the satisfaction threshold.
 *          - The 'movement' of a cell is implemented by swapping the states of the RED (BLUE)
 *            cell with the state of the empty cell.
 *      2. Otherwise, if the ratio of its neighbors in the same state is greater than the
 *         satisfaction threshold, its state does not change.
 *
 * @author Ben Schwennesen
 */
public class SegregationCell extends Cell {

    private enum State {
        BLUE,
        RED,
        EMPTY
    }

    private double satisfactionThreshold;

    public SegregationCell(Object initialState) {
        super(initialState);
        //this.setSatisfactionThreshold(0.5);
        setSatisfactionThreshold(PropertiesGetter.getDefaultSatisfactionThreshold());
    }

    public void calculateNextState() {
        State currentState = (State) getCurrentState();
        if (currentState == State.EMPTY) {
            return;
        }
        int numberOfSameStateNeighbors = countNeighborsInState(currentState);
        int numberOfNeighbors = getNumberOfNeighbors();
        double neighborSimilarityRatio = numberOfSameStateNeighbors / numberOfNeighbors;
        if (neighborSimilarityRatio < satisfactionThreshold) {
            setNextState(State.EMPTY);
        }
    }
    
    public void setSatisfactionThreshold(double satisfactionThreshold) {
        this.satisfactionThreshold = satisfactionThreshold;
    }

   //  FOR TESTING

    public static void main(String[] args) {
        //SegregationCell test = new SegregationCell(State.BLUE);
        /*test.addNeighbors(new SegregationCell(State.RED), new SegregationCell(State.RED));
        test.calculateNextState();
        System.out.println(test.getCurrentState() + " " + test.getNextState());*/
    	//System.out.println(test.getCurrentState().getClass());
    } 
}