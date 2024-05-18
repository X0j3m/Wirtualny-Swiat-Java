package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Rosliny.*;
import WirtualnySwiat.Organizmy.Zwierzeta.*;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Swiaty.SwiatHeksagonalny;
import WirtualnySwiat.Swiaty.SwiatProstokatny;


public class WirtualnySwiat {
    public static void main(String[] args) {
//        Swiat swiat=new SwiatHeksagonalny(10,10);
        Swiat swiat=new SwiatProstokatny(10,10);
//        swiat.dodajOrganizm(new Wilk(new Punkt(0,0), swiat));
//        swiat.dodajOrganizm(new Owca(new Punkt(5,0), swiat));
        swiat.dodajOrganizm(new Antylopa(new Punkt(2,0), swiat));
        swiat.dodajOrganizm(new Lis(new Punkt(3,1), swiat));
//        swiat.dodajOrganizm(new Zolw(new Punkt(2,1), swiat));
//        swiat.dodajOrganizm(new Trawa(new Punkt(1,1), swiat));
//        swiat.dodajOrganizm(new Guarana(new Punkt(0,2), swiat));
//        swiat.dodajOrganizm(new Mlecz(new Punkt(1,2), swiat));
//        swiat.dodajOrganizm(new BarszczSosnowskiego(new Punkt(2,2), swiat));
//        swiat.dodajOrganizm(new WilczeJagody(new Punkt(3,0), swiat));
        swiat.dodajOrganizm(new Czlowiek(new Punkt(5,5), swiat));
        new OknoSymulacji(swiat);
    }
}
