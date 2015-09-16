/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.logiikka;

/**
 *
 * @author pekka
 */
public class Peli {
    private Vaikeus vaikeus;
    private Kentta kentta;
    private Ajanotto aika;
    
    public Peli(Vaikeus vaikeus) {
        this.vaikeus = vaikeus;
        this.kentta = new Kentta(vaikeus);
        this.aika = new Ajanotto();
    }
    
    public void vasenKlikkaus(int x, int y) {
        if(!aika.aikaKaynnistetty()) {
            if(kentta.onkoMiinaa(x, y)) {
                kentta.siirraMiinaEkanKlikkauksenTielta(x, y);
            }
            aika.aloita();
        }
        if(kentta.onkoMerkitty(x, y)) {
        } else if(kentta.onkoMiinaa(x, y)) {
            peliPaattyy();
        } else {
            kentta.avaaRuutu(x, y);
        }
    }
    
    public void oikeaKlikkaus(int x, int y) {
        if(!aika.aikaKaynnistetty()) {
            aika.aloita();
        }
        if(!kentta.onkoAvattu(x, y)) {
            kentta.merkkaus(x, y);
        }
    }
    
    public void peliPaattyy() {
        aika.lopeta();
    }
    
    public int getAika() {
        return aika.aikaaKulunut();
    }
}
