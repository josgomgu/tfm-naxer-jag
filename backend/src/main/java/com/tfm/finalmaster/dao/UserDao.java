package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    boolean deleteUser(Long userid);

    User getUser(Long userid);

    User getUserByLogin(String login);

    User addUser(User user);

    void updateUser(User user);

    User getUserCredentials(User user);
}
