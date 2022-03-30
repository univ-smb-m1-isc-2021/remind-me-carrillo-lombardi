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

    public void delete(Long userEntityId) {
        Optional<UserEntity> userEntity = repository.findById(userEntityId);
        userEntity.ifPresent(repository::delete);
    }

    public void create(String name, String password) {
        UserEntity temp = new UserEntity(name, password);

        if(!isPresent(temp))
            repository.save(temp);
    }

    public void update(Long userEntityId, String name, String password) {
        UserEntity userEntity = repository.getById(userEntityId);
        userEntity.setName(name);
        userEntity.setPassword(password);

        repository.save(userEntity); //! checker si ça écrase bien l'autre
    }

    private boolean isPresent(UserEntity userEntity) {
        for (UserEntity elem : users()) {
            if(elem.getName().equals(userEntity.getName()))
                return true;
        }
        return false;
    }
}
