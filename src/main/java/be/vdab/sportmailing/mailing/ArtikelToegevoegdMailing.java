package be.vdab.sportmailing.mailing;

import be.vdab.sportmailing.domain.Sporter;
import be.vdab.sportmailing.events.ArtikelGemaakt;

public interface ArtikelToegevoegdMailing {
    void stuurMailNaArtikelToegevoegd(Sporter sporter, ArtikelGemaakt gemaakt);
}
