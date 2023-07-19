package HT2022;

import java.util.ArrayList;
import java.util.List;

/**
 * @author otsokinanen
 * @version 29 Aug 2022
 * Luokka joka hallinnoi listaa johon harjoitusajat tallentuu
 */
public class Harjoitusajat {
    private static List<Harjoitusaika> harjoitusajat;
    
    //parametriton muodostaja
    Harjoitusajat(){
        setHarjoitusajat(new ArrayList<Harjoitusaika>());
    }

    /**
     * @return the harjoitusajat
     */
    public List<Harjoitusaika> getHarjoitusajat() {
        return harjoitusajat;
    }

    /**
     * @param harjoitusajat the harjoitusajat to set
     */
    public static void setHarjoitusajat(List<Harjoitusaika> harjoitusajat) {
        Harjoitusajat.harjoitusajat = harjoitusajat;
    }
    
    /**
     * @param id haettava harjoitusaika id
     * @return haettu harjoitus
     */
    public static Harjoitusaika getHarjoitusAikaIdlla(int id) {
        for(Harjoitusaika hA : harjoitusajat) {
            if (id == hA.getId())return hA;
        }
        return null;
               
    }
}
