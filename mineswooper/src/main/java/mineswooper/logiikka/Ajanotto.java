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
    private boolean aikaOtettu;
    private long lahtoaika;
    private int loppuaika;
    
    public Ajanotto() {
        aikaOtettu = false;
        lahtoaika = -1;
        loppuaika = -1;
    }
    
    public void aloita() {
        if(!aikaOtettu) {
            lahtoaika = System.nanoTime();
            aikaOtettu = true;
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
        return aikaOtettu;
    }
}
