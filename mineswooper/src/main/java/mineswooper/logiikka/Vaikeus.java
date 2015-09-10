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
public enum Vaikeus {
    HELPPO (9, 9, 10),
    NORMAALI (16, 16, 40),
    VAIKEA (16, 30, 99);
    
    private final int leveys;
    private final int korkeus;
    private final int miinat;
    
    Vaikeus(int leveys, int korkeus, int miinat) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinat = miinat;
    }
    
    public int getLeveys() {
        return leveys;
    }
    
    public int getKorkeus() {
        return korkeus;
    }
    
    public int getMiinat() {
        return miinat;
    }
    
}
