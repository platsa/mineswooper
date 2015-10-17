/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.logiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author pekka
 */
public class KenttaTest {
    Kentta kentta;
    Vaikeus vaikeus;
    
    @Before
    public void setUp() {
        vaikeus = Vaikeus.VAIKEA;
        kentta = new Kentta(vaikeus);
    }
    
    @Test
    public void dummy(){
        assertTrue(true);
    }
    
    @Test
    public void laskeMiinat() {
        int miinat = 0;
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                if(kentta.onkoMiinaa(i, j)) {
                    miinat++;
                }
            }
        }
        assertEquals(vaikeus.getMiinat(), miinat);
    }
    
    @Test
    public void piirraKentta() {
        for (int j = 0; j < vaikeus.getKorkeus(); j++) {
            for (int i = 0; i < vaikeus.getLeveys(); i++) {
                if (kentta.onkoMiinaa(i, j)) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }
    
    @Test
    public void piirraNumerot() {
        for (int j = 0; j < vaikeus.getKorkeus(); j++) {
            for (int i = 0; i < vaikeus.getLeveys(); i++) {
                if (kentta.onkoMiinaa(i, j)) {
                    System.out.print("*");
                } else {
                    System.out.print(kentta.montaLahistolla(i, j));
                }
            }
            System.out.println("");
        }
    }
    
    @Test
    public void testataanNumeroa() {
        int x = 6;
        int y = 7;
        if (kentta.onkoMiinaa(x, y)) {
            assertEquals(0, kentta.montaLahistolla(x, y));
        } else {
            int miinat = 0;
            for (int k = -1; k < 2; k++) {
                for (int l = -1; l < 2; l++) {
                    if (x + k >= 0 && x + k < vaikeus.getLeveys() && y + l >= 0 && y + l < vaikeus.getKorkeus()) {
                        if (kentta.onkoMiinaa(x + k, y + l)) {
                            miinat++;
                        }
                    }
                }
            }
            assertEquals(miinat, kentta.montaLahistolla(x, y));
        }
    }
    
    @Test
    public void toimiikoAvaus() {
        int x = 3;
        int y = 7;
        kentta.avaaRuutu(x, y);
        assertEquals(true, kentta.onkoAvattu(x, y));
    }
    
    @Test
    public void toimiikoMerkkaus() {
        int x = 3;
        int y = 7;
        kentta.merkkaus(x, y);
        assertEquals(true, kentta.onkoMerkitty(x, y));
        kentta.merkkaus(x, y);
        assertEquals(false, kentta.onkoMerkitty(x, y));
    }
    
    @Test
    public void siirtoEkanKlikkauksenTielta() {
        boolean siirretty = false;
        for (int i = 1; i < vaikeus.getLeveys(); i++) {
            for (int j = 1; j < vaikeus.getKorkeus(); j++) {
                if (kentta.onkoMiinaa(i, j) && !siirretty) {
                    kentta.siirraMiinaEkanKlikkauksenTielta(i, j);
                    //siirretty = true;
                    assertEquals(false, kentta.onkoMiinaa(i, j));
                }
            }
        }
        assertEquals(true, kentta.onkoMiinaa(0, 0));
        
        int miinoja = 0;
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                if (kentta.onkoMiinaa(i, j)) {
                    miinoja++;
                }
            }
        }
        assertEquals(vaikeus.getMiinat(), miinoja);
    }
    
    @Test
    public void onkohanAvaamattomiaMiinattomiaRuutuja() {
        assertEquals(true, kentta.onkoAvaamattomiaMiinattomiaRuutuja());
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                kentta.avaaRuutu(i, j);
            }
        }
        assertEquals(false, kentta.onkoAvaamattomiaMiinattomiaRuutuja());
    }
    
    @Test
    public void avaakoViereiset() {
        int x = -1;
        int y = -1;
        for (int i = 0; i < vaikeus.getLeveys(); i++) {
            for (int j = 0; j < vaikeus.getKorkeus(); j++) {
                if (kentta.montaLahistolla(i, j) == 0 && !kentta.onkoMiinaa(i, j)) {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x != -1) {
                break;
            }
        }
        kentta.avaaRuutu(x, y);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x + i >= 0 && y + j >= 0 && x + i < vaikeus.getLeveys() && y + j < vaikeus.getKorkeus()) {
                    assertTrue(kentta.onkoAvattu(x + i, y + j));
                }
            }
        }
    }
    
}
