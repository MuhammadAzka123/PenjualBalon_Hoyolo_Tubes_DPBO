package model;

import java.util.ArrayList;
import java.util.List;
public class Post {
    private int id;
    private String content;
    private User author;
    private List<Comment> comments = new ArrayList<>();

    public Post(int id, String content, User author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void editCommentByIndex(int index, String newContent) {
        if (index >= 1 && index <= comments.size()) {
            comments.get(index - 1).setContent(newContent);
        }
    }

    public void deleteCommentByIndex(int index) {
        if (index >= 1 && index <= comments.size()) {
            comments.remove(index - 1);
        }
    }
}
