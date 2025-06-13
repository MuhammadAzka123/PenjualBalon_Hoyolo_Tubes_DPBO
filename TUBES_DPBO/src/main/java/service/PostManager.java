package service;
import model.AdminUser;
import model.Comment;
import model.Post;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
public class PostManager implements PostCRUD {
    private Map<Integer, Post> posts = new HashMap<>();

    @Override
    public void create(Post post) {
        if (posts.containsKey(post.getId())) {
            throw new IllegalArgumentException("ID post sudah digunakan.");
        }
        posts.put(post.getId(), post);
    }

    @Override
    public Post read(int id) throws NoSuchElementException {
        Post post = posts.get(id);
        if (post == null) {
            throw new NoSuchElementException("Post tidak ditemukan.");
        }
        return post;
    }

    @Override
    public void update(Post post) {
        posts.put(post.getId(), post);
    }

    @Override
    public void delete(int id) throws NoSuchElementException {
        if (!posts.containsKey(id)) {
            throw new NoSuchElementException("Post tidak ditemukan.");
        }
        posts.remove(id);
    }

    public void deletePost(int id, User requester) {
        Post post = posts.get(id);
        if (post == null) throw new NoSuchElementException("Post tidak ditemukan.");
        if (!(requester instanceof AdminUser || post.getAuthor().getEmail().equals(requester.getEmail()))) {
            throw new SecurityException("Anda tidak memiliki izin untuk menghapus post ini.");
        }
        posts.remove(id);
    }

    public void editPost(int id, String newContent, User requester) {
        Post post = posts.get(id);
        if (post == null) throw new NoSuchElementException("Post tidak ditemukan.");
        if (!(requester instanceof AdminUser || post.getAuthor().getEmail().equals(requester.getEmail()))) {
            throw new SecurityException("Anda tidak memiliki izin untuk mengedit post ini.");
        }
        post.setContent(newContent);
    }

    public void deleteComment(int postId, int commentIndex, User requester) {
        Post post = posts.get(postId);
        if (post == null) throw new NoSuchElementException("Post tidak ditemukan.");
        List<Comment> comments = post.getComments();
        if (commentIndex < 1 || commentIndex > comments.size()) {
            throw new IndexOutOfBoundsException("Komentar tidak ditemukan.");
        }
        Comment target = comments.get(commentIndex - 1);
        if (!(requester instanceof AdminUser || target.getAuthor().getEmail().equals(requester.getEmail()))) {
            throw new SecurityException("Anda tidak memiliki izin untuk menghapus komentar ini.");
        }
        post.deleteCommentByIndex(commentIndex);
    }

    public void editComment(int postId, int commentIndex, String newContent, User requester) {
        Post post = posts.get(postId);
        if (post == null) throw new NoSuchElementException("Post tidak ditemukan.");
        List<Comment> comments = post.getComments();
        if (commentIndex < 1 || commentIndex > comments.size()) {
            throw new IndexOutOfBoundsException("Komentar tidak ditemukan.");
        }
        Comment target = comments.get(commentIndex - 1);
        if (!(requester instanceof AdminUser || target.getAuthor().getEmail().equals(requester.getEmail()))) {
            throw new SecurityException("Anda tidak memiliki izin untuk mengedit komentar ini.");
        }
        post.editCommentByIndex(commentIndex, newContent);
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(posts.values());
    }
}