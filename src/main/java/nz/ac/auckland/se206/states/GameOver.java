package nz.ac.auckland.se206.states;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.GameStateContext;
import nz.ac.auckland.se206.speech.TextToSpeech;

/**
 * The GameOver state of the game. Handles interactions after the game has ended, informing the
 * player that the game is over and no further actions can be taken.
 */
public class GameOver implements GameState {

  private final GameStateContext context;

  /**
   * Constructs a new GameOver state with the given game state context.
   *
   * @param context the context of the game state
   */
  public GameOver(GameStateContext context) {
    this.context = context;
  }

  /**
   * Handles the event when the guess button is clicked. Informs the player that the game is over
   * and no further guesses can be made.
   *
   * @throws IOException if there is an I/O error
   */
  @Override
  public void handleGuessClick() throws IOException {
    TextToSpeech.speak("You have already guessed!");
  }

  /**
   * Handles the event when the clue button is clicked. Informs the player that the game is over and
   * no further clues can be given.
   *
   * @param event the mouse event triggered by clicking the clue button
   * @param rectangleId the ID of the clicked rectangle
   * @throws IOException if there is an I/O error
   */
  @Override
  public void handleClueClick(MouseEvent event, String rectangleId) throws IOException {
    TextToSpeech.speak("You have already guessed!");
  }
}
