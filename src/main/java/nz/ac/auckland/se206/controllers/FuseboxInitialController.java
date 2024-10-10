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
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class FuseboxInitialController extends MasterController {

  @FXML private Rectangle lock;
  @FXML private ImageView lockClueGlow;

  /** Initializes the fuse box view. */
  @FXML
  public void initialize() {
    // Inirialises all the necessary fields and initialises the timeline
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    lockClueGlow.setVisible(false);
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.FUSEBOXCLOSE, 1);
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
  private void handleRectClick(MouseEvent event) throws IOException {
    // Add audio for clicking on the lock
    Rectangle button = (Rectangle) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.LOCKSCENE));
  }

  @FXML
  private void onMouseEnteredL() {
    lockClueGlow.setVisible(true);
  }

  @FXML
  private void onMouseExitedL() {
    lockClueGlow.setVisible(false);
  }
}
