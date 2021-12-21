import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;
import java.net.URISyntaxException;
import java.util.*;

public class SabersmithyReforged extends Main {
	// Scenes
	Scene smithyMenuScene, forgeScene, galleryScene, previewScene, editScene;
	
	// BorderPanes for saber preview and saber editing
	BorderPane previewBorderPane, editBorderPane;
	
	// Boolean for toggling saber
	static boolean saberIsOn = false;
	
	// MediaPlayer for saber hum
	static MediaPlayer hum;
	
	// Import saber parts
	static Image anakinEmitter = new Image("/Saber Parts/AnakinEmitter.png");
	Image anakinGuard = new Image("/Saber Parts/AnakinGuard.png");
	Image anakinSwitch = new Image("/Saber Parts/AnakinSwitch.png");
	Image anakinPommel = new Image("/Saber Parts/AnakinPommel.png");
	static Image ahsokaEmitter = new Image("/Saber Parts/AhsokaEmitter.png");
	Image ahsokaGuard = new Image("/Saber Parts/AhsokaGuard.png");
	Image ahsokaSwitch = new Image("/Saber Parts/AhsokaSwitch.png");
	Image ahsokaPommel = new Image("/Saber Parts/AhsokaPommel.png");
	static Image kalEmitter = new Image("/Saber Parts/KalEmitter.png");
	Image kalGuard = new Image("/Saber Parts/KalGuard.png");
	Image kalSwitch = new Image("/Saber Parts/KalSwitch.png");
	Image kalPommel = new Image("/Saber Parts/KalPommel.png");
	static Image lukeEmitter = new Image("/Saber Parts/LukeEmitter.png");
	Image lukeGuard = new Image("/Saber Parts/LukeGuard.png");
	Image lukeSwitch = new Image("/Saber Parts/LukeSwitch.png");
	Image lukePommel = new Image("/Saber Parts/LukePommel.png");
	static Image vaderEmitter = new Image("/Saber Parts/VaderEmitter.png");
	Image vaderGuard = new Image("/Saber Parts/VaderGuard.png");
	Image vaderSwitch = new Image("/Saber Parts/VaderSwitch.png");
	Image vaderPommel = new Image("/Saber Parts/VaderPommel.png");
	static Image darkEmitter = new Image("/Saber Parts/DarkEmitter.png");
	Image darkGuard = new Image("/Saber Parts/DarkGuard.png");
	Image darkSwitch = new Image("/Saber Parts/DarkSwitch.png");
	Image darkPommel = new Image("/Saber Parts/DarkPommel.png");
	static Image orig1Emitter = new Image("/Saber Parts/OrigEmitter1.png");
	Image orig1Guard = new Image("/Saber Parts/OrigGuard1.png");
	Image orig1Switch = new Image("/Saber Parts/OrigSwitch1.png");
	Image orig1Pommel = new Image("/Saber Parts/OrigPommel1.png");
	static Image orig2Emitter = new Image("/Saber Parts/OrigEmitter2.png");
	Image orig2Guard = new Image("/Saber Parts/OrigGuard2.png");
	Image orig2Switch = new Image("/Saber Parts/OrigSwitch2.png");
	Image orig2Pommel = new Image("/Saber Parts/OrigPommel2.png");
			
