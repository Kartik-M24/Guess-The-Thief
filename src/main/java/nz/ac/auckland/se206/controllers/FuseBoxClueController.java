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
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class FuseBoxClueController {

  @FXML private ImageView notepad;
  @FXML private ProgressIndicator progressIndicator;
  @FXML private Button analyseButton;
  @FXML private Text text1;
  @FXML private Text text2;
  @FXML private Text text3;
  @FXML private Text text4;
  @FXML private Text text5;
  private Timeline timeLine;
  private boolean t1;
  private boolean t2;
  private boolean t3;

  @FXML
  public void initialize() {
    progressIndicator.setVisible(false);
    notepad.setVisible(false);
    text1.setVisible(false);
    text2.setVisible(false);
    text3.setVisible(false);
    text4.setVisible(false);
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
}
