package controllers.dao;

import models.Question;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class AskedByQuestionsDAO {
    public static List<Question> getAskedByQuestions(String userId) {
        return EMPTY_LIST;
    }

    public static List<Question> getAskedByQuestions(String userId, String questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
