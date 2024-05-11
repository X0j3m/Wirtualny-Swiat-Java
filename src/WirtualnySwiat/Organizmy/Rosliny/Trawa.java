package WirtualnySwiat.Organizmy.Rosliny;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Trawa extends Roslina {
    private static final int TRAWA_SILA = 0;

    public Trawa(Punkt pozycja, Swiat swiat) {
        super(pozycja, TRAWA_SILA, Gatunek.TRAWA, swiat);
    }


    @Override
    protected void zasiej(Punkt miejsceRozsiewu, Swiat swiat) {
        Trawa potomek=new Trawa(miejsceRozsiewu, swiat);
        potomek.setNowonarodzony();
    }
}
