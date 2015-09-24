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
    private boolean peliLoppunut;
    private boolean peliVoitettu;
    
    public Peli(Vaikeus vaikeus) {
        this.vaikeus = vaikeus;
        this.kentta = new Kentta(vaikeus);
        this.aika = new Ajanotto();
        this.peliLoppunut = false;
        this.peliVoitettu = false;
    }
    
    public void vasenKlikkaus(int x, int y) {
        if (x < vaikeus.getLeveys() && y < vaikeus.getKorkeus()) {
            if (!aika.aikaKaynnistetty()) {
                if (kentta.onkoMiinaa(x, y)) {
                    kentta.siirraMiinaEkanKlikkauksenTielta(x, y);
                }
                aika.aloita();
            }
        
            if (kentta.onkoMiinaa(x, y) && !peliLoppunut && !kentta.onkoMerkitty(x, y)) {
                kentta.laukaise(x, y);
                peliPaattyy();
            } else if (!kentta.onkoMerkitty(x, y)) {
                if (!peliLoppunut) {
                    kentta.avaaRuutu(x, y);
                    voittikoPelin();
                }
            }
        }
    }
    
    public void oikeaKlikkaus(int x, int y) {
        if (x < vaikeus.getLeveys() && y < vaikeus.getKorkeus()) {
            if (!kentta.onkoAvattu(x, y) && !peliLoppunut) {
                kentta.merkkaus(x, y);
            }
        }
    }
    
    public void voittikoPelin() {
        if (!kentta.onkoAvaamattomiaMiinattomiaRuutuja()) {
            peliVoitettu = true;
            peliPaattyy();
        }
    }
    
    public void peliPaattyy() {
        peliLoppunut = true;
        aika.lopeta();
    }
    
    public boolean onkoPeliLoppunut() {
        return peliLoppunut;
    }
    
    public boolean onkoPeliVoitettu() {
        return peliVoitettu;
    }
    
    public int getAika() {
        return aika.aikaaKulunut();
    }
    
    public Vaikeus getVaikeus() {
        return vaikeus;
    }
    
    public int mikaRuutu(int x, int y) {
        return kentta.mikaRuutu(x, y, peliLoppunut, peliVoitettu);
    }
}
