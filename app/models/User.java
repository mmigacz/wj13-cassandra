package models;

import play.data.validation.Constraints;

public class User {
    @Constraints.Required(message = "Login is required")
    @Constraints.Pattern(value = "[a-zA-Z0-9_]*", message="Login should include only alphanumeric characters and underscores")
    @Constraints.MinLength(value= 3, message = "Minimum length is 3")
    public String login;

    @Constraints.Pattern(value = "[a-zA-Z ]*", message = "First name should consists of letters")
    public String firstName;
    @Constraints.Pattern(value = "[a-zA-Z ]*", message = "Last name should consists of letters")
    public String lastName;

    @Constraints.Required(message = "Email is required")
    @Constraints.Email(message = "This should be a valid email address")
    public String email;

    public String password;

    public int reputation = 0;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(String login, String firstName, String lastName, String email, String password, int reputation) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.reputation = reputation;
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
