package com.example.forwork;

public class Feedback {
    private String commenter;
    private String category;
    private String comment;
    private String date;

    public Feedback(String commenter, String category, String comment, String date) {
        setCommenter(commenter);
        setCategory(category);
        setComment(comment);
        setDate(date);
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
