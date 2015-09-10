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
        for(int i = 0; i < vaikeus.getLeveys(); i++) {
            for(int j = 0; j < vaikeus.getKorkeus(); j++) {
                if(kentta.onkoMiinaa(i, j)) {
                    miinat++;
                }
            }
        }
        assertEquals(99, miinat);
    }
    
    @Test
    public void avaaRuudut() {
        for(int i = 0; i < vaikeus.getLeveys(); i++) {
            for(int j = 0; j < vaikeus.getKorkeus(); j++) {
                kentta.avaaRuutu(i, j);
            }
        }
    }
    
    @Test
    public void piirraKentta() {
        for(int i = 0; i < vaikeus.getLeveys(); i++) {
            for(int j = 0; j < vaikeus.getKorkeus(); j++) {
                if(kentta.onkoMiinaa(i, j)) {
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
        for(int i = 0; i < vaikeus.getLeveys(); i++) {
            for(int j = 0; j < vaikeus.getKorkeus(); j++) {
                if(kentta.onkoMiinaa(i, j)) {
                    System.out.print("*");
                } else {
                    System.out.print(kentta.montaLahistolla(i, j));
                }
            }
            System.out.println("");
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
    
}
