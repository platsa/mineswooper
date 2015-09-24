package mineswooper.kayttoliittyma;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;
import mineswooper.logiikka.Vaikeus;

/**
 * Runnable-rajapinnan toteuttava luokka, joka luo käyttöliittymän.
 * 
 */
public class Kayttoliittyma implements Runnable {
    private final int sivu = 16;
    private JFrame ikkuna;
    private Pelilauta lauta;
    private Vaikeus vaikeus;

    public Kayttoliittyma() {
        vaikeus = Vaikeus.NORMAALI;
    }
    
    /**
     * Luodaan käyttöliittymän ikkuna ja kutsutaan luoKomponentit-metodia.
     */
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
    
    /**
     * Luo ikkunaan käyttöliittymän komponentit.
     * @param container 
     */
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
