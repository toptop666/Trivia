package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collections;

// TriviaDataBase class is meant for reading the data base and outputting the questions in a
// comfortable data structure
public class TriviaDataBase {

    // the data structure we use is arrayList of Question();
    private ArrayList<Question> _questions;

    // represents the number of lines each question spreads on. the format is q \n correct \n op1 \n \op2 \n op3 \n
    private final int BLOCK_LENGTH = 5;

    // constructs an object according to the database
    public TriviaDataBase() {
        try {
            Scanner input = new Scanner(new File("trivia.txt"));
            ArrayList<Question> lines = new ArrayList<>();
            while(input.hasNextLine()) {
                String[] q = new String[BLOCK_LENGTH];
                for(int i = 0; i<BLOCK_LENGTH; i++) {
                    q[i] = input.nextLine();
                    if(q[i].equals("")) {
                            q[i] = input.nextLine();
                    }
                }
                ArrayList<String> options = new ArrayList<>();
                options.add(q[1]);
                options.add(q[2]);
                options.add(q[3]);
                options.add(q[4]);
                Question question = new Question(q[0], q[1], options);
                lines.add(question);
            }
            Collections.shuffle(lines);
            this._questions = lines;
        } catch (FileNotFoundException e) {
            System.out.println("Could not open the file");
        }
    }

    // shuffle method is used to shuffle the order of the questions
    public void shuffle() {
        Collections.shuffle(this._questions);
    }

    // getter to the arraylist of the questions
    public ArrayList<Question> get_questions() {
        return _questions;
    }

}
