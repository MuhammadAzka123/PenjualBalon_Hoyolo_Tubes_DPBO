package service;
import model.Post;

interface PostCRUD {
    void create(Post post);
    Post read(int id);
    void update(Post post);
    void delete(int id);
}
