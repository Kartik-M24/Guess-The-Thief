package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

/**
 * Controller for the initial scene without an artifact. Manages the fade transitions and timer
 * updates based on elapsed time. - Fades out the light rectangle after 4 minutes and 44 seconds. -
 * Resets the timer to 4 minutes and 59 seconds when the time reaches 4 minutes and 43.9 seconds. -
 * Fades in the introduction pane and enables the next button when the timer reaches 4 minutes and
 * 57 seconds.
 */
public class InitialSceneWithoutArtifactController extends MasterController {

  @FXML private Rectangle lightRect;
  @FXML private Pane introPane;
  @FXML private ImageView nextButton;

  private FadeTransition fadeLightTransition = new FadeTransition();
  private FadeTransition fadeIntroTransition = new FadeTransition();

  /**
   * Initializes the controller class. This method is automatically called after the FXML file has
   * been loaded. It sets up the necessary fields and creates all the fade transitions to be used in
   * the game.
   */
  @FXML
  public void initialize() {
    // Initialises all the necessary fields and creates all the fade transitions to be used in the
    // game.
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

  /**
   * Updates the timer display. This method is called to refresh the timer text with the current
   * formatted time.
   */
  @Override
  public void updateTimer() {
    // Sets the text for the timer
    timer.setText(timerManager.getFormattedTime());
  }

  /**
   * Checks the current time and performs specific actions based on the time. It handles the fade
   * transitions and updates the timer accordingly.
   */
  public void checkTime() {
    // Checks the time and does events depending on what the time is.
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
    IntroSceneController.audioManager.stopAudio();
  }
}
