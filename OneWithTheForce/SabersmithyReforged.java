import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.util.*;

public class SabersmithyReforged extends Application {
	// Scenes
	Scene smithyMenuScene, forgeScene, galleryScene;
	// Import saber parts
	Image anakinEmitter = new Image("/Saber Parts/AnakinEmitter.png");
	Image anakinGuard = new Image("/Saber Parts/AnakinGuard.png");
	Image anakinSwitch = new Image("/Saber Parts/AnakinSwitch.png");
	Image anakinPommel = new Image("/Saber Parts/AnakinPommel.png");
	Image ahsokaEmitter = new Image("/Saber Parts/AhsokaEmitter.png");
	Image ahsokaGuard = new Image("/Saber Parts/AhsokaGuard.png");
	Image ahsokaSwitch = new Image("/Saber Parts/AhsokaSwitch.png");
	Image ahsokaPommel = new Image("/Saber Parts/AhsokaPommel.png");
	Image kalEmitter = new Image("/Saber Parts/KalEmitter.png");
	Image kalGuard = new Image("/Saber Parts/KalGuard.png");
	Image kalSwitch = new Image("/Saber Parts/KalSwitch.png");
	Image kalPommel = new Image("/Saber Parts/KalPommel.png");
	Image lukeEmitter = new Image("/Saber Parts/LukeEmitter.png");
	Image lukeGuard = new Image("/Saber Parts/LukeGuard.png");
	Image lukeSwitch = new Image("/Saber Parts/LukeSwitch.png");
	Image lukePommel = new Image("/Saber Parts/LukePommel.png");
	Image vaderEmitter = new Image("/Saber Parts/VaderEmitter.png");
	Image vaderGuard = new Image("/Saber Parts/VaderGuard.png");
	Image vaderSwitch = new Image("/Saber Parts/VaderSwitch.png");
	Image vaderPommel = new Image("/Saber Parts/VaderPommel.png");
	Image origEmitter1 = new Image("/Saber Parts/OrigEmitter1.png");
	Image origGuard1 = new Image("/Saber Parts/OrigGuard1.png");
	Image origSwitch1 = new Image("/Saber Parts/OrigSwitch1.png");
	Image origPommel1 = new Image("/Saber Parts/OrigPommel1.png");
			
	// Import colored emitters
	Image anakinBlack = new Image("/Colored Emitters/AnakinEmitter/AnakinBlack.png");
	Image anakinBlue = new Image("/Colored Emitters/AnakinEmitter/AnakinBlue.png");
	Image anakinGreen = new Image("/Colored Emitters/AnakinEmitter/AnakinGreen.png");
	Image anakinOrange = new Image("/Colored Emitters/AnakinEmitter/AnakinOrange.png");
	Image anakinPurple = new Image("/Colored Emitters/AnakinEmitter/AnakinPurple.png");
	Image anakinRed = new Image("/Colored Emitters/AnakinEmitter/AnakinRed.png");
	Image anakinSilver = new Image("/Colored Emitters/AnakinEmitter/AnakinSilver.png");
	Image anakinYellow = new Image("/Colored Emitters/AnakinEmitter/AnakinYellow.png");
	Image ahsokaBlack = new Image("/Colored Emitters/AhsokaEmitter/AhsokaBlack.png");
	Image ahsokaBlue = new Image("/Colored Emitters/AhsokaEmitter/AhsokaBlue.png");
	Image ahsokaGreen = new Image("/Colored Emitters/AhsokaEmitter/AhsokaGreen.png");
	Image ahsokaOrange = new Image("/Colored Emitters/AhsokaEmitter/AhsokaOrange.png");
	Image ahsokaPurple = new Image("/Colored Emitters/AhsokaEmitter/AhsokaPurple.png");
	Image ahsokaRed = new Image("/Colored Emitters/AhsokaEmitter/AhsokaRed.png");
	Image ahsokaSilver = new Image("/Colored Emitters/AhsokaEmitter/AhsokaSilver.png");
	Image ahsokaYellow = new Image("/Colored Emitters/AhsokaEmitter/AhsokaYellow.png");
	Image kalBlack = new Image("/Colored Emitters/KalEmitter/KalBlack.png");
	Image kalBlue = new Image("/Colored Emitters/KalEmitter/KalBlue.png");
	Image kalGreen = new Image("/Colored Emitters/KalEmitter/KalGreen.png");
	Image kalOrange = new Image("/Colored Emitters/KalEmitter/KalOrange.png");
	Image kalPurple = new Image("/Colored Emitters/KalEmitter/KalPurple.png");
	Image kalRed = new Image("/Colored Emitters/KalEmitter/KalRed.png");
	Image kalSilver = new Image("/Colored Emitters/KalEmitter/KalSilver.png");
	Image kalYellow = new Image("/Colored Emitters/KalEmitter/KalYellow.png");
	Image lukeBlack = new Image("/Colored Emitters/LukeEmitter/LukeBlack.png");
	Image lukeBlue = new Image("/Colored Emitters/LukeEmitter/LukeBlue.png");
	Image lukeGreen = new Image("/Colored Emitters/LukeEmitter/LukeGreen.png");
	Image lukeOrange = new Image("/Colored Emitters/LukeEmitter/LukeOrange.png");
	Image lukePurple = new Image("/Colored Emitters/LukeEmitter/LukePurple.png");
	Image lukeRed = new Image("/Colored Emitters/LukeEmitter/LukeRed.png");
	Image lukeSilver = new Image("/Colored Emitters/LukeEmitter/LukeSilver.png");
	Image lukeYellow = new Image("/Colored Emitters/LukeEmitter/LukeYellow.png");
	Image vaderBlack = new Image("/Colored Emitters/VaderEmitter/VaderBlack.png");
	Image vaderBlue = new Image("/Colored Emitters/VaderEmitter/VaderBlue.png");
	Image vaderGreen = new Image("/Colored Emitters/VaderEmitter/VaderGreen.png");
	Image vaderOrange = new Image("/Colored Emitters/VaderEmitter/VaderOrange.png");
	Image vaderPurple = new Image("/Colored Emitters/VaderEmitter/VaderPurple.png");
	Image vaderRed = new Image("/Colored Emitters/VaderEmitter/VaderRed.png");
	Image vaderSilver = new Image("/Colored Emitters/VaderEmitter/VaderSilver.png");
	Image vaderYellow = new Image("/Colored Emitters/VaderEmitter/VaderYellow.png");
	Image orig1Black = new Image("/Colored Emitters/OrigEmitter1/Orig1Black.png");
	Image orig1Blue = new Image("/Colored Emitters/OrigEmitter1/Orig1Blue.png");
	Image orig1Green = new Image("/Colored Emitters/OrigEmitter1/Orig1Green.png");
	Image orig1Orange = new Image("/Colored Emitters/OrigEmitter1/Orig1Orange.png");
	Image orig1Purple = new Image("/Colored Emitters/OrigEmitter1/Orig1Purple.png");
	Image orig1Red = new Image("/Colored Emitters/OrigEmitter1/Orig1Red.png");
	Image orig1Silver = new Image("/Colored Emitters/OrigEmitter1/Orig1Silver.png");
	Image orig1Yellow = new Image("/Colored Emitters/OrigEmitter1/Orig1Yellow.png");
	
