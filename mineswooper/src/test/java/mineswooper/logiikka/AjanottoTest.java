/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.logiikka;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author pekka
 */
public class AjanottoTest {
    Ajanotto aika;
    
    @Before
    public void setUp() {
        aika = new Ajanotto();
    }
    
    @Test
    public void dummy(){
        assertTrue(true);
    }
    
    @Test
    public void aikaKaynnistyy() {
        aika.aloita();
        assertEquals(true, aika.aikaKaynnistetty());
    }
    
    @Test
    public void aikaKaynnistetty() {
        assertEquals(false, aika.aikaKaynnistetty());
        aika.aloita();
        assertEquals(true, aika.aikaKaynnistetty());
    }
    
    @Test
    public void aikaaKulunut() {
        aika.aloita();
        try {
            Thread.sleep(1100);
        } catch (InterruptedException ex) {
            Logger.getLogger(AjanottoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(1, aika.aikaaKulunut());
    }
    
    @Test
    public void ajanLopetus() {
        aika.aloita();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AjanottoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        aika.lopeta();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AjanottoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(1, aika.aikaaKulunut());
        aika.aloita();
        aika.lopeta();
        assertEquals(1, aika.aikaaKulunut());
    }
    
    @Test
    public void eiVoiLopettaaEnnenAloitusta() {
        aika.lopeta();
        assertEquals(0, aika.aikaaKulunut());
    }
    
    @Test
    public void lahdetaanNollasta() {
        assertEquals(0, aika.aikaaKulunut());
    }
    
}
