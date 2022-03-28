package com.example.remindme.classes.persistence;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.annotation.PostConstruct;

@Service
class Initializer {

    private final EventRepository eRep;
    private final UserEntityRepository uRep;

    public Initializer(EventRepository eRep, UserEntityRepository uRep) {
        this.eRep = eRep;
        this.uRep = uRep;
    }

    @PostConstruct
    public void initialize() {

        eRep.deleteAllInBatch();

        if (eRep.findAll().isEmpty()) {
            eRep.saveAndFlush(new Event("Event 1", "description hyper poussé", new Date()));
            eRep.saveAndFlush(new Event("Event 2", "non je suis quand meme pas sur la bdd ?!", new Date()));
        }

        uRep.deleteAllInBatch();

        if (uRep.findAll().isEmpty()) {
            uRep.saveAndFlush(new UserEntity("a", new BCryptPasswordEncoder().encode("a")));
        }
    }

}
