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
    private PelinHaltija haltija;
    
    public void asetaAika() {
        if (haltija != null) {
            setText("Aika: " + haltija.getAika() + " ");
        }
    }
    
    public void asetaPelinHaltija(PelinHaltija haltija) {
        this.haltija = haltija;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        asetaAika();
    }
}
