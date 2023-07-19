package HT2022;




import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ListChooser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * @author otsokinanen
 * @version 3 Aug 2022
 * Ohjelman pääikkunan käyttöliittymä, hallitsee ikkunan toimintoja ja pitää istunnon aikana yllä muuttujia joissa käytettävä tieto. 
 */
public class Ht2022GUIController implements Initializable {
    

    private static Relaatiot relaatiot;
    /**
     * Lajit, lista lajeista ohjelman aliohjelmien käyttöön. 
     */
    protected static Lajit lajit;
    /**
     * Lista aktiivisista, ei poistetuista lajiesta, ohjelman ja aliohjelmien käyttöön
     */
    protected static Lajit naytettavatLajit;
    private static Henkilot henkilot;
    private static Harjoitusajat harjoitusajat;
    private static Henkilo h;
 
    @FXML
    private ListChooser<Harjoitusaika>ListChooserAiemmatHarjoitukset;
    

    @FXML
    private ListChooser<Harjoitusaika> ListChooserTulevatHarjoitukset;

    @FXML
    private TextArea valittuHarjoitus;

    @FXML
    private TextField aloitupvm;

    @FXML
    private TextField etunimi;

    @FXML
    private TextField kunta;        

    @FXML
    private MenuItem lisaaHenkilo;

    @FXML
    private MenuItem lisaaLaji;

    @FXML
    private MenuItem muokkaaLaji;
    
    @FXML
    private MenuItem poistaLaji;
    
    @FXML
    private MenuItem lisaaHarjoitus;
    
    @FXML
    private MenuItem muokkaaHarjoitus;
    
    @FXML
    private MenuItem poistaHarjoitus;
    
    @FXML
    private MenuItem vaihdaKayttaja;

    @FXML
    private Button muokkaaHlo;

    @FXML
    private TextField osoite;

    @FXML
    private TextField postinumero;

    @FXML
    private TextField sukunimi;

    @FXML
    private TextField syntymaaika;

    

    @FXML
    void NaytaValittuHarjoitus(MouseEvent event) {
        naytaHarjoitus(event);
    }
   
    @FXML 
    private ListChooser<Laji> omatLajit;
    
    
    @FXML
     void lisaaHenkilo() {
        lisaaUusiHenkilo();
    }

    @FXML
    void lisaaLaji() {
        lisaaUusiLaji();
    }
    
    @FXML
    void muokkaaLaji() {
        muokkaaValittuLaji();
    }
    
    @FXML
    void poistaLaji() {
        poistaValittuLaji();
    }

        @FXML
    void lisaaHarjoitus() {
        lisaaUusiHarjoitus();
    }

    @FXML
    void muokkaaHarjoitus() {
        muokkaaHarjoitusta();
    }
    
    @FXML
    void poistaHarjoitus() {
        poistaValittuHarjoitus();
    }


    @FXML
    void vaihdaKayttaja() {
        vaihdaAktiivinenHenkilo();
    }
    

    @FXML
    void avaaHloTied() {
      muokkaaHenkilo();
    }


  //-----------------------------------------------------------------------------//
  
    /**
     * lukee tiedostostoista lajit, käyttäjät ja harjoitusajat kun ohjelma käynnistyy
     * Luo relaatiot olion, joka ylläpitää eri tietotyyppien suhteita
     * Avaa käyttäjän valinta ikkunan.
     */
    public Ht2022GUIController() {
        relaatiot = new Relaatiot();
        
        lajit = Rekisteri.lueLajiRekisteri();
        
        henkilot = Rekisteri.lueHenkiloRekisteri(getRelaatiot());
        
        harjoitusajat = Rekisteri.lueHArekisteri(getRelaatiot(), lajit);
        
        h = ValitseHenkiloController.valitseKayttaja(h);

        

    }

