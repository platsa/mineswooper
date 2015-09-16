/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswooper.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import mineswooper.kayttoliittyma.Ikkuna;
import mineswooper.logiikka.Vaikeus;

/**
 *
 * @author pekka
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {                
                JFrame ikkuna = new Ikkuna(Vaikeus.VAIKEA);
                ikkuna.setVisible(true);                
            }
        });
    }
}
