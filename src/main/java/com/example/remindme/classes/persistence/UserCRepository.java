package com.example.remindme.classes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCRepository extends JpaRepository<UserC, Long> {

    UserC findByName(String title);
    UserC findById(long id);
}
