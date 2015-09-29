package mineswooper.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import mineswooper.logiikka.Vaikeus;

/**
 * JMenuBar-luokan perivä luokka, joka muodostaa käyttöliittymän menupalkin.
 * 
 */
public class MenuPalkki extends JMenuBar {
    private JMenu uusiPeli;
    private JMenuItem[] vaihtoehdot;
    private PelinHaltija haltija;
    
    /**
     * Luo uuden menupalkin ja lisää sille komponentit.
     * @param lauta pelilauta
     */
    public MenuPalkki(PelinHaltija haltija) {
        this.haltija = haltija;
        
        uusiPeli = new JMenu("Uusi Peli");
        this.add(uusiPeli);
        
        this.vaihtoehdot = new JMenuItem[3];
        this.vaihtoehdot[0] = new JMenuItem("Helppo");
        this.vaihtoehdot[1] = new JMenuItem("Normaali");
        this.vaihtoehdot[2] = new JMenuItem("Vaikea");
        
        lisaaKuuntelijat();
        
        for (JMenuItem vaihtoehto : vaihtoehdot) {
            uusiPeli.add(vaihtoehto);
        }
    }
    
    /**
     * Lisää JMenuItemeille tapahtumankuuntelijat.
     */
    private void lisaaKuuntelijat() {
        for (int i = 0; i < 3; i++) {
            final Vaikeus vaikeus = Vaikeus.values()[i];
            vaihtoehdot[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    haltija.uusiPeli(vaikeus);
                }
            });
        }
    }
    
}
