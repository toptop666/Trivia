package sample;

import java.util.ArrayList;
import java.util.Collections;

// Question class is a class the represents a trivia question.
public class Question {

    // MAX_QUESTION_LENGTH is a variable represents the maximum length of a question
    // if the question is longer the constructor adds "\n" so the question would be
    // able to fully fit inside the game screen
    private final int MAX_QUESTION_LENGTH = 50;

    // represents the question
    private String _question;

    // represents the correct answer
    private String _correctAnswer;

    // represents the options to the right answer. the correct answer is also in the arraylist
    private ArrayList<String> _options;

    // construct a question
    public Question(String question, String correctAnswer, ArrayList<String> options) {
        //if the question to long  we add "\n" so the question would be able to fully fit inside the game screen
        if (question.length()>MAX_QUESTION_LENGTH) {
            int index = question.indexOf(" ", MAX_QUESTION_LENGTH);
            if(index != -1) {
                question = question.substring(0, index) + "\n" + question.substring(index + 1);
            }
        }

        this._question = question;
        this._correctAnswer = correctAnswer;

        //shuffles the options so the correct answer would not be the first option every time
        Collections.shuffle(options);

        this._options = options;
    }

    // returns the correct answer, this getter exist so we can show the user the correct answer if
    // his answer was a mistake
    public String getCorrectAnswer() {
        return _correctAnswer;
    }

    // returns the question
    public String getQuestion() {
        return _question;
    }

    // inputs a string and outputs a boolean, true of the string is equal to the correct answer,
    // false otherwise
    public boolean checkAnswer(String answer) {
        return this._correctAnswer.equals(answer);
    }

    // returns the options for the possible answer
    public ArrayList<String> getOptions() {
        return _options;
    }
}
