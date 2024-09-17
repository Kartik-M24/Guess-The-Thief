package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This is the entry point of the JavaFX application. This class initializes and runs the JavaFX
 * application.
 */
public class App extends Application {

  private static Scene scene;

  /**
   * The main method that launches the JavaFX application.
   *
   * @param args the command line arguments
   */
  public static void main(final String[] args) {
    launch();
  }

  public static void initialize() throws IOException {
    SceneManager.addUi(SceneManager.AppUi.INTROSCENE, loadFxml("introscene"));
    SceneManager.addUi(SceneManager.AppUi.MAINSCENE, loadFxml("mainscene"));
    SceneManager.addUi(SceneManager.AppUi.SUSPECTSSELECTION, loadFxml("suspectsselection"));
    SceneManager.addUi(SceneManager.AppUi.COLLECTORROOM, loadFxml("collectorroom"));
    SceneManager.addUi(SceneManager.AppUi.ARCHAEOLOGISTROOM, loadFxml("archaeologistroom"));
    SceneManager.addUi(SceneManager.AppUi.AUCTIONEERROOM, loadFxml("auctioneerroom"));
    SceneManager.addUi(
        SceneManager.AppUi.INITIALARTIFACTSCENE, loadFxml("initialscenewithartifact"));
    SceneManager.addUi(
        SceneManager.AppUi.INTIALWITHOUTARTIFACTSCENE, loadFxml("initialscenewithOUTartifact"));
    SceneManager.addUi(SceneManager.AppUi.LETTERCLUE, loadFxml("letterclue"));
    SceneManager.addUi(SceneManager.AppUi.FUSEBOXCLUE, loadFxml("fuseboxclue"));
    SceneManager.addUi(SceneManager.AppUi.LECTERNCLUE, loadFxml("lecternclue"));
    SceneManager.addUi(SceneManager.AppUi.GUESSINGSCENE, loadFxml("guessingscene"));
    SceneManager.addUi(SceneManager.AppUi.CRIMEEXPLANATION, loadFxml("crimeexplanation"));
    SceneManager.addUi(SceneManager.AppUi.GAMEOVER, loadFxml("gameover"));
    AudioManager.addAudio(AudioManager.AudioType.INITIALTHEFTAUDIO, "intialtheftaudio");
    AudioManager.addAudio(AudioManager.AudioType.AUDIENCEMURMUR, "audienceMurmur");
    AudioManager.addAudio(AudioManager.AudioType.BACKGROUNDMUSIC, "backgroundMusic");
  }

  /**
   * Sets the root of the scene to the specified FXML file.
   *
   * @param fxml the name of the FXML file (without extension)
   * @throws IOException if the FXML file is not found
   */
  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));
  }

  /**
   * Loads the FXML file and returns the associated node. The method expects that the file is
   * located in "src/main/resources/fxml".
   *
   * @param fxml the name of the FXML file (without extension)
   * @return the root node of the FXML file
   * @throws IOException if the FXML file is not found
   */
  private static Parent loadFxml(final String fxml) throws IOException {
    return new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml")).load();
  }

  /**
   * This method is invoked when the application starts. It loads and shows the "room" scene.
   *
   * @param stage the primary stage of the application
   * @throws IOException if the "src/main/resources/fxml/room.fxml" file is not found
   */
  @Override
  public void start(final Stage stage) throws IOException {
    initialize();
    scene = new Scene(SceneManager.getUiRoot(AppUi.INTROSCENE));
    stage.setScene(scene);
    stage.show();
    // stage.setOnCloseRequest(event -> handleWindowClose(event));
    // root.requestFocus();
  }

  public static void restart(Stage stage) throws IOException {
    initialize();
    scene = new Scene(SceneManager.getUiRoot(AppUi.INTROSCENE));
    stage.setScene(scene);
    stage.show();
  }

  // private void handleWindowClose(WindowEvent event) {
  //   FreeTextToSpeech.deallocateSynthesizer();
  // }
}
