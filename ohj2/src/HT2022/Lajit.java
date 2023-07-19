package HT2022;

import java.util.List;
import java.util.ArrayList;

/**
 * @author otsokinanen
 * @version 29 Aug 2022
 *  Luokka joka pitää yllä listaa lajeista
 */
public class Lajit {
    private static List <Laji> lajit;
    
    /**
     * parametriton muodostaja lajit luokalle, muodostaa ArrayList tyyppisen uuden listan Laji olioille
     */
    public Lajit() {
        lajit = new ArrayList <Laji>();
    }

    /**
     * @param l lisättävä laji, lajit listaan
     */
    public void add(Laji l) {
        lajit.add(l);
    }
    
    /**
     * @return the lajit
     */
    public List <Laji> getLajit() {
        return lajit;
    }
    
   /**
     * @param id haettavan lajin id numero
     * @return palauttaa lajin joka vastaa haettua id:tä
     */
    public Laji getLajiIdlla(int id){
           for(Laji laji : lajit) {
               if (id == laji.getLajiId())return laji;
           }
           return null;
   }
    
   /**
    * @return lista näytettävistä lajiesta
    */
    public List <Laji> getVisibleLajit(){
       List <Laji> visibleLajit = new ArrayList <Laji>();
       for(Laji l: lajit ) {
           if(l.getVisible())visibleLajit.add(l);
       }
       return visibleLajit;
   }
}
