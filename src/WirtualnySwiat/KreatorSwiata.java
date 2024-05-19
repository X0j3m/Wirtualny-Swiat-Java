package WirtualnySwiat;

import WirtualnySwiat.Organizmy.Zwierzeta.Czlowiek;
import WirtualnySwiat.Swiaty.Swiat;
import WirtualnySwiat.Swiaty.SwiatHeksagonalny;
import WirtualnySwiat.Swiaty.SwiatProstokatny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KreatorSwiata extends JDialog implements ActionListener {
    private JRadioButton przyciskSwiatProstokatny;
    private JRadioButton przyciskSwiatHeksagonalny;
    private JTextField wymiarX;
    private JTextField wymiarY;
    private JButton przyciskOK;
    private JButton przyciskAnuluj;
    private Swiat swiat = null;

    public KreatorSwiata(Frame parent) {
        super(parent, "Kreator Świata", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        przyciskSwiatProstokatny = new JRadioButton("Świat Prostokątny");
        przyciskSwiatHeksagonalny = new JRadioButton("Świat Heksagonalny");

        ButtonGroup group = new ButtonGroup();
        group.add(przyciskSwiatProstokatny);
        group.add(przyciskSwiatHeksagonalny);

        przyciskSwiatProstokatny.setSelected(true);

        wymiarX = new JTextField();
        wymiarY = new JTextField();

        przyciskOK = new JButton("OK");
        przyciskAnuluj = new JButton("Anuluj");
        przyciskOK.addActionListener(this);
        przyciskAnuluj.addActionListener(this);
        przyciskSwiatProstokatny.addActionListener(this);
        przyciskSwiatHeksagonalny.addActionListener(this);

        add(przyciskSwiatProstokatny);
        add(przyciskSwiatHeksagonalny);
        add(new JLabel("Wymiar X:"));
        add(wymiarX);
        add(new JLabel("Wymiar Y:"));
        add(wymiarY);
        add(przyciskOK);
        add(przyciskAnuluj);

        wymiarY.setEnabled(przyciskSwiatProstokatny.isSelected());

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == przyciskAnuluj) {
            dispose();
        } else if (e.getSource() == przyciskOK) {
            try {
                int x = Integer.parseInt(wymiarX.getText());
                if (przyciskSwiatProstokatny.isSelected()) {
                    int y = Integer.parseInt(wymiarY.getText());
                    swiat = new SwiatProstokatny(x, y);
                } else {
                    swiat = new SwiatHeksagonalny(x, x);
                }
                swiat.dodajOrganizm(new Czlowiek(new Punkt(swiat.getRozmiarX() / 2, swiat.getRozmiarY() / 2), swiat));
                dispose();
            } catch (NumberFormatException exception) {

            }
        } else if (e.getSource() == przyciskSwiatHeksagonalny) {
            wymiarY.setEnabled(false);
        } else if (e.getSource() == przyciskSwiatProstokatny) {
            wymiarY.setEnabled(true);
        }
    }

    public Swiat getSwiat() {
        return swiat;
    }
}
