package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

import java.util.Random;

public class Antylopa extends Zwierze {
    private static final int ANTYLOPA_INICJATYWA = 4;
    private static final int ANTYLOPA_SILA = 4;

    public Antylopa(Punkt pozycja, Swiat swiat) {
        super(pozycja, ANTYLOPA_SILA, ANTYLOPA_INICJATYWA, Gatunek.ANTYLOPA, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        this.zwiekszWiek();
        Punkt nowaPozycja = this.znajdzWolnePole(swiat, 2);
        this.przejdz(nowaPozycja, swiat);
    }

    public boolean czyOdpieraAtak(Organizm atakujacy){
        Random random=new Random();
        if(random.nextInt(2)==0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Antylopa potomek=new Antylopa(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
    }

    public void obronSie(Zwierze atakujacy, Swiat swiat) {
        Punkt nowePole=this.znajdzWolnePole(swiat);
        if(nowePole!=null){
            this.setPozycja(nowePole);
        }else{
            atakujacy.zabij(this, swiat);
        }
        atakujacy.setPozycja();
    }
}
