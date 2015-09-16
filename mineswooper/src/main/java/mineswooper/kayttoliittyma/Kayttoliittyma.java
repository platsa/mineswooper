/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.kayttoliittyma;

/**
 *
 * @author pekka
 */
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import mineswooper.logiikka.Vaikeus;

public class Kayttoliittyma implements Runnable {
    private final int SIVU = 16;
    private JFrame ikkuna;
    private Pelilauta lauta;
    private Vaikeus vaikeus;

    public Kayttoliittyma() {
        vaikeus = Vaikeus.NORMAALI;
    }

    @Override
    public void run() {
        ikkuna = new JFrame("Mineswooper");
        ikkuna.setPreferredSize(new Dimension(vaikeus.getLeveys() * SIVU, vaikeus.getKorkeus() * SIVU));

        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.setLocationRelativeTo(null);

        luoKomponentit(ikkuna.getContentPane());

        ikkuna.pack();
        ikkuna.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        this.lauta = new Pelilauta(SIVU, vaikeus);
        ikkuna.add(this.lauta);
    }

    public JFrame getFrame() {
        return ikkuna;
    }
}
