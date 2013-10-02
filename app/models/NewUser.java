package models;

import play.data.validation.Constraints;

public class NewUser extends User {

    public NewUser() {
        super(null);
    }

    public NewUser(String login) {
        super(login);
    }

    @Constraints.Required
    @Constraints.MinLength(8)
    public String password2;

    public String validate() {
        if (password.compareTo(password2) != 0)
            return "Passwords don't match";

        return null;
    }
}
