package com.example.remindme.service;

import java.util.List;
import java.util.Optional;

import com.example.remindme.classes.persistence.UserEntity;
import com.example.remindme.classes.persistence.UserEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService implements UserDetailsService {

    @Autowired
    UserEntityRepository repository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
        temp.setPassword(passwordEncoder().encode(password));
        if(!isPresent(temp))
            repository.save(temp);
    }

    private boolean isPresent(UserEntity userEntity) {
        for (UserEntity elem : users()) {
            if(elem.getName().equals(userEntity.getName()))
                return true;
        }
        return false;
    }

    public void update(Long userEntityId, String name, String password, String tweeter, String email) {
        UserEntity userEntity = repository.getById(userEntityId);
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setTweeter(tweeter);
        userEntity.setEmail(email);

        repository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username);
    }

    public UserEntity findAndAuthenticateUser(String username, String providedPassword) {
        UserEntity user = repository.findByName(username);
        if (user == null) {
            return null;
        }

        if (passwordEncoder().matches(providedPassword,user.getPassword())) {
            return user;
        }

        return null;
    }
}
