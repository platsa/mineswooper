package mineswooper.logiikka;

/**
 *Luokka huolehtii ajanotosta.
 *
 */
public class Ajanotto {
    private long lahtoaika;
    private long loppuaika;
    
    public Ajanotto() {
        lahtoaika = -1;
        loppuaika = -1;
    }
    
    /**
     * Jos ajanottoa ei ole vielä aloitettu, metodi aloittaa sen.
     */
    public void aloita() {
        if (lahtoaika == -1) {
            lahtoaika = System.nanoTime();
        }
    }
    
    /**
     * Jos ajanotto ei ole alkanut, metodi palauttaa nollan. Ajanoton ollessa
     * käynnissä palauttaa aloituksesta kuluneen ajan ja ajanoton päätyttyä
     * palauttaa aloituksen ja lopetuksen välisen ajan.
     * 
     * @return aika sekunteina
     */
    public int aikaaKulunut() {
        if (lahtoaika == -1) {
            return 0;
        } else if (loppuaika == -1) {
            return (int) ((System.nanoTime() - lahtoaika) / 1000000000);
        } else {
            return (int) (loppuaika / 1000000000);
        }
    }
    
    /**
     * Jos ajanotto ei ole alkanut, metodi palauttaa nollan. Ajanoton ollessa
     * käynnissä palauttaa aloituksesta kuluneen ajan ja ajanoton päätyttyä
     * palauttaa aloituksen ja lopetuksen välisen ajan.
     * 
     * @return aika sekunteina sadasosan tarkkuudella
     */
    public double aikaaKulunutTarkka() {
        if (lahtoaika == -1) {
            return 0;
        } else if (loppuaika == -1) {
            return (double) ((System.nanoTime() - lahtoaika) / 1000000000.0);
        } else {
            return (double) (loppuaika / 1000000000.0);
        }
    }
    
    /**
     * Jos ajanottoa ei ole lopetettu mutta on aloitettu, lopettaa sen.
     */
    public void lopeta() {
        if (loppuaika == -1 && lahtoaika != -1) {
            loppuaika = System.nanoTime() - lahtoaika;
        }
    }
    
    
    /**
     * Kertoo onko ajanotto käynnistetty.
     * 
     * @return onko ajanotto käynnistetty
     */
    public boolean aikaKaynnistetty() {
        return lahtoaika != -1;
    }
    
    /**
     * Kertoo onko ajanotto lopetettu
     * @return  onko ajanotto lopetettu
     */
    public boolean aikaLopetettu() {
        return loppuaika != -1;
    }
}
