package nz.ac.auckland.se206;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimerManager {

  private static TimerManager instance;

  public static TimerManager getInstance() {
    if (instance == null) {
      instance = new TimerManager();
    }
    return instance;
  }

  private int minutes = 4;
  private int seconds = 59;
  private int milliseconds = 999;
  private static Timeline timeline;

  private TimerManager() {}

  public void startTimer() {
    timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  public static Timeline getTimeline() {
    return timeline;
  }

  public void stopTimer() {
    if (timeline != null) {
      timeline.stop();
    }
  }

  private void updateTimer() {
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

  public void setStartingtime() {
    minutes = 4;
    seconds = 59;
    milliseconds = 999;
  }

  public String getFormattedTime() {
    return String.format("%1d:%02d", minutes, seconds);
  }

  // Removed unnecessary methods
}
