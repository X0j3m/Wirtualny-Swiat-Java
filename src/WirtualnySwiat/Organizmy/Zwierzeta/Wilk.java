package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;


public class Wilk extends Zwierze {
    private static final int WILK_INICJATYWA = 5;
    private static final int WILK_SILA = 9;

    public Wilk(Punkt pozycja, Swiat swiat) {
        super(pozycja, WILK_SILA, WILK_INICJATYWA, swiat);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Wilk;
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Wilk potomek = new Wilk(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
        swiat.dodajNowyOrganizm(potomek);
    }

    @Override
    public String toString() {
        return "WILK";
    }
}
