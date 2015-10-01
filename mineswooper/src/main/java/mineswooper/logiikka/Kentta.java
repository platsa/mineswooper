package mineswooper.logiikka;

import java.util.Random;

/**
 * Luokka hallitsee miinaharava-pelin miinakenttää.
 * 
 */
public class Kentta {
    private Ruutu[][] ruudukko;
    private Vaikeus vaikeus;
    
    /**
     * Konstruktori luo uuden miinaharava-pelin miinakentän.
     * 
     * @param vaikeus pelin vaikeustaso
     */
    public Kentta(Vaikeus vaikeus) {
        this.vaikeus = vaikeus;
        this.ruudukko = new Ruutu[vaikeus.getLeveys()][vaikeus.getKorkeus()];
        for (int j = 0; j < vaikeus.getKorkeus(); j++) {
            for (int i = 0; i < vaikeus.getLeveys(); i++) {
                ruudukko[i][j] = new Ruutu();
            }
        }
        asetaMiinat();
        merkitseNumerot();
    }
    
    /**
     * Yksityiden metodi joka arpoo ja asettaa miinat kentälle.
     */
    private void asetaMiinat() {
        Random random = new Random();
        int miinat = vaikeus.getMiinat();
        while (miinat > 0) {
            int x = random.nextInt(vaikeus.getLeveys());
            int y = random.nextInt(vaikeus.getKorkeus());
            if (!ruudukko[x][y].onkoMiinaa()) {
                ruudukko[x][y].asetaMiina();
                miinat--;
            }
        }
    }
    
