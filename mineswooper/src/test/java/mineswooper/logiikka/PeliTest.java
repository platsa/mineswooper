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
    
    private boolean onkoRuudukossa(int x, int y) {
        return x >= 0 && y >= 0 && x < vaikeus.getLeveys() && y < vaikeus.getKorkeus();
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
    public void miinalaskuriToimii() {
        peli.oikeaKlikkaus(6, 7);
        peli.oikeaKlikkaus(7, 7);
        assertEquals(vaikeus.getMiinat() - 2, peli.getMiinoja());
        peli.oikeaKlikkaus(6, 7);
        assertEquals(vaikeus.getMiinat() - 1, peli.getMiinoja());
    }
    
    @Test
    public void eiVoiYlimerkata() {
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                peli.oikeaKlikkaus(i, j);
            }
        }
        assertEquals(0, peli.getMiinoja());
    }
    
    @Test
    public void miinoja() {
        assertEquals(vaikeus.getMiinat(), peli.getMiinoja());
    }
    
    @Test
    public void vasenKlikkausAlueenRajoilla() {
        peli.vasenKlikkaus(-1, -1);
        peli.vasenKlikkaus(0, 0);
        assertTrue(peli.mikaRuutu(0, 0) != 9);
        peli.vasenKlikkaus(vaikeus.getLeveys(), vaikeus.getKorkeus());
        peli.vasenKlikkaus(vaikeus.getLeveys() - 1, vaikeus.getKorkeus() - 1);
        assertTrue(peli.mikaRuutu(vaikeus.getLeveys() - 1, vaikeus.getKorkeus() - 1) != 9);
    }
    
    @Test
    public void oikeaKlikkausAlueenRajoilla() {
        peli.oikeaKlikkaus(-1, -1);
        peli.oikeaKlikkaus(0, 0);
        assertTrue(peli.mikaRuutu(0, 0) == 10);
        peli.oikeaKlikkaus(vaikeus.getLeveys(), vaikeus.getKorkeus());
        peli.oikeaKlikkaus(vaikeus.getLeveys() - 1, vaikeus.getKorkeus() - 1);
        assertTrue(peli.mikaRuutu(vaikeus.getLeveys() - 1, vaikeus.getKorkeus() - 1) == 10);
    }
    
    @Test
    public void rullanKlikkausTesti() {
        int x = 0;
        int y = 0;
        peli.vasenKlikkaus(x, y);
        int montaMiinaa = peli.mikaRuutu(x, y);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (montaMiinaa > 0 && !(i == 0 && j == 0) && onkoRuudukossa(x + i, y + j)) {
                    peli.oikeaKlikkaus(x + i, y + j);
                    montaMiinaa--;
                }
            }
        }
        peli.rullanKlikkaus(x, y);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                assertTrue(peli.mikaRuutu(x + i, y + j) != 9);
            }
        }
    }
    
    @Test
    public void ekallaKlikkauksellaEiHavia() {
        int x = -1;
        int y = -1;
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                if (peli.getKentta().onkoMiinaa(i, j)) {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x != -1) {
                break;
            }
        }
        peli.vasenKlikkaus(x, y);
        assertTrue(!peli.onkoPeliLoppunut());
    }
    
    @Test
    public void laukeaakoMiina() {
        peli.vasenKlikkaus(0, 0);
        int x = -1;
        int y = -1;
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                if (peli.getKentta().onkoMiinaa(i, j)) {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x != -1) {
                break;
            }
        }
        peli.vasenKlikkaus(x, y);
        assertEquals(12, peli.mikaRuutu(x, y));
        assertTrue(peli.onkoPeliLoppunut());
        assertTrue(!peli.onkoPeliVoitettu());
    }
    
    @Test
    public void voitetaanPeli() {
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                if (!peli.getKentta().onkoMiinaa(i, j)) {
                    peli.vasenKlikkaus(i, j);
                }
            }
            
        }
        assertTrue(peli.onkoPeliLoppunut());
        assertTrue(peli.onkoPeliVoitettu());
        assertTrue(peli.getAjanotto().aikaLopetettu());
        assertEquals(0, peli.getMiinoja());
    }
}
