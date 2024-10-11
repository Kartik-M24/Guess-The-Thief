package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

/**
 * Controller for the Lock Scene. This class manages the interactions and behavior of the lock scene
 * in the application, where players interact with sliders to unlock a fuse box. It handles the
 * initialization of sliders and gauges, manages slider movements, and transitions to the next scene
 * upon successfully unlocking the fuse box.
 */
public class LockSceneController extends MasterController {

  @FXML private Slider slider1;
  @FXML private Slider slider2;
  @FXML private Slider slider3;
  @FXML private ImageView gauge1;
  @FXML private ImageView gauge2;
  @FXML private ImageView gauge3;
  @FXML private ImageView nextScene;
  @FXML private Label text1;
  private Timeline timeline1;
  private Timeline timeline2;
  private Timeline timeline3;
  private AudioManager audioManager = new AudioManager();
  // Create a boolean flag for direction and use an array to mutate it within the lambda
  final boolean[] isIncreasing = {true};

  /**
   * Initializes the locked fuse box view. Sets up the initial visibility of sliders and gauges, and
   * creates timelines for each slider.
   */
  @FXML
  public void initialize() {
    // Inirialises all the necessary fields and initialises the timeline
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    slider2.setVisible(false);
    slider3.setVisible(false);
    gauge2.setVisible(false);
    gauge3.setVisible(false);
    nextScene.setVisible(false);
    // Create timelines for each slider with different speeds
    createTimelineForSlider(slider1, 50); // Slider 1 moves every 0.05 seconds
    createTimelineForSlider(slider2, 25); // Slider 2 moves every 0.025 seconds
    createTimelineForSlider(slider3, 15); // Slider 3 moves every 0.015 second
  }

  /**
   * Creates a timeline for a slider with a specified duration.
   *
   * @param slider the slider to create a timeline for
   * @param durationMillis the duration of the timeline in milliseconds
   */
  private void createTimelineForSlider(Slider slider, int durationMillis) {
    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.millis(durationMillis),
                e -> {
                  double value = slider.getValue();
                  double increment = 1; // Increment or decrement by 1 each tick

                  // Check the direction
                  if (isIncreasing[0]) {
                    value += increment;
                    if (value >= slider.getMax()) {
                      value = slider.getMax();
                      isIncreasing[0] = false; // Reverse direction when reaching the top
                    }
                  } else {
                    value -= increment;
                    if (value <= slider.getMin()) {
                      value = slider.getMin();
                      isIncreasing[0] = true; // Reverse direction when reaching the bottom
                    }
                  }

                  slider.setValue(value); // Update slider value
                }));

    timeline.setCycleCount(Timeline.INDEFINITE);

    // Start the timeline for slider1 initially
    if (slider == slider1) {
      timeline.play();
      timeline1 = timeline;
    } else if (slider == slider2) {
      timeline2 = timeline;
    } else if (slider == slider3) {
      timeline3 = timeline;
    }
  }

  /**
   * Handles mouse clicks on sliders.
   *
   * @param event the mouse event triggered by clicking a slider
   */
  @FXML
  private void handleSliderClick(MouseEvent event) {
    Slider slider = (Slider) event.getSource();
    double value = slider.getValue();

    // Define the "suitable zone" for accepting the slider position
    if (value > 20 && value < 40) {
      if (slider == slider1) {
        slider1.setVisible(false);
        gauge1.setVisible(false);
        slider2.setVisible(true);
        gauge2.setVisible(true);
        audioManager.playAudio(AudioManager.AudioType.LOCKPICKING, 0.6);
        timeline1.stop();
        timeline2.play();
      } else if (slider == slider2) {
        slider2.setVisible(false);
        gauge2.setVisible(false);
        slider3.setVisible(true);
        gauge3.setVisible(true);
        audioManager.playAudio(AudioManager.AudioType.LOCKPICKING, 0.6);
        timeline2.stop();
        timeline3.play();
      } else if (slider == slider3) {
        slider3.setVisible(false);
        gauge3.setVisible(false);
        audioManager.playAudio(AudioManager.AudioType.LOCKPICKING, 0.6);
        timeline3.stop();
        // All sliders done, move to next scene
        nextScene.setVisible(true);
        text1.setText("You've Unlocked the Fusebox!");
      }
    }
  }

  /**
   * Handles mouse clicks on the next scene button.
   *
   * @param event the mouse event triggered by clicking the next scene button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void moveNextScene(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.FUSEBOXOPEN, 1);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.FUSEBOXCLUE));
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.FUSEBOXCLOSE, 1);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
  }
}
