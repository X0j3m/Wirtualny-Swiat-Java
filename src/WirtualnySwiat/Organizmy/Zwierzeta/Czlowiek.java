package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class Czlowiek extends Zwierze {
    private static final int CZLOWIEK_INICJATYWA = 4;
    private static final int CZLOWIEK_SILA = 5;
    private static final int CZAS_DZIALANIA_UMIEJETNOSCI = 5;
    private static final int CZAS_REGENERACJI_UMIEJETNOSCI = 5;

    private int turaAktywacji;
    private Punkt kierunekRuchu;

    public Czlowiek(Punkt pozycja, Swiat swiat) {
        super(pozycja, CZLOWIEK_SILA, CZLOWIEK_INICJATYWA, swiat);
        turaAktywacji = swiat.getTura() - CZAS_DZIALANIA_UMIEJETNOSCI - CZAS_REGENERACJI_UMIEJETNOSCI;
        swiat.setCzlowiek(this);
        kierunekRuchu = null;
    }

    public Punkt getKierunekRuchu() {
        return kierunekRuchu;
    }

    public void setKierunekRuchu(Punkt kierunekRuchu) {
        if (kierunekRuchu != null) {
            try {
                this.kierunekRuchu = (Punkt) kierunekRuchu.clone();
            } catch (CloneNotSupportedException e) {
                System.exit(1);
            }
        }
    }

    public int getTuraAktywacji() {
        return turaAktywacji;
    }

    public void setTuraAktywacji(int turaAktywacji) {
        this.turaAktywacji = turaAktywacji;
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
    }

    @Override
    protected Punkt znajdzPole(Swiat swiat) {
        return this.getPozycja().getPrzesuniecieOWektor(this.getKierunekRuchu());
    }

    @Override
    public String toString() {
        return "CZLOWIEK";
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    public void aktywujUmiejetnosc() {
        if (turaAktywacji + CZAS_DZIALANIA_UMIEJETNOSCI + CZAS_REGENERACJI_UMIEJETNOSCI <= this.getSwiat().getTura()) {
            turaAktywacji = this.getSwiat().getTura();
            this.getSwiat().dopiszLog(this + " atywuje umiejetnosc");
        }
    }

    public boolean czyUmiejetnoscAktywna() {
        return turaAktywacji + CZAS_DZIALANIA_UMIEJETNOSCI - 1 >= this.getSwiat().getTura();
    }

    public boolean czyOdpieraAtak(Organizm atakujacy) {
        if (this.czyUmiejetnoscAktywna()) {
            return true;
        } else {
            return super.czyOdpieraAtak(atakujacy);
        }
    }
}
