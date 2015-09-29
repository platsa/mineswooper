package mineswooper.kayttoliittyma;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Hiirenadapteri Pelilauta-luokan oliolle.
 * 
 */
public class HiiriAdapteri extends MouseAdapter {
    private PelinHaltija haltija;
    private int sivu;
    private MiinanLaskija miinoja;
    
    /**
     * Konstruktori luo uuden HiiriAdapterin.
     * @param lauta pelilauta
     * @param sivu ruudun sivun pituus
     */
    public HiiriAdapteri(PelinHaltija haltija, int sivu) {
        this.haltija = haltija;
        this.sivu = sivu;
    }
    
    /**
     * Kuuntelee hiiren vasemman ja oikean painikkeen painalluksia.
     * 
     */
    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX() / sivu;
        int y = e.getY() / sivu;
        
        
        if (e.getButton() == MouseEvent.BUTTON1) {
            haltija.vasenKlikkaus(x, y);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            haltija.oikeaKlikkaus(x, y);
        }
    }
    
}
