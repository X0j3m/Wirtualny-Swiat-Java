package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Organizm;
import WirtualnySwiat.Organizmy.Zwierzeta.Wilk;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Swiaty.SwiatProstokatny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class OknoSymulacji extends JFrame implements ActionListener, KeyListener, MouseListener {
    private static final int klawiszQ = 81, klawiszW = 87, klawiszE = 69, klawiszA = 65, klawiszS = 83, klawiszD = 68, klawiszSpacja = 32;

    JPanel panelLogow, panelRoboczy, panelNawigacji;
    PanelSymulacji panelSymulacji;
    JButton przyciskNastepnaTura, przyciskZapisz, przyciskWczytaj;
    Swiat swiat;
    JTextArea poleLogow;
    MenadzerSwiata menadzerSwiata;
    JComboBox dostepneOrganizmyDoDodania;
    String dodawanyGatunek = "";


    public OknoSymulacji(Swiat swiat) {
        this.swiat = swiat;
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        Font czcionka = new Font("Arial", Font.PLAIN, 16);
        menadzerSwiata = new MenadzerSwiata(swiat);
        panelSymulacji = new PanelSymulacji(swiat);
        przyciskNastepnaTura = new JButton("Nastepna Tura");
        przyciskNastepnaTura.addActionListener(this);
        przyciskNastepnaTura.setFont(czcionka);
        przyciskNastepnaTura.setPreferredSize(new Dimension(150, 50));
        przyciskZapisz = new JButton("Zapisz");
        przyciskZapisz.addActionListener(this);
        przyciskZapisz.setFont(czcionka);
        przyciskZapisz.setPreferredSize(new Dimension(150, 50));
        przyciskWczytaj = new JButton("Wczytaj");
        przyciskWczytaj.addActionListener(this);
        przyciskWczytaj.setFont(czcionka);
        przyciskWczytaj.setPreferredSize(new Dimension(150, 50));
        Dimension rozmiarEkranu = Toolkit.getDefaultToolkit().getScreenSize();
        int wysokoscEkranu = (int) rozmiarEkranu.getHeight();
        int szerokoscEkranu = (int) rozmiarEkranu.getWidth();
        this.setSize(szerokoscEkranu, wysokoscEkranu);
        this.setTitle("Wirtualny Świat Java - Michał Ptasznik 197933");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("res/ikona.jpg").getImage());

        panelLogow = new JPanel(new BorderLayout());
        panelLogow.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        poleLogow = new JTextArea();
        poleLogow.setEditable(false);
        poleLogow.setLineWrap(true);
        poleLogow.setWrapStyleWord(true);

        poleLogow.setFont(czcionka);

        panelLogow.add(new JScrollPane(poleLogow), BorderLayout.CENTER);

        poleLogow.setText(swiat.getLogi());

        panelSymulacji.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        panelNawigacji = new JPanel();
        panelNawigacji.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panelNawigacji.setLayout(new FlowLayout());
        panelNawigacji.add(przyciskNastepnaTura);
        panelNawigacji.add(przyciskZapisz);
        panelNawigacji.add(przyciskWczytaj);

        panelRoboczy = new JPanel(new BorderLayout());

        this.setLayout(new BorderLayout());

        this.add(panelLogow, BorderLayout.WEST);
        this.add(panelRoboczy, BorderLayout.CENTER);
        panelRoboczy.add(panelNawigacji, BorderLayout.NORTH);
        panelRoboczy.add(panelSymulacji, BorderLayout.CENTER);

        panelLogow.setPreferredSize(new Dimension(szerokoscEkranu / 4, 0));
        panelNawigacji.setPreferredSize(new Dimension(0, wysokoscEkranu / 10));

        if (swiat.getCzlowiek() != null) {
            przyciskNastepnaTura.setEnabled(false);
        }
        String[] gatunki = {"WILK", "OWCA", "LIS", "ZOLW", "ANTYLOPA", "TRAWA", "MLECZ", "GUARANA", "WILCZE JAGODY", "BARSZCZ SOSNOWSKIEGO"};
        dostepneOrganizmyDoDodania = new JComboBox(gatunki);
        panelNawigacji.add(dostepneOrganizmyDoDodania);
        dostepneOrganizmyDoDodania.addActionListener(this);
        dodawanyGatunek = gatunki[0];
        dostepneOrganizmyDoDodania.setPreferredSize(new Dimension(150, 50));
        dostepneOrganizmyDoDodania.setFont(czcionka);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == przyciskNastepnaTura) {
            swiat.wykonajTure();
            poleLogow.setText(swiat.getLogi());
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == przyciskZapisz) {
            JFileChooser wyborPliku = new JFileChooser(System.getProperty("user.dir"));
            if (wyborPliku.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                menadzerSwiata.zapiszSwiat(new File(wyborPliku.getSelectedFile().getAbsolutePath()));
            }
            this.setFocusable(true);
            this.requestFocusInWindow();
        } else if (e.getSource() == przyciskWczytaj) {
            JFileChooser wyborPliku = new JFileChooser(System.getProperty("user.dir"));
            if (wyborPliku.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                swiat = menadzerSwiata.wczytajSwiat(new File(wyborPliku.getSelectedFile().getAbsolutePath()));
                panelSymulacji.setSwiat(swiat);
                menadzerSwiata.stworzNowySwiat(swiat);
                poleLogow.setText("");
                this.revalidate();
                this.repaint();
            }
            this.setFocusable(true);
            this.requestFocusInWindow();
        } else if (e.getSource() == dostepneOrganizmyDoDodania) {
            dodawanyGatunek = (String) dostepneOrganizmyDoDodania.getSelectedItem();
            this.setFocusable(true);
            this.requestFocusInWindow();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (swiat.getCzlowiek() != null) {
            Punkt kierunekRuchu = null;
            int klawisz = e.getKeyCode();
            if (swiat instanceof SwiatProstokatny) {
                switch (klawisz) {
                    case klawiszW:
                        kierunekRuchu = new Punkt(0, -1);
                        break;
                    case klawiszA:
                        kierunekRuchu = new Punkt(-1, 0);
                        break;
                    case klawiszS:
                        kierunekRuchu = new Punkt(0, 1);
                        break;
                    case klawiszD:
                        kierunekRuchu = new Punkt(1, 0);
                        break;
                }
            } else {
                switch (klawisz) {
                    case klawiszQ:
                        kierunekRuchu = new Punkt(-1, 0);
                        break;
                    case klawiszW:
                        kierunekRuchu = new Punkt(-1, -1);
                        break;
                    case klawiszE:
                        kierunekRuchu = new Punkt(0, -1);
                        break;
                    case klawiszA:
                        kierunekRuchu = new Punkt(0, 1);
                        break;
                    case klawiszS:
                        kierunekRuchu = new Punkt(1, 1);
                        break;
                    case klawiszD:
                        kierunekRuchu = new Punkt(1, 0);
                        break;
                }
            }
            if (kierunekRuchu != null) {
                if (!swiat.getCzlowiek().getNowaPozycja(kierunekRuchu).czyPozaZakresem(swiat.getRozmiarX(), swiat.getRozmiarY())) {
                    swiat.getCzlowiek().setKierunekRuchu(kierunekRuchu);
                    swiat.wykonajTure();
                    poleLogow.setText(swiat.getLogi());
                }
            } else if (klawisz == klawiszSpacja) {
                swiat.getCzlowiek().aktywujUmiejetnosc();
                if (swiat.getCzlowiek().czyUmiejetnoscAktywna()) {
                    poleLogow.setText(swiat.getLogi());
                }
            }
            if (swiat.getCzlowiek() == null) {
                przyciskNastepnaTura.setEnabled(true);
            }
            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point panelLocation = panelSymulacji.getLocationOnScreen();
        Point mouseLocation = e.getLocationOnScreen();
        if((mouseLocation.x-panelLocation.x)>=0 && (mouseLocation.y - panelLocation.y)>=0){
            int myszX=mouseLocation.x;
            int myszY=mouseLocation.y;
            Punkt klikniete=panelSymulacji.zwrocKliknietePole(myszX, myszY);
            if (klikniete!=null && swiat.getOrganizm(klikniete) == null) {
                Organizm organizm = swiat.stworzOrganizm(dodawanyGatunek,(int) klikniete.getX(),(int) klikniete.getY());
                swiat.dodajOrganizm(organizm);
                swiat.dopiszLog("Pojawia się " + organizm + " na polu " + organizm.getPozycja());
                poleLogow.setText(swiat.getLogi());
                this.revalidate();
                this.repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}