package nz.ac.auckland.se206.controllers;

import java.io.IOException; // Add this import statement
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class SuspectSelectionController {
  @FXML private Rectangle rectArchaeologist;
  @FXML private Rectangle rectAuctioneer;
  @FXML private Rectangle rectCollector;

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleArchaeologistClick(MouseEvent event) throws IOException {}

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleAuctioneerClick(MouseEvent event) throws IOException {}

  /**
   * Handles mouse clicks on rectangles representing people in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCollectorClick(MouseEvent event) throws IOException {}
}
