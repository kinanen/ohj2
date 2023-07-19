package HT2022;

import java.time.LocalDateTime;

/**
 * @author otsokinanen
 * @version 9 Dec 2022
 *
 *Kellon aika luokka, tunnit ja minuutit 24 kellolla.
 */
public class Aika {
    private int h;
    private int m;
    
    /**
     *  Aliohjelma joka asettaa ajaksi paikallisen ajan.
     */
    public void kellonAika(){
        LocalDateTime aika = LocalDateTime.now();
        this.h = aika.getHour();
        this.m = aika.getMinute();
    }
    
    /**
     * Parametriton muodostajan joka kutsuu kellonAika aliohjelmaa
     */
    public Aika() {
        this.kellonAika();
    }
    
    /**
     * muodostaja joka asettaa tunnit ja minuutit, sekä tarkistaa että ne ovat oikein
     * @param h tunnit
     * @param m minuutit
     */
    public Aika (int h, int m) {
        this.h = h;
        this.m = m;
    }
    
    /**
     * @return Tunnit Int lukuna
     */
    public int getH() {
        return this.h;
    }
    
    /**
     * @return minuutit Int lukuna
     */
    public int getM() {
        return this.m;
    }
    /**
     * Muodostaja yhdelle parametsille. jos paramatri on yli 24 tulkitaan sen olevan minuutti, jos alle tulkitaan sen oleva tunti.
     * @param x saatu syöte numerona
      */
    public Aika (int x) {
        if(x>24) {
            this.h = LocalDateTime.now().getHour();
            this.m = x;
        }
        else {
        this.h = x;
        this.m = 0;
        }
    }
    
    @Override
    public String toString(){
        String mm = m + "";
        String hh = h + "";
        if(m<10) mm = "0"+mm;
        if(h<10) hh = "0"+hh;
        return hh + ":" + mm;
    }

    /**
     * @param string vastaanotettava String, josta kellon aika luetaan, erotin merkkinä oltava ":" 
     * @return false jos luku ei onnistu, true jos ok
     */
    public Boolean parse(String string) {
        String [] t = string.split(":");
        int hh = Integer.parseInt(t[0]);
        int mm = Integer.parseInt(t[1]);
        if(hh>23|hh<0){
            return false;
        }
        if(mm>59|mm<0){
            return false;
        }
        this.h = Integer.parseInt(t[0]);
        this.m = Integer.parseInt(t[1]);
        return true;
    }
}
