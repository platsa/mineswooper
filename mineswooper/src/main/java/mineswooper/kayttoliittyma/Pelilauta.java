/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.kayttoliittyma;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mineswooper.logiikka.Peli;
import mineswooper.logiikka.Vaikeus;

/**
 *
 * @author pekka
 */
public class Pelilauta extends JPanel {
    private int sivu;
    private Image[] kuvat;
    private Vaikeus vaikeus;
    private Peli peli;
    private JFrame ikkuna;
    
    public Pelilauta(int sivu, Vaikeus vaikeus, JFrame ikkuna) {
        this.sivu = sivu;
        this.kuvat = new Image[13];
        this.ikkuna = ikkuna;
        lisaaKuvat();
        
        setDoubleBuffered(true);
        
        uusiPeli(vaikeus);
    }
    
    private void lisaaKuvat() {
        this.kuvat[0] = (new ImageIcon("resurssit/kuvat/tyhja.jpg")).getImage();
        for(int i = 1; i < 9; i++) {
            this.kuvat[i] = (new ImageIcon("resurssit/kuvat/" + i + ".jpg")).getImage();
        }
        this.kuvat[9] = (new ImageIcon("resurssit/kuvat/avaamaton.jpg")).getImage();
        this.kuvat[10] = (new ImageIcon("resurssit/kuvat/merkitty.jpg")).getImage();
        this.kuvat[11] = (new ImageIcon("resurssit/kuvat/miina.jpg")).getImage();
        this.kuvat[12] = (new ImageIcon("resurssit/kuvat/miinaLauennut.jpg")).getImage();
    }
    
    public void uusiPeli(Vaikeus vaikeus) {
        this.vaikeus = vaikeus;
        this.peli = new Peli(vaikeus);
        ikkuna.setPreferredSize(new Dimension(vaikeus.getLeveys() * sivu, vaikeus.getKorkeus() * sivu));
    }
    
    public void vasenKlikkaus(int x, int y) {
        peli.vasenKlikkaus(x, y);
        repaint();
    }
    
    public void oikeaKlikkaus(int x, int y) {
        peli.oikeaKlikkaus(x, y);
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        for(int j = 0; j < vaikeus.getKorkeus(); j++) {
            for(int i = 0; i < vaikeus.getLeveys(); i++) {
                g.drawImage(kuvat[peli.mikaRuutu(i, j)], i * sivu, j * sivu, this);
            }
        }
    }
    
}
