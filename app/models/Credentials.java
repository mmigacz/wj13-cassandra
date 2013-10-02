package models;

import play.data.validation.Constraints;

public class Credentials {
    @Constraints.Required
    public String login;

    @Constraints.Required
    public String password;
}
