package HT2022;

/**
 * @author otsokinanen
 * @version 18 Aug 2022
 * Luokka Harjoitusaika olioille. 
 * Harjoitusjalla on oltava päivä, aika, sali, Laji, lisäksi luokka antaa kullekin harjoitusajalle oman Id:n jonka perusteella sen relaatiot voidaan kirjata tiedostoihin. 
 */
public class Harjoitusaika {
    private Pvm paiva;
    private Aika kloaika;
    private String sali;
    
    // ei käytössä
    private int ohjaajaId;
    //harjoituksen uniikki id numero
    private int id;
    /**
     * seuraava vapaa id numero
     */
    public static int nextId  = 10000; 
    private int paikkoja;
    private int[] ilmoittautuneet  =  new int[paikkoja];
    private Laji laji;
    
    

    
    /**
     * parametriton muodostaja luo uuden Harjoitusaika olion oletus tiedoilla. 
     */
    public Harjoitusaika() {
        this.kloaika = new Aika();
        this.paiva = new Pvm();
        this.sali = "Oletus sali";
        this.laji = new Laji("temp");
        this.id = nextId;
        this.paikkoja = 0;
        nextId++;        
    }
    
    
    /**
     * @return harjoitusaika Olion ID
     */
    public int getId() {
        return id;
    }

    /**
     * asetetaan päivämäärä 
     * @param pvm paivamaara
     */
    public void setPaiva(Pvm pvm) {
        this.paiva = pvm;
    }
        
   /**
    * palauttaa harjoituksen alkamisajan
    * @return aika
    */
    public Aika getKloaika() {
        return kloaika;
    }

    /**
     * asetetaan harjoituksen alkamisaika
     * @param kloaika aika
     */
    public void setKloAika(Aika kloaika) {
        this.kloaika = kloaika;
    }

    /**
     * palauttaa kirjatun salin 
     * @return sali
     */
    public String getSali() {
        return sali;
    }

    /**
     * asettaa Harjoitusajan sali muuttujalle arvon
     * @param sali sali
     */
    public void setSali(String sali) {
        this.sali = sali;
    }

    /**
     * Palauttaa harjoitusajan Lajin
     * @return laji
     */
    public Laji getLaji() {
        return laji;
    }

    /**
     * asettaa Harjoitusajan Lajin
     * @param laji l
     */
    public void setLaji(Laji laji) {
        this.laji = laji;
    }

    /**
     * Palauttaa Harjoitusajan päivän
     * @return paiva
     */
    public Pvm getPaiva() {
        return paiva;
    }
    
    /**
     * @return paikkojen lukumäärä
     */
    public int getPaikkoja() {
        return paikkoja;
    }

    /**
     * @param paikkoja paikkojen määrä
     */
    public void setPaikkoja(int paikkoja) {
        this.paikkoja = paikkoja;
    }

    /**
     * @return the ohjaajaId
     */
    public int getOhjaajaId() {
        return ohjaajaId;
    }
    
    /**
     * @return booelan arvo true jos harjoitu on tulevaisuudessa false jos se on jo mennyt
     */
    public boolean getTulevaisuudessa() {
        Pvm pvm = new Pvm();
        return pvm.tulevaisuudessa(paiva);
    }

    /**
     * @param ohjaajaId the ohjaajaId to set
     */
    public void setOhjaajaId(int ohjaajaId) {
        this.ohjaajaId = ohjaajaId;
    }

    /**
     * @return palauttaa harjoituksen tiedot pitkässä muodossa
     */
    public String pitkaString() {
        return laji.getLaji() + " \r\n " + paiva.getPv()+"."+paiva.getKk() + "  " + kloaika +"\r\n" + sali  +"\r\nIlmoittautuneita : " + ilmoittautuneet.length + "/" + paikkoja;
    }
    
    @Override
    public String toString() {
        return paiva.toString() + " " + kloaika.toString() + " " + laji.getLaji();
    }


    
    /**
     * Lukee lukee harjoitusajan määrämuotoisesta String muuttujasta, muodostaa tietojen perusteella olion, tallentaa sen relaatiot 
     * ja palautta harjoitusaika tyyppisen olion. 
     * 
     * @param s Merkkijono jossa harjoitujksen tiedot
     * @param relaatiot relaatiolista jota käytetään tietokantojen apuna
     * @param l lajit lista
     * @return harjoitusaika luetusta merkkijonosta
     * 
     * @example
     * <pre name="test">
     * #import HT2022.*;
     * 
     * String s1 = "30.8.2022,19:00,Isosali 4.krs,100,1002,20";
     * Relaatiot r1 = new Relaatiot();
     * String lajiString  ="Brasilialainen jujitsu,Valkoinen vyö:Sininen vyö:Purppura vyö:Ruskea vyö:Musta vyö,1";
     * 
     * Laji l = Laji.lueLaji(lajiString);
     * Lajit lajit = new Lajit();
     * lajit.add(l);
     * 
     * 
     * Harjoitusaika ha = lueHarjoitusaika(s1,  r1, lajit);
     * ha.toString()==="30.8.2022 19:00 Brasilialainen jujitsu";
     * ha.getSali()==="Isosali 4.krs"; 
     *
     * ha.getLaji().toString()==="Brasilialainen jujitsu";
     * 
     * 
     * </pre>
     * 
     * 
     */
    public static Harjoitusaika lueHarjoitusaika(String s, Relaatiot relaatiot, Lajit l) {
        //luodaan uusi harjoitusaika olio
        Harjoitusaika hA = new Harjoitusaika();
        
        
        //pilkotaan parametrina saatu merkkijono erottimen perusteella osiin.
        String [] a = s.split(",",0);
        
        Pvm paivaus = new Pvm();
        paivaus.parse(a[0]);
        hA.setPaiva(paivaus);

        Aika aika = new Aika();
        aika.parse(a[1]);
        hA.setKloAika(aika);
        
        hA.setSali(a[2]);
        
        Relaatio rel = new Relaatio();

        rel.setLaji(Integer.parseInt(a[3]));
        hA.setLaji(l.getLajiIdlla(Integer.parseInt(a[3])));;
        
        rel.setHarjoitusaikaId(hA.getId());
        hA.setOhjaajaId(Integer.parseInt(a[4]));
        
        
        //lisää relaatiot relaatiokantaan
        relaatiot.Add(rel);
        
                
        hA.setPaikkoja(Integer.parseInt(a[5]));      
        
        return hA;
        
        
    }


    /**
     * palauttaa harjoitusajan tiedostoon kirjoitettavassa formaatissa
     * @return kirjoitettavassa muodossa rekisteriin
     */
    public String getHarjoitusaikaKirjoitettava() {
        return  getPaiva() + "," + getKloaika() +","+getSali() + ","+getLaji().getLajiId() + "," + getOhjaajaId()+ "," + getPaikkoja();
    }
    
    /**
     * Kutsuu Rekisteri luokan aliohjelmaa joka kirjoittaa syötetyn tai muokatun harjoitusajan.
     */
    public static void KirjoitaHarjoitusaika() {
        Rekisteri.kirjoitaHArekisteri(Ht2022GUIController.getHarjoitusajat());
        
    }




}
