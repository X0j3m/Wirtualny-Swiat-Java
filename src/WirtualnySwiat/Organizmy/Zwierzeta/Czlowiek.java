package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Czlowiek extends Zwierze {
    private static final int CZLOWIEK_INICJATYWA = 4;
    private static final int CZLOWIEK_SILA = 5;

    public Czlowiek(Punkt pozycja, Swiat swiat) {
        super(pozycja, CZLOWIEK_SILA, CZLOWIEK_INICJATYWA, Gatunek.CZLOWIEK, swiat);
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {}

    @Override
    public void akcja(Swiat swiat) {
        this.zwiekszWiek();
    }

}
