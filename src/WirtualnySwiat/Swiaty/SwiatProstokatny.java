package WirtualnySwiat.Swiaty;

import WirtualnySwiat.Punkt;

public class SwiatProstokatny extends Swiat {
    public SwiatProstokatny(int rozmiarX, int rozmiarY) {
        super(rozmiarX, rozmiarY);
        kierunki = new Punkt[]{new Punkt(0, 1), new Punkt(1, 0), new Punkt(0, -1), new Punkt(-1, 0)};
    }
}
