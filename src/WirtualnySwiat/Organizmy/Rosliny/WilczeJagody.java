package WirtualnySwiat.Organizmy.Rosliny;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Organizmy.Zwierzeta.Zwierze;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

public class WilczeJagody extends Roslina{
    private static final int WILCZE_JAGODY_SILA=99;
    public WilczeJagody(Punkt pozycja, Swiat swiat) {
        super(pozycja, WILCZE_JAGODY_SILA, Gatunek.WILCZE_JAGODY, swiat);
    }

    @Override
    protected void zasiej(Punkt miejsceRozsiewu, Swiat swiat) {
        WilczeJagody potomek=new WilczeJagody(miejsceRozsiewu, swiat);
        potomek.setNowonarodzony();
    }

    @Override
    public void zostanZjedzony(Zwierze zwierze, Swiat swiat){
        super.zostanZjedzony(zwierze, swiat);
        zwierze.martwy();
    }
}
