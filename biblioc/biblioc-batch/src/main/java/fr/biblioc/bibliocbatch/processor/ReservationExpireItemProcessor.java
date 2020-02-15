package fr.banane.batchjsontomail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class ReservationExpireItemProcessor implements ItemProcessor<ReservationExpire, ReservationExpire> {

    private static final Logger log = LoggerFactory.getLogger(ReservationExpireItemProcessor.class);

    @Override
    public ReservationExpire process(final ReservationExpire re) throws Exception {
        final String bibliotheque = re.getBibliotheque().toUpperCase();
        final String prenom = re.getPrenom().toUpperCase();

        final ReservationExpire transformedRe = new ReservationExpire(re.getId_reservation(), re.getId_exemplaire(), re.getEmail(), prenom, bibliotheque, re.getTitre());

        log.info("Converting (" + re + ") into (" + transformedRe + ")");

        return transformedRe;
    }

}