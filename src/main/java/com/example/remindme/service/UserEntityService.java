package com.example.remindme.service;

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

    public UserEntity findByName(String name) {
        return repository.findByName(name);
    }

    public UserEntity findById(Long id) {
        return repository.getById(id);
    }

    public void delete(Long userEntityId) {
        Optional<UserEntity> userEntity = repository.findById(userEntityId);
        userEntity.ifPresent(repository::delete);
    }

    public void create(String name, String password, String tweeter, String email) {
        UserEntity temp = new UserEntity(name, password, tweeter, email);

        if(!isPresent(temp))
            repository.save(temp);
    }

    public void update(Long userEntityId, String name, String password, String tweeter, String email) {
        UserEntity userEntity = repository.getById(userEntityId);
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setTweeter(tweeter);
        userEntity.setEmail(email);

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
