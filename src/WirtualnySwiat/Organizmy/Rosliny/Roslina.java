package WirtualnySwiat.Organizmy.Rosliny;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Zwierzeta.Zwierze;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public abstract class Roslina extends Organizm {
    protected static final int PRAWDOPODOBIENSTWO_ROZSIANIA_W_PROCENTACH = 10;

    public Roslina(Punkt pozycja, int sila, Swiat swiat) {
        super(pozycja, sila, 0, swiat);
    }

    public void akcja(Swiat swiat) {
        if (swiat.losuj(100) < PRAWDOPODOBIENSTWO_ROZSIANIA_W_PROCENTACH) {
            Punkt miejsceRozsiewu = this.znajdzPole(swiat);
            if (miejsceRozsiewu != null) {
                swiat.dopiszLog(this + " zasiewa nowa rosline na polu " + miejsceRozsiewu);
                this.zasiej(miejsceRozsiewu, swiat);
            }
        }
    }

    @Override
    public boolean czyPrzejscieDozwolone(Punkt nastepny) {
        Organizm napotkany = this.getSwiat().getOrganizm(this.getPozycja().getPrzesuniecieOWektor(nastepny));
        return napotkany == null;
    }

    public void kolizja(Organizm organizm, Swiat swiat) {

    }

    protected abstract void zasiej(Punkt miejsceRozsiewu, Swiat swiat);

    public void zostanZjedzony(Zwierze zwierze, Swiat swiat) {
        this.martwy();
    }
}