	// Arraylist for storing all saber objects
	ArrayList<Saber> allSabers = new ArrayList<>();
	
	// Construct default sabers
	Saber anakinSaber = new Saber(true, "The Skywalker", "Blue", anakinBlue, 
			anakinEmitter, anakinGuard, anakinSwitch, anakinPommel);
	Saber ahsokaSaber = new Saber(true, "The Fulcrum", "Green", ahsokaGreen,
			ahsokaEmitter, ahsokaGuard, ahsokaSwitch, ahsokaPommel);
	Saber kalSaber = new Saber(true, "The Fallen", "Blue", kalBlue,
			kalEmitter, kalGuard, kalSwitch, kalPommel);
	Saber lukeSaber = new Saber(true, "The Prodigal Son", "Green", lukeGreen,
			lukeEmitter, lukeGuard, lukeSwitch, lukePommel);
	Saber vaderSaber = new Saber(true, "The Chosen One", "Red", vaderRed,
			vaderEmitter, vaderGuard, vaderSwitch, vaderPommel);	
	
	@Override
	public void start(Stage primaryStage) {
		// Add default sabers to allSabers
		allSabers.add(anakinSaber);
		allSabers.add(ahsokaSaber);
		allSabers.add(kalSaber);
		allSabers.add(lukeSaber);
		allSabers.add(vaderSaber);
		
		FlowPane smithyFlowPane = new FlowPane(Orientation.VERTICAL);
		smithyFlowPane.setStyle("-fx-background-color: black");
		smithyFlowPane.setAlignment(Pos.BASELINE_CENTER);
		smithyFlowPane.setVgap(150);
		Image smithyTitle = new Image("/GUI Components/SabersmithyTitle2.png");
		ImageView smithyTitleView = new ImageView(smithyTitle);
		smithyTitleView.setFitWidth(1280);
		smithyTitleView.setPreserveRatio(true);
		Image reforged = new Image("/GUI Components/Reforged.png");
		ImageView reforgedView = new ImageView(reforged);
		VBox smithyTitleBox = new VBox();
		smithyTitleBox.setAlignment(Pos.CENTER);
		smithyTitleBox.getChildren().addAll(smithyTitleView, reforgedView);
		
		// Navigate to forge
		Button btForge = new Button("Forge");
		btForge.setPrefSize(150, 100);
		btForge.setOnAction(e -> primaryStage.setScene(forgeScene));
		
		// Navigate to gallery
		Button btGallery = new Button("Gallery");
		btGallery.setPrefSize(150, 100);
		btGallery.setOnAction(e -> primaryStage.setScene(galleryScene));
		
		HBox buttonBox = new HBox(200);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(btForge, btGallery);
		
		smithyFlowPane.getChildren().addAll(smithyTitleBox, buttonBox);
		
		// Set primary stage
		smithyMenuScene = new Scene(smithyFlowPane, 1280, 720);
		primaryStage.setScene(smithyMenuScene);
		primaryStage.show();
	}
	
	// Main method
	public static void main(String[] args) {
		launch(args);
	}
} 
