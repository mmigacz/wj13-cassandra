package controllers.dao;

import models.Question;
import models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class RecentQuestionsDAO {
    public static List<Question> getRecentQuestions() {
        // TODO Mock code - remove it after implementing DAO
        User user = new User("jk");
        user.firstName = "Jan";
        user.lastName = "Kowalski";

        Question q1 = new Question();
        q1.id = "1";
        q1.author = user;
        q1.title = "First question";
        q1.voteCount = 5;
        q1.date = new Date();

        Question q2 = new Question();
        q2.id = "2";
        q2.author = user;
        q2.title = "Second question";
        q2.voteCount = 3;
        q2.date = new Date(System.currentTimeMillis() - 24 * 3600 * 1000);

        Question q3 = new Question();
        q3.id = "3";
        q3.author = user;
        q3.title = "Third question";
        q3.date = new Date(System.currentTimeMillis() - 13 * 24 * 3600 * 1000);

        List<Question> questions = new ArrayList<Question>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        return questions;
    }

    public static List<Question> getRecentQuestions(String questionId, Direction direction) {
        return EMPTY_LIST;
    }
}
