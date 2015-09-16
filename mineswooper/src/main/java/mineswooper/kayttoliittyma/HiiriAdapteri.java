/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.kayttoliittyma;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author pekka
 */
public class HiiriAdapteri extends MouseAdapter {
    private Pelilauta lauta;
    private int sivu;
    
    public HiiriAdapteri(Pelilauta lauta, int sivu) {
        this.lauta = lauta;
        this.sivu = sivu;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX() / sivu;
        int y = e.getY() / sivu;
        
        
        if(e.getButton() == MouseEvent.BUTTON1) {
            lauta.vasenKlikkaus(x, y);
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            lauta.oikeaKlikkaus(x, y);
        }
    }
    
}
