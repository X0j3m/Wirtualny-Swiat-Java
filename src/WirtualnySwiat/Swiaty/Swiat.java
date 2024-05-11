package WirtualnySwiat.Swiaty;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Zwierzeta.Zwierze;
import WirtualnySwiat.PorownywaczOrganizmow;
import WirtualnySwiat.Punkt;

import java.util.ArrayList;
import java.util.List;

public abstract class Swiat {
    private final PorownywaczOrganizmow porownywacz = new PorownywaczOrganizmow();
    protected Punkt[] kierunki;
    private int tura;
    private final int rozmiarX, rozmiarY;
    private List<Organizm> organizmy;

    public Swiat(int rozmiarX, int rozmiarY) {
        this.rozmiarX = rozmiarX;
        this.rozmiarY = rozmiarY;
        this.tura = 0;
        this.organizmy = new ArrayList<>();
    }

    public Punkt[] dozwoloneKierunki() {
        return kierunki;
    }

    public int getLiczbaKierunkow() {
        return kierunki.length;
    }

    public int getRozmiarX() {
        return rozmiarX;
    }

    public int getRozmiarY() {
        return rozmiarY;
    }

    public int getTura() {
        return tura;
    }

    public void wykonajTure() {
        this.tura++;
        for (Organizm organizm : organizmy) {
            if (organizm.getWiek() != organizm.NOWONARODZONY) {
                organizm.akcja(this);
            }
        }
        organizmy.removeIf(Organizm::czyMartwy);
        this.posortujOrganizmy();
    }

    public Organizm getOrganizm(Punkt pozycja) {
        return organizmy.stream().filter(o -> o.getPozycja().equals(pozycja)).findFirst().orElse(null);
    }

    public void dodajOrganizm(Organizm organizm) {
        organizmy.add(organizm);
    }

    public void usunOrganizm(Organizm organizm) {
        organizmy.remove(organizm);
    }

    public void posortujOrganizmy() {
        organizmy.sort(porownywacz::porownaj);
    }

    public void walka(Zwierze atakujacy, Zwierze broniacy) {
        if (broniacy.czyOdpieraAtak(atakujacy)) {
            broniacy.obronSie(atakujacy, this);
        } else {
            atakujacy.zabij(broniacy, this);
            atakujacy.setPozycja();
        }
    }
}
