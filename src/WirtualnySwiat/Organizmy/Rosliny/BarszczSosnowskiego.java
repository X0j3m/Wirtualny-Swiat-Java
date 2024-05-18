package WirtualnySwiat.Organizmy.Rosliny;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Zwierzeta.Zwierze;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class BarszczSosnowskiego extends Roslina {
    private static final int BARSZCZ_SOSNOWSKIEGO_SILA = 10;

    public BarszczSosnowskiego(Punkt pozycja, Swiat swiat) {
        super(pozycja, BARSZCZ_SOSNOWSKIEGO_SILA, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        for (int i = 0; i < swiat.getLiczbaKierunkow(); i++) {
            Organizm sasiad = swiat.getOrganizm(this.getPozycja().getPrzesuniecieOWektor(swiat.dozwoloneKierunki()[i]));
            if (sasiad instanceof Zwierze) {
                this.zabij(sasiad, swiat);
            }
        }
        super.akcja(swiat);
    }

    @Override
    public String toString() {
        return "BARSZCZ SOSNOWSKIEGO";
    }

    @Override
    protected void zasiej(Punkt miejsceRozsiewu, Swiat swiat) {
        BarszczSosnowskiego potomek = new BarszczSosnowskiego(miejsceRozsiewu, swiat);
        potomek.setNowonarodzony();
        swiat.dodajNowyOrganizm(potomek);
    }

    @Override
    public void zostanZjedzony(Zwierze zwierze, Swiat swiat) {
        super.zostanZjedzony(zwierze, swiat);
        swiat.dopiszLog(this+" zabija "+zwierze);
        zwierze.martwy();
    }
}
