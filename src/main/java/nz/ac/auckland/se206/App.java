package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.concurrent.Task;
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

  // Add all the scenes in the whole video game into the hashmap in SceneManager, also adds the
  // audio used in the video game in to AudioManager
  Task<Void> initializescene =
      new Task<>() {
        @Override
        protected Void call() {
          // Add all the scenes in the whole video game into the hashmap in SceneManager, also adds
          // the audio used in the video game in to AudioManager
          try {
            SceneManager.addUi(SceneManager.AppUi.SECURITYFOOTAGE, loadFxml("securityfootage"));
            SceneManager.addUi(SceneManager.AppUi.SUSPECTSSELECTION, loadFxml("suspectsselection"));
            SceneManager.addUi(SceneManager.AppUi.COLLECTORROOM, loadFxml("collectorroom"));
            SceneManager.addUi(SceneManager.AppUi.ARCHAEOLOGISTROOM, loadFxml("archaeologistroom"));
            SceneManager.addUi(SceneManager.AppUi.AUCTIONEERROOM, loadFxml("auctioneerroom"));
            SceneManager.addUi(SceneManager.AppUi.LETTERCLUE, loadFxml("letterclue"));
            SceneManager.addUi(SceneManager.AppUi.FUSEBOXCLUE, loadFxml("fuseboxclue"));
            SceneManager.addUi(SceneManager.AppUi.LECTERNCLUE, loadFxml("lecternclue"));
            SceneManager.addUi(
                SceneManager.AppUi.PHONELOGAUCTIONEER, loadFxml("phonelogauctioneer"));
            SceneManager.addUi(
                SceneManager.AppUi.PHONELOGARCHAEOLOGIST, loadFxml("phonelogarchaeologist"));
            SceneManager.addUi(SceneManager.AppUi.PHONELOGCOLLECTOR, loadFxml("phonelogcollector"));
            SceneManager.addUi(SceneManager.AppUi.GUESSINGSCENE, loadFxml("guessingscene"));
            SceneManager.addUi(SceneManager.AppUi.CRIMEEXPLANATION, loadFxml("crimeexplanation"));
            SceneManager.addUi(SceneManager.AppUi.GAMEOVER, loadFxml("gameover"));
            SceneManager.addUi(SceneManager.AppUi.LIGHTSOFFSCENE, loadFxml("lightsoffscene"));
            SceneManager.addUi(SceneManager.AppUi.LOCKSCENE, loadFxml("lockscene"));
            SceneManager.addUi(SceneManager.AppUi.FUSEBOXINITIAL, loadFxml("fuseboxinitial"));
            AudioManager.addAudio(AudioManager.AudioType.BACKGROUNDMUSIC, "backgroundMusic");
            AudioManager.addAudio(AudioManager.AudioType.TYPEWRITER, "typewriteraudio");
            AudioManager.addAudio(AudioManager.AudioType.PAGEFLIP, "pageflip");
            AudioManager.addAudio(AudioManager.AudioType.DOOR, "door");
            AudioManager.addAudio(AudioManager.AudioType.PHONENEXT, "phoneNext");
            AudioManager.addAudio(AudioManager.AudioType.PHONEBACK, "phoneBack");
            AudioManager.addAudio(AudioManager.AudioType.CRIMESCENE, "crimeScene");
            AudioManager.addAudio(AudioManager.AudioType.FUSEBOXOPEN, "fuseBoxOpen");
            AudioManager.addAudio(AudioManager.AudioType.FUSEBOXCLOSE, "fuseBoxClose");
            AudioManager.addAudio(AudioManager.AudioType.WALKING, "walking");
            AudioManager.addAudio(AudioManager.AudioType.CCTVSTART, "CCTVstart");
            AudioManager.addAudio(AudioManager.AudioType.CCTVSTOP, "CCTVstop");
            AudioManager.addAudio(AudioManager.AudioType.TIMESUP, "timesup");
            AudioManager.addAudio(AudioManager.AudioType.POLICESIREN, "policesiren");
            AudioManager.addAudio(AudioManager.AudioType.LOCKPICKING, "lockPicking");
            AudioManager.addAudio(AudioManager.AudioType.WRITING, "writing");
          } catch (IOException e) {
            e.printStackTrace();
          }
          return null;
        }
      };

  /**
   * This method is invoked when the application starts. It loads and shows the "room" scene.
   *
   * @param stage the primary stage of the application
   * @throws IOException if the "src/main/resources/fxml/room.fxml" file is not found
   */
  @Override
  public void start(final Stage stage) throws IOException {
    SceneManager.addUi(SceneManager.AppUi.MAINSCENE, loadFxml("mainscene"));
    Thread initialize = new Thread(initializescene);
    initialize.start();
    SceneManager.addUi(SceneManager.AppUi.INTROSCENE, loadFxml("introscene"));
    SceneManager.addUi(
        SceneManager.AppUi.INITIALARTIFACTSCENE, loadFxml("initialscenewithartifact"));
    SceneManager.addUi(
        SceneManager.AppUi.INTIALWITHOUTARTIFACTSCENE, loadFxml("initialscenewithOUTartifact"));
    AudioManager.addAudio(AudioManager.AudioType.INITIALTHEFTAUDIO, "intialtheftaudio");
    AudioManager.addAudio(AudioManager.AudioType.AUDIENCEMURMUR, "audienceMurmur");
    scene = new Scene(SceneManager.getUiRoot(AppUi.INTROSCENE));
    stage.setScene(scene);
    stage.show();
  }
}
