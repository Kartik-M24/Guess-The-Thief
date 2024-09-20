package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.AudioManager.AudioType;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class SecurityFootageController extends MasterController {

  @FXML private ImageView rightButton;

  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.CCTVSTOP, 0.1);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
  }

  /**
   * Handles mouse clicks on right button, moves them to next scene.
   *
   * @param event the mouse event triggered by clicking an image
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void nextSceneRight(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.SECURITYCAMERA, 0.4);
    Scene scene = ((ImageView) event.getSource()).getScene();
    scene.setRoot(SceneManager.getUiRoot(AppUi.SECURITYFOOTAGEAFTER));
  }

  /**
   * Handles mouse clicks on left button, moves them to next scene.
   *
   * @param event the mouse event triggered by clicking an image
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void nextSceneLeft(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.SECURITYCAMERA, 0.4);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.SECURITYFOOTAGEBEFORE));
  }
}
