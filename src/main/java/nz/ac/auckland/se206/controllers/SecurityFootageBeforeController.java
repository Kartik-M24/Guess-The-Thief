package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class SecurityFootageBeforeController {

  @FXML private Label timer;
  @FXML private Circle suspect1Circle;
  @FXML private Circle suspect2Circle;
  @FXML private Line suspect1Line;
  @FXML private Line suspect2Line;
  @FXML private ImageView suspect1Image;
  @FXML private ImageView suspect2Image;
  private TimerManager timerManager = TimerManager.getInstance();

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
  }

  public void updateTimer() {
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
  }

  /**
   * Handles mouse clicks on right button, moves them to next scene.
   *
   * @param event the mouse event triggered by clicking an image
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void nextSceneRight(MouseEvent event) throws IOException {
    Scene scene = ((ImageView) event.getSource()).getScene();
    scene.setRoot(SceneManager.getUiRoot(AppUi.ARCHAEOLOGISTROOM));
  }

  /**
   * Handles mouse clicks on left button, moves them to next scene.
   *
   * @param event the mouse event triggered by clicking an image
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void nextSceneLeft(MouseEvent event) throws IOException {
    Scene scene = ((ImageView) event.getSource()).getScene();
    scene.setRoot(SceneManager.getUiRoot(AppUi.AUCTIONEERROOM));
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

  /**
   * Handles mouse hover on circle
   *
   * @param event the mouse event triggered by clicking a rectangle
   */
  @FXML
  private void onMouseEntered(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
  }
}
