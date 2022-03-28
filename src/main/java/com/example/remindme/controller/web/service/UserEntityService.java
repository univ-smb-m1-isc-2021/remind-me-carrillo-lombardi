package com.example.remindme.controller.web.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.remindme.classes.persistence.UserEntity;
import com.example.remindme.classes.persistence.UserEntityRepository;

@Service
public class UserEntityService {

    private final UserEntityRepository repository;

    public UserEntityService(UserEntityRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> users() {
        return repository.findAll();
    }

    public void delete(Long UserEntityId) {
        Optional<UserEntity> UserEntity = repository.findById(UserEntityId);
        UserEntity.ifPresent(repository::delete);
    }

    public void create(String title, String details) {
        UserEntity temp = new UserEntity(title, details);

        if(!isPresent(temp))
            repository.save(temp);
    }

    public void update(Long UserEntityId, String name, String password) {
        UserEntity UserEntity = repository.getById(UserEntityId);
        UserEntity.setName(name);
        UserEntity.setPassword(password);

        repository.save(UserEntity); //! checker si ça écrase bien l'autre
    }

    private boolean isPresent(UserEntity UserEntity) {
        for (UserEntity elem : users()) {
            if(elem.getName().equals(UserEntity.getName()))
                return true;
        }
        return false;
    }
}
