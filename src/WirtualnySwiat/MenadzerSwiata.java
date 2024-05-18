package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Rosliny.*;
import WirtualnySwiat.Organizmy.Zwierzeta.*;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Swiaty.SwiatHeksagonalny;
import WirtualnySwiat.Swiaty.SwiatProstokatny;

import java.io.*;

public class MenadzerSwiata {
    private Swiat swiat;

    public MenadzerSwiata(Swiat swiat) {
        this.swiat = swiat;
    }

    public void stworzNowySwiat(Swiat nowySwiat) {
        this.swiat = nowySwiat;
    }

    public void zapiszSwiat(File plik) {
        try {
            FileWriter zapis = new FileWriter(plik);
            zapis.write(swiat.toString());
            zapis.close();
            swiat.dopiszLog("Zapis sie powiodl");
        } catch (IOException exception) {
            swiat.dopiszLog("Zapis sie nie powiodl");
        }
    }

    public Swiat wczytajSwiat(File plik) {
        Swiat wczytanySwiat = null;
        try {
            FileReader odczyt = new FileReader(plik);
            BufferedReader odczytywaczLinii = new BufferedReader(odczyt);
            String linia;
            linia = odczytywaczLinii.readLine();
            int x, y;
            if (linia.equals("Prostokatny")) {
                x = Integer.parseInt(odczytywaczLinii.readLine());
                y = Integer.parseInt(odczytywaczLinii.readLine());
                wczytanySwiat = new SwiatProstokatny(x, y);
            } else {
                x = Integer.parseInt(odczytywaczLinii.readLine());
                y = Integer.parseInt(odczytywaczLinii.readLine());
                wczytanySwiat = new SwiatHeksagonalny(x, y);
            }
            int tura = Integer.parseInt(odczytywaczLinii.readLine());
            wczytanySwiat.setTura(tura);
            int liczbaOrganizmow = Integer.parseInt(odczytywaczLinii.readLine());
            boolean czyIstniejeCzlowiek = Boolean.parseBoolean(odczytywaczLinii.readLine());
            int turaAktywacjiUmiejetnosciCzlowieka = 0;
            if (czyIstniejeCzlowiek) {
                turaAktywacjiUmiejetnosciCzlowieka = Integer.parseInt(odczytywaczLinii.readLine());
            }else {
                odczytywaczLinii.readLine();
            }
            for (int i = 0; i < liczbaOrganizmow; i++) {
                String gatunek = odczytywaczLinii.readLine();
                x = Integer.parseInt(odczytywaczLinii.readLine());
                y = Integer.parseInt(odczytywaczLinii.readLine());
                Organizm organizm = stworzOrganizmZZapisu(gatunek, x, y, wczytanySwiat);
                organizm.setSila(Integer.parseInt(odczytywaczLinii.readLine()));
                organizm.setInicjatywa(Integer.parseInt(odczytywaczLinii.readLine()));
                organizm.setWiek(Integer.parseInt(odczytywaczLinii.readLine()));
                organizm.setPoprzedniaPozycja(organizm.getPozycja());
                if (organizm instanceof Czlowiek) {
                    ((Czlowiek) organizm).setTuraAktywacji(turaAktywacjiUmiejetnosciCzlowieka);
                }
                wczytanySwiat.dodajOrganizm(organizm);
            }
            odczytywaczLinii.close();
        } catch (IOException exception) {
            swiat.dopiszLog("Odczyt sie nie powiodl");
        }
        return wczytanySwiat;
    }

    private Organizm stworzOrganizmZZapisu(String gatunek, int pX, int pY, Swiat swiat) {
        return swiat.stworzOrganizm(gatunek, pX, pY);
    }
}
