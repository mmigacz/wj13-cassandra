package controllers;

import controllers.dao.UserDAO;
import models.Credentials;
import models.NewUser;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
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
        return UserDAO.getUser(login);
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

        Credentials credentials = form.get();
        if (!UserDAO.authenticate(credentials)) {
            form.reject("Invalid login or password.");
            return badRequest(login.render(form));
        }

        session().put("currentUser", credentials.login);
        return RecentQuestionsController.recent();
    }

    public static Result register() {
        DynamicForm df = DynamicForm.form().bindFromRequest(request());
        if (df.get("_cancel") != null)
            return redirectBackToReferer();

        Form<NewUser> form = Form.form(NewUser.class).bind(df.data());
        if (form.hasErrors())
            return badRequest(register.render(form));

        NewUser user = form.get();
        if (!UserDAO.createUser(user)) {
            form.reject("login", "User already exists.");
            return badRequest(register.render(form));
        }

        session().put("currentUser", user.login);
        return login();
    }

    public static Result logout() {
        session().remove("currentUser");
        return RecentQuestionsController.recent();
    }

    public static Result redirectBackToReferer() {
        String referer = session().get(REFERER);
        if (referer == null)
            referer = request().getHeader(REFERER);
        session().remove(REFERER);
        return redirect(referer);
    }

}
