package com.example.remindme.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.remindme.classes.persistence.UserEntity;
import com.example.remindme.classes.persistence.UserEntityRepository;

@Service
public class UserEntityService implements UserDetailsService {

    @Autowired
    UserEntityRepository repository;

    // @Autowired
    // PasswordEncoder passwordEncoder;

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
        //temp.setPassword(passwordEncoder().encode(password));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username);
    }

    public UserEntity findAndAuthenticateUser(String username, String providedPassword) {
        UserEntity user = repository.findByName(username);
        if (user == null) {
            return null;
        }

        System.out.println("user.getPassword()");
        System.out.println(user.getPassword());
        System.out.println("passwordEncoder.encode(providedPassword))");
        System.out.println(passwordEncoder().encode(providedPassword));
        System.out.println(passwordEncoder().encode(providedPassword));
        System.out.println(providedPassword);

        System.out.println(passwordEncoder().matches(user.getPassword(),passwordEncoder().encode(providedPassword)));
        System.out.println(passwordEncoder().matches(user.getPassword(),providedPassword));
        System.out.println(user.getPassword().equals(providedPassword));
        System.out.println(user.getPassword().equals(passwordEncoder().encode(providedPassword)));


        System.out.println("Suite");
        System.out.println(passwordEncoder().matches(passwordEncoder().encode(providedPassword), user.getPassword()));
        System.out.println(passwordEncoder().matches(providedPassword,user.getPassword()));

        //if (passwordEncoder.matches(user.getPassword(),passwordEncoder.encode(providedPassword))) {
        //if (user.getPassword().equals(providedPassword)) {
        if (passwordEncoder().matches(providedPassword,user.getPassword())) {
            return user;
        }

        return null;
    }
}
