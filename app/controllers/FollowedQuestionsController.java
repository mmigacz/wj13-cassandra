package controllers;

import controllers.dao.Direction;
import controllers.dao.FollowedByQuestionsDAO;
import models.Question;
import models.QuestionList;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class FollowedQuestionsController extends Controller {
    public static Result followedByMe() {
        String userId = LoginController.getCurrentUserId();
        List<Question> questions = FollowedByQuestionsDAO.getFollowedByQuestions(userId);
        return followedInternal(questions);
    }

    public static Result followedByMeAfter(String questionId) {
        String userId = LoginController.getCurrentUserId();
        List<Question> questions = FollowedByQuestionsDAO.getFollowedByQuestions(userId, questionId, Direction.AFTER);
        return followedInternal(questions);
    }

    public static Result followedByMeBefore(String questionId) {
        String userId = LoginController.getCurrentUserId();
        List<Question> questions = FollowedByQuestionsDAO.getFollowedByQuestions(userId, questionId, Direction.BEFORE);
        return followedInternal(questions);
    }

    private static Result followedInternal(List<Question> questions) {
        QuestionList list = new QuestionList(QuestionList.Category.FOLLOWED_BY_ME, questions);
        if (!questions.isEmpty()) {
            list.forwardLink = routes.FollowedQuestionsController.followedByMeAfter(questions.get(questions.size() - 1).id).url();
            list.backwardLink = routes.FollowedQuestionsController.followedByMeBefore(questions.get(questions.size() - 1).id).url();
        }
        return ok(index.render("Questions Followed By Me", list));
    }
}
