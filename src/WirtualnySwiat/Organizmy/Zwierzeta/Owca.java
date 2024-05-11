package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Owca extends Zwierze {
    private static final int OWCA_INICJATYWA = 4;
    private static final int OWCA_SILA = 4;

    public Owca(Punkt pozycja, Swiat swiat) {
        super(pozycja, OWCA_SILA, OWCA_INICJATYWA, Gatunek.OWCA, swiat);
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Owca potomek=new Owca(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
    }
}
