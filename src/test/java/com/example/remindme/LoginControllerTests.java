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
import com.example.remindme.controller.LoginController;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class LoginControllerTests {
    
    protected MockHttpSession session;

    @InjectMocks
    private LoginController loginController;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setup(){

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
 
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();
    }

    @AfterEach
    void tearDown() {
        
    }

    @Test
    public void logout() throws Exception{
        //Redirection
        mockMvc.perform(get("/logout")).andExpect(status().is(302));
        mockMvc.perform(get("/logout").param("lang", "fr")).andExpect(status().is(302));
        session=new MockHttpSession();
        session.setAttribute("lang", "fr");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/logout")
                                        .session(session);
        
        //verify bien la redirection en cas de session non null
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .is(302));
    }

    @Test
    public void login() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().is(200));
        mockMvc.perform(get("/login").param("lang", "fr")).andExpect(status().is(200));
        session=new MockHttpSession();
        session.setAttribute("lang", "fr");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/login")
                                        .session(session);
        
        //verify bien la redirection en cas de session non null
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .is(302));
    }

   
}