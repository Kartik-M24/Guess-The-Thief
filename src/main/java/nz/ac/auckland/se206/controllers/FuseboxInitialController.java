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
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

/**
 * Controls the initial fuse box scene, managing user interactions with the lock clue. Handles mouse
 * events for clicking on the lock and changing the cursor when hovering over the rectangle
 * representing the lock. Updates the scene based on user actions.
 */
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

  /**
   * Changes the cursor to a hand cursor and makes the lock clue glow visible when the mouse enters
   * a rectangle.
   *
   * @param event the mouse event triggered by entering a rectangle
   */
  @FXML
  private void onMouseEnteredL(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    lockClueGlow.setVisible(true);
  }

  /**
   * Hides the lock clue glow when the mouse exits a rectangle.
   *
   * @param event the mouse event triggered by exiting a rectangle
   */
  @FXML
  private void onMouseExitedL(MouseEvent event) {
    lockClueGlow.setVisible(false);
  }
}
