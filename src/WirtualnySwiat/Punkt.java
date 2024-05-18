package WirtualnySwiat;

import WirtualnySwiat.Swiaty.Swiat;

public class Punkt implements Cloneable{
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
        return new Punkt(this.getX() + wektor.getX(),this.getY() + wektor.getY());
    }

    public boolean czyPozaZakresem(int zakresX, int zakresY) {
        return this.x < 0 || this.x >= zakresX || this.y < 0 || this.y >= zakresY;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Punkt) {
            return this.getX() == ((Punkt) object).getX() && this.getY() == ((Punkt) object).getY();
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void przemnorz(int mnoznik) {
        this.setX(this.getX() * mnoznik);
        this.setY(this.getY() * mnoznik);
    }

    public void nastepnyKierunek(Swiat swiat) {
        int index = 0;
        for (Punkt kierunek : swiat.dozwoloneKierunki()) {
            if (this.equals(kierunek)) {
                index++;
                break;
            } else {
                index++;
            }
        }
        if (index == swiat.getLiczbaKierunkow()) {
            index = 0;
        }
        this.setX(swiat.dozwoloneKierunki()[index].getX());
        this.setY(swiat.dozwoloneKierunki()[index].getY());
    }

    @Override
    public String toString() {
        return "(" + this.getX() + "; " + this.getY() + ")";
    }
}
