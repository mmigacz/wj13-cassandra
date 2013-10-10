package controllers.dao;

import models.Question;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class UnansweredQuestionsDAO {
    public static List<Question> getUnansweredQuestions() {
        return EMPTY_LIST;
    }

    public static List<Question> getUnansweredQuestions(String questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
