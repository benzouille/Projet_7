package fr.banane.batchjsontomail.writers;

import fr.banane.batchjsontomail.ReservationExpire;
import fr.banane.batchjsontomail.services.MailContentGenerator;
import fr.banane.batchjsontomail.services.ReservationExpireMailSenderService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ReservationExpireItemWriter implements ItemWriter<ReservationExpire> {

    private final ReservationExpireMailSenderService reservationExpireService;

    private final MailContentGenerator mailContentGenerator;

    public ReservationExpireItemWriter(final ReservationExpireMailSenderService reservationExpireService,
                              final MailContentGenerator mailContentGenerator) {
        super();
        this.reservationExpireService = reservationExpireService;
        this.mailContentGenerator = mailContentGenerator;
    }

    @Override
    public void write(List<? extends ReservationExpire> reservations) throws Exception {
        for (ReservationExpire reservation : reservations) {
            String content = mailContentGenerator.generate(reservation);
            reservationExpireService.send(reservation.getEmail(), content);
        }
    }
}
