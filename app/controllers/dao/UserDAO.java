package controllers.dao;

import models.Credentials;
import models.NewUser;
import models.User;

public class UserDAO {
    public static boolean updateUser(User user) {
        return false; // TODO: implement me
    }

    public static boolean createUser(NewUser user) {
        return false; // TODO: implement me
    }

    public static boolean authenticate(Credentials credentials) {
        return false; // TODO: implement me
    }

    public static User getUser(String login) {
        return new User(login);
    }
}