    /**
     * Näyttää Henkilötiedot omissa kentissään Controller ikkunassa
     * Kutsuu aliohjelmia naytaLajit ja naytaHarjoitus
     * Päivittää pääikkunan näkymän muokkausten jälkeen.
     */
    void naytaTiedot() {
        etunimi.setText(h.getEtunimi());
        sukunimi.setText(h.getSukunimi());
        syntymaaika.setText(h.getSyntymaAika().toString());
        osoite.setText(h.getOsoite());
        postinumero.setText(h.getPostinumero());
        kunta.setText(h.getKaupunki());
        
        naytaLajit();
        naytaHarjoitus(null);

        

    }
    
    /**
     * Näyttää valitun käyttäjän Lajit käyttöliittymässä
     * Näyttää käyttäjän lajien menneet ja tulevat harjoitukse käyttöliittymässä
     */
    private void naytaLajit() { 
        omatLajit.clear();
        if(h == null)return;
        
        int [] taul = Relaatiot.getRelaatiotHenkiloIdlla(h.getId());
        ArrayList<Laji> henkilonOmatLajit = new ArrayList<Laji>();
        for(int i = 0; i<taul.length; i++) {
            if(Relaatiot.getLajiRelaatioIdlla(taul[i]) != null) {
                if(Relaatiot.getLajiRelaatioIdlla(taul[i]).getVisible()){
                henkilonOmatLajit.add(Relaatiot.getLajiRelaatioIdlla(taul[i]));
                }
            }
        }
        
        ArrayList<Harjoitusaika> naytettavatHAt= new ArrayList<Harjoitusaika>();
        for(Laji l : henkilonOmatLajit) {
            if(l == null)break;
            omatLajit.add(l);
            int lajiId = l.getLajiId();
            List<Harjoitusaika> harjoitusAjat= relaatiot.getHarjoitusajatLajiIdlla(lajiId);
            naytettavatHAt.addAll(harjoitusAjat);
        }
        
        ListChooserTulevatHarjoitukset.clear();
        ListChooserAiemmatHarjoitukset.clear();
        
        for(Harjoitusaika ha : naytettavatHAt) {
            if (ha.getTulevaisuudessa()) {
                if(ha.getLaji().getVisible()) {
                ListChooserTulevatHarjoitukset.add(ha);
                }
            } else {
                ListChooserAiemmatHarjoitukset.add(ha);
            }
            
        }
        
    }
    
    
    /**
     * Näyttää valitun harjoituksen tiedot ikkunassa. Mikäli harjoitusta ei ole valittu, 
     * näytetään teksti "Valitse näytettävä harjoitus"
     * @param event Klikkaus joka aktivoi naytaHarjoitus aliohjelman
     */
    private void naytaHarjoitus(Event event) {
        if( event != null) {
            if(event.getSource()==ListChooserAiemmatHarjoitukset) {
                if (ListChooserAiemmatHarjoitukset.getSelectedObject() != null) {
                    Harjoitusaika harjoitus = ListChooserAiemmatHarjoitukset.getSelectedObject();
                    valittuHarjoitus.setText(harjoitus.pitkaString());
                    }
                else {
                    valittuHarjoitus.setText("Valitse näytettävä harjoitus");
                    return;
                }
            }
            if(event.getSource() == ListChooserTulevatHarjoitukset){
                if (ListChooserTulevatHarjoitukset.getSelectedObject() != null) {
                    Harjoitusaika harjoitus = ListChooserTulevatHarjoitukset.getSelectedObject();
                    valittuHarjoitus.setText(harjoitus.pitkaString());
                    }
                else {
                    valittuHarjoitus.setText("Valitse näytettävä harjoitus");
                    return;
                }
            }
           
        }
        else {
            valittuHarjoitus.setText("Valitse näytettävä harjoitus");
        }

    }

    /**
     * Avaa henkilön lisäämistä käsittelevän HenkiloControllerin
     * ja luo sen käytettäväksi uuden Henkilö olion.
     */
    private void lisaaUusiHenkilo() {
        HenkiloController hc =  new HenkiloController();
        Henkilo uusiHenkilo = new Henkilo();
        //henkilot.getHenkilot().add(uusiHenkilo);
        hc.naytaHenkilo(null, uusiHenkilo);
        
    }
    /**
     * avaa henkilön muokkaukseen käytettävän HenkiloControllerin, ja välittää sille aktiivisen käyttäjän tiedot muokattavaksi
     */
    private void muokkaaHenkilo() {
        HenkiloController hc = new HenkiloController();       
        hc.naytaHenkilo(null, h);
        naytaTiedot();
    }
    
