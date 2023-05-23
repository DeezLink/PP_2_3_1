package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();

    void saveUser(User user);

    void updateUser(User user);

    User show(int id);

    void deleteUser(int id);
}