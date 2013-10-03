package controllers;

import models.Question;
import models.User;
import play.mvc.*;
import views.html.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        User user = new User("jk");
        user.firstName = "Jan";
        user.lastName = "Kowalski";

        Question q1 = new Question();
        q1.id = "1";
        q1.author = user;
        q1.title = "First question";
        q1.voteCount = 5;
        q1.date = new Date();

        Question q2 = new Question();
        q2.id = "2";
        q2.author = user;
        q2.title = "Second question";
        q2.voteCount = 3;
        q2.date = new Date(System.currentTimeMillis() - 24 * 3600 * 1000);

        Question q3 = new Question();
        q3.id = "3";
        q3.author = user;
        q3.title = "Third question";
        q3.date = new Date(System.currentTimeMillis() - 13 * 24 * 3600 * 1000);


        List<Question> questions = new ArrayList<Question>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);

        return ok(index.render(questions));
    }

    public static Result recent() {
        return index();
    }

    public static Result popular() {
        return ok();
    }

    public static Result active() {
        return ok();
    }

    public static Result unanswered() {
        return ok();
    }

    public static Result askedBy(String userId) {
        return ok();
    }



}
