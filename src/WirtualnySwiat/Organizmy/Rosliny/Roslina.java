package WirtualnySwiat.Organizmy.Rosliny;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Typ;
import WirtualnySwiat.Organizmy.Zwierzeta.Zwierze;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

import java.util.Random;

public abstract class Roslina extends Organizm {
    protected static final int PRAWDOPODOBIENSTWO_ROZSIANIA = 50;

    public Roslina(Punkt pozycja, int sila, Gatunek gatunek, Swiat swiat) {
        super(pozycja, sila, 0, Typ.ROSLINA, gatunek, swiat);
    }

    public void akcja(Swiat swiat) {
        Random random = new Random();
        if (random.nextInt(100) < PRAWDOPODOBIENSTWO_ROZSIANIA) {
            Punkt miejsceRozsiewu = this.znajdzWolnePole(swiat);
            if (miejsceRozsiewu != null) {
                this.zasiej(miejsceRozsiewu, swiat);
            }
        }
    }

    public void kolizja(Organizm organizm, Swiat swiat) {

    }

    protected abstract void zasiej(Punkt miejsceRozsiewu, Swiat swiat);

    public void zostanZjedzony(Zwierze zwierze, Swiat swiat) {
        this.martwy();
    }
}
