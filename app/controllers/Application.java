package controllers;

import models.Question;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return recent();
    }

    public static Result recent() {

        // TODO: Fetch recent questions from the database:
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

        // -----------------------------------

        return ok(index.render("Recent Questions", 1, questions));
    }

    public static Result votes() {

        // TODO: Fetch questions by the number of votes
        List<Question> questions = new ArrayList<Question>();

        return ok(index.render("Popular Questions", 2, questions));
    }

    public static Result answers() {
        // TODO: Fetch questions by the number of answers

        List<Question> questions = new ArrayList<Question>();
        return ok(index.render("Active Questions", 3, questions));
    }

    public static Result unanswered() {
        // TODO: Fetch unanswered questions

        List<Question> questions = new ArrayList<Question>();
        return ok(index.render("Unanswered Questions", 4, questions));
    }

    public static Result askedBy(String userId) {
        // TODO: Fetch questions asked by a given user

        List<Question> questions = new ArrayList<Question>();
        return ok(index.render("My Questions", 5, questions));
    }

    public static Result followedBy(String userId) {
        // TODO: Fetch questions followed by a given user

        List<Question> questions = new ArrayList<Question>();
        return ok(index.render("Followed", 6, questions));
    }

}
