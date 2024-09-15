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

  @FXML
  public void changeScene(MouseEvent event) throws IOException {
    timerManager.setTime(4, 59, 999);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.INITIALARTIFACTSCENE));
    audioManager.playAudio(AudioManager.AudioType.AUDIENCEMURMUR, 0.4);
  }
}
