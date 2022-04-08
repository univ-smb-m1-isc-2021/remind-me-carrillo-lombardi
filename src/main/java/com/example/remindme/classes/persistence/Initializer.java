package com.example.remindme.classes.persistence;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class Initializer {

    private final EventRepository eRep;
    private final UserEntityRepository uRep;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Initializer(EventRepository eRep, UserEntityRepository uRep) {
        this.eRep = eRep;
        this.uRep = uRep;
    }

    @PostConstruct
    public void initialize() {

        uRep.deleteAllInBatch();

        if (uRep.findAll().isEmpty()) {
            uRep.saveAndFlush(new UserEntity("a", passwordEncoder.encode("a"), "aTweeter", "a@a.a"));
        }

        eRep.deleteAllInBatch();

        if (eRep.findAll().isEmpty()) {
            eRep.saveAndFlush(new Event(1l,"Event 1", "description hyper poussé", new Date(), false, false, false));
            eRep.saveAndFlush(new Event(1l,"Event 2", "non je suis quand meme pas sur la bdd ?!", new Date(), false, false, false));
        }
    }

}