	// Import colored emitters
	static Image anakinBlack = new Image("/Colored Emitters/AnakinEmitter/AnakinBlack.png");
	static Image anakinBlue = new Image("/Colored Emitters/AnakinEmitter/AnakinBlue.png");
	static Image anakinGreen = new Image("/Colored Emitters/AnakinEmitter/AnakinGreen.png");
	static Image anakinOrange = new Image("/Colored Emitters/AnakinEmitter/AnakinOrange.png");
	static Image anakinPurple = new Image("/Colored Emitters/AnakinEmitter/AnakinPurple.png");
	static Image anakinRed = new Image("/Colored Emitters/AnakinEmitter/AnakinRed.png");
	static Image anakinSilver = new Image("/Colored Emitters/AnakinEmitter/AnakinSilver.png");
	static Image anakinYellow = new Image("/Colored Emitters/AnakinEmitter/AnakinYellow.png");
	static Image ahsokaBlack = new Image("/Colored Emitters/AhsokaEmitter/AhsokaBlack.png");
	static Image ahsokaBlue = new Image("/Colored Emitters/AhsokaEmitter/AhsokaBlue.png");
	static Image ahsokaGreen = new Image("/Colored Emitters/AhsokaEmitter/AhsokaGreen.png");
	static Image ahsokaOrange = new Image("/Colored Emitters/AhsokaEmitter/AhsokaOrange.png");
	static Image ahsokaPurple = new Image("/Colored Emitters/AhsokaEmitter/AhsokaPurple.png");
	static Image ahsokaRed = new Image("/Colored Emitters/AhsokaEmitter/AhsokaRed.png");
	static Image ahsokaSilver = new Image("/Colored Emitters/AhsokaEmitter/AhsokaSilver.png");
	static Image ahsokaYellow = new Image("/Colored Emitters/AhsokaEmitter/AhsokaYellow.png");
	static Image kalBlack = new Image("/Colored Emitters/KalEmitter/KalBlack.png");
	static Image kalBlue = new Image("/Colored Emitters/KalEmitter/KalBlue.png");
	static Image kalGreen = new Image("/Colored Emitters/KalEmitter/KalGreen.png");
	static Image kalOrange = new Image("/Colored Emitters/KalEmitter/KalOrange.png");
	static Image kalPurple = new Image("/Colored Emitters/KalEmitter/KalPurple.png");
	static Image kalRed = new Image("/Colored Emitters/KalEmitter/KalRed.png");
	static Image kalSilver = new Image("/Colored Emitters/KalEmitter/KalSilver.png");
	static Image kalYellow = new Image("/Colored Emitters/KalEmitter/KalYellow.png");
	static Image lukeBlack = new Image("/Colored Emitters/LukeEmitter/LukeBlack.png");
	static Image lukeBlue = new Image("/Colored Emitters/LukeEmitter/LukeBlue.png");
	static Image lukeGreen = new Image("/Colored Emitters/LukeEmitter/LukeGreen.png");
	static Image lukeOrange = new Image("/Colored Emitters/LukeEmitter/LukeOrange.png");
	static Image lukePurple = new Image("/Colored Emitters/LukeEmitter/LukePurple.png");
	static Image lukeRed = new Image("/Colored Emitters/LukeEmitter/LukeRed.png");
	static Image lukeSilver = new Image("/Colored Emitters/LukeEmitter/LukeSilver.png");
	static Image lukeYellow = new Image("/Colored Emitters/LukeEmitter/LukeYellow.png");
	static Image vaderBlack = new Image("/Colored Emitters/VaderEmitter/VaderBlack.png");
	static Image vaderBlue = new Image("/Colored Emitters/VaderEmitter/VaderBlue.png");
	static Image vaderGreen = new Image("/Colored Emitters/VaderEmitter/VaderGreen.png");
	static Image vaderOrange = new Image("/Colored Emitters/VaderEmitter/VaderOrange.png");
	static Image vaderPurple = new Image("/Colored Emitters/VaderEmitter/VaderPurple.png");
	static Image vaderRed = new Image("/Colored Emitters/VaderEmitter/VaderRed.png");
	static Image vaderSilver = new Image("/Colored Emitters/VaderEmitter/VaderSilver.png");
	static Image vaderYellow = new Image("/Colored Emitters/VaderEmitter/VaderYellow.png");
	static Image darkBlack = new Image("/Colored Emitters/DarkEmitter/DarkEmitterBlack.png");
	static Image orig1Black = new Image("/Colored Emitters/OrigEmitter1/Orig1Black.png");
	static Image orig1Blue = new Image("/Colored Emitters/OrigEmitter1/Orig1Blue.png");
	static Image orig1Green = new Image("/Colored Emitters/OrigEmitter1/Orig1Green.png");
	static Image orig1Orange = new Image("/Colored Emitters/OrigEmitter1/Orig1Orange.png");
	static Image orig1Purple = new Image("/Colored Emitters/OrigEmitter1/Orig1Purple.png");
	static Image orig1Red = new Image("/Colored Emitters/OrigEmitter1/Orig1Red.png");
	static Image orig1Silver = new Image("/Colored Emitters/OrigEmitter1/Orig1Silver.png");
	static Image orig1Yellow = new Image("/Colored Emitters/OrigEmitter1/Orig1Yellow.png");
	static Image orig2Black = new Image("/Colored Emitters/OrigEmitter2/Orig2Black.png");
	static Image orig2Blue = new Image("/Colored Emitters/OrigEmitter2/Orig2Blue.png");
	static Image orig2Green = new Image("/Colored Emitters/OrigEmitter2/Orig2Green.png");
	static Image orig2Orange = new Image("/Colored Emitters/OrigEmitter2/Orig2Orange.png");
	static Image orig2Purple = new Image("/Colored Emitters/OrigEmitter2/Orig2Purple.png");
	static Image orig2Red = new Image("/Colored Emitters/OrigEmitter2/Orig2Red.png");
	static Image orig2Silver = new Image("/Colored Emitters/OrigEmitter2/Orig2Silver.png");
	static Image orig2Yellow = new Image("/Colored Emitters/OrigEmitter2/Orig2Yellow.png");
	
	// Import crystals
	static Image blackCrystal = new Image("/Crystals/BlackCrystal.png");
	static Image blueCrystal = new Image("/Crystals/BlueCrystal.png");
	static Image greenCrystal = new Image("/Crystals/GreenCrystal.png");
	static Image orangeCrystal = new Image("/Crystals/OrangeCrystal.png");
	static Image purpleCrystal = new Image("/Crystals/PurpleCrystal.png");
	static Image redCrystal = new Image("/Crystals/RedCrystal.png");
	static Image silverCrystal = new Image("/Crystals/SilverCrystal.png");
	static Image yellowCrystal = new Image("/Crystals/YellowCrystal.png");
	
