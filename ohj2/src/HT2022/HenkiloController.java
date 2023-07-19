package HT2022;

import java.util.ArrayList;
import java.util.List;

import fi.jyu.mit.fxgui.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * @author otsokinanen
 * @version 16 Aug 2022
 * Henkilön muokkaus tai lisäys käyttöliittymä ja ikkuna. 
 */

public class HenkiloController implements ModalControllerInterface <Henkilo> {

    // Käsiteltävä Henkilo Olio
    private Henkilo henkilo;
    
    
    @Override
    public Henkilo getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    //näytetään Henkilo:n tiedot ikkunan teitokentissä
    //@param oletus kutsun mukana välitetty Henkilö olio
    @Override
    public void setDefault(Henkilo oletus) {
        naytaTiedot(oletus);
    }
    
   

    @FXML
    private TextField etunimi;

    @FXML
    private TextField kunta;

    @FXML
    private Button lisaaLaji;

    @FXML
    private TextField osoite;

    @FXML
    private Button poistaLaji;

    @FXML
    private TextField postinumero;

    @FXML
    private TextField sukunimi;

    @FXML
    private TextField syntymaaika;

    @FXML
    private Button ButtonTallenna;
    
    @FXML 
    private ListChooser<Laji> listChooserKaikkiLajit;
                              
    @FXML 
    private ListChooser<Laji> listChooserOmatLajit;

    @FXML
    void lisaaLaji() {
        lisaaLajiHenkilolle();
    }


    @FXML
    void poistaLaji() {
        poistaLajiHenkilolta();
    }


    @FXML
    void tallennaHlo() {
        tallennaHenkilo();
    }
  //-----------------------------------------------------------------//

    /**
     * lisätään listChooserKaikkiLajit listalta valittu laji Henkilölle ja luodaan siitä syntyvä relaatio, tallennetaan muuttuneet relaatiot tietokantaan.
     */
    private void lisaaLajiHenkilolle() {
        if(listChooserKaikkiLajit.getSelectedObject() != null) {
            Laji laji = listChooserKaikkiLajit.getSelectedObject();
            for (StringAndObject<Laji> ol : listChooserOmatLajit.getItems()) {
                if(ol.getObject() == laji)return;              
            }
            listChooserOmatLajit.add(laji);
            Relaatio r = new Relaatio(henkilo.getId(), laji.getLajiId());
            Relaatiot.getRelaatiot().add(r);
            for(Relaatio rels : Relaatiot.getRelaatiot()) {
                System.out.println(rels);
            }

        }
    }

    /**
     * Poistetaan listChooresOmatLajit listalta valittu laji henkilön lajeista, ja muokataan poistetaan sen ja Henkilön relaatio Relaatiot listalta.
     */
    private void poistaLajiHenkilolta() {
        List<Relaatio> henkilonRelaatiot = Relaatiot.getRelaatioListaHenkiloIdlla(henkilo.getId());
        int valitunLajinId = listChooserOmatLajit.getSelectedObject().getLajiId();
        for(Relaatio r : henkilonRelaatiot) {
            if(r.getLaji() == valitunLajinId) {
                r.nollaaLaji();
            }
        }
        listChooserOmatLajit.getItems().remove(listChooserOmatLajit.getSelectedIndex());
                
    }
    
    
    /**
     * Tallennetaan henkilon syötetyt tiedot. tallennetaan Henkilo oliolle kenttien mukaiset tiedot. Varmistetaan että kaikki kentät on täytetty. 
     * kutsutaan Henkilo luokan aliohjelmaa joka kirjoittaa henkilön tiedot tiedostoon.
     */
    private void tallennaHenkilo() {
        if(etunimi.getText() == "" || sukunimi.getText() == "" || syntymaaika.getText() == "" || osoite.getText()  == "" || postinumero.getText() == "" ||kunta.getText() == "" ) {
            return;
        }
        henkilo.setEtunimi(etunimi.getText());
        henkilo.setSukunimi(sukunimi.getText());
        Pvm sA = new Pvm();
        sA.parse(syntymaaika.getText());
        henkilo.setSyntymaAika(sA);
        henkilo.setOsoite(osoite.getText());
        henkilo.setPostinumero(postinumero.getText());
        henkilo.setKaupunki(kunta.getText());
        
        if(!Ht2022GUIController.getHenkilot().getHenkilot().contains(henkilo)) {
            Ht2022GUIController.getHenkilot().getHenkilot().add(henkilo);
        }
            
        

        henkilo.kirjoitaHenkilo();

        
        ModalController.closeStage(ButtonTallenna);
        
    }

    /**
     * 
     * @param oletus Henkilo olio jonka tiedot näytetään kun Controller avataan.
     * Asettaa kuhunkin kenttään sitä vastaavan olion tietueen. 
     * Näyttää aktiivisen lajit kaikki lajit listassa,
     * lukee relaatioista aktiivisen henkilön omat lajit ja näyttää ne omat lajit listassa.
     *
     */
    private void naytaTiedot(Henkilo oletus) {
        etunimi.setText(oletus.getEtunimi());
        sukunimi.setText(oletus.getSukunimi());
        osoite.setText(oletus.getOsoite());
        syntymaaika.setText(oletus.getSyntymaAika().toString());
        postinumero.setText(oletus.getPostinumero());
        kunta.setText(oletus.getKaupunki());
        
        for (Laji l : Ht2022GUIController.getLajit().getLajit()) {
            if(l.getVisible())listChooserKaikkiLajit.add(l);
        }
        
        int [] taul = Relaatiot.getRelaatiotHenkiloIdlla(oletus.getId());
        
        if (taul!=null) {
            for(int i = 0; i<taul.length; i++) {
            if(Relaatiot.getLajiRelaatioIdlla(taul[i])==null)break;
            if(Relaatiot.getLajiRelaatioIdlla(taul[i]).getVisible())listChooserOmatLajit.add(Relaatiot.getLajiRelaatioIdlla(taul[i]));
            }
        }
        
        ArrayList<Laji> henkilonOmatLajit = new ArrayList<Laji>();
        for(Laji l : henkilonOmatLajit) {
            Ht2022GUIController.getLajit().add(l);
        }
        // asetetaan oletus Henkilo Luokka muuttujaan henkilo, muiden luokan aliohelmien käyttöön.
        henkilo = oletus;
    }
    
    
    
    /**
     * @param modalityStage ikkuna on modaalinen
     * @param h Henkilo jonka tietoja syötetään tai muokataan
     */
    public void naytaHenkilo(Stage modalityStage, Henkilo h) {
        ModalController.showModal(Ht2022GUIController.class.getResource("HenkiloView.fxml"), "Henkilotiedot",modalityStage, h, null);
    }

}
