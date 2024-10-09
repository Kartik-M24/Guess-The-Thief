package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

  @FXML private ImageView pauseImage;
  @FXML private ImageView playImage;
  @FXML private MediaView securityMedia;
  @FXML private Label currentTime;
  @FXML private Label totalTime;
  @FXML private Slider sliderTime;
  @FXML private Button button;
  private Media video;
  private MediaPlayer mediaPlayer;
  private boolean playing = false;
  private boolean endOfVideo = false;

  @FXML
  public void initialize() throws URISyntaxException {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    video = new Media(App.class.getResource("/images/securityFootage.mp4").toURI().toString());
    mediaPlayer = new MediaPlayer(video);
    securityMedia.setMediaPlayer(mediaPlayer);
    playImage.setVisible(true);
    pauseImage.setVisible(false);
    bindCurrentTimeLabel();

    // Set the total time of the video and the slider
    mediaPlayer
        .totalDurationProperty()
        .addListener(
            new ChangeListener<Duration>() {
              @Override
              public void changed(
                  ObservableValue<? extends Duration> observable,
                  Duration oldValue,
                  Duration newValue) {
                sliderTime.setMax(newValue.toSeconds());
                totalTime.setText(getTime(newValue));
              }
            });

    // Set the slider to the correct time when the user drags it
    sliderTime
        .valueChangingProperty()
        .addListener(
            new ChangeListener<Boolean>() {
              @Override
              public void changed(
                  ObservableValue<? extends Boolean> observable,
                  Boolean wasChanging,
                  Boolean isChanging) {
                if (!isChanging) {
                  mediaPlayer.seek(Duration.seconds(sliderTime.getValue()));
                }
              }
            });

    // Set the slider to the correct time when the video is playing
    sliderTime
        .valueProperty()
        .addListener(
            new ChangeListener<Number>() {
              @Override
              public void changed(
                  ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (Math.abs(newValue.doubleValue() - mediaPlayer.getCurrentTime().toSeconds())
                    > 0.1) {
                  mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                }
                labelMatchEndVideo(currentTime.getText(), totalTime.getText());
              }
            });

    // Checks if the sider value is changing
    mediaPlayer
        .currentTimeProperty()
        .addListener(
            new ChangeListener<Duration>() {
              // If the slider is not changing, set the value of the slider to the current time
              @Override
              public void changed(
                  // If the slider is not changing, set the value of the slider to the current time
                  ObservableValue<? extends Duration> observable,
                  Duration oldValue,
                  Duration newValue) {
                if (!sliderTime.isValueChanging()) {
                  // Set the value of the slider to the current time
                  sliderTime.setValue(newValue.toSeconds());
                }
                labelMatchEndVideo(currentTime.getText(), totalTime.getText());
              }
            });

    // Set progress colour of the slider as the video plays
    sliderTime
        .valueProperty()
        .addListener(
            new ChangeListener<Number>() {
              // Set the progress colour of the slider as the video plays
              @Override
              public void changed(
                  // Set the progress colour of the slider as the video plays
                  ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                if (mediaPlayer.getTotalDuration() != null) {
                  // Calculate the progress of the video
                  double progress =
                      newValue.doubleValue() / mediaPlayer.getTotalDuration().toSeconds() * 100;
                  String style =
                      String.format(
                          "-fx-background-color: linear-gradient(to right, #2D819D %f%%, #969696"
                              + " %f%%);",
                          progress, progress);
                  sliderTime.setStyle(style);
                }
              }
            });

    // Initial style
    sliderTime.setStyle("-fx-background-color: linear-gradient(to right, #2D819D 0%, #969696 0%);");

    // Automatically restart the video when it finishes
    mediaPlayer.setOnEndOfMedia(
        () -> {
          mediaPlayer.seek(Duration.ZERO); // Restart the video from the beginning
          mediaPlayer.play(); // Play the video again
        });
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    // Stops the video and plays the audio when the player clicks the button to leave the CCTV room
    if (playing) {
      playVideo(event);
    }
    mediaPlayer.seek(Duration.ZERO);
    // Plays the audio for the CCTV stopping
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
    // Checks if the video is currenty playing and sets visibility of the play and pause buttons
    if (playing) {
      playImage.setVisible(true);
      pauseImage.setVisible(false);
      mediaPlayer.pause();
      playing = false;
    } else {
      // Checks if the video has reached the end and sets visibility of the play and pause buttons
      playImage.setVisible(false);
      pauseImage.setVisible(true);
      mediaPlayer.play();
      playing = true;
    }
  }

  /** Updates the timer of the video. */
  private void bindCurrentTimeLabel() {
    // Bind the current time of the video to the label
    currentTime
        .textProperty()
        .bind(
            Bindings.createStringBinding(
                new Callable<String>() {
                  // Bind the current time of the video to the label
                  @Override
                  public String call() throws Exception {
                    // Return the current time of the video
                    return getTime(mediaPlayer.getCurrentTime());
                  }
                },
                mediaPlayer.currentTimeProperty()));
  }

  private String getTime(Duration time) {
    return String.format("%02d:%02d", (int) time.toSeconds() / 60, (int) time.toSeconds() % 60);
  }

  private void labelMatchEndVideo(String currentTime, String totalTime) {
    endOfVideo = false;
    if (currentTime.equals(totalTime)) {
      endOfVideo = true;
    }
  }

  @FXML
  private void skip5Seconds() {
    mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(5)));
  }

  @FXML
  private void goBack5Seconds() {
    mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(5)));
  }
}
