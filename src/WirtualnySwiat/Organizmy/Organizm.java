package WirtualnySwiat.Organizmy;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

import java.util.Random;

public abstract class Organizm {
    public final int MARTWY = -2;
    public final int NOWONARODZONY = -1;
    private Punkt pozycja;
    private Punkt poprzedniaPozycja;
    private int sila, inicjatywa, wiek;
    private Typ typ;
    private Gatunek gatunek;
    private Swiat swiat;

    public Organizm(Punkt pozycja, int sila, int inicjatywa, Typ typ, Gatunek gatunek, Swiat swiat) {
        this.pozycja = pozycja;
        this.poprzedniaPozycja = pozycja;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.typ = typ;
        this.gatunek = gatunek;
        this.swiat = swiat;
        this.wiek = 0;
    }

    public abstract void akcja(Swiat swiat);

    public abstract void kolizja(Organizm organizm, Swiat swiat);

    public void zwiekszWiek() {
        if (this.wiek == NOWONARODZONY) {
            this.wiek = 1;
        } else {
            this.wiek++;
        }
    }

    public void setPozycja(int x, int y) {
        this.pozycja.setX(x);
        this.pozycja.setY(y);
    }

    public void setPozycja(Punkt pozycja) {
        this.pozycja = pozycja;
    }

    public void setPozycja() {
        this.poprzedniaPozycja.setX(pozycja.getX());
        this.poprzedniaPozycja.setY(pozycja.getY());
    }

    protected void setNowonarodzony() {
        this.wiek = NOWONARODZONY;
    }

    public boolean czyMartwy() {
        return this.getWiek()==MARTWY;
    }

    public Punkt getPozycja() {
        return pozycja;
    }

    public int getWiek() {
        return wiek;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public Swiat getSwiat() {
        return swiat;
    }

    public Typ getTyp() {
        return typ;
    }

    public Gatunek getGatunek() {
        return gatunek;
    }

    public void cofnijRuch() {
        this.pozycja = poprzedniaPozycja;
    }

    public void martwy() {
        this.wiek = MARTWY;
    }

    protected Punkt getNowaPozycja(Punkt wektorPrzesuniecia) {
        return this.getPozycja().getPrzesuniecieOWektor(wektorPrzesuniecia);
    }

    protected Punkt znajdzWolnePole(Swiat swiat, int odlegloscPrzeszukiwania) {
        int liczbaKierunkow = swiat.getLiczbaKierunkow();
        Random random = new Random();
        int index = random.nextInt(liczbaKierunkow);
        Punkt nowaPozycja = null;
        Punkt nastepny = this.getNowaPozycja(swiat.dozwoloneKierunki()[index]);
        nastepny.przemnorz(odlegloscPrzeszukiwania);
        for (int i = 0; i < liczbaKierunkow; i++) {
            if (nastepny.czyPozaZakresem(swiat.getRozmiarX(), swiat.getRozmiarY())) {
                nastepny.nastepnyKierunek(swiat);
                nastepny.przemnorz(odlegloscPrzeszukiwania);
                this.getNowaPozycja(nastepny);
            } else {
                nowaPozycja = nastepny;
                break;
            }
        }
        return nowaPozycja;
    }

    protected Punkt znajdzWolnePole(Swiat swiat) {
        return this.znajdzWolnePole(swiat, 1);
    }

    public void zabij(Organizm organizm, Swiat swiat) {
        organizm.martwy();
    }
}
