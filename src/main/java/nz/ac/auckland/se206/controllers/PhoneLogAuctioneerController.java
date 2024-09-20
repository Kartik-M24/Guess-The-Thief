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

public class PhoneLogAuctioneerController extends MasterController {

  @FXML private ImageView imgPolice;
  @FXML private ImageView imgFriend;
  @FXML private ImageView nextCollectorButton;
  @FXML private ImageView nextArchaeologistButton;

  @FXML private Rectangle rectFriend;
  @FXML private Rectangle rectPolice;
  @FXML private Rectangle backButton;

  /** Initializes the letter clue view. */
  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleSuspectsClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.PAGEFLIP, 0.8);
    backButton.setDisable(true);
    imgPolice.setOpacity(0);
    imgFriend.setOpacity(0);
    rectFriend.setDisable(false);
    rectPolice.setDisable(false);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.SUSPECTSSELECTION));
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleNextArchaeologistClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.PHONEBACK, 0.8);
    imgPolice.setOpacity(0);
    imgFriend.setOpacity(0);
    rectFriend.setDisable(false);
    rectPolice.setDisable(false);
    backButton.setDisable(true);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.PHONELOGARCHAEOLOGIST));
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleNextCollectorClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.PHONEBACK, 0.8);
    imgPolice.setOpacity(0);
    imgFriend.setOpacity(0);
    rectFriend.setDisable(false);
    rectPolice.setDisable(false);
    backButton.setDisable(true);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.PHONELOGCOLLECTOR));
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleFriendClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.PHONENEXT, 0.8);
    imgPolice.setOpacity(0);
    imgFriend.setOpacity(1);
    backButton.setDisable(false);
    rectFriend.setDisable(true);
    rectPolice.setDisable(true);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handlePoliceClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.PHONENEXT, 0.8);
    imgPolice.setOpacity(1);
    imgFriend.setOpacity(0);
    backButton.setDisable(false);
    rectFriend.setDisable(true);
    rectPolice.setDisable(true);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleBackClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.PHONENEXT, 0.8);
    imgPolice.setOpacity(0);
    imgFriend.setOpacity(0);
    backButton.setDisable(true);
    rectFriend.setDisable(false);
    rectPolice.setDisable(false);
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
    imgPolice.setOpacity(0);
    imgFriend.setOpacity(0);
    rectFriend.setDisable(false);
    rectPolice.setDisable(false);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
    backButton.setDisable(true);
  }
}
