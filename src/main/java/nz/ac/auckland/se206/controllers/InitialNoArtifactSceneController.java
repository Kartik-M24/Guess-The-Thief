package nz.ac.auckland.se206.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.TimerManager;

public class InitialNoArtifactSceneController {

  @FXML private Rectangle lightRect;
  @FXML private Pane introPane;

  private TimerManager timerManager = TimerManager.getInstance();

  private FadeTransition fadeLightTransition = new FadeTransition();
  private FadeTransition fadeIntroTransition = new FadeTransition();

  @FXML
  public void initialize() {
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> checkTime()));

    fadeLightTransition.setNode(lightRect);
    fadeLightTransition.setDuration(Duration.seconds(1.5));
    fadeLightTransition.setFromValue(1.0);
    fadeLightTransition.setToValue(0.0);
    fadeLightTransition.setCycleCount(1);
    fadeLightTransition.setAutoReverse(false);

    fadeIntroTransition.setNode(introPane);
    fadeIntroTransition.setDuration(Duration.seconds(1.5));
    fadeIntroTransition.setFromValue(0);
    fadeIntroTransition.setToValue(1);
    fadeIntroTransition.setCycleCount(1);
    fadeIntroTransition.setAutoReverse(false);
  }

  public void checkTime() {
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 44
        && timerManager.getMilliseconds() == 0) {
      fadeLightTransition.play();
    }
    if (timerManager.getMinutes() == 4
        && timerManager.getSeconds() == 40
        && timerManager.getMilliseconds() == 700) {
      fadeIntroTransition.play();
    }
  }
}
