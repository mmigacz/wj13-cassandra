package controllers;

import controllers.dao.ActiveQuestionsDAO;
import controllers.dao.Direction;
import models.Question;
import models.QuestionList;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class ActiveQuestionsController extends Controller {
    public static Result active() {
        List<Question> questions = ActiveQuestionsDAO.getActiveQuestions();
        return activeInternal(questions);
    }

    public static Result activeAfter(String questionId) {
        List<Question> questions = ActiveQuestionsDAO.getActiveQuestions(questionId, Direction.AFTER);
        return activeInternal(questions);
    }

    public static Result activeBefore(String questionId) {
        List<Question> questions = ActiveQuestionsDAO.getActiveQuestions(questionId, Direction.BEFORE);
        return activeInternal(questions);
    }

    private static Result activeInternal(List<Question> questions) {
        QuestionList list = new QuestionList(QuestionList.Category.ACTIVE, questions);
        if (!questions.isEmpty()) {
            list.forwardLink = routes.ActiveQuestionsController.activeAfter(questions.get(questions.size() - 1).id).url();
            list.backwardLink = routes.ActiveQuestionsController.activeBefore(questions.get(questions.size() - 1).id).url();
        }
        return ok(index.render("Active Questions", list));
    }
}
