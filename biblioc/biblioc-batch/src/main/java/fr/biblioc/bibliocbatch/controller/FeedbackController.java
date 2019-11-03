package fr.biblioc.bibliocbatch.controller;

import fr.biblioc.bibliocbatch.configurations.EmailConfiguration;
import fr.biblioc.bibliocbatch.mail.Feedback;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private EmailConfiguration emailConfiguration;

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            try {
                throw new ValidationException("Feedback invalide");
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }

        //mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfiguration.getHost());
        mailSender.setPort(emailConfiguration.getPort());
        mailSender.setUsername(emailConfiguration.getUsername());
        mailSender.setPassword(emailConfiguration.getPassword());

        //email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        //TODO le mail de l'utilisateur dont l'exemplaire est en date dépassé
        mailMessage.setTo("test@usager.com");
        mailMessage.setSubject("pret expiré");
        mailMessage.setText(feedback.getFeedback());

        //send mail
        mailSender.send(mailMessage);
    }
}
