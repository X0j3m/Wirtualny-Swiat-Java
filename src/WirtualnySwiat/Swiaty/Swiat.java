package WirtualnySwiat.Swiaty;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Rosliny.*;
import WirtualnySwiat.Organizmy.Zwierzeta.*;
import WirtualnySwiat.PorownywaczOrganizmow;
import WirtualnySwiat.Punkt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Swiat {
    private final PorownywaczOrganizmow porownywacz = new PorownywaczOrganizmow();
    protected Punkt[] kierunki;
    private final int rozmiarX, rozmiarY;
    private int tura;
    private Czlowiek czlowiek;
    private StringBuilder logi;
    private final List<Organizm> organizmy;
    private final List<Organizm> noweOrganizmy;
    private Random losowa;

    public Swiat(int rozmiarX, int rozmiarY) {
        this.rozmiarX = rozmiarX;
        this.rozmiarY = rozmiarY;
        this.tura = 0;
        this.organizmy = new ArrayList<>();
        this.noweOrganizmy = new ArrayList<>();
        this.czlowiek = null;
        this.logi = new StringBuilder("");
        this.losowa = new Random();
    }

    public int losuj(int zakres) {
        return losowa.nextInt(zakres);
    }

    @Override
    public String toString() {
        String string = new String();
        string += String.valueOf(this.getRozmiarX()) + '\n';
        string += String.valueOf(this.getRozmiarY()) + '\n';
        string += String.valueOf(this.getTura()) + '\n';
        string += String.valueOf(this.getOrganizmy().size()) + '\n';
        string += String.valueOf(this.getCzlowiek() != null) + "\n";
        if (this.getCzlowiek() != null) {
            string += String.valueOf(this.getCzlowiek().getTuraAktywacji());
        }
        string += ("\n");
        for (Organizm organizm : this.getOrganizmy()) {
            string += String.valueOf(organizm.toString()) + '\n';
            string += String.valueOf((int)organizm.getPozycja().getX()) + '\n';
            string += String.valueOf((int)organizm.getPozycja().getY()) + '\n';
            string += String.valueOf(organizm.getSila()) + '\n';
            string += String.valueOf(organizm.getInicjatywa()) + '\n';
            string += String.valueOf(organizm.getWiek()) + '\n';
        }
        return string;
    }

    public Punkt[] dozwoloneKierunki() {
        return kierunki;
    }

    public int getLiczbaKierunkow() {
        return kierunki.length;
    }

    public int getRozmiarX() {
        return rozmiarX;
    }

    public int getRozmiarY() {
        return rozmiarY;
    }

    public int getTura() {
        return tura;
    }

    public void setTura(int tura) {
        this.tura = tura;
    }

    public List<Organizm> getOrganizmy() {
        return organizmy;
    }

    public String getLogi() {
        return String.valueOf(logi);
    }

    public void setCzlowiek(Czlowiek czlowiek) {
        this.czlowiek = czlowiek;
    }

    public Czlowiek getCzlowiek() {
        return czlowiek;
    }

    public void wykonajTure() {
        this.wyczyscLogi();
        if(this.getCzlowiek()!=null) {
            if(this.getCzlowiek().czyUmiejetnoscAktywna()){
                this.dopiszLog("UMIEJETNOSC AKTYWNA");
            }else {
                this.dopiszLog("UMIEJETNOSC NIE AKTYWNA");
            }
        }
        this.tura++;
        for (Organizm organizm : organizmy) {
            if (organizm.getWiek() != organizm.MARTWY) {
                organizm.akcja(this);
            }
        }
        organizmy.removeIf(Organizm::czyMartwy);
        for (Organizm organizm : noweOrganizmy) {
            organizm.zwiekszWiek();
        }
        organizmy.addAll(noweOrganizmy);
        noweOrganizmy.clear();
        this.posortujOrganizmy();
        if(this.getCzlowiek()!=null){
            this.getCzlowiek().setKierunekRuchu(null);
        }
    }

    public Organizm getOrganizm(Punkt pozycja) {
        Organizm o1 = organizmy.stream().filter(o -> o.getPozycja().equals(pozycja)).findFirst().orElse(null);
        Organizm o2 = noweOrganizmy.stream().filter(o -> o.getPozycja().equals(pozycja)).findFirst().orElse(null);

        if(o1==null){
            return o2;
        }else {
            return o1;
        }
    }

    public void dodajOrganizm(Organizm organizm) {
        organizmy.add(organizm);
    }

    public Organizm stworzOrganizm(String gatunek, int pX, int pY){
        switch (gatunek) {
            case "WILK":
                return new Wilk(new Punkt(pX, pY), this);
            case "OWCA":
                return new Owca(new Punkt(pX, pY), this);
            case "LIS":
                return new Lis(new Punkt(pX, pY), this);
            case "ZOLW":
                return new Zolw(new Punkt(pX, pY), this);
            case "ANTYLOPA":
                return new Antylopa(new Punkt(pX, pY), this);
            case "TRAWA":
                return new Trawa(new Punkt(pX, pY), this);
            case "MLECZ":
                return new Mlecz(new Punkt(pX, pY), this);
            case "GUARANA":
                return new Guarana(new Punkt(pX, pY), this);
            case "WILCZE JAGODY":
                return new WilczeJagody(new Punkt(pX, pY), this);
            case "BARSZCZ SOSNOWSKIEGO":
                return new BarszczSosnowskiego(new Punkt(pX, pY), this);
            default:
                return new Czlowiek(new Punkt(pX, pY), this);
        }
    }

    public void dodajNowyOrganizm(Organizm organizm) {
        noweOrganizmy.add(organizm);
    }

    public void posortujOrganizmy() {
        organizmy.sort(porownywacz::porownaj);
    }

    public void walka(Zwierze atakujacy, Zwierze broniacy) {
        this.dopiszLog(atakujacy + " atakuje " + broniacy + " na polu " + broniacy.getPozycja());
        if (broniacy.czyOdpieraAtak(atakujacy)) {
            broniacy.obronSie(atakujacy, this);
        } else {
            atakujacy.zabij(broniacy, this);
            atakujacy.setPozycja();
        }
    }

    public void dopiszLog(String log) {
        this.logi.append(log).append("\n");
    }

    public void wyczyscLogi() {
        this.logi = new StringBuilder();
    }
}
