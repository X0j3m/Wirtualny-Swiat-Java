package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Zwierzeta.*;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Swiaty.SwiatHeksagonalny;


public class WirtualnySwiat {
    public static void main(String[] args) {
        Swiat swiat=new SwiatHeksagonalny(20,20);
        swiat.dodajOrganizm(new Czlowiek(new Punkt(9,9), swiat));
        new OknoSymulacji(swiat);
    }
}
