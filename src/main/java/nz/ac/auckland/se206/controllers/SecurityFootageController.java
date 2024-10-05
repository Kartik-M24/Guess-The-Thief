package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class SecurityFootageController extends MasterController {

  @FXML private ImageView rightButton;
  @FXML private MediaView securityMedia;
  private Media video;
  private MediaPlayer mediaPlayer;
  private boolean playing = false;

  @FXML
  public void initialize() throws URISyntaxException {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    video = new Media(App.class.getResource("/images/securityFootage.mp4").toURI().toString());
    mediaPlayer = new MediaPlayer(video);
    securityMedia.setMediaPlayer(mediaPlayer);
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.CCTVSTOP, 0.1);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
  }

  /**
   * Plays the video when the scene is opened.
   *
   * @param event the mouse event triggered by clicking the video
   */
  @FXML
  private void playVideo(MouseEvent event) {
    if (playing) {
      mediaPlayer.pause();
      playing = false;
    } else {
      mediaPlayer.play();
      playing = true;
    }
  }
}
