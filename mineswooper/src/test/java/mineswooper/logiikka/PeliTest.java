/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.logiikka;

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
}
