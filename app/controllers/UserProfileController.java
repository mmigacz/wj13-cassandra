package controllers;

import controllers.dao.UserDAO;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.userprofile;
import views.html.userprofileedit;

public class UserProfileController extends Controller {

    public static Result display(String login) {
        User user = UserDAO.getUser(login);
        return ok(userprofile.render(user));
    }

    public static Result edit() {
        User user = LoginController.getCurrentUser();
        return ok(userprofileedit.render(Form.form(User.class).fill(user)));
    }

    public static Result saveProfile() {
        Form<User> form = Form.form(User.class).bindFromRequest(request());
        if (form.hasErrors())
            return badRequest(userprofileedit.render(form));

        String updateLogin = form.apply("login").valueOr("");
        String currentLogin = LoginController.getCurrentUserId();
        if (!updateLogin.equals(currentLogin)) {
            form.reject("You are allowed to edit only your own profile.");
            return badRequest(userprofileedit.render(form));
        }

        if (!UserDAO.updateUser(form.get())) {
            form.reject("User cannot be saved.");
            return badRequest(userprofileedit.render(form));
        }

        return ok(userprofile.render(LoginController.getCurrentUser()));
    }

}
