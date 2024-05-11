package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;

import java.util.Random;

public class Lis extends Zwierze {
    private static final int LIS_INICJATYWA = 7;
    private static final int LIS_SILA = 3;

    public Lis(Punkt pozycja, Swiat swiat) {
        super(pozycja, LIS_SILA, LIS_INICJATYWA, Gatunek.LIS, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        this.zwiekszWiek();
        Random random = new Random();
        int liczbaKierunkow = swiat.getLiczbaKierunkow();
        int index = random.nextInt(liczbaKierunkow);
        Punkt nowaPozycja = null;
        Punkt kierunek = this.getNowaPozycja(swiat.dozwoloneKierunki()[index]);
        for (int i = 0; i < liczbaKierunkow; i++) {
            if (kierunek.czyPozaZakresem(swiat.getRozmiarX(), swiat.getRozmiarY())) {
                kierunek.nastepnyKierunek(swiat);
                this.getNowaPozycja(kierunek);
            } else {
                if (this.czyBezpiecznyRuch(kierunek)) {
                    nowaPozycja = kierunek;
                    break;
                } else {
                    kierunek.nastepnyKierunek(swiat);
                    this.getNowaPozycja(kierunek);
                }
            }
        }
        this.przejdz(nowaPozycja, swiat);
    }

    @Override
    protected void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat) {
        Lis potomek=new Lis(miejsceRozrodu, swiat);
        potomek.setNowonarodzony();
    }
}
