package nz.ac.auckland.se206.states;

import java.io.IOException;
import javafx.scene.input.MouseEvent;

/**
 * Interface representing the state of the game. Defines methods to handle user interactions such as
 * clicking on a rectangle and making a guess.
 */
public interface GameState {

  /**
   * Handles the event when the guess button is clicked.
   *
   * @throws IOException if there is an I/O error
   */
  void handleGuessClick() throws IOException;

  /**
   * Handles the event when the clue button is clicked.
   *
   * @param event the mouse event triggered by clicking the clue button
   * @param rectangleId the ID of the clicked rectangle
   * @throws IOException if there is an I/O error
   */
  void handleClueClick(MouseEvent event, String rectangleId) throws IOException;
}
