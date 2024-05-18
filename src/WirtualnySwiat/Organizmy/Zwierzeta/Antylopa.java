package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Antylopa extends Zwierze {
    private static final int ANTYLOPA_INICJATYWA = 4;
    private static final int ANTYLOPA_SILA = 4;

    public Antylopa(Punkt pozycja, Swiat swiat) {
        super(pozycja, ANTYLOPA_SILA, ANTYLOPA_INICJATYWA, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        this.zwiekszWiek();
        Punkt nowaPozycja = this.znajdzPole(swiat, 2);
        this.przejdz(nowaPozycja, swiat);
    }

    @Override
    public String toString() {
        return "ANTYLOPA";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Antylopa;
    }

    public boolean czyOdpieraAtak(Organizm atakujacy) {
        return this.getSwiat().losuj(2) == 0;
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Antylopa potomek = new Antylopa(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
        swiat.dodajNowyOrganizm(potomek);
    }

    public void obronSie(Zwierze atakujacy, Swiat swiat) {
        Punkt nowePole = this.znajdzPole(swiat);
        if (nowePole != null) {
            this.getSwiat().dopiszLog(this + " ucieka na pole " + nowePole);
            this.setPozycja(nowePole);
        } else {
            atakujacy.zabij(this, swiat);
        }
        atakujacy.setPozycja();
    }
}
