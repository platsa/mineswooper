package mineswooper.kayttoliittyma;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Hiirenadapteri Pelilauta-luokan oliolle.
 * 
 */
public class HiiriAdapteri extends MouseAdapter {
    private Pelilauta lauta;
    private int sivu;
    private MiinanLaskija miinoja;
    
    /**
     * Konstruktori luo uuden HiiriAdapterin.
     * @param lauta pelilauta
     * @param sivu ruudun sivun pituus
     */
    public HiiriAdapteri(Pelilauta lauta, int sivu) {
        this.lauta = lauta;
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
            lauta.vasenKlikkaus(x, y);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            lauta.oikeaKlikkaus(x, y);
        }
    }
    
}
