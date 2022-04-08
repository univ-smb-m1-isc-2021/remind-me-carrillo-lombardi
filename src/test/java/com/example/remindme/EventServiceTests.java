package com.example.remindme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.remindme.classes.persistence.Event;
import com.example.remindme.classes.persistence.EventRepository;
import com.example.remindme.service.EventService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(MockitoExtension.class)
public class EventServiceTests {
    
    // @Mock
    // private EventRepository eventRepository;
    // @Autowired
    // @InjectMocks
    // private EventService eventService;
    // private Event event1;
    // private Event event2;
    // List<Event> eventList;
    
    // @BeforeEach
    // public void setUp() {
    //     eventList = new ArrayList<>();
    //     event1 = new Event((long)12, "title", "details", new Date(), false, false, false);
    //     event2 = new Event((long)13, "title2", "details", new Date(), false, false, false);
    //     eventList.add(event1);
    //     eventList.add(event2);
    // }

    // @AfterEach
    // public void tearDown() {
    //     event1 = event2 = null;
    //     eventList = null;
    // }

    // @Test
    // void createAndSaveEvent() {
    //     //stubbing
    //     when(eventRepository.save(any())).thenReturn(event1);
    //     eventService.create(event1.getUserId(),event1.getTitle(),event1.getDetails(),event1.getDate(),event1.getPeriodique(), event1.getTweeter(), event1.getEmail());
    //     verify(eventRepository,times(1)).save(any());
    // }
    // @Test
    // void createListEventsAndSaveEvent() {
    //     //stubbing
    //     eventService.createAll(eventList,event1.getUserId());
    //     verify(eventRepository,times(2)).save(any());
    // }

    // @Test
    // void updateEventWithoutValided() {

    //     //Cree nouvel event 
    //     Event newEvent = new Event(event1.getUserId(), event1.getTitle(), event1.getDetails(), event1.getDate(), event1.getPeriodique(), event1.getTweeter(), event1.getEmail());
    //     //Le modifie
    //     newEvent.setDetails("detailsUpdate");
    //     newEvent.setTitle("titleUpdate");
    //     //Simulation 
    //     when(eventRepository.getById(event1.getId())).thenReturn(event1);
    //     when(eventRepository.findByTitle(newEvent.getTitle())).thenReturn(newEvent);
    //     //Update
    //     eventService.update(event1.getId(), newEvent, false);
    //     Event verify = eventRepository.findByTitle(event1.getTitle());
    //     assertEquals(verify.getTitle(), "titleUpdate");
    // }
    // @Test
    // void updateEventWithValided() {
    //     //Cree nouvel event 
    //     Event newEvent = new Event(event1.getUserId(), event1.getTitle(), event1.getDetails(), event1.getDate(), event1.getPeriodique(), event1.getTweeter(), event1.getEmail());
    //     //Le modifie
    //     newEvent.setDetails("detailsUpdate");
    //     newEvent.setTitle("titleUpdate");
    //     //Simulation 
    //     when(eventRepository.getById(event1.getId())).thenReturn(event1);
    //     when(eventRepository.findByTitle(event1.getTitle())).thenReturn(event1);
    //     //Update avec un true donc title ne change pas
    //     eventService.update(event1.getId(), newEvent, true);
    //     Event verify = eventRepository.findByTitle(event1.getTitle());
    //     assertEquals(verify.getTitle(), "title");


    // }
    // @Test
    // void createisPresent(){
    //     when(eventService.events()).thenReturn(eventList);
    //     //Event newEvent1 = new Event(event1.getUserId(), "mauvais title", event1.getDetails(), event1.getDate(), event1.getPeriodique());
    //     eventService.create(event1.getUserId(),event1.getTitle(),event1.getDetails(),event1.getDate(),event1.getPeriodique(), event1.getTweeter(), event1.getEmail());
    //     eventService.create(event1.getUserId(),"mauvais title",event1.getDetails(),event1.getDate(),event1.getPeriodique(), event1.getTweeter(), event1.getEmail()); // ne save pas
    //     //donc ne fera un save que une fois
    //     verify(eventRepository,times(1)).save(any());
    // }

    // @Test
    // void eventsOfUser(){
    //     when(eventService.events()).thenReturn(eventList);
    //     assertEquals(eventService.eventsOfUser((long)12).size(),1);
    // }



}