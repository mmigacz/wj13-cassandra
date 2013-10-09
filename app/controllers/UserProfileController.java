package controllers;

import models.User;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.userprofile;
import views.html.userprofileedit;

public class UserProfileController extends Controller {

    public static Result display(String login) {
        User user = LoginController.getUser(login);
        return ok(userprofile.render(user));
    }


    public static Result edit() {
        User user = LoginController.getCurrentUser();
        return ok(userprofileedit.render(Form.form(User.class).fill(user)));
    }

    public static Result saveProfile() {
        return ok(userprofile.render(LoginController.getCurrentUser()));
    }

}
