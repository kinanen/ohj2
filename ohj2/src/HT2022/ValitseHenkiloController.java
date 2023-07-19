package HT2022;

import fi.jyu.mit.fxgui.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;





/**
 * @author otsokinanen
 * @version 31 Aug 2022
 * Ikkuna käyttäjän valintaan, näytetään ohjelman käynnistyksessä ja jos käyttäjää halutaan vaihtaa.
 */
public class ValitseHenkiloController implements ModalControllerInterface <Henkilo> {
    private static Henkilo h;
    
    @Override
    public Henkilo getResult() {
        // TODO Auto-generated method stub
        return h;
        
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    //näyttää kaikki henkilöt listana valikossa. 
    public void setDefault(Henkilo oletus) {
        h = oletus;
        ObservableList<Henkilo> ol = FXCollections.observableArrayList(Ht2022GUIController.getHenkilot().getHenkilot());
        ComboBoxValitseKayttaja.setItems(ol);

    }
    
    @FXML
    private ComboBox<Henkilo> ComboBoxValitseKayttaja;
    
    @FXML
    private Button ButtonValitse;
    
    @FXML 
    //valitse painike asettaa listasta valitun henkilön luokan h muuttujan arvoksi
    private void valitse() {
        if(ComboBoxValitseKayttaja.getValue() == null) return;
        h = ComboBoxValitseKayttaja.getValue();
        ModalController.closeStage(ButtonValitse);
    }
    
    
    @FXML
    void ButtonValitse(@SuppressWarnings("unused") ActionEvent event) {
//
        
    }

    /**
     * 
     * @param h2 tuo henkilön kutsuvasta ikkunasta
     * @return palauttaa valitun henkilön kutsuvalle ohjelmalle.
     */
    public static Henkilo valitseKayttaja(Henkilo h2) {
        
        ModalController.showModal(Ht2022GUIController.class.getResource("ValitseHenkiloView.fxml"), "Henkilotiedot", null, h2 );
        return h;
        
    }
      
    
    
}
