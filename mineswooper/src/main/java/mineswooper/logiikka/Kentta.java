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
}
