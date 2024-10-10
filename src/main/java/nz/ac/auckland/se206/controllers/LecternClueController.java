package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.GameStateContext;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class LecternClueController extends MasterController {

  private static GameStateContext context = new GameStateContext();
  @FXML private ImageView letterClueGlow;

  /**
   * Initializes the controller. Sets up the timeline for the timer and hides the letter clue glow.
   */
  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    letterClueGlow.setVisible(false);
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.WALKING, 0.8);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
  }

  /**
   * Handles mouse clicks on rectangles representing clues in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleClueClick(MouseEvent event) throws IOException {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    context.handleClueClick(event, clickedRectangle.getId());
  }

  /**
   * Handles the mouse entering a rectangle representing a clue. Changes the cursor to a hand and
   * makes the letter clue glow visible.
   *
   * @param event the mouse event triggered by entering a rectangle
   */
  @FXML
  private void onMouseEnteredL(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    letterClueGlow.setVisible(true);
  }

  /**
   * Handles the mouse exiting a rectangle representing a clue. Hides the letter clue glow.
   *
   * @param event the mouse event triggered by exiting a rectangle
   */
  @FXML
  private void onMouseExitedL(MouseEvent event) {
    letterClueGlow.setVisible(false);
  }
}
