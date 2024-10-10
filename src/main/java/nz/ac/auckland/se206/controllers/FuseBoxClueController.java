package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class FuseBoxClueController extends MasterController {

  @FXML private ImageView notepad;
  @FXML private ImageView cigarClueGlow;
  @FXML private ImageView switchClueGlow;
  @FXML private ImageView wireClueGlow;
  @FXML private ProgressIndicator progressIndicator;
  @FXML private Button analyseButton;
  @FXML private Text text1;
  @FXML private Text text2;
  @FXML private Text text3;
  @FXML private Text text4;
  @FXML private Text text5;
  private Timeline timeLine;
  private boolean t1 = false;
  private boolean t2 = false;
  private boolean t3 = false;

  /** Initializes the fuse box view. */
  @FXML
  public void initialize() {
    // Inirialises all the necessary fields and initialises the timeline
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    progressIndicator.setVisible(false);
    notepad.setVisible(false);
    text1.setVisible(false);
    text2.setVisible(false);
    text3.setVisible(false);
    text4.setVisible(false);
    text5.setVisible(false);
    cigarClueGlow.setVisible(false);
    switchClueGlow.setVisible(false);
    wireClueGlow.setVisible(false);
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.FUSEBOXCLOSE, 1);
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
    // Handles the player clicking the clues and writes them down on the notebook for the player to
    // see
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    String clickedRectangleId = clickedRectangle.getId();
    if (clickedRectangleId.equals("wires") && t1) {
      return;
    }
    if (clickedRectangleId.equals("switches") && t2) {
      return;
    }
    if (clickedRectangleId.equals("ashes") && t3) {
      return;
    }
    progressIndicator.setVisible(true);
    progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
    IntegerProperty seconds = new SimpleIntegerProperty();
    timeLine =
        new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
            new KeyFrame(Duration.seconds(2), new KeyValue(seconds, 1)));
    timeLine.setCycleCount(1);
    timeLine.play();
    timeLine.setOnFinished(
        e -> {
          progressIndicator.setVisible(false);
          notepad.setVisible(true);
          text3.setVisible(true);
          if (clickedRectangleId.equals("wires")) {
            text1.setVisible(true);
            t1 = true;
            checkAllTexts();
          }
          if (clickedRectangleId.equals("switches")) {
            text2.setVisible(true);
            t2 = true;
            checkAllTexts();
          }
          if (clickedRectangleId.equals("ashes")) {
            text4.setVisible(true);
            t3 = true;
            checkAllTexts();
          }
        });
  }

  /** Displays analysis is complete when all clues have been analysed */
  private void checkAllTexts() {
    if (t1 && t2 && t3) {
      text5.setVisible(true);
      progressIndicator.setVisible(false);
    }
  }

  /**
   * Handles mouse hover on rectangles to help identify clues
   *
   * @param event the mouse event triggered by clicking a rectangle
   */
  @FXML
  private void onMouseEnteredC(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    cigarClueGlow.setVisible(true);
    checkAllTexts();
  }

  @FXML
  private void onMouseExitedC(MouseEvent event) {
    cigarClueGlow.setVisible(false);
  }

  @FXML
  private void onMouseEnteredS(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    switchClueGlow.setVisible(true);
    checkAllTexts();
  }

  @FXML
  private void onMouseExitedS(MouseEvent event) {
    switchClueGlow.setVisible(false);
  }

  @FXML
  private void onMouseEnteredW(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    wireClueGlow.setVisible(true);
    checkAllTexts();
  }

  @FXML
  private void onMouseExitedW(MouseEvent event) {
    wireClueGlow.setVisible(false);
  }
}
