package menu;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.AdminUser;
import model.Comment;
import model.Post;
import service.PostManager;
import model.RegularUser;
import model.User;
import service.UserManager;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static PostManager postManager = new PostManager();
    static UserManager userManager = new UserManager();
    static User currentUser;
    public static void main(String[] args) {
        try {
            User user1 = new RegularUser(1, "Aether", "aether@example.com", "Traveler from Mondstadt");
            User admin = new AdminUser(2, "Lumine", "lumine@example.com", "Admin of Hoyolab");
            userManager.addUser(user1);
            userManager.addUser(admin);
            currentUser = user1;

            int choice;
            do {
                Display.showMenu(currentUser.getUsername(), currentUser.getEmail());
                while (true) {
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        Display.showError("Input harus berupa angka. Silakan coba lagi.");
                        Display.showMenu(currentUser.getUsername(), currentUser.getEmail());
                    }
                }
                
                switch (choice) {
                    case 1: tampilkanProfil(); break;
                    case 2: buatPost(); break;
                    case 3: tampilkanSemuaPost(); break;
                    case 4: tambahKomentar(); break;
                    case 5: editPost(); break;
                    case 6: hapusPost(); break;
                    case 7: hapusKomentar(); break;
                    case 8: tambahUserBaru(); break;
                    case 9: pindahUser(); break;
                    case 10: editKomentar(); break;
                    case 0: 
                        Display.showSuccess("Terima kasih telah menggunakan aplikasi! Keluar dari aplikasi...");
                        break;
                    default: 
                        Display.showError("Pilihan tidak valid. Silakan pilih menu yang tersedia.");
                }
            } while (choice != 0);

        } catch (Exception e) {
            Display.showError("Terjadi kesalahan: " + e.getMessage());
        }
    }

    static void tampilkanProfil() {
        currentUser.displayProfile();
    }

    static void buatPost() {
        try {
            System.out.print("ID Post: ");
            int postId = Integer.parseInt(scanner.nextLine());
            System.out.print("Isi Post: ");
            String content = scanner.nextLine();
            Post post = new Post(postId, content, currentUser);
            postManager.create(post);
            Display.showSuccess("Post berhasil dibuat!");
        } catch (IllegalArgumentException e) {
            Display.showError("Gagal membuat post: " + e.getMessage());
        }
    }

    static void tampilkanSemuaPost() {
        Display.showPostList(postManager.getAllPosts());
    }

    static void tambahKomentar() {
        try {
            System.out.print("ID Post yang ingin dikomentari: ");
            int postId = Integer.parseInt(scanner.nextLine());
            System.out.print("Isi Komentar: ");
            String commentContent = scanner.nextLine();
            Comment comment = new Comment(commentContent, currentUser);
            postManager.read(postId).addComment(comment);
            Display.showSuccess("Komentar berhasil ditambahkan!");
        } catch (NoSuchElementException e) {
            Display.showError("Post tidak ditemukan.");
        }
    }

    static void editPost() {
        try {
            System.out.print("ID Post yang ingin diedit: ");
            int postId = Integer.parseInt(scanner.nextLine());
            System.out.print("Isi baru: ");
            String newContent = scanner.nextLine();
            postManager.editPost(postId, newContent, currentUser);
            Display.showSuccess("Post berhasil diedit!");
        } catch (Exception e) {
            Display.showError("Gagal mengedit post: " + e.getMessage());
        }
    }

    static void hapusPost() {
        try {
            System.out.print("ID Post yang ingin dihapus: ");
            int postId = Integer.parseInt(scanner.nextLine());
            postManager.deletePost(postId, currentUser);
            Display.showSuccess("Post berhasil dihapus!");
        } catch (Exception e) {
            Display.showError("Gagal menghapus post: " + e.getMessage());
        }
    }

    static void hapusKomentar() {
        try {
            System.out.print("ID Post yang mengandung komentar: ");
            int postId = Integer.parseInt(scanner.nextLine());
            System.out.print("Nomor komentar yang ingin dihapus: ");
            int commentIndex = Integer.parseInt(scanner.nextLine());
            postManager.deleteComment(postId, commentIndex, currentUser);
            Display.showSuccess("Komentar berhasil dihapus!");
        } catch (Exception e) {
            Display.showError("Gagal menghapus komentar: " + e.getMessage());
        }
    }

    static void tambahUserBaru() {
        try {
            System.out.print("Masukkan ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Bio: ");
            String bio = scanner.nextLine();
            System.out.print("Tipe user (1 = Regular, 2 = Admin): ");
            int tipe = Integer.parseInt(scanner.nextLine());

            User newUser = (tipe == 2)
                    ? new AdminUser(id, username, email, bio)
                    : new RegularUser(id, username, email, bio);
            userManager.addUser(newUser);
            Display.showSuccess("User berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            Display.showError("Gagal menambahkan user: " + e.getMessage());
        }
    }

    static void pindahUser() {
        System.out.print("Masukkan email user yang ingin login: ");
        String email = scanner.nextLine();
        try {
            currentUser = userManager.getUserByEmail(email);
            Display.showSuccess("Berhasil login sebagai " + currentUser.getUsername() + "!");
        } catch (NoSuchElementException e) {
            Display.showError("Login gagal: " + e.getMessage());
        }
    }

    static void editKomentar() {
        try {
            System.out.print("ID Post: ");
            int postId = Integer.parseInt(scanner.nextLine());
            System.out.print("Nomor komentar: ");
            int commentIndex = Integer.parseInt(scanner.nextLine());
            System.out.print("Isi komentar baru: ");
            String newContent = scanner.nextLine();
            postManager.editComment(postId, commentIndex, newContent, currentUser);
            Display.showSuccess("Komentar berhasil diedit!");
        } catch (Exception e) {
            Display.showError("Gagal mengedit komentar: " + e.getMessage());
        }
    }
}
