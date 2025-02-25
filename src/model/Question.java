package model;


public class Question {
    private Integer id;
    private String text;

    public Question() {
    }

    public Question(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return id + " - " + text;
    }
}
