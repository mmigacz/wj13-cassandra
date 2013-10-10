package controllers;

import controllers.dao.Direction;
import controllers.dao.UnansweredQuestionsDAO;
import models.Question;
import models.QuestionList;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class UnansweredQuestionsController extends Controller {
    public static Result unanswered() {
        List<Question> questions = UnansweredQuestionsDAO.getUnansweredQuestions();
        return unansweredInternal(questions);
    }

    public static Result unansweredAfter(String questionId) {
        List<Question> questions = UnansweredQuestionsDAO.getUnansweredQuestions(questionId, Direction.AFTER);
        return unansweredInternal(questions);
    }

    public static Result unansweredBefore(String questionId) {
        List<Question> questions = UnansweredQuestionsDAO.getUnansweredQuestions(questionId, Direction.BEFORE);
        return unansweredInternal(questions);
    }

    private static Result unansweredInternal(List<Question> questions) {
        QuestionList list = new QuestionList(QuestionList.Category.UNANSWERED, questions);
        if (!questions.isEmpty()) {
            list.forwardLink = routes.UnansweredQuestionsController.unansweredAfter(questions.get(questions.size() - 1).id).url();
            list.backwardLink = routes.UnansweredQuestionsController.unansweredBefore(questions.get(questions.size() - 1).id).url();
        }
        return ok(index.render("Unanswered Questions", list));
    }
}
