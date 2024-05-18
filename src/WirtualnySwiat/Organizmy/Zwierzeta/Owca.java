package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Owca extends Zwierze {
    private static final int OWCA_INICJATYWA = 4;
    private static final int OWCA_SILA = 4;

    public Owca(Punkt pozycja, Swiat swiat) {
        super(pozycja, OWCA_SILA, OWCA_INICJATYWA, swiat);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Owca;
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Owca potomek = new Owca(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
        swiat.dodajNowyOrganizm(potomek);
    }

    @Override
    public String toString() {
        return "OWCA";
    }
}
