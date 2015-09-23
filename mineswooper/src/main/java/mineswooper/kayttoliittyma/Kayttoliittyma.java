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
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;
import mineswooper.logiikka.Vaikeus;

public class Kayttoliittyma implements Runnable {
    private final int sivu = 16;
    private JFrame ikkuna;
    private Pelilauta lauta;
    private Vaikeus vaikeus;

    public Kayttoliittyma() {
        vaikeus = Vaikeus.NORMAALI;
    }

    @Override
    public void run() {
        ikkuna = new JFrame("Mineswooper");
        
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.setLocationRelativeTo(null);

        luoKomponentit(ikkuna.getContentPane());

        ikkuna.pack();
        ikkuna.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        this.lauta = new Pelilauta(sivu, vaikeus, ikkuna);
        this.lauta.addMouseListener(new HiiriAdapteri(this.lauta, sivu));
        
        ikkuna.setJMenuBar(new MenuPalkki(this.lauta));
        ikkuna.add(this.lauta);
    }

    public JFrame getFrame() {
        return ikkuna;
    }
}
