package controllers;

import controllers.dao.Direction;
import controllers.dao.RecentQuestionsDAO;
import models.Question;
import models.QuestionList;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class RecentQuestionsController extends Controller {
    public static Result recent() {
        List<Question> questions = RecentQuestionsDAO.getRecentQuestions();
        return recentInternal(questions);
    }

    public static Result recentAfter(String questionId) {
        List<Question> questions = RecentQuestionsDAO.getRecentQuestions(questionId, Direction.AFTER);
        return recentInternal(questions);
    }

    public static Result recentBefore(String questionId) {
        List<Question> questions = RecentQuestionsDAO.getRecentQuestions(questionId, Direction.AFTER);
        return recentInternal(questions);
    }

    private static Result recentInternal(List<Question> questions) {
        QuestionList list = new QuestionList(QuestionList.Category.RECENT, questions);
        if (!questions.isEmpty()) {
            list.forwardLink = routes.RecentQuestionsController.recentAfter(questions.get(questions.size() - 1).id).url();
            list.backwardLink = routes.RecentQuestionsController.recentBefore(questions.get(questions.size() - 1).id).url();
        }
        return ok(index.render("Recent Questions", list));
    }
}
