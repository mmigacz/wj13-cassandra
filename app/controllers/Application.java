package controllers;

import models.Question;
import models.QuestionList;
import models.User;
import play.mvc.*;
import views.html.*;
import views.html.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application extends Controller {

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

        QuestionList questionList = new QuestionList(QuestionList.Category.RECENT, questions);
        return ok(index.render("Recent Questions", questionList));
    }

    public static Result recentAfter(String questionId) {
        // TODO: Implement paging:
        return recent();
    }

    public static Result recentBefore(String questionId) {
        // TODO: Implement paging:
        return recent();
    }

    public static Result popular() {
        // TODO: Fetch popular questions from the database:
        List<Question> questions = new ArrayList<Question>();
        QuestionList questionList = new QuestionList(QuestionList.Category.POPULAR, questions);
        return ok(index.render("Popular Questions", questionList));
    }

    public static Result popularAfter(String questionId) {
        // TODO: Implement paging
        return popular();
    }

    public static Result popularBefore(String questionId) {
        // TODO: Implement paging
        return popular();
    }

    public static Result active() {
        // TODO: Fetch active questions from the database:

        List<Question> questions = new ArrayList<Question>();
        QuestionList questionList = new QuestionList(QuestionList.Category.ACTIVE, questions);
        return ok(index.render("Active Questions", questionList));
    }

    public static Result activeAfter(String questionId) {
        // TODO: Paging
        return active();
    }

    public static Result activeBefore(String questionId) {
        // TODO: Paging
        return active();
    }

    public static Result unanswered() {
        // TODO: Fetch unanswered questions from the database:

        List<Question> questions = new ArrayList<Question>();
        QuestionList questionList = new QuestionList(QuestionList.Category.UNANSWERED, questions);
        return ok(index.render("Unanswered Questions", questionList));
    }

    public static Result unansweredAfter(String questionId) {
        // TODO: Paging
        return unanswered();
    }

    public static Result unansweredBefore(String questionId) {
        // TODO: Paging
        return unanswered();
    }

    public static Result askedByMe() {
        // TODO: Fetch my questions from the database:

        String userId = LoginController.getCurrentUserId();
        List<Question> questions = new ArrayList<Question>();
        QuestionList questionList = new QuestionList(QuestionList.Category.ASKED_BY_ME, questions);
        return ok(index.render("My Questions", questionList));

    }

    public static Result askedByMeAfter(String questionId) {
        // TODO: Paging
        return askedByMe();
    }

    public static Result askedByMeBefore(String questionId) {
        // TODO: Paging
        return askedByMe();
    }

    public static Result followedByMe() {
        // TODO: Fetch followed questions from the database:

        String userId = LoginController.getCurrentUserId();
        List<Question> questions = new ArrayList<Question>();
        QuestionList questionList = new QuestionList(QuestionList.Category.FOLLOWED_BY_ME, questions);
        return ok(index.render("Followed Questions", questionList));

    }

    public static Result followedByMeAfter(String questionId) {
        // TODO: Paging
        return followedByMe();
    }

    public static Result followedByMeBefore(String questionId) {
        // TODO: Paging
        return followedByMe();
    }

}
