package WirtualnySwiat;

import WirtualnySwiat.Swiaty.Swiat;

public class Punkt {
    private int x, y;

    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Punkt getPrzesuniecieOWektor(Punkt wektor) {
        return new Punkt(this.getX() + wektor.getX(), this.getX() + wektor.getY());
    }

    public boolean czyPozaZakresem(int zakresX, int zakresY) {
        if (this.x < 0 || this.x >= zakresX || this.y < 0 || this.y >= zakresY) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Punkt) {
            if (this.getX() == ((Punkt) object).getX() && this.getY() == ((Punkt) object).getY()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public Punkt przemnorz(int mnoznik){
        return new Punkt(this.getX()*mnoznik, this.getY()*mnoznik);
    }

    public Punkt nastepnyKierunek(Swiat swiat) {
        int index = 0;
        for (Punkt kierunek : swiat.dozwoloneKierunki()) {
            if (this.equals(kierunek)) {
                index++;
                break;
            }
            index++;
        }
        if (index == swiat.getLiczbaKierunkow()) {
            return swiat.dozwoloneKierunki()[0];
        } else {
            return swiat.dozwoloneKierunki()[index];
        }
    }
}