    /**
     * luo uuden Laji olion ja avaa LisaaLajiControllerin jolla sille voidaan syöttää Lajin tiedot
     */
    private void lisaaUusiLaji() {
        LisaaLajiController lLC = new LisaaLajiController();
        Laji uusiLaji = new Laji();
        lLC.naytaLaji(null, uusiLaji);
        naytaTiedot();
    }
    /**
     * avaa LisaaLajiControllerin ja välittää sille valitun Laji olion muokattavaksi,
     * mikäli lajia ei ole valittu omatLajit listalta näytetään virhe.
     */
    private void muokkaaValittuLaji() {
        LisaaLajiController lLC = new LisaaLajiController();
        Laji muokattavaLaji;
   
        if (omatLajit.getSelectedObject() != null) {
            muokattavaLaji = omatLajit.getSelectedObject();
            }
        else {
            naytaVirhe("Valitse muokattava laji");
            return;

        }

                
        lLC.naytaLaji(null, muokattavaLaji);
        naytaTiedot();
    }
    
    /**
     * Asettaa valitun lajin näkymättömäksi käyttäjälle. Poistettava Laji valitaan omatLajit listalta.
     * avaa varmistus ikkunan: Poistetaanko valittu laji ja kaikki sen tiedot? 
     * Jos Lajia ei ole valittu näyttää virheen "Valitse poistettava laji".
     * Kutsuu naytaTiedot funktiota joka päivitää aloitusnäkymän
     */
    private void poistaValittuLaji() {
        
        Laji poistettavaLaji;
        
        if (omatLajit.getSelectedObject() != null) {
            poistettavaLaji = omatLajit.getSelectedObject();
            if( oletkoVarma("Poistetaanko valittu laji ja kaikki sen tiedot?")) {
                System.out.println(poistettavaLaji);
                
                //Poistetaan laji rekisteristä
                //lajit.getLajit().remove(poistettavaLaji);
                poistettavaLaji.setVisible(false);
                Rekisteri.kirjoitaLajiRekisteri(lajit);
                
               /*poistetaan lajin KAIKKI relaaiota relaatioista
                for(Relaatio r : Relaatiot.getRelaatiot()) {
                    if(r.getLaji() == poistettavaLaji.getLajiId()) {
                        Relaatiot.getRelaatiot().remove(r);
                    }
                }*/
                
            }
            else {
                System.out.println("false eli ei poisteta");
            }
            }
        else {
            naytaVirhe("Valitse poistettava laji");
            return;

        }
        naytaTiedot();

        
    }
    
    
    /**
     * avaa HarjoitusController ikkunan, luo uuden Harjoitusaika olion muokattavksi controllerille. 
     * kutsuu naytaTiedot funktiota joka päivitää aloitusnäkymän
     */
    private void lisaaUusiHarjoitus() {
        HarjoitusController hController = new HarjoitusController();
        Harjoitusaika hA = new Harjoitusaika();
        hController.naytaHarjoitus(null, hA);
        naytaTiedot();
    }
    
    
    /**
     * Avaa HarjoitusControllerin valitun harjoituksen muokkaukseen. Jos harjoitusta ei ole valittu tai se on valittu menneistä harjoituksista
     * näytetään virheilmoitus "Valitse muokattava harjoitus tulevista harjoituksista"
     * kutsuu naytaTiedot funktiota joka päivitää aloitusnäkymän
     */
    private void muokkaaHarjoitusta() {
        if (ListChooserTulevatHarjoitukset.getSelectedObject() != null) {
            Harjoitusaika harjoitus = ListChooserTulevatHarjoitukset.getSelectedObject();  
            HarjoitusController hController = new HarjoitusController();
            hController.naytaHarjoitus(null, harjoitus);   
            }
        else {
            naytaVirhe("Valitse muokattava harjoitus tulevista harjoituksista");
            return;
        }
        naytaTiedot();
    }
    
