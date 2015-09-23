package mineswooper.main;

import javax.swing.SwingUtilities;
import mineswooper.kayttoliittyma.Kayttoliittyma;

public class Main {

    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
