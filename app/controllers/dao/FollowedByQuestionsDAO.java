package controllers.dao;

import models.Question;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class FollowedByQuestionsDAO {
    public static List<Question> getFollowedByQuestions(String userId) {
        return EMPTY_LIST;
    }

    public static List<Question> getFollowedByQuestions(String userId, String questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
