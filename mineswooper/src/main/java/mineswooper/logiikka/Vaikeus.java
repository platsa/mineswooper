package mineswooper.logiikka;

/**
 *Lueteltu tyyppi joka pitää tallessa miinaharavan kentän koon ja miinojen määrän.
 *
 */
public enum Vaikeus {
    HELPPO (9, 9, 10),
    NORMAALI (16, 16, 40),
    VAIKEA (30, 16, 99);
    
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
