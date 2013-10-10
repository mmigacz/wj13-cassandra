package controllers;

import controllers.dao.Direction;
import controllers.dao.PopularQuestionsDAO;
import models.Question;
import models.QuestionList;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class PopularQuestionsController extends Controller {
    public static Result popular() {
        List<Question> questions = PopularQuestionsDAO.getPopularQuestions();
        return popularInternal(questions);
    }

    public static Result popularAfter(String questionId) {
        List<Question> questions = PopularQuestionsDAO.getPopularQuestions(questionId, Direction.AFTER);
        return popularInternal(questions);
    }

    public static Result popularBefore(String questionId) {
        List<Question> questions = PopularQuestionsDAO.getPopularQuestions(questionId, Direction.BEFORE);
        return popularInternal(questions);
    }

    private static Result popularInternal(List<Question> questions) {
        QuestionList list = new QuestionList(QuestionList.Category.POPULAR, questions);
        if (!questions.isEmpty()) {
            list.forwardLink = routes.PopularQuestionsController.popularAfter(questions.get(questions.size() - 1).id).url();
            list.backwardLink = routes.PopularQuestionsController.popularBefore(questions.get(questions.size() - 1).id).url();
        }
        return ok(index.render("Popular Questions", list));
    }
}
