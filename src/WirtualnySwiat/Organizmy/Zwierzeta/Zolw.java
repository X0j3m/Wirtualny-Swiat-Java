package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

import java.util.Random;

public class Zolw extends Zwierze {
    private static final int ZOLW_INICJATYWA = 1;
    private static final int ZOLW_SILA = 2;

    public Zolw(Punkt pozycja, Swiat swiat) {
        super(pozycja, ZOLW_SILA, ZOLW_INICJATYWA, Gatunek.ZOLW, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        Random random = new Random();
        if (random.nextInt(4) == 0) {
            super.akcja(swiat);
        }
    }

    @Override
    public void obronSie(Zwierze atakujacy, Swiat swiat) {
        atakujacy.cofnijRuch();
    }

    @Override
    public boolean czyOdpieraAtak(Organizm atakujacy) {
        if (atakujacy.getSila() < 5) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Zolw potomek = new Zolw(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
    }
}
