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
import javax.mail.internet.AddressException;
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

    //Apres l'appuie du bouton valider alors valide l'event ici
    @PostMapping(value="/event/validation")
	public String valideEvent(@ModelAttribute Event event, Model model) {
		eventService.update(event.getId(),event,true);
        
        return "redirect:/admin";
	}


    //Creer un event depuis un form
    @PostMapping(value="/event/create")
	public String createEvent(@ModelAttribute Event event, Model model, HttpSession session) {
		eventService.create((Long)(session.getAttribute("userId")), event.getTitle(), event.getDetails(), event.getDate(),event.getPeriodique(), event.getTweeter(), event.getEmail());
        return "redirect:/admin";
	}


    //Envoie la notif a travers mail/twitter en fonction demande User
    public void sendNotif(Event event) throws AddressException, MessagingException{
        //switch o foreach en fonction des demandes de mails
        if(event.getEmail())
            sendEmail(event);
        /*if(event.getTweeter())
            sendTwitter(event);*/
    }
    //Regarde tout les events toutes les [fixedRate] et envoie ceux a envoyer 
    //Ceux envoyer seront valider et donc plus envoyable mais resteront stocker pour avoir un historique
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
    //Verifie que [date] soit envoyable en etant egale a Date.now sauf pour les secondes
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

    //Construit un message puis envoie le mail
    private void sendEmail(Event event) throws AddressException, MessagingException{
        // Recipient's email ID needs to be mentioned.
        //String to = "serpiente61@hotmail.fr";
        //Recupere le mail de l'utilsateur de l'event
        String to = userEntityService.findById(event.getUserId()).getEmail();

        // Boite email de l'app
        String from = "integrationcontinue3@gmail.com";

        // Le serveur pour envoyer les messages
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

    }
    /**
        import twitter4j.DirectMessage;
        import twitter4j.Twitter;
        import twitter4j.TwitterException;
        import twitter4j.TwitterFactory;
        import twitter4j.conf.ConfigurationBuilder;

        public static Twitter getTwitter() {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
            .setOAuthConsumerKey("ZXZOQezJy9JIZH6GqMSQ7uDM0")
            .setOAuthConsumerSecret("6mnp44f1lUF3ixerC9EChOj0NYw4vlfBVZ9IsfkKUV8Ot274SS")
            .setOAuthAccessToken("1511641506515505154-y1N7ycw5I0ElzwyUpBvHSfqLCguNZr")
            .setOAuthAccessTokenSecret("FuEPEPyCtwOcZd6GrOOQCjTIxKtPYaY51GE60Wn85hzxL");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            return twitter;
        }
        public static String sendTwitter(String recipientName, String msg) 
        throws TwitterException {
        
            Twitter twitter = getTwitter();
            DirectMessage message = twitter.sendDirectMessage(recipientName, msg);
            return message.getText();
        }
     */
}
