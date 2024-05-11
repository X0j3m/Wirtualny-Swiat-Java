package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Organizm;

public class PorownywaczOrganizmow {
    public int porownaj(Organizm organizm1, Organizm organizm2) {
        if (organizm1.getInicjatywa() == organizm2.getInicjatywa()) {
            return organizm2.getWiek() - organizm1.getWiek();
        } else {
            return organizm2.getInicjatywa() - organizm1.getInicjatywa();
        }
    }
}
