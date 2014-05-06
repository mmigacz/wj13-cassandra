package controllers.dao;

import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.DriverException;
import models.Credentials;
import models.NewUser;
import models.User;
import play.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserDAO {

    private static String md5(String text) {
        if(text == null || text.isEmpty())
            return text;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(text.getBytes("UTF-8"));
            return new String(thedigest);
        } catch (NoSuchAlgorithmException e) {
            Logger.error("no MD5");
        } catch (UnsupportedEncodingException e) {
            Logger.error("usupprted encoding");
        }
        return text;
    }

    public static boolean updateUser(User user) {
        Session session = CassandraClient.getInstance().getSession();

        PreparedStatement statement = session.prepare(
            "update user set first_name = ?, last_name = ?, email = ?, password = ?, reputation = ? where login = ?;"
        );

        BoundStatement boundStatement = new BoundStatement(statement);

        try {
            session.execute(boundStatement.bind(user.firstName, user.lastName, user.email, md5(user.password), user.reputation, user.login));
            return true;
        } catch (DriverException e) {
            Logger.error("unable update user", e);
        }

        return false;
    }

    public static boolean createUser(NewUser user) {
        Session session = CassandraClient.getInstance().getSession();

        PreparedStatement statement = session.prepare(
            "insert into user (login, first_name, last_name, email, password, reputation) " +
                    "values(?, ?, ?, ?, ?, ?) IF NOT EXISTS;"
        );

        BoundStatement boundStatement = new BoundStatement(statement);
        ResultSet rs = session.execute(boundStatement.bind(user.login, user.firstName, user.lastName, user.email, md5(user.password), 0));
        return rs.one().getBool(0);
    }

    public static boolean authenticate(Credentials credentials) {
        Session session = CassandraClient.getInstance().getSession();
        PreparedStatement statement = session.prepare(
                "select password from user where login = ?;"
        );

        ResultSet rs = session.execute(new BoundStatement(statement).bind(credentials.login));
        return md5(credentials.password).equals(rs.one().getString(0));
    }


    public static User getUser(String login) {
        Session session = CassandraClient.getInstance().getSession();

        PreparedStatement statement = session.prepare(
                "select login, first_name, last_name, email, password, reputation from user where login = ?; "
        );

        BoundStatement boundStatement = new BoundStatement(statement);
        ResultSet rs = session.execute(boundStatement.bind(login));
        Row row = rs.one();
        if(row != null) {
            return new User(
                    row.getString("login"),
                    row.getString("first_name"),
                    row.getString("last_name"),
                    row.getString("email"),
                    row.getString("password"),
                    row.getInt("reputation")
            );
        } else {
            return new User(login);
        }
    }
}
