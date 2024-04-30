package com.septemarch.test.service;

import com.septemarch.test.dto.SearchDTO;
import com.septemarch.test.entitis.User;

import java.util.List;

public interface UserService{
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    User getUser(long id);

    List<User> getAllUsers();

    List<User> searchUsers(SearchDTO searchDTO);

}