    /**
     * poistaa valitun harjoitusAika olion ja päivittää rekisterin. Jos harjoitusta ei ole valittu tai se on valittu menneistä harjoituksista
     * näytetään virheilmoitus "Valitse muokattava harjoitus tulevista harjoituksista".
     * Avaa varmistus ikkunan joka varmistaa poiston.
     * kutsuu naytaTiedot aliohjelman joka päivittää näkymän pääikkunassa kun poistaminen on viety loppuun
     * 
     */
    private void poistaValittuHarjoitus() {
        if (ListChooserTulevatHarjoitukset.getSelectedObject() != null) {
            Harjoitusaika harjoitus = ListChooserTulevatHarjoitukset.getSelectedObject(); 
            if(oletkoVarma("Poistetaanko valittu harjoitus?")) {
                // poistetaan harjoitus harjoitukset listalta
                System.out.println(getHarjoitusajat().getHarjoitusajat().remove(harjoitus));
                
                //Kirjoitetaan päivitetty rekisteri tiedostoon
                Rekisteri.kirjoitaHArekisteri(getHarjoitusajat());
                
                
                //Poistetaan relaatiot koskien poistettua harjoitusta
                int poistettavaId = getRelaatiot().getRelaatiotHarjoitusaikaIdlla(harjoitus.getId());
                Relaatio poistettavaRelaatio = Relaatiot.getRelaatioIdlla(poistettavaId);
                Relaatiot.getRelaatiot().remove(poistettavaRelaatio);
                }
            else {
                System.out.println("false eli ei poisteta");
            }
            
        }
        else {
            naytaVirhe("Valitse poistettava harjoitus tulevista harjoituksista");
            return;
        }
        naytaTiedot();
        return;
    }

    /**
     * Avaa ValitseHenkiloController ikkunan, josta päästään vaihtamaan aktiivinen käyttäjä, kutsutaan näytäTiedot aliohjelmaa joka päivätää 
     * pääikkunan tiedot
     */
    private void vaihdaAktiivinenHenkilo() {
        h = ValitseHenkiloController.valitseKayttaja(h);   
        naytaTiedot();
    }
    
    /**
     * lajit getter palauttaa lajit Listan jossa aktiviisen lajit istunnon käyttöön
     * @return lajit
     */
    public static Lajit getLajit() {
        return lajit;
    }

    /**
     * 
     */
    @SuppressWarnings("unused")
    private void setNaytettavatLajit() {
        naytettavatLajit.getLajit().clear();
        for(Laji l :lajit.getLajit()) {
            if(l.getVisible()) { 
                naytettavatLajit.add(l);
                }
        }
    }

    
    /**
     * @return Lajit lista jossa aktiiviset näytettävät lajit
     */
    public static Lajit getNaytettavatLajit() {
        return naytettavatLajit;
    }
    
    
    /**
     * @return relaatiot joissa tiedot henkiöiden lajeista ja lajien harjoitusajoista
     */
    public static Relaatiot getRelaatiot() {
        return relaatiot;
    }

    /**
     * @return henkilöt lista 
     */
    public static Henkilot getHenkilot() {
        return henkilot;
    }

    /**
     * @return Harjoitusajat
     */
    public static Harjoitusajat getHarjoitusajat() {
        return harjoitusajat;
    }

    /**
     * Kutsuu naytaTiedot aliohjelman käynnistyksessä
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       naytaTiedot();
       
        
    }
    
    /**
     * 
     * @param viesti näytettävä varmistusviesti
     * @return palauttaa Boolean arvon, true, jos valittu Yes, false jos No
     */
    private Boolean oletkoVarma(String viesti) {
        Alert alert = new Alert(AlertType.CONFIRMATION,viesti,ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
      
    }
    /**
     * Näyttää virhe ikkunan
     * @param virhe viesti joka ikkunassa näytetään
     */
    private void naytaVirhe(String virhe) {
        Alert alert = new Alert(AlertType.INFORMATION,virhe);
        
        alert.show();
               
        return ;
        


        
    }
}

