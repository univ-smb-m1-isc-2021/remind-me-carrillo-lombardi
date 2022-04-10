package com.example.remindme;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import com.example.remindme.classes.FormWrapper;
import com.example.remindme.classes.persistence.UserEntity;
import com.example.remindme.controller.ProfileController;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class ProfileControllerTests {
    
    @Mock
    private EventService eventService;

    private UserEntity user;
    @Mock
    private UserEntityService userEntityService;
    @Mock
    private HttpSession session;
    @Mock
    private FormWrapper formWrapper;

    @InjectMocks
    private ProfileController profileController;

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
 
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).setViewResolvers(viewResolver).build();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    public void PostCreateEventAvecPasLang() throws Exception{

        when(userEntityService.findById(null)).thenReturn(user);

        mockMvc.perform(get("/admin/profile")).andExpect(status().is(200));

    }

    @Test
    public void PostCreateEventAvecLang() throws Exception{

        when(userEntityService.findById(null)).thenReturn(user);
        
        mockMvc.perform(get("/admin/profile").param("lang", "fr")).andExpect(status().is(200));
    }

    @Test
    public void PostImportFile() throws Exception{
        //when(jsonFile.getBytes()).thenReturn(formWrapper.getImage());
        mockMvc.perform(post("/admin/profile/import").flashAttr("model", formWrapper));

        verify(eventService,times(1)).createAll(anyList(),any());
    }
}