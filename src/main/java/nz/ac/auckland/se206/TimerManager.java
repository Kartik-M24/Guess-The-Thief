package nz.ac.auckland.se206;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimerManager {

  private static TimerManager instance;
  private static Timeline timeline;

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

  public void startTimer() {
    timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
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
