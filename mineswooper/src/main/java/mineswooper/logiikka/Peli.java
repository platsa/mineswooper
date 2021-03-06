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
        if (onkoRuudukossa(x, y)) {
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
    
    /**
     * Metodi toimii kun käyttäjä yrittää avata ruudun ympärillä olevia
     * merkkaamattomia ruutuja. Jos ruutu on avattu ja ympärillä on
     * oikea määrä merkittyjä ruutuja, avaa ympäröivät ruudut.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void rullanKlikkaus(int x, int y) {
        int montaMerkitty = 0;
        if (onkoRuudukossa(x, y) && kentta.onkoAvattu(x, y) && !onkoPeliLoppunut()) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (onkoRuudukossa(x + i, y + j)) {
                        if (kentta.onkoMerkitty(x + i, y + j)) {
                            montaMerkitty++;
                        }
                    }
                }
            }
            if (montaMerkitty == kentta.montaLahistolla(x, y)) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (onkoRuudukossa(x + i, y + j)) {
                            avaaRuutu(x + i, y + j);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Yksityinen metodi joka avaa ruudun pelin loppumisesta huolimatta.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    private void avaaRuutu(int x, int y) {
        if (!peliLoppunut) {
            vasenKlikkaus(x, y);
        } else if (kentta.onkoMiinaa(x, y)) {
            kentta.laukaise(x, y);
        } else if (!kentta.onkoMerkitty(x, y)) {
            kentta.avaaRuutu(x, y);
        }
    }
    
    /**
     * Metodi toimii kun käyttäjä yrittää merkitä ruutua. Jos peli on käynnissä
     * eikä ruutua ole avattu, suorittaa merkkauksen.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void oikeaKlikkaus(int x, int y) {
        if (onkoRuudukossa(x, y) && !kentta.onkoAvattu(x, y) && !peliLoppunut) {
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
     * Metodi tarkastaa ovatko annetut koordinaatit pelialueella.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    private boolean onkoRuudukossa(int x, int y) {
        return x >= 0 && y >= 0 && x < vaikeus.getLeveys() && y < vaikeus.getKorkeus();
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
     * Palauttaa 0 jos koordinaatit eivät ruudukossa.
     * Palauttaa 0-8 jos lähiruutujen miinojen lkm.
     * Palauttaa 9 jos avaamaton ruutu.
     * Palauttaa 10 jos avaamaton ja merkattu ruutu.
     * Palauttaa 11 jos miina.
     * Palauttaa 12 jos laukaistu miina.
     * Palauttaa 13 jos väärin merkattu miina
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @return ruudun numero
     */
    public int mikaRuutu(int x, int y) {
        if (onkoRuudukossa(x, y)) {
            return kentta.mikaRuutu(x, y, peliLoppunut, peliVoitettu);
        } else {
            return 0;
        }
    }
    
    /**
     * Palauttaa merkkaamattomien miinojen määrän.
     * @return merkkaamattomat miinat
     */
    public int getMiinoja() {
        if (peliVoitettu) {
            return 0;
        } else {
            return miinoja;
        }
    }
    
    /**
     * Palauttaa pelin kentän.
     * @return kentta
     */
    public Kentta getKentta() {
        return kentta;
    }
    
    /**
     * Palauttaa pelin ajanoton.
     * @return 
     */
    public Ajanotto getAjanotto() {
        return aika;
    }
}
