package mineswooper.logiikka;

/**
 * Luokka kontrolloi miinaharava-pelin kulkua.
 * 
 */
public class Peli {
    private Vaikeus vaikeus;
    private Kentta kentta;
    private Ajanotto aika;
    private boolean peliLoppunut;
    private boolean peliVoitettu;
    private int miinoja;
    
    /**
     * Konstruktori luo uuden miinaharava-pelin.
     * @param vaikeus haluttu vaikeustaso
     */
    public Peli(Vaikeus vaikeus) {
        this.vaikeus = vaikeus;
        this.kentta = new Kentta(vaikeus);
        this.aika = new Ajanotto();
        this.peliLoppunut = false;
        this.peliVoitettu = false;
        this.miinoja = vaikeus.getMiinat();
    }
    
    /**
     * Metodi toimii kun käyttäjä yrittää avata ruutua. Jos peli vasta alkaa,
     * käynnistää ajanoton ja tarvittaessa siirtää miinan ensimmäisestä avatusta
     * ruudusta pois. Jos peli on käynnissä, avaa tarvittaessa ruudun ja kysyy
     * ovatko pelin lopetusehdot täyttyneet. Jos peli on jo loppunut, metodi ei
     * tee mitään.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void vasenKlikkaus(int x, int y) {
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
    
    /**
     * Metodi toimii kun käyttäjä yrittää avata ruudun ympärillä olevia
     * merkkaamattomia ruutuja. Jos ruutu on avattu ja ympärillä on
     * oikea määrä merkittyjä ruutuja, avaa ympäröivät ruudut.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void rullanKlikkaus(int x, int y) {
        int montaMerkitty = 0;
        if (kentta.onkoAvattu(x, y)) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (x + i >= 0 && x + i < vaikeus.getLeveys() && y + j >= 0 && y + j < vaikeus.getKorkeus()) {
                        if (kentta.onkoMerkitty(x + i, y + j)) {
                            montaMerkitty++;
                        }
                    }
                }
            }
            if (montaMerkitty == kentta.montaLahistolla(x, y)) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (x + i >= 0 && x + i < vaikeus.getLeveys() && y + j >= 0 && y + j < vaikeus.getKorkeus()) {
                            vasenKlikkaus(x + i, y + j);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Metodi toimii kun käyttäjä yrittää merkitä ruutua. Jos peli on käynnissä
     * eikä ruutua ole avattu, suorittaa merkkauksen.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void oikeaKlikkaus(int x, int y) {
        if (!kentta.onkoAvattu(x, y) && !peliLoppunut) {
            if (!kentta.onkoMerkitty(x, y) && miinoja > 0) {
                kentta.merkkaus(x, y);
                miinoja--;
            } else if (kentta.onkoMerkitty(x, y)) {
                kentta.merkkaus(x, y);
                miinoja++;
            }
        }
    }
    
    /**
     * Yksityinen metodi joka tarkistaa onko pelaaja voittanut pelin.
     */
    private void voittikoPelin() {
        if (!kentta.onkoAvaamattomiaMiinattomiaRuutuja()) {
            peliVoitettu = true;
            peliPaattyy();
        }
    }
    
    /**
     * Yksityinen metodi joka lopettaa pelin ja pysäyttää ajanoton.
     */
    private void peliPaattyy() {
        peliLoppunut = true;
        aika.lopeta();
    }
    
    /**
     * Kertoo onko peli loppunut
     * @return onko loppunut
     */
    public boolean onkoPeliLoppunut() {
        return peliLoppunut;
    }
    
    /**
     * Kertoo onko pelaaja voittanut pelin.
     * @return onko voitettu
     */
    public boolean onkoPeliVoitettu() {
        return peliVoitettu;
    }
    
    /**
     * Palauttaa ajanoton tilanteen.
     * @return aika sekunteina
     */
    public int getAika() {
        return aika.aikaaKulunut();
    }
    
    /**
     * Palauttaa ajanoton tilanteen.
     * @return aika sekunteina sadasosan tarkkuudella
     */
    public double getAikaTarkka() {
        return aika.aikaaKulunutTarkka();
    }
    
    /**
     * Palauttaa pelin vaikeustason.
     * @return vaikeustaso
     */
    public Vaikeus getVaikeus() {
        return vaikeus;
    }
    
    /**
     * Kertoo mikä ruutu käyttäjälle pitäisi olla näkyvissä.
     * Palauttaa 0-8 jos lähiruutujen miinojen lkm.
     * Palauttaa 9 jos avaamaton ruutu.
     * Palauttaa 10 jos avaamaton ja merkattu ruutu.
     * Palauttaa 11 jos miina.
     * Palauttaa 12 jos laukaistu miina.
     * Palauttaa 13 jos väärin merkattu miina
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @return 
     */
    public int mikaRuutu(int x, int y) {
        return kentta.mikaRuutu(x, y, peliLoppunut, peliVoitettu);
    }
    
    public int getMiinoja() {
        if (peliVoitettu) {
            return 0;
        } else {
            return miinoja;
        }
    }
}
