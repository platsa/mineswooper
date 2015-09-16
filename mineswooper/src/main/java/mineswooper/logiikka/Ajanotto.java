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
    private int loppuaika;
    
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
            return loppuaika;
        }
    }
    
    public void lopeta() {
        loppuaika = (int) ((System.nanoTime() - lahtoaika) / 1000000000);
    }
    
    public boolean aikaKaynnistetty() {
        if(lahtoaika == -1) {
            return false;
        } else {
            return true;
        }
    }
}
