package WirtualnySwiat.Organizmy.Rosliny;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Mlecz extends Roslina {
    private static final int MLECZ_SILA = 0;

    public Mlecz(Punkt pozycja, Swiat swiat) {
        super(pozycja, MLECZ_SILA, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        for (int i = 0; i < 3; i++) {
            if (swiat.losuj(100) < PRAWDOPODOBIENSTWO_ROZSIANIA_W_PROCENTACH) {
                Punkt miejsceRozsiewu = this.znajdzPole(swiat);
                if (miejsceRozsiewu != null) {
                    swiat.dopiszLog(this + " zasiewa nowa rosline na polu " + miejsceRozsiewu);
                    this.zasiej(miejsceRozsiewu, swiat);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "MLECZ";
    }

    @Override
    protected void zasiej(Punkt miejsceRozsiewu, Swiat swiat) {
        Mlecz potomek = new Mlecz(miejsceRozsiewu, swiat);
        potomek.setNowonarodzony();
        swiat.dodajNowyOrganizm(potomek);
    }
}
