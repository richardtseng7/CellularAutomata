package Display;

import java.awt.Dimension;
import java.util.List;
import java.util.ResourceBundle;

import cells.Cell;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Display {

	// basic setup
	private static final Dimension DEFAULT_SIZE = new Dimension(600, 800);
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
	private static final double GRID_WIDTH_PERCENT = .85;

	// hold the number of rows and colums for the output
	private int numRows = 0, numCols = 0;

	// get strings from resource file
	private ResourceBundle myResources;

	// grid to hold Cells
	GridPane grid;
	
	//list of scene elements
	private Scene scene;
	private ObservableList<Node> sceneList;

	public Display(Stage s) {
		this(s, 0, 0, null, "");
	}

	/**
	 * 
	 * @param rowSpan
	 *            - number of rows
	 * @param columnSpan
	 *            - number of sliders
	 * @param sliders
	 *            - list of sliders needed for the current simulation
	 * @param simName
	 *            - name of the current simulation
	 */
	public Display(Stage s, int rowSpan, int columnSpan, List<Object> sliders, String simName) {
		try
		{
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + simName);
		}
		catch (Exception e)
		{
			myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "default");
		}
		
		//store initial grid size
		numRows = rowSpan;
		numCols = columnSpan;
		
		setupGrid();
		
		//setup scene elements
		Group root = new Group();
		scene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height, Color.WHITE);
		sceneList = root.getChildren();
		s.setScene(scene);
		s.setTitle(simName);
		s.setResizable(false);
		s.show();
		sceneList.add(grid);
		
	}

	private void setupGrid() {
		grid = new GridPane();
		grid.setPrefSize(GRID_WIDTH_PERCENT * DEFAULT_SIZE.width, GRID_WIDTH_PERCENT * DEFAULT_SIZE.width);
		grid.setGridLinesVisible(true);
		for (int i = 0; i < numCols; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / numCols);
			grid.getColumnConstraints().add(colConst);
		}
		for (int i = 0; i < numRows; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / numRows);
			grid.getRowConstraints().add(rowConst);
		}
		for(int i=0; i<numCols; i++) {
			for(int j=0; j<numRows; j++) {
				grid.add(new Rectangle(), j, i);
			}
		}
	}

	/**
	 * 
	 * @param cellArray
	 *            - 2d array of cells
	 */
	public void updateCells(Cell[][] cellArray) {
		Color color;
		String status;
		// iterate over the 2d array of cells
		for(Node n : grid.getChildren()) {
			try {
				Rectangle r = (Rectangle)n; //cast the node to a rectangle
				status = ((Enum) (cellArray[grid.getRowIndex(n)][grid.getColumnIndex(n)].getCurrentState())).name(); 	// get the current status from the
																														// cell
				color = Color.web(myResources.getString(status)); 				// set color to the color specified in the
																				// resources file
				r.setFill(color);
			}
			catch (ClassCastException e)		//failed one of the casts
			{
				System.out.println(n.getClass().toString());
				System.out.println("\n");
			}
			catch (IndexOutOfBoundsException e)
			{
				System.out.println(String.format("Index %d %d out of bounds\n", grid.getRowIndex(n), grid.getColumnIndex(n)));
			}
		}
	}
}
