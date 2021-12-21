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
	Scene mainMenuScene, optionsScene, startMenuScene, levelSelectScene;
	
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
		btStart.setOnAction(e -> primaryStage.setScene(startMenuScene));
		
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
		
		
		/* START MENU */
		FlowPane startFlowPane = new FlowPane(Orientation.VERTICAL);
		startFlowPane.setAlignment(Pos.BASELINE_CENTER);
		startFlowPane.setVgap(150);
		VBox startTitleBox = new VBox();
		startTitleBox.setAlignment(Pos.CENTER);
		startTitleBox.getChildren().add(new Text("Select One"));
		
		// Navigate to levels
		Button btLevels = new Button("Levels");
		btLevels.setOnAction(e -> primaryStage.setScene(levelSelectScene));
		
		// Navigate to Sabersmithy
		Button btSmithy = new Button("Sabersmithy");
		
		// Navigate to main menu
		Button btBack = new Button ("Back");
		btBack.setOnAction(e -> primaryStage.setScene(mainMenuScene));
		
		VBox startButtonBox = new VBox(20);
		startButtonBox.setAlignment(Pos.CENTER);
		startButtonBox.getChildren().addAll(btLevels, btSmithy, btBack);
		
		startFlowPane.getChildren().addAll(startTitleBox, startButtonBox);
		
		// Set scene
		startMenuScene = new Scene(startFlowPane, 1280, 720);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
