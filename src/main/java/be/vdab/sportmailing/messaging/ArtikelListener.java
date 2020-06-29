package be.vdab.sportmailing.messaging;

import be.vdab.sportmailing.domain.Sporter;
import be.vdab.sportmailing.events.ArtikelGemaakt;
import be.vdab.sportmailing.exceptions.KanMailNietZendenException;
import be.vdab.sportmailing.mailing.ArtikelToegevoegdMailing;
import be.vdab.sportmailing.services.SporterService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class ArtikelListener {
    private final SporterService service;
    private final ArtikelToegevoegdMailing mailing;
    private final Logger logger = LoggerFactory.logger(this.getClass());

    public ArtikelListener(SporterService service, ArtikelToegevoegdMailing mailing) {
        this.service = service;
        this.mailing = mailing;
    }

    @RabbitListener(queues = "sportartikels")
    void verwerkBericht(ArtikelGemaakt gemaakt) {
        for (Sporter sporter : service.findAll()) {
            try {
                mailing.stuurMailNaArtikelToegevoegd(sporter, gemaakt);
            } catch (KanMailNietZendenException ex) {
                logger.error("kan mail niet versturen", ex);
            }
        }
    }
}
