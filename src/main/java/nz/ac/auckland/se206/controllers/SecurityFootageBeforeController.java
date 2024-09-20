package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.AudioManager.AudioType;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class SecurityFootageBeforeController extends MasterController {

  @FXML private Circle suspect1Circle;
  @FXML private Circle suspect2Circle;
  @FXML private Line suspect1Line;
  @FXML private Line suspect2Line;
  @FXML private ImageView suspect1Image;
  @FXML private ImageView suspect2Image;
  @FXML private ImageView missingImage;
  private AudioManager audioManager = new AudioManager();

  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    suspect1Circle.setVisible(false);
    suspect2Circle.setVisible(false);
    suspect1Line.setVisible(false);
    suspect2Line.setVisible(false);
    suspect1Image.setVisible(false);
    suspect2Image.setVisible(false);
    missingImage.setVisible(false);
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
   * Displays the suspect 1 image and line when the user hovers over the suspect 1 circle.
   *
   * @param event the mouse event triggered by clicking on the circle
   */
  @FXML
  private void displaySuspect1(MouseEvent event) {
    suspect1Circle.setVisible(true);
    suspect1Line.setVisible(true);
    suspect1Image.setVisible(true);
  }

  /**
   * Displays the suspect 2 image and line when the user hovers over the suspect 2 circle.
   *
   * @param event the mouse event triggered by clicking on the circle
   */
  @FXML
  private void displaySuspect2(MouseEvent event) {
    suspect2Circle.setVisible(true);
    suspect2Line.setVisible(true);
    suspect2Image.setVisible(true);
    missingImage.setVisible(true);
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
    scene.setRoot(SceneManager.getUiRoot(AppUi.SECURITYFOOTAGE));
  }

  /**
   * Handles mouse clicks on left button, moves them to next scene.
   *
   * @param event the mouse event triggered by clicking an image
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void nextSceneLeft(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.SECURITYCAMERA, 0.7);
    Scene scene = ((ImageView) event.getSource()).getScene();
    scene.setRoot(SceneManager.getUiRoot(AppUi.SECURITYFOOTAGEAFTER));
  }
}
