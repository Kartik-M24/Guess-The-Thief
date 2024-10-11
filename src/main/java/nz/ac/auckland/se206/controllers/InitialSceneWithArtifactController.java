package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.TimerManager;

/**
 * Controller for the initial scene that includes an artifact. Manages the timer and performs
 * specific actions based on elapsed time. - Plays an auction audio when the timer reaches 4
 * minutes, 57 seconds, and 999 milliseconds. - Switches to the "lightsoffscene" when the timer
 * reaches 4 minutes, 49 seconds, and 380 milliseconds. - Switches to "initialscenewithOUTartifact"
 * and plays background music when the timer reaches 4 minutes and 48 seconds.
 */
public class InitialSceneWithArtifactController {

  public static boolean isFirstTime = true;
  public static AudioManager backgroundAudioManager = new AudioManager();

  private TimerManager timerManager = TimerManager.getInstance();
  private AudioManager audioManager = new AudioManager();

  /**
   * Initializes the controller. This method is called after the FXML file has been loaded. It sets
   * up a timeline with a key frame that calls the checkTime method every millisecond.
   */
  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> checkTime()));
  }

  /**
   * Checks the current time from the TimerManager and performs specific actions based on the time.
   * - Plays an auction audio when the timer reaches 4 minutes, 57 seconds, and 999 milliseconds. -
   * Switches the scene to "lightsoffscene" when the timer reaches 4 minutes, 49 seconds, and 380
   * milliseconds. - Switches the scene to "initialscenewithOUTartifact" and plays background music
   * when the timer reaches 4 minutes and 48 seconds.
   */
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
      backgroundAudioManager.playAudio(AudioManager.AudioType.BACKGROUNDMUSIC, 0.5);
      try {
        App.setRoot("initialscenewithOUTartifact");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
