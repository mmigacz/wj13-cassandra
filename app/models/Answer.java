package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer {
    public String id;
    public User author;
    public String text;
    public Date date;
    public int voteCount;
    public List<Comment> comments = new ArrayList<Comment>();
}
