package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.TimerManager;

public class IntialArtifactSceneController {

  @FXML private Rectangle lightRect;
  private TimerManager timerManager = TimerManager.getInstance();
  private AudioManager audioManager = new AudioManager();
  private AudioManager audioManager2 = new AudioManager();

  @FXML
  public void initialize() {
    lightRect.setVisible(false);
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> checkTime()));
  }

  public void checkTime() {

    // Play auction audio
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 57
        && timerManager.getMilliseconds() == 999) {
      audioManager.playAudio(AudioManager.AudioType.INITIALTHEFTAUDIO, 1.2);
    }

    // Turn off light
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 49
        && timerManager.getMilliseconds() == 380) {
      lightRect.setVisible(true);
    }

    // Switch scene to no artifact scene
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 48
        && timerManager.getMilliseconds() == 0) {
      audioManager2.playAudio(AudioManager.AudioType.BACKGROUNDMUSIC, 0.5);
      try {
        App.setRoot("initialscenewithOUTartifact");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
