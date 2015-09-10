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
    private boolean kaynnissa;
    private boolean aikaOtettu;
    private long lahtoaika;
    private int loppuaika;
    
    public Ajanotto() {
        kaynnissa = false;
        aikaOtettu = false;
    }
    
    public void aloita() {
        lahtoaika = System.nanoTime();
        kaynnissa = true;
    }
    
    public int aikaaKulunut() {
        if(!aikaOtettu) {
            return (int) ((System.nanoTime() - lahtoaika) / 1000000000);
        } else {
            return loppuaika;
        }
    }
    
    public void lopeta() {
        loppuaika = (int) ((System.nanoTime() - lahtoaika) / 1000000000);
        kaynnissa = false;
        aikaOtettu = true;
    }
    
    public boolean aikaKaynnissa() {
        return kaynnissa;
    }
}
