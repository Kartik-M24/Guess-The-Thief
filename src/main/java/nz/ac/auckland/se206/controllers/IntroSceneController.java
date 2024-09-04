package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.controllers.SceneManager.AppUi;

public class IntroSceneController {

  @FXML private ImageView startButton;

  @FXML
  public void changeScene(MouseEvent event) throws IOException {

    System.out.println("Button clicked: " + startButton.getId());
    ImageView button = (ImageView) event.getSource();
    System.out.println("Button clicked: " + button.getId());
    Scene sceneButtonIsIn = button.getScene();

    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
  }
}
