package service;
import model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class UserManager {
    private Map<String, User> usersByEmail = new HashMap<>();

    public void addUser(User user) {
        if (usersByEmail.containsKey(user.getEmail())) {
            throw new IllegalArgumentException("Email sudah digunakan oleh user lain.");
        }
        usersByEmail.put(user.getEmail(), user);
    }

    public User getUserByEmail(String email) throws NoSuchElementException {
        User user = usersByEmail.get(email);
        if (user == null) {
            throw new NoSuchElementException("User dengan email tersebut tidak ditemukan.");
        }
        return user;
    }

    public Collection<User> getAllUsers() {
        return usersByEmail.values();
    }
}
