package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller {

    private Logic _logic;
    @FXML
    private Text _question;
    @FXML
    private ChoiceBox _options;
    @FXML
    private Text _systemOutput;
    @FXML
    private Button _mainButton;
    @FXML
    private Text _points;
    @FXML
    private Button _restart;

    // holds the screen we are in now
    private screens _flag;
    private enum screens {continueScreen, questionScreen, endGameScreen}

    // the update method starts when the user presses the "submit" button. the method reads the string
    // the user marked in the choisebox, gives him the appropriate answer and goes to continue screen
    // if the user is in continue screen, the method shows a new question and updates the options
    public void update(ActionEvent actionEvent) {
        switch (this._flag) {
            case endGameScreen:
                this._systemOutput.setText("Game end, please hit the restart button to play again!");
                return;
            case continueScreen:
                updateNewQuestion();
                this._flag = screens.questionScreen;
                return;
            case questionScreen:
                if(this._options.getValue() == null) {
                    this._systemOutput.setText("Please choose an answer!");
                    return;
                }
                String answer = (String) this._options.getValue();
                if(this._logic.checkAnswer(answer)) {
                    this._systemOutput.setText("Correct!");
                }
                else {
                    this._systemOutput.setText("Wrong! the correct answer is: " + this._logic.getCurrentQuestion().getCorrectAnswer());
                }
                continueScreen();
                return;
            default:
                return;
        }

    }

    // draws on the window the continue screen.
    public void continueScreen() {
        this._points.setText("" + this._logic.getPoints());
        this._mainButton.setText("press to continue");
        this._flag = screens.continueScreen;
        if(!this._logic.hasNextQuestion()) {
            this._flag = screens.endGameScreen;
        }
    }

    // soft restart of the game, starts when the user press on the restart button
    public void restart(ActionEvent actionEvent) {
        this._logic.restart();
        this._systemOutput.setText("game restarted, press continue to begin");
        this._flag = screens.continueScreen;
        continueScreen();

    }

    // initializes the game
    public void initialize() throws IOException {
        this._logic = new Logic();
        this._points.setText("" + this._logic.getPoints());
        this._flag = screens.questionScreen;
        updateNewQuestion();
    }

    // helper method used to update the screen with a new question
    private boolean updateNewQuestion() {
        if(!_logic.hasNextQuestion()) {
            return false;
        }
        Question question = _logic.nextQuestion();
        this._options.getItems().clear();
        for(int i = 0; i<question.getOptions().size(); i++) {
            _options.getItems().addAll(question.getOptions().get(i));
        }
        this._question.setText(question.getQuestion());
        this._systemOutput.setText("");
        this._mainButton.setText("submit");
        return true;
    }
}
