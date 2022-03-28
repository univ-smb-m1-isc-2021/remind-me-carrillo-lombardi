package com.example.remindme.controller.web.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.remindme.classes.persistence.UserC;
import com.example.remindme.classes.persistence.UserCRepository;

@Service
public class UserCService {

    private final UserCRepository repository;

    public UserCService(UserCRepository repository) {
        this.repository = repository;
    }

    public List<UserC> UserCs() {
        return repository.findAll();
    }

    public void delete(Long UserCId) {
        Optional<UserC> UserC = repository.findById(UserCId);
        UserC.ifPresent(repository::delete);
    }

    public void create(String title, String details) {
        UserC temp = new UserC(title, details);

        if(!isPresent(temp))
            repository.save(temp);
    }

    public void update(Long UserCId, String name, String password) {
        UserC UserC = repository.getById(UserCId);
        UserC.setName(name);
        UserC.setPassword(password);

        repository.save(UserC); //! checker si ça écrase bien l'autre
    }

    private boolean isPresent(UserC UserC) {
        for (UserC elem : UserCs()) {
            if(elem.getName().equals(UserC.getName()))
                return true;
        }
        return false;
    }
}
