package WirtualnySwiat.Organizmy.Rosliny;

import WirtualnySwiat.Organizmy.Zwierzeta.Zwierze;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Guarana extends Roslina {
    private static final int SILA_ZWIEKSZENIE_PO_ZJEDZENIU = 3;
    private static final int GUARANA_SILA = 0;

    public Guarana(Punkt pozycja, Swiat swiat) {
        super(pozycja, GUARANA_SILA, swiat);
    }

    @Override
    protected void zasiej(Punkt miejsceRozsiewu, Swiat swiat) {
        Guarana potomek = new Guarana(miejsceRozsiewu, swiat);
        potomek.setNowonarodzony();
        swiat.dodajNowyOrganizm(potomek);
    }

    @Override
    public void zostanZjedzony(Zwierze zwierze, Swiat swiat) {
        super.zostanZjedzony(zwierze, swiat);
        zwierze.setSila(zwierze.getSila() + SILA_ZWIEKSZENIE_PO_ZJEDZENIU);
    }

    @Override
    public String toString() {
        return "GUARANA";
    }
}
