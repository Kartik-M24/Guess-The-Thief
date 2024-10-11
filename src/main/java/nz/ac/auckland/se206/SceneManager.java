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
    LOCKSCENE,
    FUSEBOXINITIAL,
    GAMELOST,
    SECURITYFOOTAGEAFTER,
    SECURITYFOOTAGEBEFORE,
    SECURITYFOOTAGEMIDDLE
  }

  private static HashMap<AppUi, Parent> sceneMap = new HashMap<AppUi, Parent>();

  /**
   * Adds a UI scene to the scene map.
   *
   * @param appUi the enum representing the UI scene to be added
   * @param uiroot the Parent node representing the root of the UI scene
   */
  public static void addUi(AppUi appUi, Parent uiroot) {
    sceneMap.put(appUi, uiroot);
  }

  /**
   * Retrieves the root node of a specified UI scene.
   *
   * @param appUi the enum representing the UI scene to be retrieved
   * @return the Parent node representing the root of the UI scene, or null if the scene is not
   *     found
   */
  public static Parent getUiRoot(AppUi appUi) {
    return sceneMap.get(appUi);
  }

  /** Clears all UI scenes from the scene map. */
  public static void reset() {
    sceneMap.clear();
  }
}
