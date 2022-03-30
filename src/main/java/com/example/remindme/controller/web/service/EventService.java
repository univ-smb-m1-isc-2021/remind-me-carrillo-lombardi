package com.example.remindme.controller.web.service;


import java.util.Date;
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

    public void create(String title, String details, Date date, Boolean periodique) {
        Event temp = new Event(title, details, date, periodique);

        if(!isPresent(temp))
            repository.save(temp);
    }

    public void update(Long eventId,Event event,Boolean updateValid) {
        Event oldEvent = repository.getById(eventId);
        if(!updateValid){
            oldEvent.setTitle(event.getTitle());
            oldEvent.setDetails(event.getDetails());
            oldEvent.setDate(event.getDate());
        }
        oldEvent.setIsValided(updateValid);

        repository.save(oldEvent); //! checker si ça écrase bien l'autre
    }

    private boolean isPresent(Event event) {
        for (Event elem : events()) {
            if(elem.getTitle().equals(event.getTitle())) //TODO ajouter date
                return true;
        }
        return false;
    }
}