    /**
     * Yksityinen metodi joka merkitsee kentän ruutuihin lähiruuduissa olevien
     * miinojen määrän, jos ruudussa itsessään ei ole miinaa.
     */
    private void merkitseNumerot() {
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                int miinat = 0;
                if (!ruudukko[i][j].onkoMiinaa()) {
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if (i + k >= 0 && i + k < vaikeus.getLeveys() && j + l >= 0 && j + l < vaikeus.getKorkeus()) {
                                if (ruudukko[i + k][j + l].onkoMiinaa()) {
                                    miinat++;
                                }
                            }
                        }
                    }
                }
                ruudukko[i][j].setLkm(miinat);
            }
        }
    }
    
    /**
     * Palauttaa tiedon onko kysytyssä ruudussa miinaa.
     * @param x ruudun x-koordinaatti 
     * @param y ruudun y-koordinaatti
     * @return onko miinaa
     */
    public boolean onkoMiinaa(int x, int y) {
        return ruudukko[x][y].onkoMiinaa();
    }
    
    /**
     * Jos kysytyssä ruudussa ei ole miinaa, palauttaa lähiruuduissa olevien
     * miinojen lukumäärän.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @return -1 jos ruudussa on miina, muuten lahiruutujen miinojen määrä
     */
    public int montaLahistolla(int x, int y) {
        return ruudukko[x][y].getLkm();
    }
    
    /**
     * Metodi palauttaa tiedon onko kysytty ruutu jo avattu.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @return onko ruutu avattu
     */
    public boolean onkoAvattu(int x, int y) {
        return ruudukko[x][y].onkoAvattu();
    }
    
    /**
     * Metodi muuttaa ruudun merkityksi jos se ei sitä vielä ole. Jos ruutu on
     * merkitty, muuttaa ruudun merkitsemättömäksi.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void merkkaus(int x, int y) {
        if (!ruudukko[x][y].onkoMerkitty()) {
            ruudukko[x][y].merkitse();
        } else {
            ruudukko[x][y].merkkiPois();
        }
    }
    
    /**
     * Palauttaa tiedon onko ruutu merkitty vai ei.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @return onko merkitty
     */
    public boolean onkoMerkitty(int x, int y) {
        return ruudukko[x][y].onkoMerkitty();
    }
    
    /**
     * Laukaisee ruudun miinan.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void laukaise(int x, int y) {
        ruudukko[x][y].laukaise();
    }
    
    /**
     * Jos ruutua ei ole vielä avattu, avaa sen. Jos ruudussa tai sen lähiruuduissa
     * ei ole miinoja, avaa myös viereiset ruudut.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void avaaRuutu(int x, int y) {
        if (!ruudukko[x][y].onkoAvattu()) {
            ruudukko[x][y].avaa();
            if (ruudukko[x][y].getLkm() == 0 && !ruudukko[x][y].onkoMiinaa()) {
                avaaViereiset(x, y);
            }
        }
    }
    
    /**
     * Yksityinen metodi joka käy läpi ruudun vierustoverit ja tarvittaessa 
     * avaa ne.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    private void avaaViereiset(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x + i >= 0 && x + i < vaikeus.getLeveys() && y + j >= 0 && y + j < vaikeus.getKorkeus()) {
                    if (!ruudukko[x + i][y + j].onkoAvattu() && !ruudukko[x + i][y + j].onkoMerkitty()) {
                        avaaRuutu(x + i, y + j);
                    }
                }
            }
        }
    }
    
    /**
     * Palauttaa tiedon mikä ruutu käyttäjälle pitäisi olla näkyvissä.
     * Palauttaa 0-8 jos lähiruutujen miinojen lkm.
     * Palauttaa 9 jos avaamaton ruutu.
     * Palauttaa 10 jos avaamaton ja merkattu ruutu.
     * Palauttaa 11 jos miina.
     * Palauttaa 12 jos laukaistu miina.
     * Palauttaa 13 jos väärin merkattu miina
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @param peliLoppunut onko peli loppunut
     * @param peliVoitettu onko peli voitettu
     * @return mikä ruutu pitäisi olla näkyvissä
     */
    public int mikaRuutu(int x, int y, boolean peliLoppunut, boolean peliVoitettu) {
        if (!ruudukko[x][y].onkoAvattu()) {
            if (ruudukko[x][y].onkoMerkitty()) {
                if (!ruudukko[x][y].onkoMiinaa() && peliLoppunut) {
                    return 13;
                } else {
                    return 10;
                }
            } else if (ruudukko[x][y].onkoMiinaa() && peliLoppunut) {
                if (peliVoitettu) {
                    return 10;
                } else if (ruudukko[x][y].onkoLaukaistu()) {
                    return 12;
                } else {
                    return 11;
                }
            } else {
                return 9;
            }
        } else {
            return ruudukko[x][y].getLkm();
        }
    }
    
    /**
     * Metodi siirtää tarvittaessa miinan pelin ensimmäisen klikkauksen tieltä
     * mahdollisimman lähelle vasenta yläkulmaa, samalle riville jos mahdollista.
     * Lisäksi merkitsee lähiruutujen miinojen lukumäärän uudestaan.
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     */
    public void siirraMiinaEkanKlikkauksenTielta(int x, int y) {
        boolean siirretty = false;
        int i = 0;
        int j = 0;
        ruudukko[x][y].poistaMiina();
        while (!siirretty) {
            if (!onkoMiinaa(i, j) && i != x && j != y) {
                ruudukko[i][j].asetaMiina();
                merkitseNumerot();
                siirretty = true;
            }
            if (i < vaikeus.getLeveys() - 1) {
                i++;
            } else {
                i = 0;
                j++;
            }
        }
    }
    
    /**
     * Palauttaa tiedon onko avaamattomia ja miinattomia ruutuja jäljellä, eli
     * ovatko pelin voittoedellytykset täyttyneet.
     * @return onko ruutuja
     */
    public boolean onkoAvaamattomiaMiinattomiaRuutuja() {
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                if (!ruudukko[i][j].onkoMiinaa() && !ruudukko[i][j].onkoAvattu()) {
                    return true;
                }
            }
        }
        return false;
    }
}
