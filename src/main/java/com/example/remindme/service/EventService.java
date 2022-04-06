package com.example.remindme.service;


import java.util.ArrayList;
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

    public List<Event> eventsOfUser(Long id) {
        List<Event> events = events();
        List<Event> list = new ArrayList<Event>();
        for (Event event : events) {
            if(event.getUserId().equals(id)) {
                list.add(event);
            }
        }
        return list;
    }

    public void delete(Long eventId) {
        Optional<Event> event = repository.findById(eventId);
        event.ifPresent(repository::delete);
    }

    public void create(Long userId, String title, String details, Date date, Boolean periodique) {
        Event temp = new Event(userId, title, details, date, periodique);

        if(!isPresent(temp))
            repository.save(temp);
    }
    public void createAll(List<Event> events, Long userId) {
        for (Event e : events) {
            Event temp = new Event(userId, e.getTitle(), e.getDetails(), e.getDate(), e.getPeriodique());

            if(!isPresent(temp))
                repository.save(temp);
        }
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
            if(elem.getTitle().equals(event.getTitle()) && elem.getDate().equals(event.getDate()) && elem.getUserId().equals(event.getUserId()))
                return true;
        }
        return false;
    }
}
