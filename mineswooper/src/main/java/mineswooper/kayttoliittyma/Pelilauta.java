package mineswooper.kayttoliittyma;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mineswooper.logiikka.Peli;
import mineswooper.logiikka.Vaikeus;

/**
 * Luokka luo uuden miinaharava-pelin ja piirtää sen perusteella pelilaudan.
 * 
 */
public class Pelilauta extends JPanel {
    private int sivu;
    private Image[] kuvat;
    private Vaikeus vaikeus;
    private Peli peli;
    private JFrame ikkuna;
    private MiinanLaskija miinoja;
    private AjanLaskija aika;
    
    /**
     * Konstruktori luo uuden pelilaudan ja sille miinaharava-pelin.
     * @param sivu ruudun sivun pituus
     * @param vaikeus vaikeustaso
     * @param ikkuna käyttöliittymäikkuna
     */
    public Pelilauta(int sivu, Vaikeus vaikeus, JFrame ikkuna, MiinanLaskija miinoja, AjanLaskija aika) {
        this.sivu = sivu;
        this.kuvat = new Image[14];
        this.ikkuna = ikkuna;
        this.miinoja = miinoja;
        this.aika = aika;
        lisaaKuvat();
        
        setDoubleBuffered(true);
        
        uusiPeli(vaikeus);
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
    
    /**
     * Luo uuden miinaharava-pelin ja vaikeustason perusteella säätää ikkunan
     * koon.
     * @param vaikeus vaikeustaso
     */
    public void uusiPeli(Vaikeus vaikeus) {
        this.vaikeus = vaikeus;
        this.peli = new Peli(vaikeus);
        miinoja.asetaPeli(this.peli);
        miinoja.asetaTeksti();
        aika.asetaPeli(peli);
        aika.asetaAika();
        setPreferredSize(new Dimension(vaikeus.getLeveys() * sivu, vaikeus.getKorkeus() * sivu));
        repaint();
        ikkuna.getContentPane().setPreferredSize(new Dimension(
                getPreferredSize().width, getPreferredSize().height + 20));
        ikkuna.pack();
    }
    
    /**
     * Käsittelee käyttöliittymäkomponentin alueella tapahtuvat hiiren vasemman
     * painikkeen klikkaukset.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void vasenKlikkaus(int x, int y) {
        peli.vasenKlikkaus(x, y);
        repaint();
        if (peli.onkoPeliLoppunut()) {
            if (peli.onkoPeliVoitettu()) {
                miinoja.asetaTeksti();
                voititPelin();
            } else {
                havisitPelin();
            }
        }
    }
    
    /**
     * Käsittelee käyttöliittymäkomponentin alueella tapahtuvat hiiren oikean
     * painikkeen klikkaukset.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void oikeaKlikkaus(int x, int y) {
        peli.oikeaKlikkaus(x, y);
        miinoja.asetaTeksti();
        repaint();
    }
    
    /**
     * Piirtää miinakentän kuvista.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        for (int j = 0; j < vaikeus.getKorkeus(); j++) {
            for (int i = 0; i < vaikeus.getLeveys(); i++) {
                g.drawImage(kuvat[peli.mikaRuutu(i, j)], i * sivu, j * sivu, this);
            }
        }
    }
    
    /**
     * Pelaajan voittaessa näyttää asian ilmoittavan dialogin.
     */
    private void voititPelin() {
        JOptionPane.showMessageDialog(ikkuna, "Voitit pelin! Aikasi " + peli.getAika() + " sekuntia");
    }
    
    /**
     * Pelaajan hävitessä näyttää asian ilmoittavan dialogin.
     */
    private void havisitPelin() {
        JOptionPane.showMessageDialog(ikkuna, "Hävisit pelin!");
    }
    
}
