package HT2022;


/**
 * @author otsokinanen
 * @version 16 Aug 2022
 *
 * Luokka jonka tehtävänä on hallita henkilön tietoja.
 * henkilölle talletetaan etunimi, sukunimi, syntymäaika, osoite, postinumero, ja kaupunki, Luokka antaa itse lisäksi jokaiselle oliolle IDn
 */
public class Henkilo {
    private String etunimi = "";
    private String sukunimi = "";
    private int id; 
    private Pvm syntymaAika = new Pvm(1,1,1900);
    private String osoite = "";
    private String postinumero  = "";
    private String kaupunki = "";
    
    //seuraava vapaa id
    private static int nextId = 1000;

    

    
    /**
     * @param etunimi -
     * @param sukunimi -
     * @param syntymaAika -
     * @param osoite -
     * @param postinumero -
     * @param kaupunki =

     */
    public Henkilo(String etunimi, String sukunimi, Pvm syntymaAika,
            String osoite, String postinumero, String kaupunki) {
        super();
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.id = nextId;
        nextId++;
        this.syntymaAika = syntymaAika;
        this.osoite = osoite;
        this.postinumero = postinumero;
        this.kaupunki = kaupunki;


    }

    /**
     * muodostaja tapauksessa jossa henkilöllä on vain etunimi ja sukunimi tiedot
     * @param etunimi muodostaja henkilö oliolle
     * @param sukunimi muodostaja henkilö oliolle
     */
    public Henkilo(String etunimi, String sukunimi){
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.id = nextId;
        nextId++;
    }   
    
    /**
     *  aliohjelma joka antaa Henkilo Oliolle Idn ja lisää nextId arvoa yhdellä
     */
    public Henkilo() {
        this.id = nextId;
        nextId++;
    }

    /**
     * @return etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }
    
    /**
     * @param s annettava nimi
     */
    public void setEtunimi(String s) {
        etunimi = s;
    }
    
    
    /**
     * @return henkilop olion sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }

    /**
     * @param s sukunimi parametrina
     */
    public void setSukunimi(String s) {
        sukunimi = s;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the syntymaAika
     */
    public Pvm getSyntymaAika() {
        return syntymaAika;
    }

    /**
     * @param syntymaAika the syntymaAika to set
     */
    public void setSyntymaAika(Pvm syntymaAika) {
        this.syntymaAika = syntymaAika;
    }

    /**
     * @return the osoite
     */
    public String getOsoite() {
        return osoite;
    }

    /**
     * @param osoite the osoite to set
     */
    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    /**
     * @return the postinumero
     */
    public String getPostinumero() {
        return postinumero;
    }

    /**
     * @param postinumero the postinumero to set
     */
    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    /**
     * @return the kaupunki
     */
    public String getKaupunki() {
        return kaupunki;
    }

    /**
     * @param kaupunki the kaupunki to set
     */
    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    /**
     * @return the nextId
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * @param nextId the nextId to set
     */
    public static void setNextId(int nextId) {
        Henkilo.nextId = nextId;
    }
    
    @Override
    public String toString() {
        String s = this.etunimi + " " + this.sukunimi;
        return s;
    }
    
    /**
     * 
     * Aliohjelma joka lukee string syötteestä henkilön ja tallentaa sen Lajien perusteella relaatiot parametrina saatuun Relaatiot listaan.
     * String syöte on yksi CSV muotoinen rivi, järjestyksessä etunimi, sukunimi, syntymäaika, osoite, postinumero, ja kaupunki ja henkilön lajien IDt.
     * @param s  luettava merkkijono
     * @param relaatiot relaatio lista johon henkilon lajirelaatio lisätään
     * @return henkilo jonka tiedot luettiin
     * 
     * @example
     * <pre name="test">
     * #import HT2022.*;
     * 
     * String s1 = "Sami,Joukainen,15.10.2000,Savikuja 6,00100,Helsinki";
     * Relaatiot r1= new Relaatiot();
     * Henkilo h1 = lueHenkilo(s1, r1);
     * h1 != null===true;
     * 
     * String s2 ="Timo,Karhu,1.7.1977,Toripolku 6 d 3,00760,Helsinki";
     * Henkilo h2 = lueHenkilo(s2, r1);
     * h2.toString() === "Timo Karhu";
     * 
     * Relaatiot.getRelaatioIdlla(1).toString()==="1 : 1000 : 0 : 0"
     * 
     * 
     * 
     * 
     * </pre>
     */
    public static Henkilo lueHenkilo(String s, Relaatiot relaatiot) {
        Henkilo h = new Henkilo();
        String a[] = s.split(",", 0 );
        
        h.setEtunimi(a[0]);
        h.setSukunimi(a[1]);
        Pvm spaiva = new Pvm();
        spaiva.parse(a[2]);
        h.setSyntymaAika(spaiva);
        h.setOsoite(a[3]);
        h.setPostinumero(a[4]);
        h.setKaupunki(a[5]);
        if(a.length<=6){
            Relaatio r = new Relaatio(h.getId(), 0);
            relaatiot.Add(r);
        }
        else {
            String [] sa = a[6].split(":",0 );
            
            for(int i = 0; i < sa.length; i++) {
                Relaatio r = new Relaatio(h.getId(),Integer.parseInt(sa[i]) );
                relaatiot.Add(r);
            }               
        }
        
        return h; 
    }

    /**
     *  Kutsuu Reksiteri luokan aliohjelmaa kirjoittamaan Henkilöt listan rekisteriin
     */
    public void kirjoitaHenkilo() {
        Rekisteri.kirjoitaHenkiloRekisteri(Ht2022GUIController.getHenkilot());
    }

    /**
     * hakee henkilön relaatiot ja niiden perusteella henkilön Lajit, 
     * muodostaa niistä oikeanmuotoisen String:in joka on kirjoietttavissa tiedostoon. 
     *  
     * @return henkilo tallennettavassa, ja luettavassa muodossa String:inä
     * 
     */
    public String getHenkiloKirjoitettava(){
        int [] taulukko = Relaatiot.getRelaatiotHenkiloIdlla(getId());
        String lajiIdt = "";
        if(taulukko!=null) {
            for (int i = 0; i< taulukko.length; i++) {
                if(Relaatiot.getLajiRelaatioIdlla(taulukko[i]) != null){
                    lajiIdt += Relaatiot.getLajiRelaatioIdlla(taulukko[i]).getLajiId();
                    if (i < taulukko.length - 1) lajiIdt += ":";
                    }
                
            }
            if(lajiIdt.equals(""))lajiIdt = "0";
        }
        return getEtunimi() +","+getSukunimi()+","+getSyntymaAika().toString()+","+getOsoite()+","+getPostinumero()+","+getKaupunki()+","+lajiIdt;
    }
    
}
