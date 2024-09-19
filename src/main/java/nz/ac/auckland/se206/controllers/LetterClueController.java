package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

public class LetterClueController {

  @FXML private Label timer;
  @FXML private ImageView textImage1;
  @FXML private ImageView textImage2;
  @FXML private ImageView textImage3;
  @FXML private Text hintText;
  private TimerManager timerManager = TimerManager.getInstance();

  /** Initializes the letter clue view. */
  @FXML
  public void initialize() {
    hintText.setVisible(true);
    textImage1.setOpacity(0);
    textImage2.setOpacity(0);
    textImage3.setOpacity(0);
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
  }

  public void updateTimer() {
    if (timerManager.isTimeUp() && !MainSceneController.isUserAtGuessingScene) {
      timerManager.setTime(1, 0, 0);
      try {
        App.setRoot("guessingscene");
      } catch (IOException e) {
        e.printStackTrace();
      }
      MainSceneController.isUserAtGuessingScene = true;
    }
    timer.setText(timerManager.getFormattedTime());
  }

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
  }

  @FXML
  private void onSliderChanged(MouseEvent event) {
    hintText.setVisible(false);
    Slider slider = (Slider) event.getSource();
    int value = (int) slider.getValue();

    // Step 1: Add a random perturbation
    Random random = new Random();
    int fullOpacity = (random.nextInt(101)) - 50;
    int perturbation1 = random.nextInt(21) - 10; // Random value between -10 and 10
    int perturbation2 = random.nextInt(11) - 5; // Random value between -5 and 5
    int perturbation3 = random.nextInt(31) - 15; // Random value between -15 and 15

    // Step 2: Combine perturbations with a non-linear transformation
    int opacityValue = value + perturbation1 + perturbation2 + perturbation3;
    opacityValue =
        (int) (opacityValue * (0.8 + 0.3 * random.nextDouble())); // Adding a random scaling factor
    // Step 3: Ensure the final value is within the range [0, 100]
    if (opacityValue < 0) {
      opacityValue *= -1;
    } else if (opacityValue > 100) {
      opacityValue = (opacityValue % 100);
    } else if (value == 0 || value == 50 || value == -50) {
      opacityValue = 0;
    }

    if (value == fullOpacity) {
      opacityValue = 100;
    }

    // Step 4: Set the opacity of the image view
    textImage1.setOpacity(opacityValue / 100.0);
    textImage2.setOpacity(opacityValue / 100.0);
    textImage3.setOpacity(opacityValue / 100.0);
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
