package sample;

// logic class is doing the logic of the trivia game
public class Logic {

    private TriviaDataBase _triviaDataBase;
    private int _points;
    private int _nextQuestion;
    private Question _current;

    // represents the points gained by answering right
    private final int POINTS_GAIN = 10;

    // represents the points lost by answering wrong
    private final int POINTS_LOST = 5;

    // construct a logic object
    public Logic() {
        this._triviaDataBase = new TriviaDataBase();
        this._current = this._triviaDataBase.get_questions().get(0);
        this._points = 0;
        this._nextQuestion = 1;
    }

    // soft restart to an object
    public boolean restart() {
        this._triviaDataBase.shuffle();
        this._points = 0;
        this._current = this._triviaDataBase.get_questions().get(0);
        this._nextQuestion = 1;
        return true;
    }

    // returns how many questions there are
    public int length() {
        return this._triviaDataBase.get_questions().size();
    }

    // returns true if the is a question left, false otherwise
    public boolean hasNextQuestion() {
        return !(this._nextQuestion == this.length());
    }

    // returns next question in the data structure
    public Question nextQuestion() {
        if(!this.hasNextQuestion()) {
            return null;
        }
        this._current = this._triviaDataBase.get_questions().get(++this._nextQuestion);
        return this._current;
    }

    // returns the current question
    public Question getCurrentQuestion() {
        return this._current;
    }

    // checks an answer
    public boolean checkAnswer(String answer) {
        if(this.getCurrentQuestion().checkAnswer(answer)) {
            this._points += POINTS_GAIN;
            return true;
        }
        else this._points -= POINTS_LOST;
        return false;
    }

    // getter to the amount of points the user gained
    public int getPoints() {
        return this._points;
    }
}
