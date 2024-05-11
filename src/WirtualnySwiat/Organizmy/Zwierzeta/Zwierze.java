package WirtualnySwiat.Organizmy.Zwierzeta;

import WirtualnySwiat.Organizmy.Gatunek;
import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Rosliny.Roslina;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Organizmy.Typ;

import java.util.Random;

public abstract class Zwierze extends Organizm {
    public Zwierze(Punkt pozycja, int sila, int inicjatywa, Gatunek gatunek, Swiat swiat) {
        super(pozycja, sila, inicjatywa, Typ.ZWIERZE, gatunek, swiat);
    }

    @Override
    public void akcja(Swiat swiat) {
        this.zwiekszWiek();
        Punkt nowaPozycja = znajdzWolnePole(swiat);
        this.przejdz(nowaPozycja, swiat);
    }

    protected void przejdz(Punkt nowaPozycja, Swiat swiat) {
        if (nowaPozycja != null) {
            Organizm inny = swiat.getOrganizm(nowaPozycja);
            this.setPozycja(nowaPozycja);
            if (inny != null) {
                this.kolizja(inny, swiat);
            }
        }
    }

    public boolean czyOdpieraAtak(Organizm atakujacy) {
        if (atakujacy.getSila() >= this.getSila()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void kolizja(Organizm organizm, Swiat swiat) {
        if(organizm instanceof Zwierze){
            if (this.getGatunek() == organizm.getGatunek()) {
                this.cofnijRuch();
                Punkt miejsceRozrodu=this.znajdzMiejsceDoRozrodu((Zwierze) organizm, swiat);
                if(miejsceRozrodu!=null){
                    this.rozmnorzSie(miejsceRozrodu, swiat);
                }
            } else {
                swiat.walka(this, (Zwierze) organizm);
            }
        }else{
            this.zjedz((Roslina) organizm, swiat);
        }
    }

    private void zjedz(Roslina roslina, Swiat swiat) {
        this.setPozycja();
        roslina.zostanZjedzony(this, swiat);
    }

    private Punkt znajdzMiejsceDoRozrodu(Zwierze organizm, Swiat swiat) {
        Random random=new Random();
        Punkt p1=this.znajdzWolnePole(swiat);
        Punkt p2=organizm.znajdzWolnePole(swiat);
        Punkt miejsceRozrodu;
        if(random.nextInt(2)==0){
            if(p1!=null){
                miejsceRozrodu=p1;
            }else{
                miejsceRozrodu=p2;
            }
        }else{
            if(p2!=null){
                miejsceRozrodu=p2;
            }else{
                miejsceRozrodu=p1;
            }
        }
        return miejsceRozrodu;
    }

    protected abstract void rozmnorzSie(Punkt miejsceRozrodu, Swiat swiat);

    public void obronSie(Zwierze atakujacy, Swiat swiat) {
        this.zabij(atakujacy, swiat);
    }

    public boolean czyBezpiecznyRuch(Punkt wektor) {
        Organizm napotkany = this.getSwiat().getOrganizm(this.getPozycja().getPrzesuniecieOWektor(wektor));
        if (napotkany == null || napotkany.getSila() <= this.getSila()) {
            return true;
        } else {
            return false;
        }
    }
}
