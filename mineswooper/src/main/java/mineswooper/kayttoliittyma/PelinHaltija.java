package mineswooper.kayttoliittyma;

import java.awt.Dimension;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mineswooper.logiikka.Peli;
import mineswooper.logiikka.Vaikeus;

/**
 * Käyttöliittymän sovelluslogiikkaan yhteydessä oleva osa.
 * 
 */
public class PelinHaltija {
    private Pelilauta lauta;
    private Peli peli;
    private MiinanLaskija miinoja;
    private AjanLaskija aika;
    private JFrame ikkuna;
    private int sivu;
    private boolean peliPaattynyt;
    
    
    /**
     * Konstruktori luo uuden PelinHaltijan ja kertoo olemassaolostaan
     * muille käyttöliittymäluokille.
     * @param lauta pelilauta
     * @param miinoja miinanlaskija
     * @param aika ajanlaskija
     * @param ikkuna jframe
     * @param sivu ruudun sivun pituus
     * @param vaikeus vaikeustaso
     */
    public PelinHaltija(Pelilauta lauta, MiinanLaskija miinoja, AjanLaskija aika, JFrame ikkuna, int sivu, Vaikeus vaikeus) {
        this.lauta = lauta;
        this.miinoja = miinoja;
        this.aika = aika;
        this.ikkuna = ikkuna;
        this.sivu = sivu;
        
        this.miinoja.asetaPelinHaltija(this);
        this.aika.asetaPelinHaltija(this);
        this.lauta.setPelinHaltija(this);
        
        uusiPeli(vaikeus);
    }
    
    
    /**
     * Luo uuden miinaharava-pelin ja vaikeustason perusteella säätää ikkunan
     * koon.
     * @param vaikeus vaikeustaso
     */
    public void uusiPeli(Vaikeus vaikeus) {
        this.peliPaattynyt = false;
        this.peli = new Peli(vaikeus);
        miinoja.asetaTeksti();
        paivitaIkkunanKoko();
    }
    
    /**
     * Yksityinen metodi joka päivittää ikkunan koon vastaamaan uutta peliä.
     */
    private void paivitaIkkunanKoko() {
        lauta.setPreferredSize(new Dimension(getVaikeus().getLeveys() * sivu, getVaikeus().getKorkeus() * sivu));
        lauta.repaint();
        ikkuna.getContentPane().setPreferredSize(new Dimension(
                lauta.getPreferredSize().width, lauta.getPreferredSize().height + 20));
        ikkuna.pack();
    }
    
    /**
     * Käsittelee pelilaudan alueella tapahtuvat hiiren vasemman
     * painikkeen klikkaukset.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void vasenKlikkaus(int x, int y) {
        peli.vasenKlikkaus(x, y);
        lauta.repaint();
        loppuikoPeli();
        
    }
    
    /**
     * Käsittelee pelilaudan alueella tapahtuvat hiiren rullan
     * klikkaukset.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void rullanKlikkaus(int x, int y) {
        peli.rullanKlikkaus(x, y);
        lauta.repaint();
        loppuikoPeli();
    }
    
    /**
     * Käsittelee pelilaudan alueella tapahtuvat hiiren oikean
     * painikkeen klikkaukset.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void oikeaKlikkaus(int x, int y) {
        peli.oikeaKlikkaus(x, y);
        miinoja.asetaTeksti();
        lauta.repaint();
    }
    
    /**
     * Tarkistaa onko peli loppunut.
     */
    private void loppuikoPeli() {
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
     * Pelaajan voittaessa näyttää asian ilmoittavan dialogin.
     */
    private void voititPelin() {
        if (!peliPaattynyt) {
            DecimalFormat pyoristys = new DecimalFormat("#.00");
            pyoristys.setRoundingMode(RoundingMode.HALF_UP);
            JOptionPane.showMessageDialog(ikkuna, "Voitit pelin! Aikasi " + pyoristys.format(peli.getAikaTarkka()) + " sekuntia");
            peliPaattynyt = true;
        }
    }
    
    /**
     * Pelaajan hävitessä näyttää asian ilmoittavan dialogin.
     */
    private void havisitPelin() {
        if (!peliPaattynyt) {
            JOptionPane.showMessageDialog(ikkuna, "Hävisit pelin!");
            peliPaattynyt = true;
        }
    }
    
    public Vaikeus getVaikeus() {
        return peli.getVaikeus();
    }
    
    public int mikaRuutu(int x, int y) {
        return peli.mikaRuutu(x, y);
    }
    
    public int getMiinoja() {
        return peli.getMiinoja();
    }
    
    public int getAika() {
        return peli.getAika();
    }
}
