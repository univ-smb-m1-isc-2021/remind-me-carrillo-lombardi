package com.example.remindme.controller.web.service;


import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.remindme.classes.persistence.Event;
import com.example.remindme.classes.persistence.EventRepository;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> events() {
        return repository.findAll();
    }

    public void delete(Long factId) {
        Optional<Event> fact = repository.findById(factId);
        fact.ifPresent(repository::delete);
    }

    public void create(String title, String details) {
        // FIXME : check if not already present
        repository.save(new Event(title, details));
    }
}
