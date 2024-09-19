package nz.ac.auckland.se206;

import java.util.HashMap;
import javafx.scene.Parent;

public class SceneManager {
  public enum AppUi {
    INTROSCENE,
    MAINSCENE,
    COLLECTORROOM,
    ARCHAEOLOGISTROOM,
    AUCTIONEERROOM,
    SUSPECTSSELECTION,
    INITIALARTIFACTSCENE,
    INTIALWITHOUTARTIFACTSCENE,
    LETTERCLUE,
    FUSEBOXCLUE,
    LECTERNCLUE,
    GUESSINGSCENE,
    CRIMEEXPLANATION,
    GAMEOVER,
    LIGHTSOFFSCENE,
    PHONELOGAUCTIONEER,
    PHONELOGARCHAEOLOGIST,
    PHONELOGCOLLECTOR,
    SECURITYFOOTAGE,
    SECURITYFOOTAGEBEFORE,
    SECURITYFOOTAGEAFTER,
  }

  private static HashMap<AppUi, Parent> sceneMap = new HashMap<AppUi, Parent>();

  public static void addUi(AppUi appUi, Parent uiroot) {
    sceneMap.put(appUi, uiroot);
  }

  public static Parent getUiRoot(AppUi appUi) {
    return sceneMap.get(appUi);
  }

  public static void reset() {
    sceneMap.clear();
  }
}
