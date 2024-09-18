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

public class IntroSceneController {

  @FXML private ImageView startButton;
  private TimerManager timerManager = TimerManager.getInstance();
  public static AudioManager audioManager = new AudioManager();

  /**
   * Transitions to the second scene when the start button is clicked.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error1
   */
  @FXML
  public void changeScene(MouseEvent event) throws IOException {
    timerManager.setTime(4, 59, 999);
    InitialSceneWithArtifactController.isFirstTime = true;
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.INITIALARTIFACTSCENE));
    audioManager.playAudio(AudioManager.AudioType.AUDIENCEMURMUR, 0.2);
  }

  /**
   * Handles mouse hover on rectangles to help identify clues
   *
   * @param event the mouse event triggered by clicking a rectangle
   */
  @FXML
  private void onMouseEntered(MouseEvent event) {
    ImageView clickedRectangle = (ImageView) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
  }
}
