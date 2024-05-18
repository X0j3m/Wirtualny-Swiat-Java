package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Swiaty.SwiatHeksagonalny;
import WirtualnySwiat.Swiaty.SwiatProstokatny;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class PanelSymulacji extends JPanel {
    private Swiat swiat;
    Punkt[][] tablicaPunktow;
    double promienPola;

    public PanelSymulacji(Swiat swiat) {
        this.swiat = swiat;
        if (swiat instanceof SwiatHeksagonalny) {
            tablicaPunktow = new Punkt[swiat.getRozmiarX()][swiat.getRozmiarY()];
        } else {
            tablicaPunktow = null;
        }
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
        promienPola = getWidth() / (2 * rozmiarX + (rozmiarX - 1));
        double wysokoscPola = promienPola * Math.sqrt(3);
        double przesuniecie = (getHeight() - (wysokoscPola * rozmiarY)) / 2;  // poprawka: użyj rozmiarY zamiast rozmiarX
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
            double kat = 2 * Math.PI / 6 * i;
            int vx = (int) (srodek.getX() + promien * Math.cos(kat));
            int vy = (int) (srodek.getY() + promien * Math.sin(kat));
            hex.addPoint(vx, vy);
        }
        return hex;
    }

    public Punkt zwrocKliknietePole(int myszX, int myszY) {
        if (swiat instanceof SwiatHeksagonalny) {
            for (int i = 0; i < swiat.getRozmiarX(); i++) {
                for (int j = 0; j < swiat.getRozmiarY(); j++) {
                    int lokalnyX = myszX - this.getLocationOnScreen().x;
                    int lokalnyY = myszY - this.getLocationOnScreen().y;
                    if (czyKliknietoWPole(tablicaPunktow[i][j].getX(), tablicaPunktow[i][j].getY(), promienPola, lokalnyX, lokalnyY)) {
                        return new Punkt(i, j);
                    }
                }
            }
        } else {
            int szerokoscPola = this.getWidth() / swiat.getRozmiarX();
            int wysokoscPola = this.getHeight() / swiat.getRozmiarY();

            int poleX = (myszX - this.getLocationOnScreen().x) / szerokoscPola;
            int poleY = (myszY - this.getLocationOnScreen().y) / wysokoscPola;
            return new Punkt(poleX, poleY);
        }
        return null;
    }

    public static boolean czyKliknietoWPole(double xSrodka, double ySrodka, double promien, double myszX, double myszY) {
        Point2D.Double[] wierzcholki = new Point2D.Double[6];
        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI / 6 * i;
            double vx = xSrodka + promien * Math.cos(angle);
            double vy = ySrodka + promien * Math.sin(angle);
            wierzcholki[i] = new Point2D.Double(vx, vy);
        }

        return czyWFigurze(wierzcholki, myszX, myszY);
    }

    private static boolean czyWFigurze(Point2D.Double[] wierzcholki, double x, double y) {
        boolean wynik = false;
        for (int i = 0, j = wierzcholki.length - 1; i < wierzcholki.length; j = i++) {
            if ((wierzcholki[i].y > y) != (wierzcholki[j].y > y) &&
                    (x < (wierzcholki[j].x - wierzcholki[i].x) * (y - wierzcholki[i].y) / (wierzcholki[j].y - wierzcholki[i].y) + wierzcholki[i].x)) {
                wynik = !wynik;
            }
        }
        return wynik;
    }
}