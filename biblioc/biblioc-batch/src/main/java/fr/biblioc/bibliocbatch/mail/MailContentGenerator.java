package fr.banane.batchjsontomail.services;

import fr.banane.batchjsontomail.ReservationExpire;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface MailContentGenerator {

    String generate(ReservationExpire reservationExpire) throws TemplateException, IOException;
}
