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
    PHONEBACK,
    CRIMESCENE,
    FUSEBOXOPEN,
    FUSEBOXCLOSE,
    WALKING,
    CCTVSTART,
    CCTVSTOP,
    TIMESUP,
    POLICESIREN
  }

  private static HashMap<AudioType, String> audioMap = new HashMap<AudioType, String>();

  public static void addAudio(AudioType audioType, String audioFileName) {
    audioMap.put(audioType, audioFileName);
  }

  private Media sound;
  private MediaPlayer player;

  public void playAudio(AudioType audioType, double volume) {
    // Gets the audio file name from the hash map and plays it so the player can hear it when
    // playing the game
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
