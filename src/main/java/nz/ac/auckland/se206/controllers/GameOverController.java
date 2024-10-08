package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.TimerManager;

public class GameOverController extends MasterController {

  public static AudioManager audioManager = new AudioManager();

  @FXML private ImageView restartButton;
  private TimerManager timerManager = TimerManager.getInstance();

  /**
   * Transitions to the second scene when the start button is clicked.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  public void changeScene(MouseEvent event) throws IOException {
    // Correctly resets everything in the game and restarts the game properly so that the player can
    // play again.
    InitialSceneWithArtifactController.backgroundAudioManager.stopAudio();
    ArchaeologistRoomController.setArchaeologistRoomVisited();
    CollectorRoomController.setCollectorRoomVisited();
    AuctioneerRoomController.setAuctioneerRoomVisited();
    MainSceneController.setClueClicked();
    timerManager.stopTimer();
    Stage stage = (Stage) restartButton.getScene().getWindow();
    App app = new App();
    app.start(stage);
  }
}
