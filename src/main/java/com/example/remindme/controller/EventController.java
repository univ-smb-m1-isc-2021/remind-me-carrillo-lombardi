package com.example.remindme.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import com.example.remindme.classes.persistence.Event;
import com.example.remindme.service.EventService;
import com.example.remindme.service.UserEntityService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private final EventService eventService;
    private final UserEntityService userEntityService;
	

    public EventController(EventService eventService,UserEntityService userEntityService) {
        this.eventService = eventService;
        this.userEntityService = userEntityService;
    }

    @PostMapping(value="/event/validation")
	public String valideEvent(@ModelAttribute Event event, Model model) {
		eventService.update(event.getId(),event,true);
        
        return "redirect:/admin";
	}



    @PostMapping(value="/event/create")
	public String createEvent(@ModelAttribute Event event, Model model, HttpSession session) {
		eventService.create((Long)(session.getAttribute("userId")), event.getTitle(), event.getDetails(), event.getDate(),event.getPeriodique(), event.getTweeter(), event.getEmail());
        return "redirect:/admin";
	}


    //a completer
    public void sendNotif(Event event){
        //switch o foreach en fonction des demandes de mails
        if(event.getEmail())
            sendEmail(event);
        /*if(event.getTweeter())
            sendTwetter(event);*/
    }
    
    @Scheduled(fixedRate = 10000)
	public void checkEventsASend() {
        List<Event> events = eventService.events();
        for (Event event : events) {
            Date now = new Date();
            if(sendable(now,event.getDate()) && !event.getIsValided()){
                //sendNotif(event); // TODO FAUDRA Y DECOMMENTER
                if(!event.getPeriodique()){
                    eventService.update(event.getId(), event, true);
                }
                    
            }
        }
	}

    public boolean sendable(Date now, Date date) {
        if(now.getYear()!=date.getYear()){
            return false;
        }
        if(now.getMonth()!=date.getMonth()){
            return false;
        }
        if(now.getDay()!=date.getDay()){
            return false;
        }
        if(now.getHours()!=date.getHours()){
            return false;
        }
        if(now.getMinutes()!=date.getMinutes()){
            return false;
        }
        return true;
    }


    private void sendEmail(Event event){
        // Recipient's email ID needs to be mentioned.
        //String to = "serpiente61@hotmail.fr";
        String to = userEntityService.findById(event.getUserId()).getEmail();

        // Sender's email ID needs to be mentioned
        String from = "integrationcontinue3@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("integrationcontinue3@gmail.com", "Integration.Continue3");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(event.getTitle());

            // Now set the actual message
            message.setText(event.getDetails());

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
