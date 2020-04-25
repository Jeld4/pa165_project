package cz.fi.muni.pa165.service;

import java.util.List;

import cz.fi.muni.pa165.entity.User;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
    User findByEmail(String email);
    User findByLogin(String login);
    void create(User user);
    void remove(User user);
    
}
