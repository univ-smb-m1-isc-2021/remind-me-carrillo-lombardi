package com.example.remindme.classes.persistence;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

@Proxy(lazy = false)
@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    //Pour savoir a quel User il appartient
    private Long userId;
    private String title;
    private String details; 
    //Format de la date pour le formater automatiquement
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;
    private boolean isValided=false;

    private boolean periodique=false;
    private boolean tweeter=false;
    private boolean email=false;

    public Event() { }

    public Event(Long userId, String title, String details,Date date,boolean periodique, boolean tweeter, boolean email) {
        this.userId = userId;
        this.title = title;
        this.details = details;
        this.date = date;
        this.periodique = periodique;
        this.tweeter = tweeter;
        this.email = email;
    }
    ///Getter et Setter
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsValided() {
        return isValided;
    }

    public void setIsValided(Boolean isValided) {
        this.isValided = isValided;
    }

    public Boolean getPeriodique() {
        return periodique;
    }

    public void setPeriodique(Boolean periodique) {
        this.periodique = periodique;
    }

    public Boolean getTweeter() {
        return tweeter;
    }

    public void setTweeter(Boolean tweeter) {
        this.tweeter = tweeter;
    }
    
    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean periodique) {
        this.email = periodique;
    }

}
