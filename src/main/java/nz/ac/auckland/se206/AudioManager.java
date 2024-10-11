package nz.ac.auckland.se206;

import java.net.URISyntaxException;
import java.util.HashMap;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

  /**
   * Enum representing different types of audio that can be used in the application. Each constant
   * corresponds to a specific sound effect or music track.
   */
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
    POLICESIREN,
    LOCKPICKING,
    WRITING
  }

  private static HashMap<AudioType, String> audioMap = new HashMap<AudioType, String>();

  /**
   * Adds an audio file to the audio map with the specified audio type.
   *
   * @param audioType The type of audio to add.
   * @param audioFileName The name of the audio file to add.
   */
  public static void addAudio(AudioType audioType, String audioFileName) {
    audioMap.put(audioType, audioFileName);
  }

  private DoubleProperty volume = new SimpleDoubleProperty(1.0);

  /**
   * Returns the volume property to be used in other necessary scenes
   *
   * @return The volume property.
   */
  public DoubleProperty volumeProperty() {
    return volume;
  }

  private Media sound;
  private MediaPlayer player;

  /**
   * Plays the specified audio type at the given volume.
   *
   * @param audioType The type of audio to play.
   * @param volume The volume at which to play the audio.
   */
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

  /**
   * Sets the volume of the currently playing audio.
   *
   * @param volume The volume to set.
   */
  public void setVolume(double volume) {
    if (player != null) {
      player.setVolume(volume);
    }
  }

  /** Stops the currently playing audio. */
  public void stopAudio() {
    if (player != null) {
      player.stop(); // Stop the audio immediately
      player = null; // Clean up the player
    }
  }

  /**
   * Binds the volume of the audio player to the specified volume slider.
   *
   * @param volumeSlider The slider to bind the volume to.
   */
  public void bindVolumeSlider(Slider volumeSlider) {
    if (player != null) {
      player.volumeProperty().bind(volumeSlider.valueProperty().divide(100));
    }
  }
}
