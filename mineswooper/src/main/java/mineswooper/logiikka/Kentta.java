/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.logiikka;

import java.util.Random;

/**
 *
 * @author pekka
 */
public class Kentta {
    private Ruutu[][] ruudukko;
    private Vaikeus vaikeus;
    
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
    
    public boolean onkoMiinaa(int x, int y) {
        return ruudukko[x][y].onkoMiinaa();
    }
    
    public int montaLahistolla(int x, int y) {
        if (!onkoMiinaa(x, y)) {
            return ruudukko[x][y].getLkm();
        } else {
            return -1;
        }
    }
    
    public boolean onkoAvattu(int x, int y) {
        return ruudukko[x][y].onkoAvattu();
    }
    
    public void merkkaus(int x, int y) {
        if (!ruudukko[x][y].onkoMerkitty()) {
            ruudukko[x][y].merkitse();
        } else {
            ruudukko[x][y].merkkiPois();
        }
    }
    
    public boolean onkoMerkitty(int x, int y) {
        return ruudukko[x][y].onkoMerkitty();
    }
    
    public void laukaise(int x, int y) {
        ruudukko[x][y].laukaise();
    }
    
    public void avaaRuutu(int x, int y) {
        if (!ruudukko[x][y].onkoAvattu()) {
            ruudukko[x][y].avaa();
            if (ruudukko[x][y].getLkm() == 0 && !ruudukko[x][y].onkoMiinaa()) {
                avaaViereiset(x, y);
            }
        }
    }
    
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
