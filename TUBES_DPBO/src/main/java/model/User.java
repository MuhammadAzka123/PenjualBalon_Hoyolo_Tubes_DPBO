package model;

public abstract class User {
    private int id;
    private String username;
    private String email;
    private String bio;

    public User(int id, String username, String email, String bio) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void displayProfile() {
        System.out.println("----------------------------------------");
        System.out.println("Username : " + username);
        System.out.println("Email    : " + email);
        System.out.println("Bio      : " + bio);
        System.out.println("----------------------------------------");
    }
}
