package be.vdab.sportmailing.events;

public class ArtikelGemaakt {
    private long id;
    private String naam;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
