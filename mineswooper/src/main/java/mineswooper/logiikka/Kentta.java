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
        for(int j = 0; j < vaikeus.getKorkeus(); j++) {
            for(int i = 0; i < vaikeus.getLeveys(); i++) {
                ruudukko[j][i] = new Ruutu();
            }
        }
        asetaMiinat();
        merkitseNumerot();
    }
    
    private void asetaMiinat() {
        Random random = new Random();
        int miinat = vaikeus.getMiinat();
        while(miinat > 0) {
            int x = random.nextInt(vaikeus.getLeveys());
            int y = random.nextInt(vaikeus.getKorkeus());
            if(!ruudukko[x][y].onkoMiinaa()) {
                ruudukko[x][y].asetaMiina();
                miinat--;
            }
        }
    }
    
    private void merkitseNumerot() {
        for(int i = 0; i < vaikeus.getLeveys(); i++) {
            int miinat = 0;
            for(int j = 0; i < vaikeus.getKorkeus(); i++) {
                if(!ruudukko[i][j].onkoMiinaa()) {
                    for(int k = -1; k < 2; k++) {
                        for(int l = -1; l < 2; l++) {
                            if(ruudukko[i+k][j+l].onkoMiinaa()) {
                                miinat++;
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
    
    public void avaaRuutu(int x, int y) {
        if(!ruudukko[x][y].onkoAvattu()) {
            ruudukko[x][y].avaa();
            if(ruudukko[x][y].getLkm() == 0 && !ruudukko[x][y].onkoMiinaa()) {
                avaaViereiset(x, y);
            }
        }
    }
    
    private void avaaViereiset(int x, int y) {
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                if(!ruudukko[x+i][y+j].onkoAvattu()) {
                    avaaRuutu(x+i, y+j);
                }
            }
        }
    }
}
