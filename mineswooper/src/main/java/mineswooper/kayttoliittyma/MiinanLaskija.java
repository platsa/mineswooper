package mineswooper.kayttoliittyma;

import javax.swing.JLabel;

/**
 * Näyttää käyttäjälle merkkaamattomien miinojen määrän
 * 
 */
public class MiinanLaskija extends JLabel {
    private PelinHaltija haltija;
    
    public void asetaTeksti() {
        if (haltija != null) {
            setText(" Miinoja: " + haltija.getMiinoja());
        }
    }
    
    public void asetaPelinHaltija(PelinHaltija haltija) {
        this.haltija = haltija;
    }
}
