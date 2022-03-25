package com.example.remindme.classes.persistence;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class Initializer {

    private final EventRepository repository;

    public Initializer(EventRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void initialize() {

        repository.deleteAllInBatch();

        if (repository.findAll().isEmpty()) {
            repository.saveAndFlush(new Event("Event 1", "description hyper poussé"));
            repository.saveAndFlush(new Event("Event 2", "non je suis quand meme pas sur la bdd ?!"));
        }
    }

}
