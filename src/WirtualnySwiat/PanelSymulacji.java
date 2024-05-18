package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Swiaty.SwiatProstokatny;

import javax.swing.*;
import java.awt.*;

import java.awt.geom.Path2D;

public class PanelSymulacji extends JPanel {
    private Swiat swiat;

    public PanelSymulacji(Swiat swiat) {
        this.swiat=swiat;
    }

    public void setSwiat(Swiat swiat) {
        this.swiat = swiat;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(swiat instanceof SwiatProstokatny){
            int szerokoscPola = getWidth() / swiat.getRozmiarX();
            int wysokoscPola = getHeight() / swiat.getRozmiarY();

            for (int wspolrzednaX = 0; wspolrzednaX < swiat.getRozmiarX(); wspolrzednaX++) {
                for (int wspolrzednaY = 0; wspolrzednaY < swiat.getRozmiarY(); wspolrzednaY++) {
                    int x = wspolrzednaX * szerokoscPola;
                    int y = wspolrzednaY * wysokoscPola;
                    g.drawRect(x, y, szerokoscPola, wysokoscPola);
                    Organizm o=swiat.getOrganizm(new Punkt(wspolrzednaX, wspolrzednaY));
                    if(o!=null){
                        g.drawImage(new ImageIcon("res/"+o+".png").getImage(), x, y, szerokoscPola, wysokoscPola, null);
                    }
                }
            }
        }else{
            int numRows = swiat.getRozmiarY();
            int numCols = swiat.getRozmiarX();

            int radius = Math.min(getWidth() / (3 * numCols), getHeight() / (2 * numRows));

            int szerokoscPola = radius * 3;
            int wysokoscPola = radius * 2;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (int wspolrzednaX = 0; wspolrzednaX < numCols; wspolrzednaX++) {
                for (int wspolrzednaY = 0; wspolrzednaY < numRows; wspolrzednaY++) {
                    int x = (int) (wspolrzednaX * szerokoscPola * 0.75);
                    int y = (int) (wspolrzednaY * wysokoscPola + (wspolrzednaX % 2) * wysokoscPola * 0.5);
                    drawHexagon(g2d, x, y, radius);
                    Organizm o = swiat.getOrganizm(new Punkt(wspolrzednaX, wspolrzednaY));
                    if (o != null) {
                        g.drawImage(new ImageIcon("res/" + o + ".png").getImage(), x - radius, y - radius, szerokoscPola, wysokoscPola, null);
                    }
                }
            }
        }
    }
    private void drawHexagon(Graphics2D g2d, int x, int y, int size) {
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            double angleRad = Math.toRadians(60 * i);
            xPoints[i] = x + (int) (size * Math.cos(angleRad));
            yPoints[i] = y + (int) (size * Math.sin(angleRad));
        }

        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, 6);
    }

}