package nz.ac.auckland.se206;

import java.net.URISyntaxException;
import java.util.HashMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
  public enum AudioType {}

  private static Media sound;
  private static HashMap<AudioType, String> audioMap = new HashMap<AudioType, String>();

  public static void addAudio(AudioType audioType, String audioFileName) {
    audioMap.put(audioType, audioFileName);
  }

  public static void playAudio(AudioType audioType) {
    String audioFileName = audioMap.get(audioType);
    try {
      sound =
          new Media(App.class.getResource("/sounds/" + audioFileName + ".mp3").toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    MediaPlayer player = new MediaPlayer(sound);
    player.play();
  }
}
