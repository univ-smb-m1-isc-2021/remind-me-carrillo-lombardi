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

    public void delete(Long eventId) {
        Optional<Event> event = repository.findById(eventId);
        event.ifPresent(repository::delete);
    }

    public void create(String title, String details) {
        Event temp = new Event(title, details);

        if(!isPresent(temp))
            repository.save(temp);
    }

    public void update(Long eventId, String title, String details) {
        Event event = repository.getById(eventId);
        event.setTitle(title);
        event.setDetails(details);

        repository.save(event); //! checker si ça écrase bien l'autre
    }

    private boolean isPresent(Event event) {
        for (Event elem : events()) {
            if(elem.getTitle().equals(event.getTitle())) //TODO ajouter date
                return true;
        }
        return false;
    }
}
