package controllers;

import play.mvc.Result;
import play.mvc.Controller;

public class UserProfileController extends Controller {

    public static Result display(String login) {
        return ok();
    }

}
