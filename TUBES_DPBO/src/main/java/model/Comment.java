package model;
public class Comment {
    private String content;
    private User author;

    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public void displayComment() {
        System.out.println(author.getUsername() + ": " + content);
    }

    public void setContent(String content) {
        this.content = content;
    }
}
