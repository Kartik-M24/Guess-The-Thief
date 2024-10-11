package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;

/**
 * Controller for the introductory scene. Manages the transition to the main scene when the start
 * button is clicked. - Resets the timer to 4 minutes and 59 seconds upon starting the game. -
 * Initializes the audio for the audience murmurs.
 */
public class IntroSceneController extends MasterController {

  public static AudioManager audioManager = new AudioManager();

  @FXML private ImageView startButton;
  private TimerManager timerManager = TimerManager.getInstance();

  /**
   * Transitions to the second scene when the start button is clicked.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error1
   */
  @FXML
  public void changeScene(MouseEvent event) throws IOException {
    // Sets the time to 5 minutes correctly and changes the scene to the main scene
    timerManager.setTime(4, 59, 999);
    InitialSceneWithArtifactController.isFirstTime = true;
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.INITIALARTIFACTSCENE));
    audioManager.playAudio(AudioManager.AudioType.AUDIENCEMURMUR, 0.2);
  }
}
