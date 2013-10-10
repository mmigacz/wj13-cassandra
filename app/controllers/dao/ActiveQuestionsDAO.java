package controllers.dao;

import models.Question;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class ActiveQuestionsDAO {
    public static List<Question> getActiveQuestions() {
        return EMPTY_LIST;
    }

    public static List<Question> getActiveQuestions(String questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
