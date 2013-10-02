package models;

import java.util.List;

public class Answer {
    public String id;
    public User user;
    public String text;
    public int votes;
    public List<Comment> comments;
}
