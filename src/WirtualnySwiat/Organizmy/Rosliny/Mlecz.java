package WirtualnySwiat.Organizmy.Rosliny;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

import java.util.Random;

public class Mlecz extends Roslina{
    private static final int MLECZ_SILA = 0;

    public Mlecz(Punkt pozycja, Swiat swiat) {
        super(pozycja, MLECZ_SILA, Gatunek.MLECZ, swiat);
    }

    @Override
    public void akcja(Swiat swiat){
        for(int i=0; i<3; i++){
            Random random = new Random();
            if (random.nextInt(100) < PRAWDOPODOBIENSTWO_ROZSIANIA) {
                Punkt miejsceRozsiewu = this.znajdzWolnePole(swiat);
                if (miejsceRozsiewu != null) {
                    this.zasiej(miejsceRozsiewu, swiat);
                    break;
                }
            }
        }
    }

    @Override
    protected void zasiej(Punkt miejsceRozsiewu, Swiat swiat) {
        Mlecz potomek=new Mlecz(miejsceRozsiewu, swiat);
        potomek.setNowonarodzony();
    }
}
