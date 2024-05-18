package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Rosliny.Roslina;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public abstract class Zwierze extends Organizm{
    public Zwierze(Punkt pozycja, int sila, int inicjatywa, Swiat swiat) {
        super(pozycja, sila, inicjatywa, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        this.zwiekszWiek();
        Punkt nowaPozycja = znajdzPole(swiat);
        this.przejdz(nowaPozycja, swiat);
    }

    protected void przejdz(Punkt nowaPozycja, Swiat swiat) {
        if (nowaPozycja != null) {
            Organizm inny = swiat.getOrganizm(nowaPozycja);
            swiat.dopiszLog(this + " przechodzi z pola " + this.getPozycja() + " na pole " + nowaPozycja);
            this.setPozycja(nowaPozycja);
            if (inny != null) {
                this.kolizja(inny, swiat);
            }
        }
    }

    @Override
    public abstract boolean equals(Object obj);

    public boolean czyOdpieraAtak(Organizm atakujacy) {
        return atakujacy.getSila() < this.getSila();
    }

    @Override
    public void kolizja(Organizm organizm, Swiat swiat) {
        swiat.dopiszLog(this + " napotyka " + organizm + " na polu " + organizm.getPozycja());
        if (organizm instanceof Zwierze) {
            if (this.equals(organizm)) {
                this.cofnijRuch();
                Punkt miejsceRozrodu = this.znajdzMiejsceDoRozrodu((Zwierze) organizm, swiat);
                if (miejsceRozrodu != null) {
                    swiat.dopiszLog(this + " rozmnaza sie z " + organizm + " na polu " + organizm.getPozycja());
                    this.rozmnorzSie(miejsceRozrodu, swiat);
                }
            } else {
                swiat.walka(this, (Zwierze) organizm);
            }
        } else {
            this.zjedz((Roslina) organizm, swiat);
        }
    }

    private void zjedz(Roslina roslina, Swiat swiat) {
        swiat.dopiszLog(this + " zjada " + roslina + " na polu " + roslina.getPozycja());
        this.setPozycja();
        roslina.zostanZjedzony(this, swiat);
    }

    private Punkt znajdzMiejsceDoRozrodu(Zwierze organizm, Swiat swiat) {
        Punkt p1 = this.znajdzPole(swiat);
        Punkt p2 = organizm.znajdzPole(swiat);
        Punkt miejsceRozrodu;
        if (swiat.losuj(2) == 0) {
            if (p1 != null) {
                miejsceRozrodu = p1;
            } else {
                miejsceRozrodu = p2;
            }
        } else {
            if (p2 != null) {
                miejsceRozrodu = p2;
            } else {
                miejsceRozrodu = p1;
            }
        }
        return miejsceRozrodu;
    }

    protected abstract void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat);

    public void obronSie(Zwierze atakujacy, Swiat swiat) {
        this.zabij(atakujacy, swiat);
    }
}
