package mineswooper.main;

import javax.swing.SwingUtilities;
import mineswooper.kayttoliittyma.Kayttoliittyma;

/**
 * Main-luokka.
 */
public class Main {

    /**
     * Ohjelman käynnistävä metodi joka luo uuden käyttöliittymän.
     * @param args String
     */
    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
