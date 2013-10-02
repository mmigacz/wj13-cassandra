package models;

import play.data.validation.Constraints;

public class User {
    @Constraints.Required
    @Constraints.Pattern("[a-zA-Z0-9_]+")
    public String login;

    public String firstName;
    public String lastName;

    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    @Constraints.MinLength(8)
    public String password;

    public int reputation = 0;

    public User(String login) {
        this.login = login;
    }

    public String getDisplayName() {
        if (firstName != null && lastName != null)
            return firstName + " " + lastName;
        if (firstName != null)
            return firstName;
        if (lastName != null)
            return lastName;

        return login;
    }
}
