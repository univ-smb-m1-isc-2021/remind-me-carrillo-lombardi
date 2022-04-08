package com.example.remindme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.remindme.classes.persistence.UserEntity;
import com.example.remindme.classes.persistence.UserEntityRepository;
import com.example.remindme.service.UserEntityService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(MockitoExtension.class)
public class UserEntityServiceTests {
    
    // @Mock
    // private UserEntityRepository userEntityRepository;
    // @Autowired
    // @InjectMocks
    // private UserEntityService userEntityService;
    // private UserEntity userEntity1;
    // private UserEntity userEntity2;
    // List<UserEntity> userEntityList;
    
    // @BeforeEach
    // public void setUp() {
    //     userEntityList = new ArrayList<>();
    //     userEntity1 = new UserEntity("a","aa", "", "");
    //     userEntity2 = new UserEntity("b","bb", "", "");
    //     userEntityList.add(userEntity1);
    //     userEntityList.add(userEntity2);
    // }

    // @AfterEach
    // public void tearDown() {
    //     userEntity1 = userEntity2 = null;
    //     userEntityList = null;
    // }

    // @Test
    // void createAndSaveUserEntity() {
    //     //stubbing
    //     when(userEntityRepository.save(any())).thenReturn(userEntity1);
    //     userEntityService.create(userEntity1.getName(),userEntity1.getPassword(), userEntity1.getTweeter(), userEntity1.getEmail());
    //     verify(userEntityRepository,times(1)).save(any());
    // }
    // @Test
    // void createisPresent(){
    //     when(userEntityService.users()).thenReturn(userEntityList);
    //     //UserEntity newUserEntity1 = new UserEntity(userEntity1.getUserEntityId(), "mauvais title", userEntity1.getDetails(), userEntity1.getDate(), userEntity1.getPeriodique());
    //     userEntityService.create(userEntity1.getName(),userEntity1.getPassword(), userEntity1.getTweeter(), userEntity1.getEmail());
    //     userEntityService.create("faux",userEntity1.getPassword(), userEntity1.getTweeter(), userEntity1.getEmail()); // ne save pas
    //     //donc ne fera un save que une fois
    //     verify(userEntityRepository,times(1)).save(any());
    // }


    // @Test
    // void updateUserEntityWithoutValided() {
    //     //Cree nouvel userEntity 
    //     UserEntity newUserEntity = new UserEntity(userEntity1.getName(), userEntity1.getPassword(), userEntity1.getTweeter(), userEntity1.getEmail());
    //     //Le modifie
    //     newUserEntity.setName("faux");
    //     //Simulation 
    //     when(userEntityRepository.getById(userEntity1.getId())).thenReturn(userEntity1);
    //     when(userEntityRepository.findByName(newUserEntity.getName())).thenReturn(newUserEntity);
    //     //Update
    //     userEntityService.update(userEntity1.getId(), newUserEntity.getName(), newUserEntity.getPassword(), newUserEntity.getTweeter(), newUserEntity.getEmail());
    //     UserEntity verify = userEntityRepository.findByName(userEntity1.getName());
    //     assertEquals(verify.getName(), "faux");
    // }

}