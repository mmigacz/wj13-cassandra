package controllers;

import models.Question;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import tyrex.services.UUID;
import views.html.newquestion;

import java.util.Date;

public class NewQuestionController extends Controller {

    public static Result newQuestionForm() {
        Form<Question> form = new Form<Question>(Question.class);
        return ok(newquestion.render(form));
    }


    public static Result saveQuestion() {
        Form<Question> form = new Form<Question>(Question.class).bindFromRequest(request());
        Question question = form.get();
        question.date = new Date();
        question.author = LoginController.getCurrentUser();

        createNewQuestion(question);

        return QuestionController.display(question.id);
    }

    private static void createNewQuestion(Question question) {
        // TODO generate identifier and create a question in a database
        question.id = UUID.create();
    }
}


