package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nz.ac.auckland.se206.App;

/**
 * Controller for the game lost scene, managing the restart functionality. Handles user interactions
 * with the restart rectangle, allowing the game to reset and transition back to the initial scene
 * for a new game.
 */
public class GameLostController extends MasterController {

  @FXML private Rectangle restartRect;

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
    Stage stage = (Stage) restartRect.getScene().getWindow();
    App app = new App();
    app.start(stage);
  }
}
