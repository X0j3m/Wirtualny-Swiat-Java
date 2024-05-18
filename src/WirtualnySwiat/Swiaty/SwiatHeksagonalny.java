package WirtualnySwiat.Swiaty;

import WirtualnySwiat.Punkt;

import java.awt.*;

public class SwiatHeksagonalny extends Swiat {

    public SwiatHeksagonalny(int rozmiarX, int rozmiarY) {
        super(rozmiarX, rozmiarY);
        kierunki = new Punkt[]{new Punkt(-1, -1), new Punkt(-1, 0), new Punkt(0, 1), new Punkt(1, 1), new Punkt(1, 0), new Punkt(0, -1)};
    }

    @Override
    public String toString() {
        return "Heksagonalny\n"+super.toString();
    }
}