	// Arraylist for storing all saber objects
	static ArrayList<Saber> allSabers = new ArrayList<>();
	
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
	Saber darkSaber = new Saber(true, "The Dark Saber", "Black", darkBlack, 
			darkEmitter, darkGuard, darkSwitch, darkPommel);
	Saber origSaber1 = new Saber(true, "Original Saber #1", "Red", orig1Red, 
			orig1Emitter, orig1Guard, orig1Switch, orig1Pommel);
	Saber origSaber2 = new Saber(true, "Original Saber #2", "Yellow", orig2Yellow, 
			orig2Emitter, orig2Guard, orig2Switch, orig2Pommel);
	
	@Override
	public void start(Stage primaryStage) throws URISyntaxException {
		// Add default sabers to allSabers
		allSabers.add(anakinSaber);
		allSabers.add(ahsokaSaber);
		allSabers.add(kalSaber);
		allSabers.add(lukeSaber);
		allSabers.add(vaderSaber);
		allSabers.add(darkSaber);
		allSabers.add(origSaber1);
		allSabers.add(origSaber2);
		
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
		
		// Gallery title
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

			// Display saber's color
			switch (allSabers.get(i).getColor()) {
				case "Black":
					formatBox.getChildren().add(new ImageView(blackCrystal));
					break;
				case "Blue":
					formatBox.getChildren().add(new ImageView(blueCrystal));
					break;
				case "Green":
					formatBox.getChildren().add(new ImageView(greenCrystal));
					break;
				case "Orange":
					formatBox.getChildren().add(new ImageView(orangeCrystal));
					break;
				case "Purple":
					formatBox.getChildren().add(new ImageView(purpleCrystal));
					break;
				case "Red":
					formatBox.getChildren().add(new ImageView(redCrystal));
					break;
				case "Silver":
					formatBox.getChildren().add(new ImageView(silverCrystal));
					break;
				case "Yellow":
					formatBox.getChildren().add(new ImageView(yellowCrystal));
					break;
			}
			
			// Add buttons based on saber's default value
			if (allSabers.get(i).getIsDefault() == true) {
				Button btView = new Button("View");
				int f = i;
				btView.setOnAction(e -> previewSaber(allSabers.get(f), primaryStage, 
						previewScene, previewBorderPane));
				formatBox.getChildren().add(btView);
			}
			else {
				Button btView = new Button("View");
				btView.setOnAction(e -> primaryStage.setScene(previewScene));
				Button btEdit = new Button("Edit");
				btEdit.setOnAction(e -> primaryStage.setScene(editScene));
				Button btDelete = new Button("Delete");
				int f = i;
				btDelete.setOnAction(e -> {
					deleteSaber(allSabers.get(f), allSabers, galleryFlowPane, formatBox, saberLabel);
				});
				VBox saberButtonBox = new VBox(5);
				saberButtonBox.getChildren().addAll(btView, btEdit, btDelete);
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
		TextField tfName = new TextField(customSaber.getName());
		tfName.setEditable(true);
		tfName.setAlignment(Pos.BASELINE_CENTER);
		nameBox.getChildren().add(tfName);
				
		HBox forgeButtonBox = new HBox(20);
		forgeButtonBox.setAlignment(Pos.BOTTOM_LEFT);
		
		// Save customSaber to gallery
		Button btSave = new Button("Save");
		btSave.setOnAction(e -> {
			try {
				addCustomSaber(customSaber, allSabers, galleryFlowPane, tfName, primaryStage);
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		});
		
		// Return to smithyMenuScene without saving customSaber
		Button btDiscard = new Button("Discard");
		btDiscard.setOnAction(e -> {
			primaryStage.setScene(smithyMenuScene);
		});
			
		// Activate/Deactivate blade
		Button btToggleBlade = new Button("Toggle Blade");
		btToggleBlade.setOnAction(e -> {
			try {
				saberIsOn = toggleBlade(customSaber, customSaberBox, 
						customSaber.getColoredEmitter(), customSaber.getEmitter(), saberIsOn);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		
		forgeButtonBox.getChildren().addAll(btSave, btDiscard, btToggleBlade);		
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
		Button btBlack = new Button();
		btBlack.setGraphic(new ImageView(blackCrystal));
		btBlack.setOnAction(e -> {
			customSaber.setColor("Black");
			changeColoredEmitter(customSaber, customSaberBox);
		});
		Button btBlue = new Button();
		btBlue.setGraphic(new ImageView(blueCrystal));
		btBlue.setOnAction(e -> {
			customSaber.setColor("Blue");
			changeColoredEmitter(customSaber, customSaberBox);
		});
		Button btGreen = new Button();
		btGreen.setGraphic(new ImageView(greenCrystal));
		btGreen.setOnAction(e -> {
			customSaber.setColor("Green");
			changeColoredEmitter(customSaber, customSaberBox);
		});
		Button btOrange = new Button();
		btOrange.setGraphic(new ImageView(orangeCrystal));
		btOrange.setOnAction(e -> {
			customSaber.setColor("Orange");
			changeColoredEmitter(customSaber, customSaberBox);
		});
		Button btPurple = new Button();
		btPurple.setGraphic(new ImageView(purpleCrystal));
		btPurple.setOnAction(e -> {
			customSaber.setColor("Purple");
			changeColoredEmitter(customSaber, customSaberBox);
		});
		Button btRed = new Button();
		btRed.setGraphic(new ImageView(redCrystal));
		btRed.setOnAction(e -> {
			customSaber.setColor("Red");
			changeColoredEmitter(customSaber, customSaberBox);
		});
		Button btSilver = new Button();
		btSilver.setGraphic(new ImageView(silverCrystal));
		btSilver.setOnAction(e -> {
			customSaber.setColor("Silver");
			changeColoredEmitter(customSaber, customSaberBox);
		});
		Button btYellow = new Button();
		btYellow.setGraphic(new ImageView(yellowCrystal));
		btYellow.setOnAction(e -> {
			customSaber.setColor("Yellow");
			changeColoredEmitter(customSaber, customSaberBox);
		});
		
		colorPane.getChildren().addAll(btBlack, btBlue, btGreen, 
				btOrange, btPurple, btRed, btSilver, btYellow);
		
		forgeBorderPane.setLeft(leftBox);
		forgeBorderPane.setRight(rightBox);
		forgeBorderPane.setBottom(bottomBox);
		forgeBorderPane.setCenter(customSaberBox);
		
		// Create scene
		forgeScene = new Scene(forgeBorderPane, 1280, 720);
		
		
		/* PREVIEW SABER */
		previewBorderPane = new BorderPane();
		
		Button btBackPreview = new Button("<--");
		btBackPreview.setOnAction(e -> primaryStage.setScene(galleryScene));
		
		previewBorderPane.setLeft(btBackPreview);
		
		// Create scene
		previewScene = new Scene(previewBorderPane, 1280, 720);
		
		
		/* EDIT SABER */
		editBorderPane = new BorderPane();
		
		// Create scene
		editScene = new Scene(editBorderPane, 1280, 720);
	}
	
	// Main method
	public static void main(String[] args) {
		launch(args);
	}
	
	// Adds a custom saber object to galleryFlowPane
	public void addCustomSaber(Saber customSaber, ArrayList<Saber> allSabers, 
			FlowPane galleryFlowPane, TextField tfName, Stage primaryStage) 
					throws CloneNotSupportedException {
		// Clone saber
		Saber galleryCustomSaber = (Saber)customSaber.clone();
		
		// Set color to black if emitter is darkEmitter
		if (galleryCustomSaber.getEmitter().equals(darkEmitter)) {
			galleryCustomSaber.setColor("Black");
		}
		
		// Add saber to list
		allSabers.add(galleryCustomSaber);
			
		// Add saber to gallery
		VBox saberBox = new VBox();
		ImageView emitter = new ImageView(galleryCustomSaber.getEmitter());
		ImageView guard = new ImageView(galleryCustomSaber.getGuard());
		ImageView bladeSwitch = new ImageView(galleryCustomSaber.getBladeSwitch());
		ImageView pommel = new ImageView(galleryCustomSaber.getPommel());
		saberBox.getChildren().addAll(emitter, guard, bladeSwitch, pommel);
			
		HBox formatBox = new HBox(20);
		formatBox.getChildren().add(saberBox);
		formatBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;");
		formatBox.setPrefWidth(200);
		formatBox.setPrefHeight(200);
			
		galleryCustomSaber.setName(tfName.getText());
		Label saberLabel = new Label(galleryCustomSaber.getName(), formatBox);
		saberLabel.setContentDisplay(ContentDisplay.TOP);
			
		// Display saber's color
		switch (galleryCustomSaber.getColor()) {
			case "Black":
				formatBox.getChildren().add(new ImageView(blackCrystal));
				break;
			case "Blue":
				formatBox.getChildren().add(new ImageView(blueCrystal));
				break;
			case "Green":
				formatBox.getChildren().add(new ImageView(greenCrystal));
				break;
			case "Orange":
				formatBox.getChildren().add(new ImageView(orangeCrystal));
				break;
			case "Purple":
				formatBox.getChildren().add(new ImageView(purpleCrystal));
				break;
			case "Red":
				formatBox.getChildren().add(new ImageView(redCrystal));
				break;
			case "Silver":
				formatBox.getChildren().add(new ImageView(silverCrystal));
				break;
			case "Yellow":
				formatBox.getChildren().add(new ImageView(yellowCrystal));
				break;
		}
			
		if (galleryCustomSaber.getIsDefault() == true) {
			Button btView = new Button("View");
			btView.setOnAction(e -> previewSaber(galleryCustomSaber, primaryStage, 
					previewScene, previewBorderPane));
			formatBox.getChildren().add(btView);
		}
		else if (galleryCustomSaber.getIsDefault() == false) {
			Button btView = new Button("View");
			btView.setOnAction(e -> previewSaber(galleryCustomSaber, primaryStage, 
					previewScene, previewBorderPane));
			Button btEdit = new Button("Edit");
			btEdit.setOnAction(e -> editSaber(galleryCustomSaber, primaryStage, 
					editScene, galleryScene, editBorderPane, allSabers, formatBox, 
					saberBox, saberLabel));
			Button btDelete = new Button("Delete");
			btDelete.setOnAction(e -> {
				deleteSaber(galleryCustomSaber, allSabers, galleryFlowPane, formatBox, saberLabel);
			});
			VBox saberButtonBox = new VBox(5);
			saberButtonBox.getChildren().addAll(btView, btEdit, btDelete);
			formatBox.getChildren().add(saberButtonBox);
		}
			
		galleryFlowPane.getChildren().add(formatBox);
		galleryFlowPane.getChildren().add(saberLabel);

		primaryStage.setScene(smithyMenuScene);
	}
	
	// Deletes a saber
	public static void deleteSaber(Saber saber, ArrayList<Saber> allSabers, 
			FlowPane galleryFlowPane, HBox formatBox, Label saberLabel) {
		// Remove saber from allSabers
		allSabers.remove(saber);
		
		// Remove saber from galleryFlowPane
		galleryFlowPane.getChildren().removeAll(formatBox, saberLabel);
	}
	
	// Previews a saber from the gallery
	public static void previewSaber(Saber saber, Stage primaryStage, Scene previewScene, 
			BorderPane previewBorderPane) {
		// Set scene
		primaryStage.setScene(previewScene);
		
		// Display saber
		VBox saberBox = new VBox();
		saberBox.setAlignment(Pos.BOTTOM_CENTER);
		saberBox.getChildren().addAll(new ImageView(saber.getEmitter()), 
				new ImageView(saber.getGuard()), new ImageView(saber.getBladeSwitch()), 
				new ImageView(saber.getPommel()));
		
		// Preview name
		HBox previewTitleBox = new HBox();
		previewTitleBox.setAlignment(Pos.CENTER);
		previewTitleBox.getChildren().add(new Text(saber.getName()));
		
		// Preview sounds
		Button btClash1 = new Button("Clash1");
		btClash1.setOnAction(e -> {
			try {
				MediaPlayer clash1 = new MediaPlayer(saber.getClash1());
				clash1.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btClash2 = new Button("Clash2");
		btClash2.setOnAction(e -> {
			try {
				MediaPlayer clash2 = new MediaPlayer(saber.getClash2());
				clash2.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btClash3 = new Button("Clash3");
		btClash3.setOnAction(e -> {
			try {
				MediaPlayer clash3 = new MediaPlayer(saber.getClash3());
				clash3.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btDeflect = new Button("Deflect");
		btDeflect.setOnAction(e -> {
			try {
				MediaPlayer deflect = new MediaPlayer(saber.getDeflect());
				deflect.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btSwoosh1 = new Button("Swoosh1");
		btSwoosh1.setOnAction(e -> {
			try {
				MediaPlayer swoosh1 = new MediaPlayer(saber.getSwoosh1());
				swoosh1.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btSwoosh2 = new Button("Swoosh2");
		btSwoosh2.setOnAction(e -> {
			try {
				MediaPlayer swoosh2 = new MediaPlayer(saber.getSwoosh2());
				swoosh2.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btSwoosh3 = new Button("Swoosh3");
		btSwoosh3.setOnAction(e -> {
			try {
				MediaPlayer swoosh3 = new MediaPlayer(saber.getSwoosh3());
				swoosh3.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		
		VBox soundBox = new VBox(10, new Label("Sounds:"));
		soundBox.getChildren().addAll(btClash1, btClash2, btClash3, 
				btDeflect, btSwoosh1, btSwoosh2, btSwoosh3);
		
		// Blade toggle button
		Button btToggleBlade = new Button("Toggle Blade");
		HBox toggleBox = new HBox();
		toggleBox.setAlignment(Pos.CENTER);
		toggleBox.getChildren().add(btToggleBlade);
		btToggleBlade.setOnAction(e -> {
			try {
				saberIsOn = toggleBlade(saber, saberBox, saber.getColoredEmitter(), saber.getEmitter(), saberIsOn);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		
		previewBorderPane.setCenter(saberBox);
		previewBorderPane.setTop(previewTitleBox);
		previewBorderPane.setBottom(toggleBox);
		previewBorderPane.setRight(soundBox);
	}
	
	// Edit saber from the gallery
	public static void editSaber(Saber saber, Stage primaryStage, Scene editScene, 
			Scene galleryScene, BorderPane editBorderPane, ArrayList<Saber> allSabers, 
			HBox formatBox, VBox gallerySaberBox, Label saberLabel) {
		// Set scene
		primaryStage.setScene(editScene);
		
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
		
		// Display saber
		VBox saberBox = new VBox();
		saberBox.setAlignment(Pos.BOTTOM_CENTER);
		saberBox.getChildren().addAll(new ImageView(saber.getEmitter()), 
				new ImageView(saber.getGuard()), new ImageView(saber.getBladeSwitch()), 
				new ImageView(saber.getPommel()));
		
		HBox bottomBox = new HBox(100);
		bottomBox.setAlignment(Pos.CENTER);
				
		HBox nameBox = new HBox();
		nameBox.setAlignment(Pos.CENTER);
		TextField tfName = new TextField(saber.getName());
		tfName.setEditable(true);
		tfName.setAlignment(Pos.BASELINE_CENTER);
		nameBox.getChildren().add(tfName);
				
		HBox buttonBox = new HBox(20);
		buttonBox.setAlignment(Pos.BOTTOM_LEFT);
		
		// Save saber to gallery
		Button btSave = new Button("Save");
		btSave.setOnAction(e -> {
			saveEditedSaber(saber, formatBox, gallerySaberBox, saberLabel, tfName,
					primaryStage, galleryScene);
		});
					
		// Activate/Deactivate blade
		Button btToggleBlade = new Button("Toggle Blade");
		btToggleBlade.setOnAction(e -> {
			try {
				saberIsOn = toggleBlade(saber, saberBox, 
						saber.getColoredEmitter(), saber.getEmitter(), saberIsOn);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		
		buttonBox.getChildren().addAll(btSave, btToggleBlade);		
		bottomBox.getChildren().addAll(buttonBox, nameBox);
		
		for (int i = 0; i < 8; i++) {
			// Add emitters to emitterPane
			Image emitterImage = allSabers.get(i).getEmitter();
			ImageView emitterView = new ImageView(emitterImage);
			emitterView.setFitHeight(50);
			emitterView.setFitWidth(50);
			Button btEmitter = new Button();
			btEmitter.setGraphic(emitterView);
			btEmitter.setOnAction(e -> {
				changeEmitter(saber, saberBox, emitterImage);
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
				changeGuard(saber, saberBox, guardImage);
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
				changeSwitch(saber, saberBox, switchImage);
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
				changePommel(saber, saberBox, pommelImage);
			});
			pommelPane.getChildren().add(btPommel);
		}
		
		// Add crystals to colorPane
		Button btBlack = new Button();
		btBlack.setGraphic(new ImageView(blackCrystal));
		btBlack.setOnAction(e -> {
			saber.setColor("Black");
			changeColoredEmitter(saber, saberBox);
		});
		Button btBlue = new Button();
		btBlue.setGraphic(new ImageView(blueCrystal));
		btBlue.setOnAction(e -> {
			saber.setColor("Blue");
			changeColoredEmitter(saber, saberBox);
		});
		Button btGreen = new Button();
		btGreen.setGraphic(new ImageView(greenCrystal));
		btGreen.setOnAction(e -> {
			saber.setColor("Green");
			changeColoredEmitter(saber, saberBox);
		});
		Button btOrange = new Button();
		btOrange.setGraphic(new ImageView(orangeCrystal));
		btOrange.setOnAction(e -> {
			saber.setColor("Orange");
			changeColoredEmitter(saber, saberBox);
		});
		Button btPurple = new Button();
		btPurple.setGraphic(new ImageView(purpleCrystal));
		btPurple.setOnAction(e -> {
			saber.setColor("Purple");
			changeColoredEmitter(saber, saberBox);
		});
		Button btRed = new Button();
		btRed.setGraphic(new ImageView(redCrystal));
		btRed.setOnAction(e -> {
			saber.setColor("Red");
			changeColoredEmitter(saber, saberBox);
		});
		Button btSilver = new Button();
		btSilver.setGraphic(new ImageView(silverCrystal));
		btSilver.setOnAction(e -> {
			saber.setColor("Silver");
			changeColoredEmitter(saber, saberBox);
		});
		Button btYellow = new Button();
		btYellow.setGraphic(new ImageView(yellowCrystal));
		btYellow.setOnAction(e -> {
			saber.setColor("Yellow");
			changeColoredEmitter(saber, saberBox);
		});
		
		colorPane.getChildren().addAll(btBlack, btBlue, btGreen, 
				btOrange, btPurple, btRed, btSilver, btYellow);
		
		editBorderPane.setLeft(leftBox);
		editBorderPane.setRight(rightBox);
		editBorderPane.setCenter(saberBox);
		editBorderPane.setBottom(bottomBox);
	}
	
	// Save edited saber to galleryFlowPane
	public static void saveEditedSaber(Saber saber, HBox formatBox, 
			VBox gallerySaberBox, Label saberLabel, TextField tfName, 
			Stage primaryStage, Scene galleryScene) {
		// Update saber name
		saber.setName(tfName.getText());
		
		// Update saberLabel
		saberLabel.setText(saber.getName());
		
		// Update gallerySaberBox
		gallerySaberBox.getChildren().set(0, new ImageView(saber.getEmitter()));
		gallerySaberBox.getChildren().set(1, new ImageView(saber.getGuard()));
		gallerySaberBox.getChildren().set(2, new ImageView(saber.getBladeSwitch()));
		gallerySaberBox.getChildren().set(3, new ImageView(saber.getPommel()));
		
		
		// Update color if emitter is darkEmitter
		if (saber.getEmitter().equals(darkEmitter)) {
			saber.setColor("Black");
		}
		
		// Update crystal display
		switch (saber.getColor()) {
			case "Black":
				formatBox.getChildren().set(1, new ImageView(blackCrystal));
				break;
			case "Blue":
				formatBox.getChildren().set(1, new ImageView(blueCrystal));
				break;
			case "Green":
				formatBox.getChildren().set(1, new ImageView(greenCrystal));
				break;
			case "Orange":
				formatBox.getChildren().set(1, new ImageView(orangeCrystal));
				break;
			case "Purple":
				formatBox.getChildren().set(1, new ImageView(purpleCrystal));
				break;
			case "Red":
				formatBox.getChildren().set(1, new ImageView(redCrystal));
				break;
			case "Silver":
				formatBox.getChildren().set(1, new ImageView(silverCrystal));
				break;
			case "Yellow":
				formatBox.getChildren().set(1, new ImageView(yellowCrystal));
				break;
		}
		
		// Set scene
		primaryStage.setScene(galleryScene);
	}
		
	// Change customSaber's emitter
	public static void changeEmitter(Saber customSaber, VBox customSaberBox, 
			Image newEmitter) {
		// Change emitter
		customSaber.setEmitter(newEmitter);
		ImageView newEmitterView = new ImageView(newEmitter);
		customSaberBox.getChildren().set(0, newEmitterView);
		
		// Change colored emitter
		changeColoredEmitter(customSaber, customSaberBox);
	}
		
	// Change customSaber's coloredEmitter
	public static void changeColoredEmitter(Saber customSaber, VBox customSaberBox) {
		switch(customSaber.getColor()) {
			case "Black":
				if (customSaber.getEmitter() == anakinEmitter) {
					customSaber.setColoredEmitter(anakinBlack);
				}
				else if (customSaber.getEmitter() == ahsokaEmitter) {
					customSaber.setColoredEmitter(ahsokaBlack);
				}
				else if (customSaber.getEmitter() == kalEmitter) {
					customSaber.setColoredEmitter(kalBlack);
				}
				else if (customSaber.getEmitter() == lukeEmitter) {
					customSaber.setColoredEmitter(lukeBlack);
				}
				else if (customSaber.getEmitter() == vaderEmitter) {
					customSaber.setColoredEmitter(vaderBlack);
				}
				else if (customSaber.getEmitter() == darkEmitter) {
					customSaber.setColoredEmitter(darkBlack);
				}
				else if (customSaber.getEmitter() == orig1Emitter) {
					customSaber.setColoredEmitter(orig1Black);
				}
				else if (customSaber.getEmitter() == orig2Emitter) {
					customSaber.setColoredEmitter(orig2Black);
				}
				break;
			case "Blue":
				if (customSaber.getEmitter() == anakinEmitter) {
					customSaber.setColoredEmitter(anakinBlue);
				}
				else if (customSaber.getEmitter() == ahsokaEmitter) {
					customSaber.setColoredEmitter(ahsokaBlue);
				}
				else if (customSaber.getEmitter() == kalEmitter) {
					customSaber.setColoredEmitter(kalBlue);
				}
				else if (customSaber.getEmitter() == lukeEmitter) {
					customSaber.setColoredEmitter(lukeBlue);
				}
				else if (customSaber.getEmitter() == vaderEmitter) {
					customSaber.setColoredEmitter(vaderBlue);
				}
				else if (customSaber.getEmitter() == darkEmitter) {
					customSaber.setColoredEmitter(darkBlack);
				}
				else if (customSaber.getEmitter() == orig1Emitter) {
					customSaber.setColoredEmitter(orig1Blue);
				}
				else if (customSaber.getEmitter() == orig2Emitter) {
					customSaber.setColoredEmitter(orig2Blue);
				}
				break;
			case "Green":
				if (customSaber.getEmitter() == anakinEmitter) {
					customSaber.setColoredEmitter(anakinGreen);
				}
				else if (customSaber.getEmitter() == ahsokaEmitter) {
					customSaber.setColoredEmitter(ahsokaGreen);
				}
				else if (customSaber.getEmitter() == kalEmitter) {
					customSaber.setColoredEmitter(kalGreen);
				}
				else if (customSaber.getEmitter() == lukeEmitter) {
					customSaber.setColoredEmitter(lukeGreen);
				}
				else if (customSaber.getEmitter() == vaderEmitter) {
					customSaber.setColoredEmitter(vaderGreen);
				}
				else if (customSaber.getEmitter() == darkEmitter) {
					customSaber.setColoredEmitter(darkBlack);
				}
				else if (customSaber.getEmitter() == orig1Emitter) {
					customSaber.setColoredEmitter(orig1Green);
				}
				else if (customSaber.getEmitter() == orig2Emitter) {
					customSaber.setColoredEmitter(orig2Green);
				}
				break;
			case "Orange":
				if (customSaber.getEmitter() == anakinEmitter) {
					customSaber.setColoredEmitter(anakinOrange);
				}
				else if (customSaber.getEmitter() == ahsokaEmitter) {
					customSaber.setColoredEmitter(ahsokaOrange);
				}
				else if (customSaber.getEmitter() == kalEmitter) {
					customSaber.setColoredEmitter(kalOrange);
				}
				else if (customSaber.getEmitter() == lukeEmitter) {
					customSaber.setColoredEmitter(lukeOrange);
				}
				else if (customSaber.getEmitter() == vaderEmitter) {
					customSaber.setColoredEmitter(vaderOrange);
				}
				else if (customSaber.getEmitter() == darkEmitter) {
					customSaber.setColoredEmitter(darkBlack);
				}
				else if (customSaber.getEmitter() == orig1Emitter) {
					customSaber.setColoredEmitter(orig1Orange);
				}
				else if (customSaber.getEmitter() == orig2Emitter) {
					customSaber.setColoredEmitter(orig2Orange);
				}
				break;
			case "Purple":
				if (customSaber.getEmitter() == anakinEmitter) {
					customSaber.setColoredEmitter(anakinPurple);
				}
				else if (customSaber.getEmitter() == ahsokaEmitter) {
					customSaber.setColoredEmitter(ahsokaPurple);
				}
				else if (customSaber.getEmitter() == kalEmitter) {
					customSaber.setColoredEmitter(kalPurple);
				}
				else if (customSaber.getEmitter() == lukeEmitter) {
					customSaber.setColoredEmitter(lukePurple);
				}
				else if (customSaber.getEmitter() == vaderEmitter) {
					customSaber.setColoredEmitter(vaderPurple);
				}
				else if (customSaber.getEmitter() == darkEmitter) {
					customSaber.setColoredEmitter(darkBlack);
				}
				else if (customSaber.getEmitter() == orig1Emitter) {
					customSaber.setColoredEmitter(orig1Purple);
				}
				else if (customSaber.getEmitter() == orig2Emitter) {
					customSaber.setColoredEmitter(orig2Purple);
				}
				break;
			case "Red":
				if (customSaber.getEmitter() == anakinEmitter) {
					customSaber.setColoredEmitter(anakinRed);
				}
				else if (customSaber.getEmitter() == ahsokaEmitter) {
					customSaber.setColoredEmitter(ahsokaRed);
				}
				else if (customSaber.getEmitter() == kalEmitter) {
					customSaber.setColoredEmitter(kalRed);
				}
				else if (customSaber.getEmitter() == lukeEmitter) {
					customSaber.setColoredEmitter(lukeRed);
				}
				else if (customSaber.getEmitter() == vaderEmitter) {
					customSaber.setColoredEmitter(vaderRed);
				}
				else if (customSaber.getEmitter() == darkEmitter) {
					customSaber.setColoredEmitter(darkBlack);
				}
				else if (customSaber.getEmitter() == orig1Emitter) {
					customSaber.setColoredEmitter(orig1Red);
				}
				else if (customSaber.getEmitter() == orig2Emitter) {
					customSaber.setColoredEmitter(orig2Red);
				}
				break;
			case "Silver":
				if (customSaber.getEmitter() == anakinEmitter) {
					customSaber.setColoredEmitter(anakinSilver);
				}
				else if (customSaber.getEmitter() == ahsokaEmitter) {
					customSaber.setColoredEmitter(ahsokaSilver);
				}
				else if (customSaber.getEmitter() == kalEmitter) {
					customSaber.setColoredEmitter(kalSilver);
				}
				else if (customSaber.getEmitter() == lukeEmitter) {
					customSaber.setColoredEmitter(lukeSilver);
				}
				else if (customSaber.getEmitter() == vaderEmitter) {
					customSaber.setColoredEmitter(vaderSilver);
				}
				else if (customSaber.getEmitter() == darkEmitter) {
					customSaber.setColoredEmitter(darkBlack);
				}
				else if (customSaber.getEmitter() == orig1Emitter) {
					customSaber.setColoredEmitter(orig1Silver);
				}
				else if (customSaber.getEmitter() == orig2Emitter) {
					customSaber.setColoredEmitter(orig2Silver);
				}
				break;
			case "Yellow":
				if (customSaber.getEmitter() == anakinEmitter) {
					customSaber.setColoredEmitter(anakinYellow);
				}
				else if (customSaber.getEmitter() == ahsokaEmitter) {
					customSaber.setColoredEmitter(ahsokaYellow);
				}
				else if (customSaber.getEmitter() == kalEmitter) {
					customSaber.setColoredEmitter(kalYellow);
				}
				else if (customSaber.getEmitter() == lukeEmitter) {
					customSaber.setColoredEmitter(lukeYellow);
				}
				else if (customSaber.getEmitter() == vaderEmitter) {
					customSaber.setColoredEmitter(vaderYellow);
				}
				else if (customSaber.getEmitter() == darkEmitter) {
					customSaber.setColoredEmitter(darkBlack);
				}
				else if (customSaber.getEmitter() == orig1Emitter) {
					customSaber.setColoredEmitter(orig1Yellow);
				}
				else if (customSaber.getEmitter() == orig2Emitter) {
					customSaber.setColoredEmitter(orig2Yellow);
				}
				break;
		}			
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
		
	// Toggles blade on/off in forge
	public static boolean toggleBlade(Saber customSaber, VBox customSaberBox, 
			Image coloredEmitter, Image emitter, boolean saberIsOn) throws URISyntaxException {
		if (saberIsOn == false) {
			ImageView coloredEmitterView = new ImageView(coloredEmitter);
			customSaberBox.getChildren().set(0, coloredEmitterView);
			saberIsOn = true;
			MediaPlayer ignite = new MediaPlayer(customSaber.getIgnite());
			ignite.play();
			hum = new MediaPlayer(customSaber.getHum());
			hum.setOnEndOfMedia(new Runnable() {
				public void run() {
					hum.seek(Duration.ZERO);
				}
			});
			hum.play();
		}
		else {
			ImageView emitterView = new ImageView(emitter);
			customSaberBox.getChildren().set(0, emitterView);
			saberIsOn = false;
			MediaPlayer deactivate = new MediaPlayer(customSaber.getDeactivate());
			deactivate.play();
			hum.stop();
		}
		return saberIsOn;
	}
} 
