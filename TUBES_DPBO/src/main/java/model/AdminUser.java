package model;
public class AdminUser extends User {
    public AdminUser(int id, String username, String email, String bio) {
        super(id, username, email, bio);
    }

    @Override
    public void displayProfile() {
        System.out.println("\n========================================");
        System.out.println("              ADMIN USER");
        System.out.println("========================================");
        super.displayProfile();
    }
}
