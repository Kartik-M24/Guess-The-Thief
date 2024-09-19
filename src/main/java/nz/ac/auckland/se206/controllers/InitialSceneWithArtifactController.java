package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.TimerManager;

public class InitialSceneWithArtifactController {

  public static boolean isFirstTime = true;
  private TimerManager timerManager = TimerManager.getInstance();
  private AudioManager audioManager = new AudioManager();
  public static AudioManager audioManager2 = new AudioManager();

  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> checkTime()));
  }

  public void checkTime() {

    // Play auction audio
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 57
        && timerManager.getMilliseconds() == 999
        && isFirstTime) {
      audioManager.playAudio(AudioManager.AudioType.INITIALTHEFTAUDIO, 1.2);
    }

    // Turn off light
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 49
        && timerManager.getMilliseconds() == 380
        && isFirstTime) {
      try {
        App.setRoot("lightsoffscene");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // Switch scene to no artifact scene
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 48
        && timerManager.getMilliseconds() == 0
        && isFirstTime) {
      audioManager2.playAudio(AudioManager.AudioType.BACKGROUNDMUSIC, 0.5);
      try {
        App.setRoot("initialscenewithOUTartifact");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
