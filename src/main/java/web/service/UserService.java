package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user, String role);

    void updateUser(User user, String role);

    void deleteUser(Long id);

    User getUserByName(String name);

    User getUserById(Long id);
}
