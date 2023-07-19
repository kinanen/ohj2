package HT2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author otsokinanen
 * @version 18 Aug 2022
 *Luokka lajeille joille on harjoituksia. ja joita harrastajat voivat valita Lajille tallennetaan nimi(laji), Id numero(lajiId) ja näkyvyys boolean arvona. 
 */
public class Laji {

    private String laji;
    private int lajiId;
    private Boolean visible = true; // näkyvyys arvo oletuksena true, jolloin laji on näykvä. 
    /**
     * seuraava vapaa id
     */
    public static int seuraavaId = 100;
    private List <String> tasot = new ArrayList<String>();
    
    
    /**
     * Muodostaja kaikilla parametreilla Lajin nimi, sekä harjoitus tasot <Stirng>listana.
     * 
     * @param laji lajin nimi
     * @param tasot lajin tasot, esim vyöarvot, String lista
     */
    public Laji(String laji, List <String> tasot) {
        this.setLaji(laji);
        this.setTasot(tasot);
        this.setLajiId(seuraavaId);
        seuraavaId++;
            
    }
    
    /**
     * @param s lajin lisääminen vain nimellä. Lajille ei tule Idtä. 
     */
    public Laji(String s) {
        this.setLaji(s);
    }
    
    /**
     * Paramatriton muodostaja, muodostaa tyhjän lajin, jolle kuitenkin asetetaan Id ja kasvatetaan seuraavan vapaan Idn arvoa yhdellä. 
     */
    public Laji() {
        this.setLaji("");
        this.setTasot(new ArrayList<String>());
        this.setLajiId(seuraavaId);
        seuraavaId++;
        
    }
    
    /**
     * @return the lajiId
     */
    public int getLajiId() {
        return lajiId;
    }

    /**
     * @param lajiId the lajiId to set
     */
    public void setLajiId(int lajiId) {
        this.lajiId = lajiId;
    }

    /**
     * @return the laji
     */
    public String getLaji() {
        return laji;
    }

    /**
     * @param laji the laji to set
     */
    public void setLaji(String laji) {
        this.laji = laji;
    }

    /**
     * @return the tasot
     */
    public List <String> getTasot() {
        return tasot;
    }

    /**
     * @param tasot the tasot to set
     */
    public void setTasot(List <String> tasot) {
        this.tasot = tasot;
    }
    
    /**
     * @param s rivi josta luetaan sisään lajikohtaiset tiedot
     * @return uusi muodostettu laji olio
     * 
     * @example
     * <pre name="test">
     * #import HT2022.*;
     * 
     * 
     * String s  ="Brasilialainen jujitsu,Valkoinen vyö:Sininen vyö:Purppura vyö:Ruskea vyö:Musta vyö,1";
     * Laji l = lueLaji(s);
     * l.getVisible()===true;
     * l.getLaji()==="Brasilialainen jujitsu"
     * l.getLajiId()===100;
     * 
     * String s2 ="Judo,Aloittelija:Harrastaja:Kilpa,0";
     * Laji l2 = lueLaji(s2);
     * l2.toString() === "Judo"
     * l2.getVisible()=== false;
     * l2.getTasot().toString() === "[Aloittelija, Harrastaja, Kilpa]";
     * l2.getLajiId()===101;
     *
     * </pre>
     * 
     *  
     */
    public static Laji lueLaji(String s) {
        Laji l = new Laji();
        String[] t = s.split(",", 0);
        l.setLaji(t[0]);
        
        String[] tasoTaulukko = t[1].split(":",0);
        List <String> tasolista = Arrays.asList(tasoTaulukko);
        l.setTasot(tasolista);
        if(t[2].equals("0")) {
            l.setVisible(false);
        }

        return l;
        
    }
    
    /**
     * testataan comtestin toimiminen packagessa
     * @param x luku1
     * @param y luku2
     * @return summa 1 ja 2
     * 
     * @example
     * <pre name="test">
     *  testiTesti(1,2)===3;
     *  testiTesti(-1,-2)===-3
     * </pre>
     */
    public static int testiTesti(int x, int y) {
        
        return x+y;
        
    }
    
    /**
     * palauttaa lajin nimen String muodossa
     */
    @Override
    public String toString() {
        return getLaji();
    }

    /**
     * Kutsuu rekisteriluokan aliohjelmaa, joka kirjoittaa päivitetyt lajit tiedostoon
     */
    public void kirjoitaLaji() {
        Rekisteri.kirjoitaLajiRekisteri(Ht2022GUIController.getLajit());
    }
    
    /**
     * Muodostaa tiedostoon kirjoitettavaksi soveltuvan String muuttujan Laji oliosta.
     * 
     * @return kirjoitetetavassa muodossa laji
     */
    public String getLajiKirjoitettava() {
        String tasokirjoitettava = "";
        int i = 0;
        for(String t : this.tasot) {
            tasokirjoitettava = tasokirjoitettava + t;
            i++;
            if (i<this.tasot.size())tasokirjoitettava = tasokirjoitettava + ":";
        }
        int nakyvyys = 0;
        if(this.getVisible())nakyvyys =1 ;
        return getLaji() +","+ tasokirjoitettava + "," + nakyvyys;
    }

    /**
     * @return palauttaa lajin näkyvyys asetuksen true, mikäli laji on näytettävä, false jos se on poistettu
     * 
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * @param visible Asettaa lajin näkyvyys asetuksen
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    
    
}
