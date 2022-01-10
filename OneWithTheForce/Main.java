import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;

public class Main extends Application {
	/*
	 * --------------------------
	 *  Level Fields and Methods
	 * --------------------------
	 */
	
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	
	private ArrayList<Node> platforms = new ArrayList<Node>();
	
	private ArrayList<Node> platformEnds = new ArrayList<Node>();
	
	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();
	
	private Node player;
	private Point2D playerVelocity = new Point2D(0, 0);
	private boolean canJump = true;
	
	private int levelWidth;
	
	private void initContent() {
		Rectangle background = new Rectangle(1280, 720);
		
		levelWidth = LevelData.LEVEL1[0].length() * 60;
		
		for (int i = 0; i < LevelData.LEVEL1.length; i++) {
			String line = LevelData.LEVEL1[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
					case '0':
						break;
					case '1':
						Node platform = createEntity(j*60, i*60, 60, 60, Color.BROWN, false);
						platforms.add(platform);
						break;
					case '2':
						Node platformEnd = createEntity(j*60, i*60, 60, 60, Color.WHITE, false);
						platformEnds.add(platformEnd);
						break;
				}
			}
		}
		
		player = createEntity(200, 0, 40, 40, Color.BLUE, true);
		
		player.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			
			if (offset > 640 && offset < levelWidth - 640) {
				gameRoot.setLayoutX(-(offset - 640));
			}
		});
		
		player.translateYProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			
			if (offset > 450 && offset < levelWidth - 450) {
				gameRoot.setLayoutY(-(offset - 450));
			}
		});
		
		appRoot.getChildren().addAll(background, gameRoot, uiRoot);
	}
	
	private void update() {
		if (isPressed(KeyCode.SPACE) && player.getTranslateY() >= 5) {
			jumpPlayer();
		}
		
		if (isPressed(KeyCode.A) && player.getTranslateX() >= 5) {
			movePlayerX(-5);
		}
		
		if (isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth - 5) {
			movePlayerX(5);
		}
		
		if (playerVelocity.getY() < 10) {
			playerVelocity = playerVelocity.add(0, 1);
		}
		
		movePlayerY((int)playerVelocity.getY());
	}
	
	// ! FIX THE COLLISION DETECTION !
	private void movePlayerX(int value) {
		boolean movingRight = value > 0;
		
		for (int i = 0; i < Math.abs(value); i++) {
			// Platforms
			for (Node platform : platforms) {
				if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (player.getTranslateX() + 40 == platform.getTranslateX()) {
							//return;
						}
					}
					else {
						if (player.getTranslateX() == platform.getTranslateX() + 60) {
							//return;
						}
					}
				}
			}
			
			// Platform ends
			for (Node platformEnd : platformEnds) {
				if (player.getBoundsInParent().intersects(platformEnd.getBoundsInParent())) {
					if (movingRight) {
						if (player.getTranslateX() + 40 == platformEnd.getTranslateX()) {
							return;
						}
					}
					else {
						if (player.getTranslateX() == platformEnd.getTranslateX() + 60) {
							return;
						}
					}
				}
			}
			
			player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
		}
	}
	
	private void movePlayerY(int value) {
		boolean movingDown = value > 0;
		// Platforms
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingDown) {
						if (player.getTranslateY() + 40 == platform.getTranslateY()) {
							canJump = true;
							return;
						}
					}
					else {
						if (player.getTranslateY() == platform.getTranslateY() + 60) {
							return;
						}
					}
				}
			}
			
			// Platform ends
			for (Node platformEnd : platformEnds) {
				if (player.getBoundsInParent().intersects(platformEnd.getBoundsInParent())) {
					if (movingDown) {
						if (player.getTranslateY() + 40 == platformEnd.getTranslateY()) {
							canJump = true;
							return;
						}
					}
					else {
						if (player.getTranslateY() == platformEnd.getTranslateY() + 60) {
							return;
						}
					}
				}
			}
			
			player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
		}
	}
	
	private void jumpPlayer() {
		if (canJump) {
			playerVelocity = playerVelocity.add(0, -30);
			canJump = false;
		}
	}
	
	private Node createEntity(int x, int y, int w, int h, Color color, boolean isPlayer) {
		if (isPlayer) {
			StackPane test = new StackPane();
			
			Rectangle entity = new Rectangle(w, h);
			entity.setTranslateX(x);
			entity.setTranslateY(y);
			entity.setFill(color);
			
			VBox saberBox = new VBox();
			saberBox.getChildren().addAll(new ImageView(selectedSaber.getColoredEmitter()), 
					new ImageView(selectedSaber.getGuard()), 
					new ImageView(selectedSaber.getBladeSwitch()), 
					new ImageView(selectedSaber.getPommel()));
			
			VBox entityBox = new VBox(10);
			entityBox.getChildren().addAll(entity, saberBox);
			
			//test.getChildren().addAll(entity, saberBox);
			
			gameRoot.getChildren().add(entityBox);
			return entityBox;
		}
		else {
			Rectangle entity = new Rectangle(w, h);
			entity.setTranslateX(x);
			entity.setTranslateY(y);
			entity.setFill(color);

			gameRoot.getChildren().add(entity);
			return entity;
		}
		
	}
	
	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}
	
	/*
	 * -------------
	 *  Menu Fields
	 * -------------
	 */
	
	// Scenes
	Scene mainMenuScene, optionsScene, startMenuScene, levelSelectScene, 
		smithyMenuScene, forgeScene, galleryScene, previewScene, editScene;
	static Scene levelOneScene;
	static Scene saberSelectScene;
	
	// BorderPanes for saber preview, saber editing, and saber selection
	BorderPane previewBorderPane, editBorderPane;
	static BorderPane saberSelectBorderPane;
	
	// FlowPane for saber select buttons
	FlowPane saberSelectFlowPane;
	
	// Boolean for toggling saber
	static boolean saberIsOn = false;
	
	// Boolean for starting levels
	static Boolean startLevel = false;
	
	// Int for tracking selected level
	int selectedLevel;
	
	// MediaPlayers for saber sounds
	static MediaPlayer clash1, clash2, clash3, deactivate, deflect, hum, 
		ignite, swoosh1, swoosh2, swoosh3;
	
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
	ArrayList<Saber> allSabers = new ArrayList<>();
	
	// Saber selected by player
	static Saber selectedSaber;
	
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
		btSmithy.setOnAction(e -> primaryStage.setScene(smithyMenuScene));
		
		// Navigate to main menu
		Button btBack = new Button ("Back");
		btBack.setOnAction(e -> primaryStage.setScene(mainMenuScene));
		
		VBox startButtonBox = new VBox(20);
		startButtonBox.setAlignment(Pos.CENTER);
		startButtonBox.getChildren().addAll(btLevels, btSmithy, btBack);
		
		startFlowPane.getChildren().addAll(startTitleBox, startButtonBox);
		
		// Set scene
		startMenuScene = new Scene(startFlowPane, 1280, 720);
		
		
		/* LEVEL SELECT SCENE */
		BorderPane levelSelectBorderPane = new BorderPane();
		FlowPane levelSelectFlowPane = new FlowPane(100, 100);
		levelSelectFlowPane.setPadding(new Insets(50, 150, 50, 150));
		
		// Level select title
		HBox levelSelectTitleBox = new HBox(new Text("Select a Level"));
		levelSelectTitleBox.setAlignment(Pos.CENTER);
		
		// Navigate back to start menu
		Button btBackLevelSelect = new Button("<--");
		btBackLevelSelect.setOnAction(e -> primaryStage.setScene(startMenuScene));
		HBox levelSelectButtonBox = new HBox();
		levelSelectButtonBox.setAlignment(Pos.TOP_LEFT);
		levelSelectButtonBox.getChildren().add(btBackLevelSelect);
		
		// Display levels
		Button btLevelOne = new Button();
		btLevelOne.setPrefSize(200, 200);
		btLevelOne.setOnAction(e -> {
			selectedLevel = 1;
			selectSaber(primaryStage, allSabers, saberSelectFlowPane, selectedLevel);
		});
		Label lblLevelOne = new Label("Level 1", btLevelOne);
		lblLevelOne.setContentDisplay(ContentDisplay.TOP);
		
		Button btLevelTwo = new Button();
		btLevelTwo.setPrefSize(200, 200);
		Label lblLevelTwo = new Label("Level 2", btLevelTwo);
		lblLevelTwo.setContentDisplay(ContentDisplay.TOP);
		
		Button btLevelThree = new Button();
		btLevelThree.setPrefSize(200, 200);
		Label lblLevelThree = new Label("Level 3", btLevelThree);
		lblLevelThree.setContentDisplay(ContentDisplay.TOP);
		
		Button btLevelFour = new Button();
		btLevelFour.setPrefSize(200, 200);
		Label lblLevelFour = new Label("Level 4", btLevelFour);
		lblLevelFour.setContentDisplay(ContentDisplay.TOP);
		
		Button btLevelFive = new Button();
		btLevelFive.setPrefSize(200, 200);
		Label lblLevelFive = new Label("Level 5", btLevelFive);
		lblLevelFive.setContentDisplay(ContentDisplay.TOP);
		
		Button btLevelSix = new Button();
		btLevelSix.setPrefSize(200, 200);
		Label lblLevelSix = new Label("Level 6", btLevelSix);
		lblLevelSix.setContentDisplay(ContentDisplay.TOP);
		
		// Add levels to FlowPane
		levelSelectFlowPane.getChildren().addAll(btLevelOne, lblLevelOne, btLevelTwo, 
				lblLevelTwo, btLevelThree, lblLevelThree, btLevelFour, lblLevelFour, 
				btLevelFive, lblLevelFive, btLevelSix, lblLevelSix);
		
		levelSelectBorderPane.setTop(levelSelectTitleBox);
		levelSelectBorderPane.setLeft(levelSelectButtonBox);
		levelSelectBorderPane.setCenter(levelSelectFlowPane);
		
		// Create scene
		levelSelectScene = new Scene(levelSelectBorderPane, 1280, 720);
		
		
		/* SABER SELECT SCENE */
		saberSelectBorderPane = new BorderPane();
		saberSelectFlowPane = new FlowPane(100, 100);
		saberSelectFlowPane.setPadding(new Insets(50, 150, 50, 150));
		
		// Selected saber by default
		selectedSaber = allSabers.get(0);
		
		// Place saberSelectFlowPane in a ScrollPane
		ScrollPane saberSelectScrollPane = new ScrollPane(saberSelectFlowPane);
		saberSelectScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		saberSelectScrollPane.setFitToWidth(true);
		saberSelectScrollPane.setFitToHeight(true);
		
		// Saber select title
		HBox saberSelectTitleBox = new HBox(new Text("Select a Saber"));
		saberSelectTitleBox.setAlignment(Pos.CENTER);
		
		// Navigate back to level select
		Button btBackSaberSelect = new Button("<--");
		btBackSaberSelect.setOnAction(e -> primaryStage.setScene(levelSelectScene));
		HBox saberSelectButtonBox = new HBox(btBackSaberSelect);
		saberSelectButtonBox.setAlignment(Pos.TOP_LEFT);
		
		saberSelectBorderPane.setTop(saberSelectTitleBox);
		saberSelectBorderPane.setLeft(saberSelectButtonBox);
		saberSelectBorderPane.setCenter(saberSelectScrollPane);
		
		// Create scene
		saberSelectScene = new Scene(saberSelectBorderPane ,1280, 720);
		
		
		
		
		/*
		 * -----------
		 *  LEVEL ONE
		 * -----------
		 */

		// Create scene
		initContent();
		levelOneScene = new Scene(appRoot);
		levelOneScene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
		levelOneScene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		timer.start();
		
		
		
		/*
		 * ----------------------
		 *  SABERSMITHY REFORGED
		 * ----------------------
		 */
		
		/* SMITHY MAIN MENU */
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
		
		// Navigate to start menu
		Button btBackSmithy = new Button("<--");
		btBackSmithy.setPrefSize(100, 75);
		btBack.setAlignment(Pos.CENTER);
		btBackSmithy.setOnAction(e -> primaryStage.setScene(startMenuScene));
		
		HBox smithyButtonBox = new HBox(200);
		smithyButtonBox.setAlignment(Pos.CENTER);
		smithyButtonBox.getChildren().addAll(btForge, btGallery);
		
		smithyFlowPane.getChildren().addAll(smithyTitleBox, smithyButtonBox, btBackSmithy);
		
		// Set primary stage
		smithyMenuScene = new Scene(smithyFlowPane, 1280, 720);
		
		
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
				clash1 = new MediaPlayer(saber.getClash1());
				clash1.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btClash2 = new Button("Clash2");
		btClash2.setOnAction(e -> {
			try {
				clash2 = new MediaPlayer(saber.getClash2());
				clash2.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btClash3 = new Button("Clash3");
		btClash3.setOnAction(e -> {
			try {
				clash3 = new MediaPlayer(saber.getClash3());
				clash3.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btDeflect = new Button("Deflect");
		btDeflect.setOnAction(e -> {
			try {
				deflect = new MediaPlayer(saber.getDeflect());
				deflect.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btSwoosh1 = new Button("Swoosh1");
		btSwoosh1.setOnAction(e -> {
			try {
				swoosh1 = new MediaPlayer(saber.getSwoosh1());
				swoosh1.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btSwoosh2 = new Button("Swoosh2");
		btSwoosh2.setOnAction(e -> {
			try {
				swoosh2 = new MediaPlayer(saber.getSwoosh2());
				swoosh2.play();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		Button btSwoosh3 = new Button("Swoosh3");
		btSwoosh3.setOnAction(e -> {
			try {
				swoosh3 = new MediaPlayer(saber.getSwoosh3());
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
			ignite = new MediaPlayer(customSaber.getIgnite());
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
			deactivate = new MediaPlayer(customSaber.getDeactivate());
			deactivate.play();
			hum.stop();
		}
		return saberIsOn;
	}
	
	// Saber select for level
	public static void selectSaber(Stage primaryStage, ArrayList<Saber> allSabers, 
			FlowPane saberSelectFlowPane, int selectedLevel) {
		// Set stage
		primaryStage.setScene(saberSelectScene);
		
		// Begin level
		Button btBegin = new Button("Begin");
		btBegin.setOnAction(e -> {
			switch (selectedLevel) {
				case 1:
					primaryStage.setScene(levelOneScene);
					break;
				case 2:
					System.out.println("Level 2 is currently unavailable");
					break;
				case 3:
					System.out.println("Level 3 is currently unavailable");
					break;
				case 4:
					System.out.println("Level 4 is currently unavailable");
					break;
				case 5:
					System.out.println("Level 5 is currently unavailable");
					break;
				case 6:
					System.out.println("Level 6 is currently unavailable");
					break;
			}
		});
		
		HBox saberSelectButtonBox = new HBox(btBegin);
		saberSelectButtonBox.setAlignment(Pos.CENTER);
		
		saberSelectBorderPane.setBottom(saberSelectButtonBox);
		
		// Clear FlowPane
		saberSelectFlowPane.getChildren().clear();
		
		// Display all sabers
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
			formatBox.setPrefSize(200, 200);
			formatBox.setMaxSize(200, 200);
					
			// Dispaly saber's color
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
					
			Button btSaber = new Button();
			btSaber.setGraphic(formatBox);
			int f = i;
			btSaber.setOnAction(e -> {
				selectedSaber = allSabers.get(f);
			});
			Label lblSaber = new Label(allSabers.get(i).getName(), btSaber);
			lblSaber.setContentDisplay(ContentDisplay.TOP);
					
			saberSelectFlowPane.getChildren().addAll(btSaber, lblSaber);
		}
	}
}
