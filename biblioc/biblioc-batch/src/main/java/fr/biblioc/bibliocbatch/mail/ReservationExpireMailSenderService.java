package fr.banane.batchjsontomail.services;

import javax.mail.MessagingException;

public interface ReservationExpireMailSenderService {

    void send(String mailDestination, String content) throws MessagingException;
}
