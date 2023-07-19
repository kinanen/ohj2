package HT2022;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


/**
 * @author otsokinanen
 * @version 9 Dec 2022
 *  käyttliittymä harjoitusajan muokkaamiseen toteuttaa Modalcontroller rajapintaa
 */
public class HarjoitusController implements ModalControllerInterface <Harjoitusaika> {
    
    // luokka muuttuja
    private Harjoitusaika harjoitusaika;
   
    @Override
    public Harjoitusaika getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    // Näytetään Controllerille välitetyn harjoitsajan tiedot kentissä kun  Controller ikkuna aukeaa
    @Override
    public void setDefault(Harjoitusaika oletus) {
        if(oletus.getLaji().getLaji() != "temp") {
            ComboBoxLaji.setValue(oletus.getLaji());
            TextFieldAika.setText(oletus.getKloaika().toString());
            TextFieldPaiva.setText(oletus.getPaiva().toString());
            TextFieldSali.setText(oletus.getSali());
            String paikkoja = Integer.toString(oletus.getPaikkoja());
            TextFieldPaikkoja.setText(paikkoja);
        }
        
        // näyttää lajit joista voidaan valita mille lajille uusi harjoitusaika syötetään
        ObservableList<Laji> ol = FXCollections.observableArrayList(Ht2022GUIController.getLajit().getVisibleLajit());        
        ComboBoxLaji.setItems(ol);
        
        harjoitusaika = oletus;
    }



    @FXML
    private Button ButtonPeruuta;

    @FXML
    private Button ButtonTallenna;

    @FXML
    private ComboBox<Laji> ComboBoxLaji;

    @FXML
    private TextField TextFieldAika;

    @FXML
    private TextField TextFieldPaikkoja;

    @FXML
    private TextField TextFieldPaiva;

    @FXML
    private TextField TextFieldSali;

    @FXML
    void peruutaHarjoitus() {
        ModalController.closeStage(ButtonPeruuta);
    }
    /**
     * 
     * @param event ei käytössä
     * 
     * Tallennentaa syötetyt tiedot ja tarkistaa että kaikki kentät on syötetty oikein. 
     */
    @FXML
    void tallennaHarjoitus() {
        if(ComboBoxLaji.getValue()== null) {
            naytaVirhe("Valitse laji");
            return;
        }
        
        // hakee lajin valitsimesta
        harjoitusaika.setLaji(ComboBoxLaji.getValue());
        
        //luo PVM olion päivämäärää varten ja asettaa sille arvot syötteen perusteella
        Pvm pvm = new Pvm();
        try{
            if(!pvm.parse(TextFieldPaiva.getText())) {
                naytaVirhe("tarkista syötetty päivämäärä ja aika, käytä päivämäärän erottimena . ja ajan erottimena : merkkejä" );
                return;
            };           
            harjoitusaika.setPaiva(pvm);
        }
        catch(Exception e){
            naytaVirhe("tarkista syötetty päivämäärä ja aika, käytä päivämäärän erottimena . ja ajan erottimena : merkkejä" );
            return;
        }
         
        //Luo Aika olion ja asettaa sen arvot syötteen perusteella, jos syöte on virheellinen keskeytyy tallennus
        Aika aika = new Aika ();
        try {
            if(!aika.parse(TextFieldAika.getText())) {
                naytaVirhe("tarkista syötetty päivämäärä ja aika, käytä päivämäärän erottimena . ja ajan erottimena : merkkejä" );
                return;
            };
            harjoitusaika.setKloAika(aika);
        }
        catch(Exception e) {
            naytaVirhe("tarkista syötetty päivämäärä ja aika, käytä päivämäärän erottimena . ja ajan erottimena : merkkejä" );
            return;
        }
        
        if(pvm.getPv()<0| pvm.getKk()<0| pvm.getVv()<2020|pvm.getPv()>31| pvm.getKk()>12| pvm.getVv()>2024| aika.getH()>23|aika.getH()<0|aika.getM()>59|aika.getM()<0) {
            naytaVirhe("tarkista syötetty päivämäärä ja aika, käytä päivämäärän erottimena . ja ajan erottimena : merkkejä" );
            return;
        }
        
        //lukee Sali syötteen ja tallettaa sen Harjoitusaikaan. 
        if(TextFieldSali.getText() == null) {
            harjoitusaika.setSali("Tarkista sali");}
        else {
            harjoitusaika.setSali(TextFieldSali.getText());}
        
        
        //lukee paikkoja syötteen ja tallettaa sen harjoitusaikaan
        try {
            harjoitusaika.setPaikkoja(Integer.parseInt(TextFieldPaikkoja.getText()));
        }
        catch(Exception e ) {
            naytaVirhe("syötä paikkojen lukumäärä kokonaislukuna");
            return;
        }
        
        //Tarkistaa  onko harjoitusaika jo Harjoitusajat listalla, mikäli ei lisätään se . 
        if( ! Ht2022GUIController.getHarjoitusajat().getHarjoitusajat().contains(harjoitusaika)){
            Ht2022GUIController.getHarjoitusajat().getHarjoitusajat().add(harjoitusaika);
        }
        
        
        //Kutsutaan metodia joka kirjoittaa tiedostoon harjoituksen tiedot
        Harjoitusaika.KirjoitaHarjoitusaika();
        
        // lisätään harjoitusajan ja sille valitun Lajin relaatiot
        Relaatio r = new Relaatio();
        r.setHarjoitusaikaId(harjoitusaika.getId());
        r.setLaji(harjoitusaika.getLaji().getLajiId());        
        Relaatiot.getRelaatiot().add(r);
        
        ModalController.closeStage(ButtonTallenna);
        
        for (Harjoitusaika hA : Ht2022GUIController.getHarjoitusajat().getHarjoitusajat()) {
            System.out.println(hA.getHarjoitusaikaKirjoitettava());
        }
        
    }

    
    /**
     * @param modalityStage modaalinen
     * @param hA harjoitusaika käsittelyssä. 
     * 
     * Tuodaan kutsussa välitetyt parametrit uuteen ikkunaan.
     */
    public void naytaHarjoitus(Stage modalityStage, Harjoitusaika hA) {
        ModalController.showModal(Ht2022GUIController.class.getResource("HarjoitusView.fxml"), "Harjoituksen tiedot",modalityStage, hA, null);
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
