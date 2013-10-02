package controllers;

import models.Answer;
import models.Question;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.question;

import java.util.Date;

public class QuestionController extends Controller {

    public static Result display(String id) {

        Form<Answer> form = new Form<Answer>(Answer.class);
        return ok(question.render(getQuestion(id), form));
    }

    private static Question getQuestion(String id) {
        // TODO: Get the question from the database

        User user = new User("jk");
        user.firstName = "Jan";
        user.lastName = "Kowalski";

        Question q = new Question();
        q.id = "1";
        q.author = user;
        q.title = "Some question";
        q.text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
                "culpa qui officia deserunt mollit anim id est laborum";
        q.voteCount = 5;
        q.date = new Date();

        // --------------------------------
        return q;
    }

    public static Result postAnswer(String id) {
        Form<Answer> answerForm = Form.form(Answer.class).bindFromRequest(request());
        return redirect(routes.QuestionController.display(id));
    }
}
