package mineswooper.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import mineswooper.logiikka.Peli;

/**
 * Näyttää käyttäjälle kuluneen ajan
 * 
 */
public class AjanLaskija extends JLabel implements ActionListener {
    private Peli peli;
    
    public void asetaAika() {
        if (peli != null) {
            setText("Aika: " + peli.getAika() + " ");
        }
    }
    
    public void asetaPeli(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        asetaAika();
    }
}
