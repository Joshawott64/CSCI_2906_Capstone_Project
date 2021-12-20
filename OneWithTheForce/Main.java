import java.net.URISyntaxException;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Main extends Application {
	// Scenes
	Scene mainMenuScene, optionsScene, smithyMenuScene;
	
	@Override
	public void start(Stage primaryStage) throws URISyntaxException {
		/* MAIN MENU */
		FlowPane mainFlowPane = new FlowPane(Orientation.VERTICAL);
		mainFlowPane.setAlignment(Pos.BASELINE_CENTER);
		mainFlowPane.setVgap(150);
		VBox mainTitleBox = new VBox();
		mainTitleBox.setAlignment(Pos.CENTER);
		mainTitleBox.getChildren().add(new Text("One With The Force"));
		
		// Navigate to start
		Button btStart = new Button("Start");
		btStart.setOnAction(e -> System.out.println("E"));
		
		// Navigate to options
		Button btOptions = new Button("Options");
		
		// Quit game
		Button btQuit = new Button("Quit");
		btQuit.setOnAction(e -> System.exit(0));
		
		VBox mainButtonBox = new VBox(20);
		mainButtonBox.setAlignment(Pos.CENTER);
		mainButtonBox.getChildren().addAll(btStart, btOptions, btQuit);
		
		mainFlowPane.getChildren().addAll(mainTitleBox, mainButtonBox);
		
		// Set primaryStage
		mainMenuScene = new Scene(mainFlowPane, 1280, 720);
		primaryStage.setScene(mainMenuScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
