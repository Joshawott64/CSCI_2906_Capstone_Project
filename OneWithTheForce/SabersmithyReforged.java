import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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
	
	// Import crystals
	Image blackCrystal = new Image("/Crystals/BlackCrystal.png");
	Image blueCrystal = new Image("/Crystals/BlueCrystal.png");
	Image greenCrystal = new Image("/Crystals/GreenCrystal.png");
	Image orangeCrystal = new Image("/Crystals/OrangeCrystal.png");
	Image purpleCrystal = new Image("/Crystals/PurpleCrystal.png");
	Image redCrystal = new Image("/Crystals/RedCrystal.png");
	Image silverCrystal = new Image("/Crystals/SilverCrystal.png");
	Image yellowCrystal = new Image("/Crystals/YellowCrystal.png");
	
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
		
		/* MAIN MENU */
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
		
		HBox smithyButtonBox = new HBox(200);
		smithyButtonBox.setAlignment(Pos.CENTER);
		smithyButtonBox.getChildren().addAll(btForge, btGallery);
		
		smithyFlowPane.getChildren().addAll(smithyTitleBox, smithyButtonBox);
		
		// Set primary stage
		smithyMenuScene = new Scene(smithyFlowPane, 1280, 720);
		primaryStage.setScene(smithyMenuScene);
		primaryStage.show();
		
		
		/* GALLERY */
		BorderPane galleryBorderPane = new BorderPane();
		FlowPane galleryFlowPane = new FlowPane(100, 100);
		galleryFlowPane.setPadding(new Insets(50, 150, 50, 150));
		
		// Place galleryFlowPane in a ScrollPane
		ScrollPane galleryScrollPane = new ScrollPane();
		galleryScrollPane.setContent(galleryFlowPane);
		galleryScrollPane.hbarPolicyProperty().setValue(
				ScrollPane.ScrollBarPolicy.NEVER);
		galleryScrollPane.setFitToWidth(true);
		galleryScrollPane.setFitToHeight(true);
		
		HBox galleryTitleBox = new HBox();
		galleryTitleBox.setAlignment(Pos.CENTER);
		Text galleryTitle = new Text("Gallery");
		galleryTitleBox.getChildren().add(galleryTitle);
		
		// Navigate back to main menu
		Button btBackGallery = new Button("<--");
		btBackGallery.setOnAction(e -> primaryStage.setScene(smithyMenuScene));
		HBox galleryButtonBox = new HBox();
		galleryButtonBox.setAlignment(Pos.TOP_LEFT);
		galleryButtonBox.getChildren().add(btBackGallery);
		
		// Display all sabers on galleryFlowPane
		for (int i = 0; i < allSabers.size(); i++) {
			VBox saberBox = new VBox();
			ImageView emitter = new ImageView(allSabers.get(i).getEmitter());
			ImageView guard = new ImageView(allSabers.get(i).getGuard());
			ImageView bladeSwitch = new ImageView(allSabers.get(i).getBladeSwitch());
			ImageView pommel = new ImageView(allSabers.get(i).getPommel());
			saberBox.getChildren().addAll(emitter, guard, bladeSwitch, pommel);
			
			HBox formatBox = new HBox(20);
			formatBox.getChildren().add(saberBox);
			formatBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;");
			formatBox.setPrefWidth(200);
			formatBox.setPrefHeight(200);
			Label saberLabel = new Label(allSabers.get(i).getName(), formatBox);
			saberLabel.setContentDisplay(ContentDisplay.TOP);
			
			if (allSabers.get(i).getIsDefault() == true) {
				ComboBox<String> cbo = new ComboBox<>();
				cbo.getItems().add("View");
				formatBox.getChildren().add(cbo);
			}
			else {
				ComboBox<String> cbo = new ComboBox<>();
				cbo.getItems().addAll("View", "Edit", "Delete");
				formatBox.getChildren().add(cbo);
			}
			
			galleryFlowPane.getChildren().addAll(formatBox, saberLabel);
		}
		
		galleryBorderPane.setTop(galleryTitleBox);
		galleryBorderPane.setLeft(galleryButtonBox);
		galleryBorderPane.setCenter(galleryScrollPane);
		
		// Create scene
		galleryScene = new Scene(galleryBorderPane, 1280, 720);
		
		
		/* FORGE */
		BorderPane forgeBorderPane = new BorderPane();
		
		Label lblEmitter = new Label("Emitters");
		lblEmitter.setContentDisplay(ContentDisplay.BOTTOM);
		FlowPane emitterPane = new FlowPane(5, 5, lblEmitter);
		emitterPane.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;");
		emitterPane.setPadding(new Insets(5, 5, 5, 5));
		
		Label lblGuard = new Label("Guards");
		lblGuard.setContentDisplay(ContentDisplay.BOTTOM);
		FlowPane guardPane = new FlowPane(5, 5, lblGuard);
		guardPane.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;");
		guardPane.setPadding(new Insets(5, 5, 5, 5));
		
		Label lblColor = new Label("Colors");
		lblColor.setContentDisplay(ContentDisplay.BOTTOM);
		FlowPane colorPane = new FlowPane(5, 5, lblColor);
		colorPane.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;");
		colorPane.setPadding(new Insets(5, 5, 5, 5));
		
		Label lblSwitch = new Label("Switches");
		lblSwitch.setContentDisplay(ContentDisplay.BOTTOM);
		FlowPane switchPane = new FlowPane(5, 5, lblSwitch);
		switchPane.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;");
		switchPane.setPadding(new Insets(5, 5, 5, 5));
		
		Label lblPommel = new Label("Pommels");
		lblPommel.setContentDisplay(ContentDisplay.BOTTOM);
		FlowPane pommelPane = new FlowPane(5, 5, lblPommel);
		pommelPane.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;");
		pommelPane.setPadding(new Insets(5, 5, 5, 5));
		
		VBox leftBox = new VBox(20);
		leftBox.getChildren().addAll(emitterPane, guardPane);
		VBox rightBox = new VBox(20);
		rightBox.getChildren().addAll(colorPane, switchPane, pommelPane);
		
		Saber customSaber = new Saber();
		
		VBox customSaberBox = new VBox();
		customSaberBox.setAlignment(Pos.BOTTOM_CENTER);
		ImageView customEmitter = new ImageView(customSaber.getEmitter());
		ImageView customGuard = new ImageView(customSaber.getGuard());
		ImageView customSwitch = new ImageView(customSaber.getBladeSwitch());
		ImageView customPommel = new ImageView(customSaber.getPommel());
		customSaberBox.getChildren().addAll(customEmitter, customGuard, 
				customSwitch, customPommel);
				
		HBox bottomBox = new HBox(100);
		bottomBox.setAlignment(Pos.CENTER);
				
		HBox nameBox = new HBox();
		nameBox.setAlignment(Pos.CENTER);
		TextField name = new TextField(customSaber.getName());
		name.setEditable(true);
		name.setAlignment(Pos.BASELINE_CENTER);
		name.setOnAction(e -> customSaber.setName(name.getText()));
		nameBox.getChildren().add(name);
				
		HBox forgeButtonBox = new HBox(20);
		forgeButtonBox.setAlignment(Pos.BOTTOM_LEFT);
		
		// Save customSaber to gallery
		Button btSave = new Button("Save");
		btSave.setOnAction(e -> {
			addCustomSaber(customSaber, allSabers, galleryFlowPane, primaryStage);
		});
		
		// Return to smithyMenuScene without saving customSaber
		Button btDiscard = new Button("Discard");
		btDiscard.setOnAction(e -> {
			primaryStage.setScene(smithyMenuScene);
		});
			
		// Activate/Deactivate blade
		Button btTest = new Button("Toggle Blade");
		btTest.setOnAction(e -> {
				// ! TOGGLE COLORED EMITTER !
		});
		
		forgeButtonBox.getChildren().addAll(btSave, btDiscard, btTest);		
		bottomBox.getChildren().addAll(forgeButtonBox, nameBox);
		
		for (int i = 0; i < allSabers.size(); i++) {
			// Add emitters to emitterPane
			Image emitterImage = allSabers.get(i).getEmitter();
			ImageView emitterView = new ImageView(emitterImage);
			emitterView.setFitHeight(50);
			emitterView.setFitWidth(50);
			Button btEmitter = new Button();
			btEmitter.setGraphic(emitterView);
			btEmitter.setOnAction(e -> {
				changeEmitter(customSaber, customSaberBox, emitterImage);
			});
			emitterPane.getChildren().add(btEmitter);
			
			// Add guards to guardPane
			Image guardImage = allSabers.get(i).getGuard();
			ImageView guardView = new ImageView(guardImage);
			guardView.setFitHeight(50);
			guardView.setFitWidth(50);
			Button btGuard = new Button();
			btGuard.setGraphic(guardView);
			btGuard.setOnAction(e -> {
				changeGuard(customSaber, customSaberBox, guardImage);
			});
			guardPane.getChildren().add(btGuard);
			
			// Add switches to switchPane
			Image switchImage = allSabers.get(i).getBladeSwitch();
			ImageView switchView = new ImageView(switchImage);
			switchView.setFitHeight(50);
			switchView.setFitWidth(50);
			Button btSwitch = new Button();
			btSwitch.setGraphic(switchView);
			btSwitch.setOnAction(e -> {
				changeSwitch(customSaber, customSaberBox, switchImage);
			});
			switchPane.getChildren().add(btSwitch);
			
			// Add pommels to pommelPane
			Image pommelImage = allSabers.get(i).getPommel();
			ImageView pommelView = new ImageView(pommelImage);
			pommelView.setFitHeight(50);
			pommelView.setFitWidth(50);
			Button btPommel = new Button();
			btPommel.setGraphic(pommelView);
			btPommel.setOnAction(e -> {
				changePommel(customSaber, customSaberBox, pommelImage);
			});
			pommelPane.getChildren().add(btPommel);
		}
		
		// Add crystals to colorPane
		ImageView blackView = new ImageView(blackCrystal);
		Button btBlack = new Button();
		btBlack.setGraphic(blackView);
		btBlack.setOnAction(e -> customSaber.setColor("Black"));
		ImageView blueView = new ImageView(blueCrystal);
		Button btBlue = new Button();
		btBlue.setGraphic(blueView);
		btBlue.setOnAction(e -> customSaber.setColor("Blue"));
		ImageView greenView = new ImageView(greenCrystal);
		Button btGreen = new Button();
		btGreen.setGraphic(greenView);
		btGreen.setOnAction(e -> customSaber.setColor("Green"));
		ImageView orangeView = new ImageView(orangeCrystal);
		Button btOrange = new Button();
		btOrange.setGraphic(orangeView);
		btOrange.setOnAction(e -> customSaber.setColor("Orange"));
		ImageView purpleView = new ImageView(purpleCrystal);
		Button btPurple = new Button();
		btPurple.setGraphic(purpleView);
		btPurple.setOnAction(e -> customSaber.setColor("Purple"));
		ImageView redView = new ImageView(redCrystal);
		Button btRed = new Button();
		btRed.setGraphic(redView);
		btRed.setOnAction(e -> customSaber.setColor("Red"));
		ImageView silverView = new ImageView(silverCrystal);
		Button btSilver = new Button();
		btSilver.setGraphic(silverView);
		btSilver.setOnAction(e -> customSaber.setColor("Silver"));
		ImageView yellowView = new ImageView(yellowCrystal);
		Button btYellow = new Button();
		btYellow.setGraphic(yellowView);
		btYellow.setOnAction(e -> customSaber.setColor("Yellow"));
		
		colorPane.getChildren().addAll(btBlack, btBlue, btGreen, 
				btOrange, btPurple, btRed, btSilver, btYellow);
		
		forgeBorderPane.setLeft(leftBox);
		forgeBorderPane.setRight(rightBox);
		forgeBorderPane.setBottom(bottomBox);
		forgeBorderPane.setCenter(customSaberBox);
		
		// Create scene
		forgeScene = new Scene(forgeBorderPane, 1280, 720);
	}
	
	// Main method
	public static void main(String[] args) {
		launch(args);
	}
	
	// Adds a custom saber object to galleryFlowPane
		public void addCustomSaber(Saber customSaber, ArrayList<Saber> allSabers, 
				FlowPane galleryFlowPane, Stage primaryStage) {
			// Add saber to list
			allSabers.add(customSaber);
			VBox saberBox = new VBox();
			ImageView emitter = new ImageView(customSaber.getEmitter());
			ImageView guard = new ImageView(customSaber.getGuard());
			ImageView bladeSwitch = new ImageView(customSaber.getBladeSwitch());
			ImageView pommel = new ImageView(customSaber.getPommel());
			saberBox.getChildren().addAll(emitter, guard, bladeSwitch, pommel);
			
			HBox formatBox = new HBox();
			formatBox.getChildren().add(saberBox);
			formatBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;");
			formatBox.setPrefWidth(200);
			formatBox.setPrefHeight(200);
			
			Label saberLabel = new Label(customSaber.getName(), formatBox);
			saberLabel.setContentDisplay(ContentDisplay.TOP);
			
			if (customSaber.getIsDefault() == true) {
				ComboBox<String> cbo = new ComboBox<>();
				cbo.getItems().add("View");
				formatBox.getChildren().add(cbo);
			}
			else if (customSaber.getIsDefault() == false) {
				ComboBox<String> cbo = new ComboBox<>();
				cbo.getItems().addAll("View", "Edit", "Delete");
				formatBox.getChildren().add(cbo);
			}
			
			galleryFlowPane.getChildren().add(formatBox);
			galleryFlowPane.getChildren().add(saberLabel);
			
			primaryStage.setScene(smithyMenuScene);
		}
		
		// Change customSaber's emitter
		public static void changeEmitter(Saber customSaber, VBox customSaberBox, 
				Image newEmitter) {
			customSaber.setEmitter(newEmitter);
			ImageView newEmitterView = new ImageView(newEmitter);
			customSaberBox.getChildren().set(0, newEmitterView);
		}
		
		// Changes customSaber's guard
		public static void changeGuard(Saber customSaber, VBox customSaberBox, 
				Image newGuard) {
			customSaber.setGuard(newGuard);
			ImageView newGuardView = new ImageView(newGuard);
			customSaberBox.getChildren().set(1, newGuardView);
		}
		
		// Changes customSaber's switch
		public static void changeSwitch(Saber customSaber, VBox customSaberBox, 
				Image newBladeSwitch) {
			customSaber.setBladeSwitch(newBladeSwitch);
			ImageView newBladeSwitchView = new ImageView(newBladeSwitch);
			customSaberBox.getChildren().set(2, newBladeSwitchView);
		}
		
		// Changes customSaber's pommel
		public static void changePommel(Saber customSaber, VBox customSaberBox, 
				Image newPommel) {
			customSaber.setPommel(newPommel);
			ImageView newPommelView = new ImageView(newPommel);
			customSaberBox.getChildren().set(3, newPommelView);
		}
} 
