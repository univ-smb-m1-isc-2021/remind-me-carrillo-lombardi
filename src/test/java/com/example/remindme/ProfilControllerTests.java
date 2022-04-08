package com.example.remindme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.remindme.classes.FormWrapper;
import com.example.remindme.classes.persistence.Event;
import com.example.remindme.classes.persistence.EventRepository;
import com.example.remindme.classes.persistence.UserEntity;
import com.example.remindme.controller.EventController;
import com.example.remindme.controller.ProfilController;
import com.example.remindme.service.EventService;
import com.example.remindme.service.UserEntityService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
import org.springframework.mock.web.MockMultipartFile;
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
public class ProfilControllerTests {
    
    @Mock
    private EventService eventService;
    private Event event;
    private UserEntity user;
    @Mock
    private UserEntityService userEntityService;
    @Mock
    private HttpSession session;
    @Mock
    private FormWrapper formWrapper;

    @InjectMocks
    private ProfilController profilController;

    @Autowired
    private MockMvc mockMvc;

    
    private MockMultipartFile jsonFile;

    @BeforeEach
    public void setup(){
        user = new UserEntity("title", "details","","");
        jsonFile = new MockMultipartFile("json", "yo", "application/json", "[{\"id\":419,\"userId\":416,\"title\":\"Event 1\",\"details\":\"description hyper poussÃ©\",\"date\":1648981060787,\"isValided\":false,\"periodique\":false,\"tweeter\":false,\"email\":false}]".getBytes());
        formWrapper = new FormWrapper(jsonFile);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
 
        mockMvc = MockMvcBuilders.standaloneSetup(profilController).setViewResolvers(viewResolver).build();
    }

    @AfterEach
    void tearDown() {
        event = null;
    }

    @Test
    public void PostCreateEventAvecPasLang() throws Exception{

        when(userEntityService.findById(null)).thenReturn(user);

        mockMvc.perform(get("/admin/profil")).andExpect(status().is(200));

    }

    @Test
    public void PostCreateEventAvecLang() throws Exception{

        when(userEntityService.findById(null)).thenReturn(user);
        
        mockMvc.perform(get("/admin/profil").param("lang", "fr")).andExpect(status().is(200));
    }

    @Test
    public void PostImportFile() throws Exception{
        //when(jsonFile.getBytes()).thenReturn(formWrapper.getImage());
        mockMvc.perform(post("/admin/profil/import").flashAttr("model", formWrapper));

        verify(eventService,times(1)).createAll(anyList(),any());
    }
}