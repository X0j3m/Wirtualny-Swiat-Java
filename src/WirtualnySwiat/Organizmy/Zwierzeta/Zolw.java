package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Zolw extends Zwierze {
    private static final int ZOLW_INICJATYWA = 1;
    private static final int ZOLW_SILA = 2;

    public Zolw(Punkt pozycja, Swiat swiat) {
        super(pozycja, ZOLW_SILA, ZOLW_INICJATYWA, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        if (swiat.losuj(4) == 0) {
            super.akcja(swiat);
        }else{
            swiat.dopiszLog(this+" "+this.getPozycja()+" nie zmienia pozycji");
        }
    }

    @Override
    public String toString() {
        return "ZOLW";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Zolw;
    }

    @Override
    public void obronSie(Zwierze atakujacy, Swiat swiat) {
        swiat.dopiszLog(this + " odpiera atak " + atakujacy);
        atakujacy.cofnijRuch();
    }

    @Override
    public boolean czyOdpieraAtak(Organizm atakujacy) {
        return atakujacy.getSila() < 5;
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Zolw potomek = new Zolw(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
        swiat.dodajNowyOrganizm(potomek);
    }
}
