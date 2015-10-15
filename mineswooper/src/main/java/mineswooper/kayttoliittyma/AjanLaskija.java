package mineswooper.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 * Näyttää käyttäjälle kuluneen ajan
 * 
 */
public class AjanLaskija extends JLabel implements ActionListener {
    private PelinHaltija haltija;
    
    /**
     * Päivittää kuluneen ajan käyttäjän näkyviin.
     */
    private void asetaAika() {
        if (haltija != null) {
            setText("Aika: " + haltija.getAika() + " ");
        }
    }
    
    /**
     * Asettaa PelinHaltijan.
     * @param haltija pelinhaltija
     */
    public void asetaPelinHaltija(PelinHaltija haltija) {
        this.haltija = haltija;
    }
    
    /**
     * Kuuntelee tapahtumia.
     * @param ae ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        asetaAika();
    }
}
