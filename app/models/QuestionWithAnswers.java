package models;

import java.util.ArrayList;
import java.util.List;

public class QuestionWithAnswers extends Question {
    public Question question;
    public List<Comment> comments = new ArrayList<Comment>();
    public List<Answer> answers = new ArrayList<Answer>();
}
