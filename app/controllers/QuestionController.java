package controllers;

import controllers.dao.AnswerDAO;
import controllers.dao.QuestionDAO;
import models.Answer;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.question;

import java.util.Date;

public class QuestionController extends Controller {

    public static Result display(String id) {
        Form<Answer> form = new Form<Answer>(Answer.class);
        QuestionDAO.incrementViewsNumber(id);
        return ok(question.render(QuestionDAO.getQuestion(id), form));
    }

    public static Result postAnswer(String id) {
        if (!LoginController.isAuthenticated())
            return badRequest("You must be authenticated to post answers");

        Form<Answer> answerForm = Form.form(Answer.class).bindFromRequest(request());
        if (!answerForm.hasErrors()) {
            Answer answer = answerForm.get();
            answer.author = LoginController.getCurrentUser();
            answer.date = new Date();
            answer.voteCount = 0;
            AnswerDAO.saveAnswer(id, answer);
        }
        return redirect(routes.QuestionController.display(id));
    }

    public static Result upvoteQuestion(String questionId) {
        QuestionDAO.incQuestionVotes(questionId);
        return redirect(routes.QuestionController.display(questionId));
    }

    public static Result upvoteAnswer(String questionId, String answerId) {
        AnswerDAO.incAnswerVotes(questionId, answerId);
        return redirect(routes.QuestionController.display(questionId));
    }

    public static Result downvoteQuestion(String questionId) {
        QuestionDAO.decQuestionVotes(questionId);
        return redirect(routes.QuestionController.display(questionId));
    }

    public static Result downvoteAnswer(String questionId, String answerId) {
        AnswerDAO.decAnswerVotes(questionId, answerId);
        return redirect(routes.QuestionController.display(questionId));
    }

    public static Result followQuestion(String questionId) {
        QuestionDAO.setFollowStatus(LoginController.getCurrentUserId(), questionId, true);
        return redirect(routes.QuestionController.display(questionId));
    }

    public static Result unfollowQuestion(String questionId) {
        QuestionDAO.setFollowStatus(LoginController.getCurrentUserId(), questionId, false);
        return redirect(routes.QuestionController.display(questionId));
    }

}


