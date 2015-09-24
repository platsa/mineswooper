package mineswooper.logiikka;

/**
 * Luokka kuvaa miinaharava-pelin kentän yhtä ruutua ja sen ominaisuuksia.
 * Luokka sisältää vain gettereitä ja settereitä.
 */
public class Ruutu {
    private int lkm;
    private boolean miina;
    private boolean avattu;
    private boolean merkitty;
    private boolean laukaistu;
    
    public Ruutu() {
        this.lkm = 0;
        this.miina = false;
        this.avattu = false;
        this.merkitty = false;
        this.laukaistu = false;
    }
    
    public void setLkm(int lkm) {
        this.lkm = lkm;
    }
    
    public int getLkm() {
        return lkm;
    }
    
    public void asetaMiina() {
        this.miina = true;
    }
    
    public void poistaMiina() {
        this.miina = false;
    }
    
    public boolean onkoMiinaa() {
        return miina;
    }
    
    public void avaa() {
        this.avattu = true;
    }
    
    public boolean onkoAvattu() {
        return avattu;
    }
    
    public void merkitse() {
        this.merkitty = true;
    }
    
    public void merkkiPois() {
        this.merkitty = false;
    }
    
    public boolean onkoMerkitty() {
        return merkitty;
    }
    
    public void laukaise() {
        laukaistu = true;
    }
    
    public boolean onkoLaukaistu() {
        return laukaistu;
    }
}
