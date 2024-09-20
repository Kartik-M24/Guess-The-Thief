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

public class PhoneLogCollectorController extends MasterController {
  @FXML private ImageView imgStaff;
  @FXML private ImageView imgLoan;
  @FXML private ImageView imgSamuel;
  @FXML private Rectangle rectLoan;
  @FXML private Rectangle rectStaff;
  @FXML private Rectangle rectSamuel;
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
    // Redirects the user to the crime scene when clicked
    audioManager.playAudio(AudioManager.AudioType.PHONEBACK, 0.8);
    imgStaff.setOpacity(0);
    imgLoan.setOpacity(0);
    imgSamuel.setOpacity(0);
    rectLoan.setDisable(false);
    rectStaff.setDisable(false);
    rectSamuel.setDisable(false);
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
    // Redirects the user to the suspects selection scene
    audioManager.playAudio(AudioManager.AudioType.PAGEFLIP, 0.8);
    imgStaff.setOpacity(0);
    imgLoan.setOpacity(0);
    imgSamuel.setOpacity(0);
    rectLoan.setDisable(false);
    rectStaff.setDisable(false);
    rectSamuel.setDisable(false);
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
  private void handleLoanClick(MouseEvent event) throws IOException {
    // Handles clicking the loan shark and leads player to the chat scene
    audioManager.playAudio(AudioManager.AudioType.PHONENEXT, 0.8);
    imgStaff.setOpacity(0);
    imgLoan.setOpacity(1);
    imgSamuel.setOpacity(0);
    rectLoan.setDisable(true);
    rectStaff.setDisable(true);
    rectSamuel.setDisable(true);
    backButton.setDisable(false);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleStaffClick(MouseEvent event) throws IOException {
    // Handles clicking the staff and leads player to the chat scene
    audioManager.playAudio(AudioManager.AudioType.PHONENEXT, 0.8);
    imgStaff.setOpacity(1);
    imgLoan.setOpacity(0);
    imgSamuel.setOpacity(0);
    rectLoan.setDisable(true);
    rectStaff.setDisable(true);
    rectSamuel.setDisable(true);
    backButton.setDisable(false);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleSamuelClick(MouseEvent event) throws IOException {
    // Handles clicking the samuel doctor and leads player to the chat scene
    audioManager.playAudio(AudioManager.AudioType.PHONENEXT, 0.8);
    imgStaff.setOpacity(0);
    imgLoan.setOpacity(0);
    imgSamuel.setOpacity(1);
    rectLoan.setDisable(true);
    rectStaff.setDisable(true);
    rectSamuel.setDisable(true);
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
    // Handles clicking the back button and leads player to the messages scene
    audioManager.playAudio(AudioManager.AudioType.PHONENEXT, 0.8);
    imgStaff.setOpacity(0);
    imgLoan.setOpacity(0);
    imgSamuel.setOpacity(0);
    backButton.setDisable(true);
    rectLoan.setDisable(false);
    rectStaff.setDisable(false);
    rectSamuel.setDisable(false);
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleNextArchaeologistClick(MouseEvent event) throws IOException {
    // handles clicking on the archaeologist to move to the archaeologist
    audioManager.playAudio(AudioManager.AudioType.PHONEBACK, 0.8);
    imgStaff.setOpacity(0);
    imgLoan.setOpacity(0);
    imgSamuel.setOpacity(0);
    rectLoan.setDisable(false);
    rectStaff.setDisable(false);
    rectSamuel.setDisable(false);
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
  private void handleNextAuctioneerClick(MouseEvent event) throws IOException {
    // Handles moving the player to the auctioneer
    audioManager.playAudio(AudioManager.AudioType.PHONEBACK, 0.8);
    imgStaff.setOpacity(0);
    imgLoan.setOpacity(0);
    imgSamuel.setOpacity(0);
    rectLoan.setDisable(false);
    rectStaff.setDisable(false);
    rectSamuel.setDisable(false);
    backButton.setDisable(true);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.PHONELOGAUCTIONEER));
  }
}
