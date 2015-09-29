package mineswooper.kayttoliittyma;

import javax.swing.JLabel;
import mineswooper.logiikka.Peli;

/**
 * Näyttää käyttäjälle merkkaamattomien miinojen määrän
 * 
 */
public class MiinanLaskija extends JLabel {
    private Peli peli;
    
    public void asetaTeksti() {
        if (peli != null) {
            setText(" Miinoja: " + peli.getMiinoja());
        }
    }
    
    public void asetaPeli(Peli peli) {
        this.peli = peli;
    }
}
