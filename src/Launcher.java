
import Display.Display;
import cells.Cell;
import cells.ConwayCell;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

	private enum test {LIVE, DEAD};
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		new Display(primaryStage, 60, 60, null, "Test");
	}

	/**
	 * Start the program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
