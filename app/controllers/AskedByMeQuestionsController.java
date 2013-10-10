package controllers;

import controllers.dao.AskedByQuestionsDAO;
import controllers.dao.Direction;
import models.Question;
import models.QuestionList;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class AskedByMeQuestionsController extends Controller {
    public static Result askedByMe() {
        String userId = LoginController.getCurrentUserId();
        List<Question> questions = AskedByQuestionsDAO.getAskedByQuestions(userId);
        return askedbyInternal(questions);
    }

    public static Result askedByMeAfter(String questionId) {
        String userId = LoginController.getCurrentUserId();
        List<Question> questions = AskedByQuestionsDAO.getAskedByQuestions(userId, questionId, Direction.AFTER);
        return askedbyInternal(questions);
    }

    public static Result askedByMeBefore(String questionId) {
        String userId = LoginController.getCurrentUserId();
        List<Question> questions = AskedByQuestionsDAO.getAskedByQuestions(userId, questionId, Direction.BEFORE);
        return askedbyInternal(questions);
    }

    private static Result askedbyInternal(List<Question> questions) {
        QuestionList list = new QuestionList(QuestionList.Category.ASKED_BY_ME, questions);
        if (!questions.isEmpty()) {
            list.forwardLink = routes.AskedByMeQuestionsController.askedByMeAfter(questions.get(questions.size() - 1).id).url();
            list.backwardLink = routes.AskedByMeQuestionsController.askedByMeBefore(questions.get(questions.size() - 1).id).url();
        }
        return ok(index.render("Questions Asked By Me", list));
    }
}
