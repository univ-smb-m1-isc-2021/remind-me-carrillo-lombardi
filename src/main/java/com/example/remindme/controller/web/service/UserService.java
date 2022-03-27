package com.example.remindme.controller.web.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.remindme.classes.persistence.User;
import com.example.remindme.classes.persistence.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> users() {
        return repository.findAll();
    }

    public void delete(Long userId) {
        Optional<User> user = repository.findById(userId);
        user.ifPresent(repository::delete);
    }

    public void create(String title, String details) {
        User temp = new User(title, details);

        if(!isPresent(temp))
            repository.save(temp);
    }

    public void update(Long userId, String name, String password) {
        User user = repository.getById(userId);
        user.setName(name);
        user.setPassword(password);

        repository.save(user); //! checker si ça écrase bien l'autre
    }

    private boolean isPresent(User user) {
        for (User elem : users()) {
            if(elem.getName().equals(user.getName()))
                return true;
        }
        return false;
    }
}
