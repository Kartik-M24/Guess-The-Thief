package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.AudioManager.AudioType;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class SecurityFootageController {

  @FXML private Label timer;
  @FXML private ImageView rightButton;
  private TimerManager timerManager = TimerManager.getInstance();
  private AudioManager audioManager = new AudioManager();

  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
  }

  public void updateTimer() {
    if (timerManager.isTimeUp()
        && !MainSceneController.isUserAtGuessingScene
        && !GuessingSceneController.isUserAtExplanationScene) {
      timerManager.setTime(1, 0, 0);
      audioManager.playAudio(AudioManager.AudioType.TIMESUP, 0.5);
      try {
        App.setRoot("guessingscene");
      } catch (IOException e) {
        e.printStackTrace();
      }
      MainSceneController.isUserAtGuessingScene = true;
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

  /**
   * Handles mouse hover on image
   *
   * @param event the mouse event triggered by clicking a rectangle
   */
  @FXML
  private void onMouseEnteredImage(MouseEvent event) {
    ImageView clickedRectangle = (ImageView) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
  }
}
