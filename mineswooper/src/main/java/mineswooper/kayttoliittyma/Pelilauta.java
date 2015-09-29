package mineswooper.kayttoliittyma;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import mineswooper.logiikka.Vaikeus;

/**
 * Luokka luo uuden miinaharava-pelin ja piirtää sen perusteella pelilaudan.
 * 
 */
public class Pelilauta extends JPanel {
    private int sivu;
    private Image[] kuvat;
    private PelinHaltija haltija;
    
    /**
     * Konstruktori luo uuden pelilaudan ja sille miinaharava-pelin.
     * @param sivu ruudun sivun pituus
     * @param vaikeus vaikeustaso
     * @param ikkuna käyttöliittymäikkuna
     */
    public Pelilauta(int sivu) {
        this.sivu = sivu;
        this.kuvat = new Image[14];
        lisaaKuvat();
        
        setDoubleBuffered(true);
    }
    
    /**
     * Lukee ruudun kuvavaihtoehdot tiedostoista.
     */
    private void lisaaKuvat() {
        this.kuvat[0] = (new ImageIcon("resurssit/kuvat/tyhja.jpg")).getImage();
        for (int i = 1; i < 9; i++) {
            this.kuvat[i] = (new ImageIcon("resurssit/kuvat/" + i + ".jpg")).getImage();
        }
        this.kuvat[9] = (new ImageIcon("resurssit/kuvat/avaamaton.jpg")).getImage();
        this.kuvat[10] = (new ImageIcon("resurssit/kuvat/merkitty.jpg")).getImage();
        this.kuvat[11] = (new ImageIcon("resurssit/kuvat/miina.jpg")).getImage();
        this.kuvat[12] = (new ImageIcon("resurssit/kuvat/miinaLauennut.jpg")).getImage();
        this.kuvat[13] = (new ImageIcon("resurssit/kuvat/vaarinMerkitty.jpg")).getImage();
    }
    
    public void setPelinHaltija(PelinHaltija haltija) {
        this.haltija = haltija;
    }
    
    /**
     * Piirtää miinakentän kuvista.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        for (int j = 0; j < haltija.getVaikeus().getKorkeus(); j++) {
            for (int i = 0; i < haltija.getVaikeus().getLeveys(); i++) {
                g.drawImage(kuvat[haltija.mikaRuutu(i, j)], i * sivu, j * sivu, this);
            }
        }
    }
}
