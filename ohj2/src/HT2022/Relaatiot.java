package HT2022;

import java.util.ArrayList;
import java.util.List;

/**
 * @author otsokinanen
 * @version 30 Aug 2022
 *
 * Luokka joka ylläpitää istunnon ajan listaa Relaatiot, yksittäiset relaatiot huolehtivat Henkilön ja lajin tai lajin ja harjoitusajan välisestä linkityksestä. 
 */
public class Relaatiot {
    /**
     * lista kaikista ohjelman relaatioista
     */
    public static List<Relaatio> relaatiot;
    
    /**
     * muodostaja, luo tyhjän uuden listan Relaatio olioille.
     */
    public Relaatiot() {
        setRelaatiot(new ArrayList<Relaatio>());
    }
    
    /**
     * palauttaa relaatiot
     * @return the relaatiot
     */
    public static List<Relaatio> getRelaatiot() {
        return relaatiot;
    }

    /**
     * asettaa relaatiot, ottaa vastaan relaatiot listan ja tallentaa ne luokkamuutujaan relaatiot
     * @param relaatiot the relaatiot to set
     */
    public static void setRelaatiot(List<Relaatio> relaatiot) {
        Relaatiot.relaatiot = relaatiot;
    }
    
    /**
     * Hakee parametrina saadulla idllä relaatio tietokannasta henkilön ja palauttaa taulukon jossa haetun henkilön Lajien ID:t
     * 
     * @param id henkilo id jonka lajeja haetaan
     * @return taulukko haetun Henkilön  Relaatio Id:istä 
     */
    public static int[] getRelaatiotHenkiloIdlla(int id) {
        List<Integer> lista = new ArrayList<Integer>();
        for(Relaatio r : getRelaatiot()) {
            if(r.getHenkilo() == id) {
                lista.add(r.getRelaatioId());
            }
        }
        if(lista.size()!=0) {int[] taulukko = new int[lista.size()];
        for(int i = 0; i < lista.size(); i++) taulukko[i] = lista.get(i);
        return taulukko;
        }
        return null;
    }
    
    /**
     * Hakee henkilö idn perusteella listan ko henkilön relaatioista, eli henkilön lajeista ja näiden lajien mukaisesti harjoitusajoista.
     * @param id haettavan henkilon Id intina
     * @return lista henkilon relaatioista
     */
    public static List <Relaatio> getRelaatioListaHenkiloIdlla(int id){
        List<Relaatio> relat= new ArrayList<>();
        for(Relaatio r :  getRelaatiot()) {
            if(r.getHenkilo() == id) {
                relat.add(r);
            }
        }
        
        return relat;
    }
    
    /**
     * @param id haettava id
     * @return palauttaa intinä relaatio id:n
     */
    public int getRelaatiotHarjoitusaikaIdlla(int id) {
        for(Relaatio r : getRelaatiot()) {
            if(r.getHarjoitusaikaId() == id) {
                return r.getRelaatioId();
            }
        }
        return 0;
    }
    
    /**
     * Käy läpi ohjelman relaatiot ja etsii niistä harjoitusajan Id:n perusteella
     * @param id relaatio Id jonka perusteella etsitään harjoitusaika
     * @return harjoitusaika
     */
    public Harjoitusaika getHarjoitusaikaRelaatioIdlla(int id) {
        for(Relaatio relaatio : getRelaatiot()) {
            if (relaatio.getRelaatioId() == id) {
                return Harjoitusajat.getHarjoitusAikaIdlla(relaatio.getHarjoitusaikaId());
            }
        }
       
        return null;
        
    }
    
    /**
     * käy läpi ohjelman Relaatiot, ja etsii niistä ID:n perusteella oikean lajin.
     * @param id relaatio id jolla haetaan lajia.
     * @return hattu laji
     */
    public static Laji getLajiRelaatioIdlla(int id) {
        for(Relaatio relaatio : getRelaatiot()) {
            if (relaatio.getRelaatioId() == id) {
                return Ht2022GUIController.lajit.getLajiIdlla(relaatio.getLaji());
            }
        }
        
        return null;
        
    }
    
    /**
     * Lisää luokkamuutujalistaan relaation 
     * @param relaatio lisättävä relaatio
     */
    public void Add(Relaatio relaatio) {
        relaatiot.add(relaatio);
    }


    /**
     * Palauttaa LajiIdn perusteella listan harjoitusajoista.
     * @param lajiId a
     * @return harjoitusAjat Listan jossa haettua lajia vastaavat harjoitusajat
     */
    public List<Harjoitusaika> getHarjoitusajatLajiIdlla(int lajiId) {
        List<Harjoitusaika> harjoitusAjat = new ArrayList<Harjoitusaika>();
        for (Relaatio relaatio: getRelaatiot()) {
            if(relaatio.getHarjoitusaikaId()!=0) {
                if (relaatio.getLaji() == lajiId) {
                    harjoitusAjat.add(Harjoitusajat.getHarjoitusAikaIdlla(relaatio.getHarjoitusaikaId()));
                }
            }
        }
        return harjoitusAjat;
    }

    /**
     * Palatuttaa relaation IDn perusteella
     * @param id relaation id, jonka perusteella palautetaan relaatio. 
     * @return Haettu id:n mukainen relaatio tietue
     */
    public static Relaatio getRelaatioIdlla(int id) {
        for(Relaatio r : Relaatiot.getRelaatiot()) {
            if(r.getRelaatioId() == id) {
                return r;
            }
        }
        return null;
        
    }
}
