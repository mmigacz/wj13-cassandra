package models;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewUser extends User {

    public NewUser() {
    }

    public NewUser(String login) {
        super(login);
    }

    public String password2;

    public Map<String, List<ValidationError>> validate() {
        Map<String, List<ValidationError>> map = new HashMap<String, List<ValidationError>>(1);
        if (password == null || password.length() < 8) {
            map.put("password", Arrays.asList(new ValidationError("password", "Password is required and should have 8 characters at least")));
            return map;
        }

        if (!password.equals(password2)) {
            map.put("password2", Arrays.asList(new ValidationError("password2", "Passwords don't match")));
            return map;
        }

        return null;
    }
}
