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
import nz.ac.auckland.se206.AudioManager.AudioType;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class PhoneLogArchaeologistController extends MasterController {
  @FXML private ImageView imgColleague;
  @FXML private ImageView imgVictor;
  @FXML private Rectangle rectVictor;
  @FXML private Rectangle rectColleague;
  @FXML private Rectangle backButton;

  /** Initializes the letter clue view. */
  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
  }

  /**
   * Handles mouse click to go to the Crime Scene.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.PHONEBACK, 0.8);
    imgColleague.setOpacity(0);
    imgVictor.setOpacity(0);
    rectVictor.setDisable(false);
    rectColleague.setDisable(false);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
    backButton.setDisable(true);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleSuspectsClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.PAGEFLIP, 0.8);
    imgColleague.setOpacity(0);
    imgVictor.setOpacity(0);
    rectVictor.setDisable(false);
    rectColleague.setDisable(false);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.SUSPECTSSELECTION));
    backButton.setDisable(true);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleVictorClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.PHONENEXT, 0.8);
    imgColleague.setOpacity(0);
    imgVictor.setOpacity(1);
    rectVictor.setDisable(true);
    rectColleague.setDisable(true);
    backButton.setDisable(false);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleColleagueClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.PHONENEXT, 0.8);
    imgColleague.setOpacity(1);
    imgVictor.setOpacity(0);
    rectVictor.setDisable(true);
    rectColleague.setDisable(true);
    backButton.setDisable(false);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleBackClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.PHONENEXT, 0.8);
    imgColleague.setOpacity(0);
    imgVictor.setOpacity(0);
    backButton.setDisable(true);
    rectVictor.setDisable(false);
    rectColleague.setDisable(false);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleNextAuctioneerClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.PHONEBACK, 0.8);
    imgColleague.setOpacity(0);
    imgVictor.setOpacity(0);
    rectVictor.setDisable(false);
    rectColleague.setDisable(false);
    backButton.setDisable(true);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.PHONELOGAUCTIONEER));
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleNextCollectorClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioType.PHONEBACK, 0.8);
    imgColleague.setOpacity(0);
    imgVictor.setOpacity(0);
    rectVictor.setDisable(false);
    rectColleague.setDisable(false);
    backButton.setDisable(true);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.PHONELOGCOLLECTOR));
  }
}
