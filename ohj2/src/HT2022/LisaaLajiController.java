package HT2022;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * @author otsokinanen
 * @version 6 Sept 2022
 * Lajin muokkaamiseen ja lisäämiseen käytettävä ikkuna
 */
public class LisaaLajiController implements ModalControllerInterface <Laji> {

    private Laji laji;
    
    @Override
    public Laji getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    /**
     * Näytetään Lajin tiedot ikkunassa
     * @oletus Laji joka tulee parametrina Controllerin avaus kutsussa. Joko muokattava tai uusi Laji. Tiedot näytetään niille varatuissa kentissä.
     */
    @Override
    public void setDefault(Laji oletus) {
        if (oletus.getLaji() != "") {
            TextFieldLaji.setText(oletus.getLaji());
            for(String taso : oletus.getTasot()) {
                ListViewHarjoitusTasot.getItems().add(taso);
            }
        }
        
        laji = oletus;
    }



    @FXML
    private Button ButtonLisaa;

    @FXML
    private Button ButtonPoistaHarjoitustaso;

    @FXML
    private Button ButtonPeruuta;

    @FXML
    private Button ButtonTallenna;

    @FXML
    private ListView<String> ListViewHarjoitusTasot;

    @FXML
    private TextField TextFieldLaji;

    @FXML
    private TextField textFieldHarjoitusTasot;
    
    /**
     * tarkistaa ja tallentaa syötetyn harjoitustason
     * 
     */
    @FXML
    void LisaaHarjoitustaso() {
        String str  = textFieldHarjoitusTasot.getText();
        String tasoS = str.substring(0, 1).toUpperCase() + str.substring(1);
        ListViewHarjoitusTasot.getItems().add(tasoS);
        textFieldHarjoitusTasot.clear();
    }

    /**
     * peruutetaan lajin muokkaus tai syöttö
     * @param event peruuta painikkeen painallus
     */
    @FXML
    void Peruuta() {
        ModalController.closeStage(ButtonPeruuta);
    }

    /**
     * poistetaan valittu harjoitustaso listalta. 
     */
    @FXML
    void PoistaHarjoitustaso() {
        int index = ListViewHarjoitusTasot.getSelectionModel().getSelectedIndex();
        ListViewHarjoitusTasot.getItems().remove(index);
    }

    /**
     * Tallentaa muokatun lajin tiedot. Tarkistaa syötettyjen kenttien sisällöt. 
     * kutsuu aliohjelmaa joka kirjoittaa Lajin tiedot reksiteriin, sekä sulkee muokkaus ikkunan. 
     */
    @FXML
    void Tallenna() {
        String lajinNimi = TextFieldLaji.getText();
        if(lajinNimi == "") {
            return;
        }
        if(ListViewHarjoitusTasot.getItems().size()<1) {
          return;
        }
        
        
        String lajiS = lajinNimi.substring(0, 1).toUpperCase() + lajinNimi.substring(1);
        laji.setLaji(lajiS);
     
        laji.setTasot(ListViewHarjoitusTasot.getItems());
        
        if(!Ht2022GUIController.getLajit().getLajit().contains(laji)) {
            Ht2022GUIController.lajit.add(laji);
        }
       
        laji.kirjoitaLaji();
        
        ModalController.closeStage(ButtonTallenna);
        
    }
   
    
    /**
     * @param modalityStage modaalinen
     * @param laji1 laji jota muokataan
     */
    public void naytaLaji(Stage modalityStage, Laji laji1) {
        ModalController.showModal(Ht2022GUIController.class.getResource("LisaaLajiView.fxml"), "Laji",modalityStage, laji1, null);
    }

}
