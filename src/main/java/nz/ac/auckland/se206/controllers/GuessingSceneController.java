package nz.ac.auckland.se206.controllers;

import java.io.IOException; // Add this import statement
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class GuessingSceneController extends MasterController {

  public static boolean isUserAtExplanationScene = false;

  @FXML private Rectangle rectArchaeologist;
  @FXML private Rectangle rectAuctioneer;
  @FXML private Rectangle rectCollector;

  /** Initializes the suspect selection view. */
  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
  }

  @Override
  public void updateTimer() {
    if (timerManager.isTimeUp() && MainSceneController.isUserAtGuessingScene) {
      try {
        App.setRoot("gameover");
      } catch (IOException e) {
        e.printStackTrace();
      }
      MainSceneController.isUserAtGuessingScene = false;
    }
    timer.setText(timerManager.getFormattedTime());
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleArchaeologistClick(MouseEvent event) throws IOException {
    MainSceneController.isUserAtGuessingScene = false;
    Rectangle button = (Rectangle) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.GAMEOVER));
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleAuctioneerClick(MouseEvent event) throws IOException {
    MainSceneController.isUserAtGuessingScene = false;
    Rectangle button = (Rectangle) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.GAMEOVER));
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCollectorClick(MouseEvent event) throws IOException {
    MainSceneController.isUserAtGuessingScene = false;
    isUserAtExplanationScene = true;
    Rectangle button = (Rectangle) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.CRIMEEXPLANATION));
  }
}
