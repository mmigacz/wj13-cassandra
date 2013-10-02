package models;

import java.util.Date;

public class Question {
    public String id;
    public User author;
    public String title;
    public String text;
    public Date date;
    public int voteCount = 0;
    public int answerCount = 0;
    public int viewCount = 0;
}
