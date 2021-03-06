package View;

import Controller.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelThree implements Observer {
	Game game = Game.getInstance();
	private Stage stage;
	Timeline timeline;
	Group root = new Group();
	Scene Scene3;
	boolean isSlicedgui;
	int score;
	int Live = 3;
	ImageView life1;
	ImageView life2;
	ImageView life3;
	public LevelThree(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void update() {
		score = game.getScore();
		game.getWriter().writeScore3(score);
		ShowScore();
	}

	public void ShowInstructions() {
		ImageView instructionView = new ImageView("file:instructions_burned.PNG");
		instructionView.setLayoutX(450);
		instructionView.setLayoutY(200);
		instructionView.setFitWidth(470);
		Text info = new Text(game.level.getInstuctions());
		info.setLayoutX(480);
		info.setLayoutY(450);
		info.setFont(Font.font(Font.getFontNames().get(0), FontWeight.EXTRA_BOLD, 22));
		ImageView exitView = new ImageView("file:exitLabel-removebg.png");
		Button exitButton = new Button();
		exitButton.setGraphic(exitView);
		exitButton.setLayoutX(490);
		exitButton.setLayoutY(220);
		exitView.setFitWidth(50);
		exitView.setFitHeight(50);
		exitButton.setStyle("-fx-background-color: transparent;");
		root.getChildren().add(instructionView);
		root.getChildren().add(info);
		root.getChildren().add(exitButton);
		instructionView.setVisible(true);
		info.setVisible(true);
		exitButton.setVisible(true);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline.play();

				timer timer = new timer();
				timer.start(stage, Scene3, root);
				timer.button.fire();
				instructionView.setVisible(false);
				info.setVisible(false);
				exitButton.setVisible(false);

			}
		});

	}

	public void ShowScore() {
		ImageView board = new ImageView("file:score board.PNG");
		board.setFitWidth(200);
		board.setFitHeight(200);
		board.setLayoutX(230);
		board.setLayoutY(-30);
		root.getChildren().add(board);
		int x = game.getScore();
		Text score = new Text("Score " + x);
		score.setLayoutX(250);
		score.setLayoutY(120);
		score.setFont(Font.font(Font.getFontNames().get(0), FontWeight.EXTRA_BOLD, 40));
		root.getChildren().add(score);

	}

	public void prepareReset() {
		ImageView exitView = new ImageView("file:RESET.png");
		Button exitButton = new Button();
		exitButton.setGraphic(exitView);
		exitButton.setLayoutX(800);
		// exitButton.setLayoutY(220);
		exitView.setFitWidth(50);
		exitView.setFitHeight(50);
		exitButton.setStyle("-fx-background-color: transparent;");
		root.getChildren().add(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline.stop();
				game.getWriter().writeScore3(game.getScore());
				starting back = new starting(stage);
				game.ResetGame();
				game.setScore(0);
				back.PrepareScene();
			}
		});

	}

	public void PrepareLevelThree() {

		ImageView background = new ImageView("file:background-scene---image.PNG");
		background.setFitHeight(800);
		background.setFitWidth(1400);
		root.getChildren().add(background);
		Scene3 = new Scene(root, 1400, 800);
		ShowInstructions();
		ShowScore();
		Lives object = Lives.getinstance();
		object.Lives = 3;
		game.setLives(3);
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), (event -> {
			int i = 0;
			if (i < game.level.getArrayList().size()) {

				if (game.level.getArrayList().get(i).getFruitName().equalsIgnoreCase("Pomegranate")) {

					PreparePomegranate obj4 = new PreparePomegranate(root, Scene3);
					obj4.prepare();
					prepareReset();
				} else if (game.level.getArrayList().get(i).getFruitName().equalsIgnoreCase("Watermelon")) {
					PrepareWatermelon obj1 = new PrepareWatermelon(root, Scene3);
					obj1.prepare();
					prepareReset();
				} else if (game.level.getArrayList().get(i).getFruitName().equalsIgnoreCase("Pineapple")) {

					PreparePineapple obj2 = new PreparePineapple(root, Scene3);
					obj2.prepare();
					prepareReset();
				} else if (game.level.getArrayList().get(i).getFruitName().equalsIgnoreCase("Strawberry")) {

					PrepareStrawberry obj3 = new PrepareStrawberry(root, Scene3);
					obj3.prepare();
					prepareReset();
				}

				else if (game.level.getArrayList().get(i).getFruitName().equalsIgnoreCase("FatalBombs")) {
					PrepareFatalBomb obj4 = new PrepareFatalBomb(root, Scene3);
					obj4.prepare();
					prepareReset();
				} else if (game.level.getArrayList().get(i).getFruitName().equalsIgnoreCase("BananaX2")) {
					PrepareBananaX2 obj5 = new PrepareBananaX2(root, Scene3);
					obj5.prepare();
					prepareReset();
				} else if (game.level.getArrayList().get(i).getFruitName().equalsIgnoreCase("DangerousBombs")) {
					PrepareDangrousBomb obj6 = new PrepareDangrousBomb(root, Scene3);
					obj6.prepare();
					prepareReset();
				}

				else if (game.level.getArrayList().get(i).getFruitName().equalsIgnoreCase("DragonFruit")) {
					PrepareDragonFruit obj6 = new PrepareDragonFruit(root, Scene3);
					obj6.prepare();
					prepareReset();
				}

				game.level.removeFruit(game.level.getArrayList().get(i));
				game.level.addFruit();
				i++;

			}
		}

		)));
		timeline.setCycleCount(Animation.INDEFINITE);
		life1 = new ImageView("file:lives.png");
		life1.setFitWidth(60);
		life1.setFitHeight(60);
		life1.setLayoutX(1300);
		life2 = new ImageView("file:lives.png");
		life2.setFitWidth(60);
		life2.setFitHeight(60);
		life2.setLayoutX(1300 - 50);
		life3 = new ImageView("file:lives.png");
		life3.setFitWidth(60);
		life3.setFitHeight(60);
		life3.setLayoutX(1300 - 50 - 50);
		root.getChildren().add(life1);
		root.getChildren().add(life2);
		root.getChildren().add(life3);
		object.setlives(life1, life2, life3, Live, timeline, root);
		stage.setScene(Scene3);
		stage.show();

	}

}
