/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.logiikka;

/**
 *
 * @author pekka
 */
public class Ajanotto {
    private long lahtoaika;
    private long loppuaika;
    
    public Ajanotto() {
        lahtoaika = -1;
        loppuaika = -1;
    }
    
    public void aloita() {
        if(lahtoaika == -1) {
            lahtoaika = System.nanoTime();
        }
    }
    
    public int aikaaKulunut() {
        if(lahtoaika == -1) {
            return 0;
        } else if(loppuaika == -1) {
            return (int) ((System.nanoTime() - lahtoaika) / 1000000000);
        } else {
            return (int) loppuaika / 1000000000;
        }
    }
    
    public void lopeta() {
        if(loppuaika == -1) {
            loppuaika = System.nanoTime() - lahtoaika;
        }
    }
    
    public boolean aikaKaynnistetty() {
        if(lahtoaika == -1) {
            return false;
        } else {
            return true;
        }
    }
}
