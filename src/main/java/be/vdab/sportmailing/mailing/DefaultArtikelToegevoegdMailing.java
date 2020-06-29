package be.vdab.sportmailing.mailing;

import be.vdab.sportmailing.domain.Sporter;
import be.vdab.sportmailing.events.ArtikelGemaakt;
import be.vdab.sportmailing.exceptions.KanMailNietZendenException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class DefaultArtikelToegevoegdMailing implements ArtikelToegevoegdMailing {
    private final JavaMailSender sender;

    public DefaultArtikelToegevoegdMailing(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void stuurMailNaArtikelToegevoegd(Sporter sporter, ArtikelGemaakt toegevoegd) {
        try{
            var message = new SimpleMailMessage();
            message.setTo(sporter.getEmailAdres());
            message.setSubject("Nieuw artikel beschikbaar");
            message.setText("Er is een nieuw artikel: " + toegevoegd.getNaam());
            sender.send(message);
        } catch (MailException ex){
            throw new KanMailNietZendenException(ex);
        }
    }
}
