package nz.ac.auckland.se206.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.TimerManager;

public class IntialArtifactSceneController {

  @FXML private Rectangle lightRect;
  private TimerManager timerManager = TimerManager.getInstance();

  @FXML
  public void initialize() {
    lightRect.setVisible(false);
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> checkTime()));
  }

  public void checkTime() {
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 57
        && timerManager.getMilliseconds() == 999) {
      AudioManager.playAudio(AudioManager.AudioType.INITIALTHEFTAUDIO);
    }
  }
}
