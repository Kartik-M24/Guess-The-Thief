package nz.ac.auckland.se206.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.TimerManager;

public class InitialNoArtifactSceneController {

  @FXML private Rectangle lightRect;
  private TimerManager timerManager = TimerManager.getInstance();
  private AudioManager audioManager = new AudioManager();
  private FadeTransition fadeTransition = new FadeTransition();

  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> checkTime()));

    fadeTransition.setNode(lightRect);
    fadeTransition.setDuration(Duration.seconds(1.5));
    fadeTransition.setFromValue(1.0);
    fadeTransition.setToValue(0.0);
    fadeTransition.setCycleCount(1);
    fadeTransition.setAutoReverse(false);
  }

  public void checkTime() {
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 44
        && timerManager.getMilliseconds() == 0) {
      fadeTransition.play();
    }

    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 41
        && timerManager.getMilliseconds() == 300) {
      audioManager.playAudio(AudioManager.AudioType.AUDIENCEGASP, 0.5);
      // IntroSceneController.audioManager.stopAudio();
    }
  }
}
