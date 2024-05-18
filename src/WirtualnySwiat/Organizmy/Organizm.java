package WirtualnySwiat.Organizmy;

import WirtualnySwiat.Organizmy.Zwierzeta.*;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public abstract class Organizm {
    public final int MARTWY = -2;
    public final int NOWONARODZONY = -1;
    private Punkt pozycja;
    private Punkt poprzedniaPozycja;
    private int sila;
    private int inicjatywa;
    private int wiek;
    private final Swiat swiat;

    public Organizm(Punkt pozycja, int sila, int inicjatywa, Swiat swiat) {
        this.pozycja = pozycja;
        try {
            this.poprzedniaPozycja = (Punkt) pozycja.clone();
        } catch (CloneNotSupportedException e) {
            System.exit(1);
        }
        this.sila = sila;
        this.inicjatywa = inicjatywa;
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

    public void setPozycja(Punkt pozycja) {
        this.pozycja.setX(pozycja.getX());
        this.pozycja.setY(pozycja.getY());
    }

    public void setPozycja() {
        this.poprzedniaPozycja.setX(pozycja.getX());
        this.poprzedniaPozycja.setY(pozycja.getY());
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public void setPoprzedniaPozycja(Punkt poprzedniaPozycja) {
        this.poprzedniaPozycja = poprzedniaPozycja;
    }

    public void setInicjatywa(int inicjatywa) {
        this.inicjatywa = inicjatywa;
    }

    protected void setNowonarodzony() {
        this.wiek = NOWONARODZONY;
    }

    @Override
    public abstract String toString();

    public boolean czyMartwy() {
        return this.getWiek() == MARTWY;
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

    public void cofnijRuch() {
        try {
            this.pozycja = (Punkt) poprzedniaPozycja.clone();
        } catch (CloneNotSupportedException e) {
            System.exit(1);
        }
        this.getSwiat().dopiszLog(this + " cofa sie na pozycje " + this.getPozycja());
    }

    public void martwy() {
        this.wiek = MARTWY;
        if (this instanceof Czlowiek) {
            swiat.setCzlowiek(null);
        }
    }

    public Punkt getNowaPozycja(Punkt wektorPrzesuniecia) {
        return this.getPozycja().getPrzesuniecieOWektor(wektorPrzesuniecia);
    }

    protected Punkt znajdzPole(Swiat swiat, int odlegloscPrzeszukiwania) {
        int liczbaKierunkow = swiat.getLiczbaKierunkow();
        int index = swiat.losuj(liczbaKierunkow);
        Punkt nowaPozycja = null;
        Punkt nastepny = null;
        try {
            nastepny = (Punkt) swiat.dozwoloneKierunki()[index].clone();
        } catch (CloneNotSupportedException e) {
            System.exit(1);
        }
        nastepny.przemnorz(odlegloscPrzeszukiwania);
        for (int i = 0; i < liczbaKierunkow; i++) {
            if (this.getNowaPozycja(nastepny).czyPozaZakresem(swiat.getRozmiarX(), swiat.getRozmiarY())) {
                nastepny.nastepnyKierunek(swiat);
                nastepny.przemnorz(odlegloscPrzeszukiwania);
            } else {
                if (czyPrzejscieDozwolone(nastepny)) {
                    nowaPozycja = this.getNowaPozycja(nastepny);
                    break;
                } else {
                    nastepny.nastepnyKierunek(swiat);
                    nastepny.przemnorz(odlegloscPrzeszukiwania);
                }
            }
        }
        return nowaPozycja;
    }

    public boolean czyPrzejscieDozwolone(Punkt nastepny) {
        return true;
    }

    protected Punkt znajdzPole(Swiat swiat) {
        return this.znajdzPole(swiat, 1);
    }

    public void zabij(Organizm organizm, Swiat swiat) {
        organizm.martwy();
        swiat.dopiszLog(this + " zabija " + organizm + " na polu " + this.getPozycja().toString());
    }


}
