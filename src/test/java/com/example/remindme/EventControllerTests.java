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
import com.example.remindme.controller.EventController;
import com.example.remindme.service.EventService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class EventControllerTests {
    @Mock
    private EventService eventService;
    private Event event;

    @InjectMocks
    private EventController eventController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        event = new Event((long)12, "title", "details", new Date(), false, false, false);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @AfterEach
    void tearDown() {
        event = null;
    }

    @Test
    public void PostCreateEvent() throws Exception{
      
        mockMvc.perform(post("/event/create").flashAttr("event", event));
        //null pour l'id car c'est celui de la session en cours
        verify(eventService,times(1)).create(null,event.getTitle(),event.getDetails(),event.getDate(),event.getPeriodique(),event.getTweeter(),event.getEmail());
    }

    @Test
    public void PostValidationEvent() throws Exception{
        
        mockMvc.perform(post("/event/validation").flashAttr("event", event));
        verify(eventService,times(1)).update(event.getId(), event, true);
    }

    @Test
    public void sendable() throws Exception{
        
        assertEquals(eventController.sendable(new Date(), new Date()), true);
        Date date1 =new Date(2020, 4, 8);
        Date date2 = new Date(2020, 4, 8, 1, 2);
        assertEquals(eventController.sendable(date1, date2), false);

    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    



}
