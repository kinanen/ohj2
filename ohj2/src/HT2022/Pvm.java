/**
 * 
 */
package HT2022;

import fi.jyu.mit.ohj2.Mjonot;

import java.time.LocalDate;
/**
 * @author otsokinanen
 * @version 18 Ju 22
 *
 */
public class Pvm {
    private int pv, kk, vv;


    /**
     * Palauttaa tämänhetkisen päiväyksen.
     * TODO: muutettava toimimaan oikein.
     */
    public void paivays() {
        LocalDate date = LocalDate.now();
        pv = date.getDayOfMonth();
        kk = date.getMonthValue();
        vv = date.getYear();
    }
    
    /**
     * @param pvm a
     * @return tulevaisuus
     */
    public boolean tulevaisuudessa(Pvm pvm) {
        if(pvm.vv>vv) return true;
        else if (pvm.vv < vv) return false;
        else if(pvm.kk>kk) return true;
        else if (pvm.kk < kk) return false;
        else if(pvm.pv>pv)return true;
        else if(pvm.pv<pv) return false;
        else return false;
    }


    /**
     * Alustetaan päivämäärä. 0-arvot eivät muuta vastaavaa attribuuttia
     * TODO: oikeellisuustarkitukset
     * @param ipv päivän alustus
     * @param ikk kuukauden alustus
     * @param iivv vuoden alustus
     */
    public final void alusta(final int ipv, final int ikk, final int iivv) {
        if (ipv > 0)        this.pv = ipv;
        if (ikk > 0)        this.kk = ikk;
        if (iivv > 0)        this.vv = iivv;
        if (this.vv < 50)  this.vv += 2000;
        if (this.vv < 100) this.vv += 1900;
    }


    /** Alustetaan kaikki attribuutit oletusarvoon */
    public Pvm() {
        this(0, 0, 0);
    }


    /**
     * Alustetaan kuukausi ja vuosi oletusarvoon
     * @param pv päivän alustusarvo
     */
    public Pvm(int pv) {
        this(pv, 0, 0);
    }


    /**
     * Alustetaan vuosi oletusarvoon
     * @param pv päivän alustusarvo
     * @param kk kuukauden oletusarvo
     */
    public Pvm(int pv, int kk) {
        this(pv, kk, 0);
    }


    /**
     * Alustetaan vuosi oletusarvoon
     * @param pv päivän alustusarvo
     * @param kk kuukauden oletusarvo
     * @param vv vuoden alustusarvo
     */
    public Pvm(int pv, int kk, int vv) {
        paivays();
        alusta(pv, kk, vv);
    }

    /**
     * @return PVM olion pv osa
     */
    public int getPv() {
        return(this.pv);
    }

    /**
     * @return PVM olion Kuukausi int inä
     */
    public int getKk() {
        return(this.kk);
    }
    
    /**
     * @return PVM olion vuosi osa
     */
    public int getVv() {
        return(this.vv);
    }
    /**
     * Palautetaan päiväys merkkijonona
     * @return päiväys merkkijonoja
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm(10,2,2008);
     * pvm.toString() === "10.2.2008";
     * </pre>
     */
    @Override
    public String toString() {
        return pv + "." + kk + "." + vv;
    }


    /**
     * Alustetaan päivän arvon merkkijonosta
     * @param s jono josta pvm otetaan
     * @return totuus arvo mikäli päivämäär ok
     * @example
     * <pre name="test">
     * Pvm pvm = new Pvm();
     * pvm.parse("11.05.02");  pvm.toString() === "11.5.2002";
     * pvm.parse("11.05.97");  pvm.toString() === "11.5.1997";
     * pvm.parse("17.3");      pvm.toString() === "17.3.1997";r
     * pvm.parse("19");        pvm.toString() === "19.3.1997";
     * </pre>
     */
    public Boolean parse(String s) {
        StringBuffer sb = new StringBuffer(s);
        int p = Mjonot.erota(sb, '.', 0);
        int k = Mjonot.erota(sb, '.', 0);
        int v = Mjonot.erota(sb, '.', 0);
        alusta(p, k, v);
        
        if (p>31|p<1|k>12|k<1|v<1900| v>2050)return false;
        
        return true;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String [] args) {
        //
        Pvm pvm = new Pvm();
        pvm.parse("3.4.2011");
        Pvm tanaan = new Pvm();
        Pvm paiva = new Pvm(5,7);
        System.out.println(paiva);
        
        System.out.println(pvm.getPv()); // tulostaa 3
        System.out.println(tanaan);
    }
}
