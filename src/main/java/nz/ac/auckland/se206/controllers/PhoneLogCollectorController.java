package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class PhoneLogCollectorController {
  @FXML private ImageView imgStaff;
  @FXML private ImageView imgLoan;
  @FXML private ImageView imgSamuel;
  @FXML private Rectangle rectLoan;
  @FXML private Rectangle rectStaff;
  @FXML private Rectangle rectSamuel;
  @FXML private Rectangle backButton;

  @FXML private Label timer;
  private TimerManager timerManager = TimerManager.getInstance();

  /** Initializes the letter clue view. */
  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
  }

  public void updateTimer() {
    timer.setText(timerManager.getFormattedTime());
  }

  /**
   * Handles mouse click to go to the Crime Scene.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
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

  /**
   * Handles mouse hover on rectangles to help identify clues
   *
   * @param event the mouse event triggered by clicking a rectangle
   */
  @FXML
  private void onMouseEntered(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
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
