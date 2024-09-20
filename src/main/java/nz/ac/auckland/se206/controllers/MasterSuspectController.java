package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class MasterSuspectController {

  @FXML protected Label timer;

  protected TimerManager timerManager = TimerManager.getInstance();

  /** Updates the timer. */
  public void updateTimer() {
    if (timerManager.isTimeUp()
        && !MainSceneController.isUserAtGuessingScene
        && !GuessingSceneController.isUserAtExplanationScene) {
      timerManager.setTime(1, 0, 0);
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
   * Handles mouse click to go to the Crime Scene.
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

  @FXML
  private void onMouseEnteredImageB(MouseEvent event) {
    Button clickedRectangle = (Button) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
  }

  /**
   * Handles mouse click to go to the Collector Room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void gotoCollector(MouseEvent event) throws IOException {
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.COLLECTORROOM));
  }

  /**
   * Handles mouse click to go to the Auctioneer Room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void gotoAuctioneer(MouseEvent event) throws IOException {
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.AUCTIONEERROOM));
  }

  /**
   * Handles mouse clicks to go to the Archaeologist room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void gotoArchaeologist(MouseEvent event) throws IOException {
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.ARCHAEOLOGISTROOM));
  }
}
