package nz.ac.auckland.se206;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimerManager {

  private static TimerManager instance;
  private static Timeline timeline;

  /**
   * Returns the singleton instance of the TimerManager class. If the instance is null, a new
   * TimerManager is created and returned. Ensures that only one instance of TimerManager exists.
   *
   * @return the singleton instance of TimerManager
   */
  public static TimerManager getInstance() {
    if (instance == null) {
      instance = new TimerManager();
    }
    return instance;
  }

  public static Timeline getTimeline() {
    return timeline;
  }

  private int minutes = 10;
  private int seconds = 59;
  private int milliseconds = 999;

  private TimerManager() {}

  /**
   * Starts a timer that updates every millisecond using a JavaFX Timeline. The timer continuously
   * triggers the updateTimer() method on each key frame. It runs indefinitely until manually
   * stopped or paused.
   */
  public void startTimer() {
    timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  /**
   * Stops the timer if it is currently running. Checks if the timeline is not null before stopping
   * it.
   */
  public void stopTimer() {
    if (timeline != null) {
      timeline.stop();
    }
  }

  private void updateTimer() {
    // This updates and correctly decrements the timer so that the minutes, seconds and milliseconds
    // are decremented every millisecond
    if (milliseconds > 0) {
      milliseconds--;
    } else if (milliseconds == 0 && seconds > 0) {
      milliseconds = 999;
      seconds--;
    } else if (milliseconds == 0 && seconds == 0 && minutes > 0) {
      milliseconds = 999;
      seconds = 59;
      minutes--;
    } else {
      stopTimer();
    }
  }

  /**
   * Sets the timer values for minutes, seconds, and milliseconds.
   *
   * @param minutes the number of minutes to set
   * @param seconds the number of seconds to set
   * @param milliseconds the number of milliseconds to set
   */
  public void setTime(int minutes, int seconds, int milliseconds) {
    this.minutes = minutes;
    this.seconds = seconds;
    this.milliseconds = milliseconds;
  }

  public int getMinutes() {
    return minutes;
  }

  public int getSeconds() {
    return seconds;
  }

  public int getMilliseconds() {
    return milliseconds;
  }

  public String getFormattedTime() {
    return String.format("%1d:%02d", minutes, seconds);
  }

  public boolean isTimeUp() {
    return minutes == 0 && seconds == 0 && milliseconds == 0;
  }
}
