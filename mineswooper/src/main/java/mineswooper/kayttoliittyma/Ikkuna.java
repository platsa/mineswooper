/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.kayttoliittyma;

import javax.swing.JFrame;
import mineswooper.logiikka.Vaikeus;

/**
 *
 * @author pekka
 */
public class Ikkuna extends JFrame {
    private final int SIVU = 16;
    private Pelilauta lauta;
    
    public Ikkuna(Vaikeus vaikeus) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(vaikeus.getLeveys() * SIVU, vaikeus.getKorkeus() * SIVU);
        setLocationRelativeTo(null);
        setTitle("Mineswooper");

        this.lauta = new Pelilauta();
        add(this.lauta);

        setResizable(false);
    }
    
}
