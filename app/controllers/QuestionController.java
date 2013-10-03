package controllers;

import models.Answer;
import models.Question;
import models.QuestionWithAnswers;
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

    private static QuestionWithAnswers getQuestion(String id) {
        // TODO: Get the question and answers from the database

        User user1 = new User("jk");
        user1.firstName = "Jan";
        user1.lastName = "Kowalski";

        User user2 = new User("aragorn");

        QuestionWithAnswers q = new QuestionWithAnswers();
        q.id = "1";
        q.author = user1;
        q.title = "Some question";
        q.text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
                "culpa qui officia deserunt mollit anim id est laborum";
        q.voteCount = 5;
        q.date = new Date(System.currentTimeMillis() - 300000);

        Answer a = new Answer();
        a.id = "answer-1";
        a.author = user2;
        a.text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, " +
                "totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae " +
                "dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, " +
                "sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam " +
                "est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius " +
                "modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, " +
                "quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea " +
                "commodi consequatur?";
        a.voteCount = 0;
        a.date = new Date(System.currentTimeMillis());
        q.answers.add(a);

        // --------------------------------
        return q;
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
            saveAnswer(id, answer);
        }
        return redirect(routes.QuestionController.display(id));
    }

    private static void saveAnswer(String questionId, Answer answer) {
        // TODO: Add answer to the database

        // --------------------------------
    }
}
