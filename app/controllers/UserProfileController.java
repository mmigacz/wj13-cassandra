package controllers;

import models.User;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.userprofile;

public class UserProfileController extends Controller {

    public static Result display(String login) {
        User user = LoginController.getUser(login);
        return ok(userprofile.render(user));
    }

}
