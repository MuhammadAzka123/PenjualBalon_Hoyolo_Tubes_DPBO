package model;

public class RegularUser extends User {
    public RegularUser(int id, String username, String email, String bio) {
        super(id, username, email, bio);
    }

    @Override
    public void displayProfile() {
        System.out.println("\n========================================");
        System.out.println("              REGULAR USER");
        System.out.println("========================================");
        super.displayProfile();
    }
}
