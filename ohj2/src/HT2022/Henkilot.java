package HT2022;

import java.util.ArrayList;
import java.util.List;

/**
 * @author otsokinanen
 * @version 29 Aug 2022
 *
 * Luokka jonka ylläpitää listaa Henkilö luokan olioista
 * Osaa palauttaa Id:n perusteella henkilö olion, mikäli se on tallennettu luokan henkilot listaan.
 */
public class Henkilot {
    
        //luokkamuuttuja jossa ylläpidettävä lista aktiivisista henkilöistä
        private static List<Henkilo> henkilot;
        
        /**
         * muodostaja ilman parametreja, luo uuden listaolion kutsumalla luokan setHenkilot metodia tyhjällä henkilölistalla
         */
        public Henkilot(){
            setHenkilot(new ArrayList<Henkilo>()); 
        }

        /**
         * palauttaa henkilöt muuttujan
         * @return the henkilot
         */
        public List<Henkilo> getHenkilot() {
            return henkilot;
        }

        /**
         * asettaa paramatrina saadun Henkilo listan luokkamuuttujan arvoksi
         * @param henkilot the henkilot to set
         */
        public void setHenkilot(List<Henkilo> henkilot) {
            Henkilot.henkilot = henkilot;
        }
        
        /**
         * Aliohjelma palauttaa henkilön haettavan henkiön Id:n perusteella
         * @param id haettavan henkilon id
         * @return palautetaan idn mukainen henkilo
         */
        public static Henkilo getHenkiloIdlla(int id) {
            for(Henkilo h: henkilot) {
               if(id == h.getId()) return h;
            }
            return null;
        }
}
