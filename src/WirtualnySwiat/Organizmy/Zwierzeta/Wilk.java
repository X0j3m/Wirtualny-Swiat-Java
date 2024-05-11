package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;


public class Wilk extends Zwierze {
    private static final int WILK_INICJATYWA = 5;
    private static final int WILK_SILA = 9;

    public Wilk(Punkt pozycja, Swiat swiat) {
        super(pozycja, WILK_SILA, WILK_INICJATYWA, Gatunek.WILK, swiat);
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Wilk potomek=new Wilk(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
    }
}
