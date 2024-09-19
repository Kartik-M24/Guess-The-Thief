package nz.ac.auckland.se206;

import java.net.URISyntaxException;
import java.util.HashMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
  public enum AudioType {
    INITIALTHEFTAUDIO,
    AUDIENCEMURMUR,
    BACKGROUNDMUSIC,
    TYPEWRITER,
    PAGEFLIP,
    DOOR,
    PHONENEXT,
    PHONEBACK
  }

  private Media sound;
  private MediaPlayer player;
  private static HashMap<AudioType, String> audioMap = new HashMap<AudioType, String>();

  public static void addAudio(AudioType audioType, String audioFileName) {
    audioMap.put(audioType, audioFileName);
  }

  public void playAudio(AudioType audioType, double volume) {
    String audioFileName = audioMap.get(audioType);
    try {
      sound =
          new Media(App.class.getResource("/sounds/" + audioFileName + ".mp3").toURI().toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    player = new MediaPlayer(sound);
    player.setVolume(volume);
    player.play();
  }

  public void stopAudio() {
    if (player != null) {
      player.stop(); // Stop the audio immediately
      player = null; // Clean up the player
    }
  }
}
