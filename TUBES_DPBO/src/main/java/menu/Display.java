package menu;

import java.util.List;
import model.Comment;
import model.Post;

public class Display {
    public static void showMenu(String username, String email) {
        System.out.println("\n==================================================");
        System.out.println("                    MENU UTAMA");
        System.out.println("==================================================");
        System.out.println("User Aktif: " + username + " (" + email + ")");
        System.out.println("--------------------------------------------------");
        System.out.println(" 1. Tampilkan Profil");
        System.out.println(" 2. Buat Post");
        System.out.println(" 3. Tampilkan Semua Post");
        System.out.println(" 4. Tambahkan Komentar ke Post");
        System.out.println(" 5. Edit Post");
        System.out.println(" 6. Hapus Post");
        System.out.println(" 7. Hapus Komentar dari Post");
        System.out.println(" 8. Tambah User Baru");
        System.out.println(" 9. Pindah User (Login)");
        System.out.println("10. Edit Komentar");
        System.out.println(" 0. Keluar");
        System.out.println("==================================================");
        System.out.print("Masukkan pilihan Anda: ");
    }

    public static void showPostList(List<Post> posts) {
        if (posts.isEmpty()) {
            System.out.println("\n==================================================");
            System.out.println("                    DAFTAR POST                    ");
            System.out.println("--------------------------------------------------");
            System.out.println("           Belum ada post yang diupload.");
            System.out.println("==================================================");
            return;
        }
        System.out.println("\n==================================================");
        System.out.println("                    DAFTAR POST");
        System.out.println("==================================================");
        for (Post post : posts) {
            showSinglePost(post);
        }
    }

    public static void showSinglePost(Post post) {
        System.out.println("\n==================================================");
        System.out.println("Post ID  : " + post.getId());
        System.out.println("Author   : " + post.getAuthor().getUsername());
        System.out.println("Content  : " + post.getContent());
        System.out.println("--------------------------------------------------");
        System.out.println("Komentar:");
        List<Comment> comments = post.getComments();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            System.out.print("  [" + (i + 1) + "] ");
            showComment(c);
        }
        System.out.println("==================================================");
    }

    public static void showComment(Comment c) {
        System.out.println(c.getAuthor().getUsername() + ": " + c.getContent());
    }

    public static void showSuccess(String message) {
        System.out.println("\n[SUKSES] " + message);
    }

    public static void showError(String message) {
        System.out.println("\n[ERROR] " + message);
    }
}
