/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.logiikka;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author pekka
 */
public class PeliTest {
    Peli peli;
    Vaikeus vaikeus;
    
    @Before
    public void setUp() {
        this.vaikeus = Vaikeus.VAIKEA;
        this.peli = new Peli(vaikeus);
    }
    
    @Test
    public void dummy(){
        assertTrue(true);
    }
    
    @Test
    public void aikaKaynnistyy() {
        peli.vasenKlikkaus(6, 7);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AjanottoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(1, peli.getAika());
        assertEquals(1, peli.getAikaTarkka(), 0.1);
    }
    
    @Test
    public void klikkaamallaMiinaaHavitaan() {
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                peli.vasenKlikkaus(i, j);
            }
        }
        assertEquals(true, peli.onkoPeliLoppunut());
        assertEquals(false, peli.onkoPeliVoitettu());
    }
    
    @Test
    public void merkattuaEiVoiAvata() {
        peli.oikeaKlikkaus(6, 7);
        peli.vasenKlikkaus(6, 7);
        assertEquals(10, peli.mikaRuutu(6, 7));
    }
    
    public void havitessaMerkkaamattomatMiinatAvataan() {
        int miinat = 0;
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                peli.vasenKlikkaus(i, j);
            }
        }
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                if (peli.mikaRuutu(i, j) == 11 || peli.mikaRuutu(i, j) == 12) {
                    miinat++;
                }
            }
        }
        assertEquals(vaikeus.getMiinat(), miinat);
    }
    
    @Test
    public void merkkiPois() {
        peli.oikeaKlikkaus(6, 7);
        peli.oikeaKlikkaus(6, 7);
        assertEquals(9, peli.mikaRuutu(6, 7));
    }
    
    @Test
    public void miinoja() {
        assertEquals(vaikeus.getMiinat(), peli.getMiinoja());
    }
}
