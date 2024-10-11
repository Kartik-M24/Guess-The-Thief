package nz.ac.auckland.se206.states;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.GameStateContext;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.controllers.MainSceneController;
import nz.ac.auckland.se206.controllers.SecurityFootageController;
import nz.ac.auckland.se206.speech.TextToSpeech;

/**
 * The GameStarted state of the game. Handles the initial interactions when the game starts,
 * allowing the player to chat with characters and prepare to make a guess.
 */
public class GameStarted implements GameState {

  private final GameStateContext context;

  /**
   * Constructs a new GameStarted state with the given game state context.
   *
   * @param context the context of the game state
   */
  public GameStarted(GameStateContext context) {
    this.context = context;
  }

  /**
   * Handles the event when the guess button is clicked. Prompts the player to make a guess and
   * transitions to the guessing state.
   *
   * @throws IOException if there is an I/O error
   */
  @Override
  public void handleGuessClick() throws IOException {
    TextToSpeech.speak("Make a guess, click on the " + context.getProfessionToGuess());
    context.setState(context.getGuessingState());
  }

  /**
   * Handles the event when the clue button is clicked. Informs the player that the game has not
   * started yet and no clues can be given.
   *
   * @param event the mouse event triggered by clicking the clue button
   * @param rectangleId the ID of the clicked rectangle
   * @throws IOException if there is an I/O error
   */
  @Override
  public void handleClueClick(MouseEvent event, String rectangleId) throws IOException {
    // Handles all instances of clicking the clues in the main scene and redirects the player to the
    // acording clue scene
    if (rectangleId.equals("fuseboxClue")) {
      Rectangle rectValue = (Rectangle) event.getSource();
      Scene rectScene = rectValue.getScene();
      rectScene.setRoot(SceneManager.getUiRoot(AppUi.FUSEBOXINITIAL));
    }
    if (rectangleId.equals("lecternClue")) {
      MainSceneController.audioManager.playAudio(AudioManager.AudioType.WALKING, 0.8);
      Rectangle rectValue = (Rectangle) event.getSource();
      Scene rectScene = rectValue.getScene();
      rectScene.setRoot(SceneManager.getUiRoot(AppUi.LECTERNCLUE));
    }
    if (rectangleId.equals("letterClue")) {
      MainSceneController.audioManager.playAudio(AudioManager.AudioType.PAGEFLIP, 0.8);
      Rectangle rectValue = (Rectangle) event.getSource();
      Scene rectScene = rectValue.getScene();
      rectScene.setRoot(SceneManager.getUiRoot(AppUi.LETTERCLUE));
    }
    if (rectangleId.equals("securityCameraClue")) {
      if (SecurityFootageController.isMediaLoaded) {
        MainSceneController.audioManager.playAudio(AudioManager.AudioType.CCTVSTART, 1.3);
        Rectangle rectValue = (Rectangle) event.getSource();
        Scene rectScene = rectValue.getScene();
        rectScene.setRoot(SceneManager.getUiRoot(AppUi.SECURITYFOOTAGE));
      } else {
        MainSceneController.audioManager.playAudio(AudioManager.AudioType.CCTVSTART, 1.3);
        Rectangle rectValue = (Rectangle) event.getSource();
        Scene rectScene = rectValue.getScene();
        rectScene.setRoot(SceneManager.getUiRoot(AppUi.SECURITYFOOTAGEMIDDLE));
      }
    }
  }
}
