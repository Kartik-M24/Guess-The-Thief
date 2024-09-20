package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import nz.ac.auckland.apiproxy.chat.openai.ChatCompletionRequest;
import nz.ac.auckland.apiproxy.chat.openai.ChatCompletionResult;
import nz.ac.auckland.apiproxy.chat.openai.ChatMessage;
import nz.ac.auckland.apiproxy.chat.openai.Choice;
import nz.ac.auckland.apiproxy.config.ApiProxyConfig;
import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;
import nz.ac.auckland.se206.AudioManager;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimerManager;
import nz.ac.auckland.se206.prompts.PromptEngineering;

public class AuctioneerRoomController extends MasterController {
  private static boolean isAuctioneerRoomVisited;

  public static boolean isAuctioneerRoomVisited() {
    return isAuctioneerRoomVisited;
  }

  public static void setAuctioneerRoomVisited() {
    isAuctioneerRoomVisited = false;
  }

  @FXML private ImageView archaeologistpp;
  @FXML private ImageView collectorpp;
  @FXML private ImageView imgCrimeScene;
  @FXML private TextArea txtaChat;
  @FXML private TextField txtInput;
  @FXML private Button btnSend;

  private ChatCompletionRequest chatCompletionRequest;
  private AudioManager audioManager = new AudioManager();

  /** Initializes the Auctioneer room view. */
  @FXML
  public void initialize() {
    setProfession();
    isAuctioneerRoomVisited = false;
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
  }

  /**
   * Generates the system prompt based on the profession.
   *
   * @return the system prompt string
   */
  private String getSystemPrompt() {
    return PromptEngineering.getPrompt("auctioneerPrompt.txt");
  }

  /**
   * Sets the profession for the chat context and initializes the ChatCompletionRequest.
   *
   * @param profession the profession to set
   */
  public void setProfession() {
    // Sets the profession of the auctioneer and sets gpt to respons accordingly
    try {
      ApiProxyConfig config = ApiProxyConfig.readConfig();
      chatCompletionRequest =
          new ChatCompletionRequest(config)
              .setN(1)
              .setTemperature(0.2)
              .setTopP(0.5)
              .setMaxTokens(100);
      runGpt(new ChatMessage("system", getSystemPrompt()));
    } catch (ApiProxyException e) {
      e.printStackTrace();
    }
  }

  /**
   * Appends a chat message to the chat text area.
   *
   * @param msg the chat message to append
   */
  private void appendChatMessage(ChatMessage msg) {
    if (msg.getRole().equals("user")) {
      txtaChat.appendText("You" + ": " + msg.getContent() + "\n\n");
    } else {
      txtaChat.appendText("Emily Clarke" + ": " + msg.getContent() + "\n\n");
    }
  }

  /**
   * Runs the GPT model with a given chat message.
   *
   * <p>This method processes a chat message by sending it to the GPT model and appending the
   * response to the chat area. It also updates the chat history and handles any exceptions that
   * occur during the API call.
   *
   * @param msg the chat message to process
   * @return the response chat message
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  private ChatMessage runGpt(ChatMessage msg) throws ApiProxyException {
    isAuctioneerRoomVisited = true;
    txtaChat.appendText("Emily Clarke is thinking...");

    // Create a task to run the GPT model asynchronously
    Task<ChatMessage> task =
        new Task<ChatMessage>() {
          @Override
          protected ChatMessage call() {
            chatCompletionRequest.addMessage(msg);
            try {
              // Execute the chat completion request and get the result
              ChatCompletionResult chatCompletionResult = chatCompletionRequest.execute();
              Choice result = chatCompletionResult.getChoices().iterator().next();
              chatCompletionRequest.addMessage(result.getChatMessage());

              // Replace the "thinking" text with the actual response
              int beginIndex = txtaChat.getText().lastIndexOf("Emily Clarke is thinking...");
              txtaChat.replaceText(beginIndex, beginIndex + 27, "");

              // Append the response message to the chat area
              appendChatMessage(result.getChatMessage());
              return result.getChatMessage();
            } catch (ApiProxyException e) {
              // Handle any exceptions that occur during the API call
              e.printStackTrace();
              return null;
            }
          }
        };

    Thread gtpThread = new Thread(task);
    gtpThread.start();
    return msg;
  }

  /**
   * Handles the key pressed event.
   *
   * @param event the key event
   */
  @FXML
  public void onKeyPressed(KeyEvent event) {
    if (event.getCode().toString().equals("ENTER")) {
      try {
        onSendMessage(new ActionEvent());
      } catch (ApiProxyException | IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Handles the key pressed event.
   *
   * @param event the key event
   */
  @FXML
  public void onKeyTyped(KeyEvent event) {
    // Play typewriter audio
    audioManager.playAudio(AudioManager.AudioType.TYPEWRITER, 0.5);
  }

  /**
   * Sends a message to the GPT model.
   *
   * @param event the action event triggered by the send button
   * @throws ApiProxyException if there is an error communicating with the API proxy
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void onSendMessage(ActionEvent event) throws ApiProxyException, IOException {
    // Sends the message to GPT to get a respone back from GPT
    String message = txtInput.getText().trim();
    if (message.isEmpty()) {
      return;
    }
    txtInput.clear();
    ChatMessage msg = new ChatMessage("user", message);
    appendChatMessage(msg);
    runGpt(msg);
  }

  /**
   * Handles mouse click to go to the Crime Scene.
   *
   * @param event the mouse event triggered by clicking a rectangle
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void handleCrimeSceneClick(MouseEvent event) throws IOException {
    audioManager.playAudio(AudioManager.AudioType.CRIMESCENE, 0.4);
    ImageView button = (ImageView) event.getSource();
    Scene sceneButtonIsIn = button.getScene();
    sceneButtonIsIn.setRoot(SceneManager.getUiRoot(AppUi.MAINSCENE));
  }
}
