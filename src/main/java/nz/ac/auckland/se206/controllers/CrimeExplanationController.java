package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
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
import nz.ac.auckland.se206.TimerManager;
import nz.ac.auckland.se206.prompts.PromptEngineering;

/**
 * Controls the crime explanation scene, managing user interactions and chat communication with the
 * evaluation bot. Handles message sending, timer updates, and scene transitions, providing feedback
 * based on user input.
 */
public class CrimeExplanationController extends MasterController {

  @FXML private TextArea txtaChat;
  @FXML private TextField txtInput;
  @FXML private ImageView gameoverButton;
  @FXML private ImageView imgSend;

  private ChatCompletionRequest chatCompletionRequest;

  /** Initializes the crime explanation scene whie setting necessary fields . */
  @FXML
  public void initialize() {
    setProfession();
    Timeline timeline = TimerManager.getTimeline();
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> updateTimer()));
    gameoverButton.setVisible(false);
  }

  /** Updates the timer accordingly with all its effects. */
  @Override
  public void updateTimer() {
    // Updates the timer accordingly with all its effects
    if (timerManager.isTimeUp() && GuessingSceneController.isUserAtExplanationScene) {
      try {
        onSendMessage(null);
      } catch (ApiProxyException | IOException e) {
        e.printStackTrace();
      }
    }
    timer.setText(timerManager.getFormattedTime());
  }

  /**
   * Generates the system prompt based on the profession.
   *
   * @return the system prompt string
   */
  private String getSystemPrompt() {

    return PromptEngineering.getPrompt("crimePrompt.txt");
  }

  /** Sets the profession for the chat context and initializes the ChatCompletionRequest. */
  public void setProfession() {
    // Sets the prompt accordingly so it responds accordingly
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
      txtaChat.appendText("Evaluation Bot" + ": " + msg.getContent() + "\n\n");
    }
  }

  /**
   * Runs the GPT model with a given chat message.
   *
   * @param msg the chat message to process
   * @return the response chat message
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  private ChatMessage runGpt(ChatMessage msg) throws ApiProxyException {
    // Runs GPT and call the evaluation bot to respond to the player
    txtaChat.appendText("Evaluation Bot is thinking...");

    Task<ChatMessage> task =
        new Task<ChatMessage>() {
          @Override
          protected ChatMessage call() {
            chatCompletionRequest.addMessage(msg);
            try {

              // Calls GPT to set up the response
              ChatCompletionResult chatCompletionResult = chatCompletionRequest.execute();
              Choice result = chatCompletionResult.getChoices().iterator().next();
              chatCompletionRequest.addMessage(result.getChatMessage());

              int beginIndex = txtaChat.getText().lastIndexOf("Evaluation Bot is thinking...");
              txtaChat.replaceText(beginIndex, beginIndex + 29, "");
              // Appends the chat to the player so they can read it
              appendChatMessage(result.getChatMessage());
              return result.getChatMessage();
            } catch (ApiProxyException e) {
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
    // Check if the ENTER key was pressed
    if (event.getCode().toString().equals("ENTER")) {
      try {
        // Trigger the send message action
        onSendMessage(null);
      } catch (ApiProxyException | IOException e) {
        // Print stack trace if an exception occurs
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
  private void onSendMessage(MouseEvent event) throws ApiProxyException, IOException {
    // Sends the message to GPT and runs accordingly
    String message = txtInput.getText().trim();
    if (message.isEmpty()) {
      return;
    }

    // Sets appropriate fields correctly
    txtInput.clear();
    ChatMessage msg = new ChatMessage("user", message);
    appendChatMessage(msg);
    runGpt(msg);
    imgSend.setVisible(false);
    txtInput.setDisable(true);
    imgSend.setDisable(true);
    gameoverButton.setVisible(true);
    gameoverButton.setDisable(false);
  }
}
