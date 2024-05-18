package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

import java.util.Random;

public class Lis extends Zwierze {
    private static final int LIS_INICJATYWA = 7;
    private static final int LIS_SILA = 3;

    public Lis(Punkt pozycja, Swiat swiat) {
        super(pozycja, LIS_SILA, LIS_INICJATYWA, swiat);
    }

    @Override
    public boolean czyPrzejscieDozwolone(Punkt nastepny) {
        Organizm napotkany = this.getSwiat().getOrganizm(this.getPozycja().getPrzesuniecieOWektor(nastepny));
        return napotkany == null || napotkany.getSila() <= this.getSila();
    }

    @Override
    public String toString() {
        return "LIS";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Lis;
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Lis potomek = new Lis(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
        swiat.dodajNowyOrganizm(potomek);
    }
}
