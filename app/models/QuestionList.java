package models;

import java.util.List;

public class QuestionList {

    public enum Category { RECENT, POPULAR, ACTIVE, UNANSWERED, ASKED_BY_ME, FOLLOWED_BY_ME }

    public final Category category;
    public final List<Question> questions;

    public String forwardLink = null;
    public String backwardLink = null;

    public boolean isCategory(Category buttonCategory) {
        return category.equals(buttonCategory);
    }

    public QuestionList(Category category, List<Question> questions) {
        this.category = category;
        this.questions = questions;
    }
}
