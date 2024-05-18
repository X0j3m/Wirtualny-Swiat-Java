package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Swiaty.SwiatHeksagonalny;
import WirtualnySwiat.Swiaty.SwiatProstokatny;

import javax.swing.*;
import java.awt.*;

public class PanelSymulacji extends JPanel {
    private Swiat swiat;

    public PanelSymulacji(Swiat swiat) {
        this.swiat = swiat;
    }

    public void setSwiat(Swiat swiat) {
        this.swiat = swiat;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Ensure the background is cleared
        if (swiat instanceof SwiatProstokatny) {
            narysujProstokatnySwiat(g);
        } else if (swiat instanceof SwiatHeksagonalny) {
            narysujHeksagonalnySwiat(g);
        }
    }

    private void narysujProstokatnySwiat(Graphics g) {
        int szerokoscPola = getWidth() / swiat.getRozmiarX();
        int wysokoscPola = getHeight() / swiat.getRozmiarY();

        for (int i = 0; i < swiat.getRozmiarX(); i++) {
            for (int j = 0; j < swiat.getRozmiarY(); j++) {
                g.drawRect(i * szerokoscPola, j * wysokoscPola, szerokoscPola, wysokoscPola);
            }
        }
        for (Organizm o : swiat.getOrganizmy()) {
            int x = (int) (o.getPozycja().getX() * szerokoscPola);
            int y = (int) (o.getPozycja().getY() * wysokoscPola);
            g.drawImage(new ImageIcon("res/" + o + ".png").getImage(), x, y, szerokoscPola, wysokoscPola, null);
        }
    }


    private void narysujHeksagonalnySwiat(Graphics g) {
        double rozmiarX = swiat.getRozmiarX();
        double rozmiarY = swiat.getRozmiarY();
        double promienPola = getWidth() / (2 * rozmiarX + (rozmiarX - 1));
        double wysokoscPola = promienPola * Math.sqrt(3);
        double przesuniecie = (getHeight() - (wysokoscPola * rozmiarX)) / 2;
        Punkt[][] tablicaPunktow = new Punkt[(int) rozmiarX][(int) rozmiarY];
        Punkt pierwszy = new Punkt((double) getWidth() / 2, wysokoscPola / 2 + przesuniecie);
        tablicaPunktow[0][0] = pierwszy;
        g.drawPolygon(utworzSzesciokat(pierwszy, promienPola));
        for (int i = 1; i < rozmiarX; i++) {
            tablicaPunktow[0][i] = new Punkt(tablicaPunktow[0][i - 1].getX() - 1.5 * promienPola, tablicaPunktow[0][i - 1].getY() + ((promienPola / 2) * Math.sqrt(3)));
            g.drawPolygon(utworzSzesciokat(tablicaPunktow[0][i], promienPola));
        }
        for (int i = 0; i < rozmiarY; i++) {
            for (int j = 1; j < rozmiarX; j++) {
                tablicaPunktow[j][i] = new Punkt(tablicaPunktow[j - 1][i].getX() + 1.5 * promienPola, tablicaPunktow[j - 1][i].getY() + ((promienPola / 2) * Math.sqrt(3)));
                g.drawPolygon(utworzSzesciokat(tablicaPunktow[j][i], promienPola));
            }
        }
        for (Organizm o : swiat.getOrganizmy()) {
            int x = (int) o.getPozycja().getX();
            int y = (int) o.getPozycja().getY();
            g.drawImage(new ImageIcon("res/" + o + ".png").getImage(), (int) tablicaPunktow[x][y].getPrzesuniecieOWektor(new Punkt(-promienPola / 2, 0)).getX(), (int) tablicaPunktow[x][y].getPrzesuniecieOWektor(new Punkt(0, -(promienPola / 2) * Math.sqrt(3))).getY(), (int) (promienPola), (int) wysokoscPola, null);
        }
    }

    private Polygon utworzSzesciokat(Punkt srodek, double promien) {
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            double kat = Math.toRadians(60 * i);
            int vx = (int) (srodek.getX() + promien * Math.cos(kat));
            int vy = (int) (srodek.getY() + promien * Math.sin(kat));
            hex.addPoint(vx, vy);
        }
        return hex;
    }
}
