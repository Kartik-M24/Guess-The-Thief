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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class FuseBoxClueController {

  @FXML private ImageView notepad;
  @FXML private ProgressIndicator progressIndicator;
  @FXML private Button analyseButton;
  @FXML private Text text1;
  @FXML private Text text2;
  @FXML private Text text3;
  @FXML private Text text4;
  @FXML private Text text5;
  @FXML private Label timer;
  private Timeline timeLine;
  private boolean t1 = false;
  private boolean t2 = false;
  private boolean t3 = false;
  private TimerManager timerManager = TimerManager.getInstance();

  /** Initializes the fuse box view. */
  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    progressIndicator.setVisible(false);
    notepad.setVisible(false);
    text1.setVisible(false);
    text2.setVisible(false);
    text3.setVisible(false);
    text4.setVisible(false);
    text5.setVisible(false);
  }

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
   * Handles mouse clicks on rectangles representing clues in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleRectClick(MouseEvent event) throws IOException {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    String clickedRectangleId = clickedRectangle.getId();
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
          }
          if (clickedRectangleId.equals("switches")) {
            text2.setVisible(true);
            t2 = true;
          }
          if (clickedRectangleId.equals("ashes")) {
            text4.setVisible(true);
            t3 = true;
          }
        });

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
