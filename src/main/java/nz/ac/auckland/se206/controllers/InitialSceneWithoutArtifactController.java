package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class InitialSceneWithoutArtifactController {

  @FXML private Rectangle lightRect;
  @FXML private Pane introPane;
  @FXML private Label timer;
  @FXML private ImageView nextButton;

  private TimerManager timerManager = TimerManager.getInstance();

  private FadeTransition fadeLightTransition = new FadeTransition();
  private FadeTransition fadeIntroTransition = new FadeTransition();

  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline
        .getKeyFrames()
        .add(
            new KeyFrame(
                Duration.millis(1),
                event -> {
                  checkTime();
                  updateTimer();
                }));

    fadeLightTransition.setNode(lightRect);
    fadeLightTransition.setDuration(Duration.seconds(1.5));
    fadeLightTransition.setFromValue(1.0);
    fadeLightTransition.setToValue(0.0);
    fadeLightTransition.setCycleCount(1);
    fadeLightTransition.setAutoReverse(false);

    fadeIntroTransition.setNode(introPane);
    fadeIntroTransition.setDuration(Duration.seconds(1.5));
    fadeIntroTransition.setFromValue(0);
    fadeIntroTransition.setToValue(1);
    fadeIntroTransition.setCycleCount(1);
    fadeIntroTransition.setAutoReverse(false);
  }

  public void updateTimer() {
    timer.setText(timerManager.getFormattedTime());
  }

  public void checkTime() {
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 44
        && timerManager.getMilliseconds() == 0
        && InitialSceneWithArtifactController.isFirstTime) {
      fadeLightTransition.play();
    }

    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 43
        && timerManager.getMilliseconds() == 900
        && InitialSceneWithArtifactController.isFirstTime) {
      timerManager.setTime(4, 59, 999);
      InitialSceneWithArtifactController.isFirstTime = false;
    }

    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 57
        && timerManager.getMilliseconds() == 0
        && !InitialSceneWithArtifactController.isFirstTime) {
      fadeIntroTransition.play();
      nextButton.setDisable(false);
    }
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleNextClick(MouseEvent event) throws IOException {
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
  private void onMouseEnteredImage(MouseEvent event) {
    ImageView clickedRectangle = (ImageView) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
  }
}
