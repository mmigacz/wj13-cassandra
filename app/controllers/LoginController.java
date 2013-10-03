package controllers;

import models.Credentials;
import models.NewUser;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.helper.form;
import views.html.login;
import views.html.register;

public class LoginController extends Controller {

    public static boolean isAuthenticated() {
        return session("currentUser") != null;
    }

    public static String getCurrentUserId() {
        return session("currentUser");
    }

    public static User getCurrentUser() {
        String login = session("currentUser");
        if (login == null)
            return null;
        return getUser(login);
    }

    private static User getUser(String login) {
        return new User(login);
    }

    public static Result displayLoginPage() {
        session().put(REFERER, request().getHeader(REFERER));
        return ok(login.render(Form.form(Credentials.class)));
    }

    public static Result displayRegistrationPage() {
        session().put(REFERER, request().getHeader(REFERER));
        return ok(register.render(Form.form(NewUser.class)));
    }

    public static Result login() {
        DynamicForm df = DynamicForm.form().bindFromRequest(request());
        if (df.get("_cancel") != null)
            return redirectBackToReferer();

        Form<Credentials> form = Form.form(Credentials.class).bind(df.data());
        if (form.hasErrors()) {
            form.reject("You must provide both login and password.");
            return badRequest(login.render(form));
        }
        if (!authenticate(form.get())) {
            form.reject("Invalid login or password.");
            return badRequest(login.render(form));
        }

        return redirectBackToReferer();
    }

    public static Result register() {
        DynamicForm df = DynamicForm.form().bindFromRequest(request());
        if (df.get("_cancel") != null)
            return redirectBackToReferer();

        Form<NewUser> form = Form.form(NewUser.class).bind(df.data());
        if (form.hasErrors())
            return badRequest(register.render(form));

        if (!createUser(form.get())) {
            form.reject("login", "User already exists.");
            return badRequest(register.render(form));
        }

        return redirectBackToReferer();
    }

    public static Result logout() {
        session().remove("currentUser");
        return redirectBackToReferer();
    }

    public static Result redirectBackToReferer() {
        String referer = session().get(REFERER);
        if (referer == null)
            referer = request().getHeader(REFERER);
        session().remove(REFERER);
        return redirect(referer);
    }

    private static boolean authenticate(Credentials credentials) {
        // TODO: Implement real authentication using Database

        // ------------------
        session().put("currentUser", credentials.login);
        return true;
    }

    private static boolean createUser(NewUser newUser) {
        // TODO: Implement adding a new user to the database

        // ------------------
        session().put("currentUser", newUser.login);
        return true;
    }



}
