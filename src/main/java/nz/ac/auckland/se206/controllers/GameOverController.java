package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.TimerManager;

public class GameOverController {

  @FXML private ImageView restartButton;
  public static AudioManager audioManager = new AudioManager();
  private TimerManager timerManager = TimerManager.getInstance();

  /**
   * Transitions to the second scene when the start button is clicked.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  public void changeScene(MouseEvent event) throws IOException {
    InitialSceneWithArtifactController.audioManager2.stopAudio();
    ArchaeologistRoomController.setArchaeologistRoomVisited();
    CollectorRoomController.setCollectorRoomVisited();
    AuctioneerRoomController.setAuctioneerRoomVisited();
    MainSceneController.setClueClicked();
    // ImageView button = (ImageView) event.getSource();
    // Scene sceneButtonIsIn = button.getScene();
    // sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.INTROSCENE));
    timerManager.stopTimer();
    Stage stage = (Stage) restartButton.getScene().getWindow();
    App app = new App();
    app.start(stage);
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

  /**
   * Handles mouse hover on image
   *
   * @param event the mouse event triggered by clicking a rectangle
   */
  @FXML
  private void onMouseEnteredImage(MouseEvent event) {
    ImageView clickedRectangle = (ImageView) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
  }
}
