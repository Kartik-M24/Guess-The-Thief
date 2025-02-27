package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.AudioManager.AudioType;
import nz.ac.auckland.se206.GameStateContext;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;
import nz.ac.auckland.se206.speech.TextToSpeech;

/**
 * Controller class for the room view. Handles user interactions within the room where the user can
 * chat with customers and guess their profession.
 */
public class MainSceneController extends MasterController {

  private static boolean isFirstTimeInit = true;
  private static GameStateContext context = new GameStateContext();
  private static boolean clueClicked;
  public static boolean isUserAtGuessingScene = false;
  public static AudioManager audioManager = new AudioManager();

  public static void setClueClicked() {
    clueClicked = false;
  }

  @FXML private Button btnGuess;
  @FXML private Button btnInteract;
  @FXML private ImageView imgSuspects;
  @FXML private ImageView phoneLogButton;
  @FXML private ImageView lecternClueGlow;
  @FXML private ImageView fuseClueGlow;
  @FXML private ImageView cameraClueGlow;
  @FXML private ImageView soundIcon;
  @FXML private Slider volumeSlider;

  /**
   * Initializes the room view. If it's the first time initialization, it will provide instructions
   * via text-to-speech.
   */
  @FXML
  public void initialize() {
    // Initialises the main scene and correctly sets up all the fields
    if (isFirstTimeInit) {
      TextToSpeech.speak(
          "Welcome to the crime scene. You have 5 minutes to investigate the crime scene and find"
              + " clues. Click on the clues to investigate them. Once you have found all the clues,"
              + " you can make a guess. Good luck!");
      isFirstTimeInit = false;
    }
    clueClicked = false;
    timerManager.startTimer();
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    // Set all the glows to be invisible at the start
    btnInteract.setVisible(false);
    lecternClueGlow.setVisible(false);
    fuseClueGlow.setVisible(false);
    cameraClueGlow.setVisible(false);
    volumeSlider.setVisible(false);
    // set the volume slider to the initial state
    volumeSlider.setValue(100);
    volumeSlider
        .valueProperty()
        .addListener(
            new InvalidationListener() {
              @Override
              public void invalidated(Observable observable) {
                InitialSceneWithArtifactController.backgroundAudioManager.setVolume(
                    volumeSlider.getValue() / 100);
              }
            });
  }

  /** Hanldes when the user clicks the sound icon to adjust the volume of the video. */
  @FXML
  private void showSlider() {
    if (volumeSlider.isVisible()) {
      volumeSlider.setVisible(false);
    } else volumeSlider.setVisible(true);
  }

  @FXML
  private void sliderHide() {
    showSlider();
  }

  /**
   * Handles the key pressed event.
   *
   * @param event the key event
   */
  @FXML
  public void onKeyPressed(KeyEvent event) {
    System.out.println("Key " + event.getCode() + " pressed");
  }

  /**
   * Handles the key released event.
   *
   * @param event the key event
   */
  @FXML
  public void onKeyReleased(KeyEvent event) {
    System.out.println("Key " + event.getCode() + " released");
  }

  /**
   * Handles mouse clicks on rectangles representing clues in the room.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleClueClick(MouseEvent event) throws IOException {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    context.handleClueClick(event, clickedRectangle.getId());
    clueClicked = true;
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleGuessClick(ActionEvent event) throws IOException {
    // context.handleGuessClick();
    if (CollectorRoomController.isCollectorRoomVisited()
        && ArchaeologistRoomController.isArchaeologistRoomVisited()
        && AuctioneerRoomController.isAuctioneerRoomVisited()
        && clueClicked) {
      timerManager.setTime(1, 0, 0);
      audioManager.playAudio(AudioManager.AudioType.TIMESUP, 0.5);
      Button button = (Button) event.getSource();
      Scene sceneButtonIsIn = button.getScene();
      sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.GUESSINGSCENE));
      isUserAtGuessingScene = true;
    }
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleSuspectsClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.PAGEFLIP, 0.8);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.SUSPECTSSELECTION));
  }

  /**
   * Handles the guess button click event.
   *
   * @param event the action event triggered by clicking the guess button
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handlePhoneClick(MouseEvent event) throws IOException {
    clueClicked = true;
    audioManager.playAudio(AudioType.PHONEBACK, 0.8);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.PHONELOGAUCTIONEER));
  }

  @FXML
  private void onMouseEnteredImageB(MouseEvent event) {
    // The mouse changes on hover when the cursor is on top
    if (CollectorRoomController.isCollectorRoomVisited()
        && ArchaeologistRoomController.isArchaeologistRoomVisited()
        && AuctioneerRoomController.isAuctioneerRoomVisited()
        && clueClicked) {
      Button clickedRectangle = (Button) event.getSource();
      clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    } else {
      btnGuess.setVisible(false);
      btnInteract.setVisible(true);
    }
  }

  @FXML
  private void onMouseExitedImageB(MouseEvent event) {
    btnGuess.setVisible(true);
    btnInteract.setVisible(false);
  }

  @FXML
  private void onMouseEnteredL(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    lecternClueGlow.setVisible(true);
  }

  @FXML
  private void onMouseExitedL(MouseEvent event) {
    lecternClueGlow.setVisible(false);
  }

  @FXML
  private void onMouseEnteredF(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    fuseClueGlow.setVisible(true);
  }

  @FXML
  private void onMouseExitedF(MouseEvent event) {
    fuseClueGlow.setVisible(false);
  }

  @FXML
  private void onMouseEnteredC(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
    clickedRectangle.setCursor(javafx.scene.Cursor.HAND);
    cameraClueGlow.setVisible(true);
  }

  @FXML
  private void onMouseExitedC(MouseEvent event) {
    cameraClueGlow.setVisible(false);
  }
}
