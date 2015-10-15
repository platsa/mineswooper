package mineswooper.kayttoliittyma;


import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import mineswooper.logiikka.Vaikeus;

/**
 * Runnable-rajapinnan toteuttava luokka, joka luo käyttöliittymän.
 * 
 */
public class Kayttoliittyma implements Runnable {
    private final int sivu = 16;
    private JFrame ikkuna;
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
        container.setLayout(new BorderLayout());
        
        MiinanLaskija miinoja = new MiinanLaskija();
        AjanLaskija aika = new AjanLaskija();
        Timer paivittaja = new Timer(100, aika);
        Pelilauta lauta = new Pelilauta(sivu);
        PelinHaltija haltija = new PelinHaltija(lauta, miinoja, aika, ikkuna, sivu, vaikeus);
        lauta.addMouseListener(new HiiriAdapteri(haltija, sivu));
        
        ikkuna.setJMenuBar(new MenuPalkki(haltija));
        container.add(miinoja, BorderLayout.WEST);
        container.add(aika, BorderLayout.EAST);
        container.add(lauta, BorderLayout.SOUTH);
        
        paivittaja.start();
    }    
}
