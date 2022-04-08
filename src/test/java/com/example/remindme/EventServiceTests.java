package com.example.remindme;

import org.mockito.ArgumentMatchers;


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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EventServiceTests {
    
    @Mock
    private EventRepository eventRepository;
    @Autowired
    @InjectMocks
    private EventService eventService;
    private Event event1;
    private Event event2;
    List<Event> eventList;
    
    @BeforeEach
    public void setUp() {
        eventList = new ArrayList<>();
        event1 = new Event((long)12, "title", "details", new Date(), false, false, false);
        event2 = new Event((long)13, "title2", "details", new Date(), false, false, false);
        eventList.add(event1);
        eventList.add(event2);
    }

    @AfterEach
    public void tearDown() {
        event1 = event2 = null;
        eventList = null;
    }

    @Test
    void givenEventToAddShouldReturnAddedEvent() {
        //stubbing
        when(eventRepository.save(any())).thenReturn(event1);
        eventService.create(event1.getUserId(),event1.getTitle(),event1.getDetails(),event1.getDate(),event1.getPeriodique(), event1.getTweeter(), event1.getEmail());
        verify(eventRepository,times(1)).save(any());
    }
}
