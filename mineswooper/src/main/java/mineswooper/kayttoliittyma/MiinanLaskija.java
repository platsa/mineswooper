package mineswooper.kayttoliittyma;

import javax.swing.JLabel;

/**
 * Näyttää käyttäjälle merkkaamattomien miinojen määrän
 * 
 */
public class MiinanLaskija extends JLabel {
    private PelinHaltija haltija;
    
    /**
     * Päivittää merkkaamattomien miinojen määrän käyttäjän näkyville.
     */
    public void asetaTeksti() {
        if (haltija != null) {
            setText(" Miinoja: " + haltija.getMiinoja());
        }
    }
    
    /**
     * Asettaa PelinHaltijan.
     * @param haltija pelinhaltija
     */
    public void asetaPelinHaltija(PelinHaltija haltija) {
        this.haltija = haltija;
    }
}
