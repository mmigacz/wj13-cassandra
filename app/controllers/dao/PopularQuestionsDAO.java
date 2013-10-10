package controllers.dao;

import models.Question;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class PopularQuestionsDAO {
    public static List<Question> getPopularQuestions() {
        return EMPTY_LIST;
    }

    public static List<Question> getPopularQuestions(String questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
